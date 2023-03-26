package com.flatrock.orderservice.service.impl;

import com.flatrock.orderservice.dto.OrderLineItemsDto;
import com.flatrock.orderservice.dto.OrderRequest;
import com.flatrock.orderservice.dto.OrderResponse;
import com.flatrock.orderservice.dto.ProductResponse;
import com.flatrock.orderservice.entity.Order;
import com.flatrock.orderservice.entity.OrderLineItems;
import com.flatrock.orderservice.event.OrderPlacedEvent;
import com.flatrock.orderservice.exception.OrderNotFoundException;
import com.flatrock.orderservice.exception.ProductOutOfStockException;
import com.flatrock.orderservice.repository.OrderRepository;
import com.flatrock.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import com.flatrock.orderservice.mapper.OrderMapper;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    private final ModelMapper modelMapper;

    private final OrderMapper orderMapper;

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderMapper.mapOrderRequestToOrder(orderRequest);

        //Calling Product service to check if the product is in stock before placing the order
        order.getOrderLineItemsList().forEach(product -> isInStock(product));

        List<OrderLineItemsDto> orderLineItemsDtoList = order.getOrderLineItemsList()
                .stream()
                .map(orderLineItems -> modelMapper.map(orderLineItems, OrderLineItemsDto.class)).toList();

        Order savedOrder = orderRepository.save(order);
        kafkaTemplate.send("orderTopic", new OrderPlacedEvent(order.getId(), orderLineItemsDtoList));
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    private void isInStock(OrderLineItems orderedProduct) {
        String skuCode = orderedProduct.getSkuCode();
        ProductResponse productResponse = webClient.get()
                .uri("http://localhost:9191/api/v1/products/" + skuCode)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
        int orderedQuantity = orderedProduct.getQuantity();
        int stockLeft = productResponse.getQuantity();
        if (orderedQuantity > stockLeft) {
            throw new ProductOutOfStockException("Order failed, because the requested quantity (" + orderedQuantity
                    + ") exceeds the stock (" + stockLeft + ") of the product " + skuCode);
        }
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(orderId)
        );

        return modelMapper.map(order, OrderResponse.class);

    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        return allOrders.stream().map(order -> modelMapper.map(order, OrderResponse.class)).toList();
    }
}
