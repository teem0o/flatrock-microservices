package com.flatrock.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private List<OrderLineItemsDto> orderLineItemsList;
    private Long userId;
    private LocalDateTime orderDate;
}
