package com.flatrock.productservice.component;

import com.flatrock.productservice.dto.OrderLineItemsDto;
import com.flatrock.productservice.dto.ProductRequest;
import com.flatrock.productservice.dto.ProductResponse;
import com.flatrock.productservice.event.OrderPlacedEvent;
import com.flatrock.productservice.service.ProductsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaConsumer {

    private final ProductsService productsService;

    private final ModelMapper modelMapper;

    @KafkaListener(topics = "orderTopic", groupId = "productGroup")
    public void handleOrder(OrderPlacedEvent orderPlacedEvent){
        log.info("Received an order - {}", orderPlacedEvent.getOrderId());
        for (OrderLineItemsDto orderLineItems : orderPlacedEvent.getOrderLineItemsDto()) {
            String skuCode = orderLineItems.getSkuCode();
            ProductResponse product = productsService.getProductBySkuCode(skuCode);
            product.setQuantity(product.getQuantity() - orderLineItems.getQuantity());
            productsService.updateProduct(skuCode, modelMapper.map(product, ProductRequest.class));
        }
    }
}
