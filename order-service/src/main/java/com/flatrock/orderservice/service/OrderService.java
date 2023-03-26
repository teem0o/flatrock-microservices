package com.flatrock.orderservice.service;

import com.flatrock.orderservice.dto.OrderRequest;
import com.flatrock.orderservice.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderDto);
    OrderResponse getOrderById(Long orderId);
    List<OrderResponse> getAllOrders();
}
