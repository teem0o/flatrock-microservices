package com.flatrock.orderservice.controller;

import com.flatrock.orderservice.dto.OrderRequest;
import com.flatrock.orderservice.dto.OrderResponse;
import com.flatrock.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse savedOrder = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("orderId") Long orderId){
        OrderResponse orderDto = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> allOrders = orderService.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }
}
