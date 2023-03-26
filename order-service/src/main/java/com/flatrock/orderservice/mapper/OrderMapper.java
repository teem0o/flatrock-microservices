package com.flatrock.orderservice.mapper;

import com.flatrock.orderservice.dto.OrderRequest;
import com.flatrock.orderservice.entity.Order;
import com.flatrock.orderservice.entity.OrderLineItems;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class OrderMapper {

    private ModelMapper modelMapper;

    public Order mapOrderRequestToOrder(OrderRequest orderRequest) {
        return Order.builder().
                orderLineItemsList(orderRequest.getOrderLineItemsList().stream().map(item -> modelMapper.map(item, OrderLineItems.class)).toList())
                .userId(orderRequest.getUserId())
                .build();
    }
}
