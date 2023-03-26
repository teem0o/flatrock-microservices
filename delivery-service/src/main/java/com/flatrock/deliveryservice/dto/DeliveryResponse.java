package com.flatrock.deliveryservice.dto;

import com.flatrock.deliveryservice.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponse {
    private Long id;
    private Long orderId;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private LocalDate expectedDeliveryDate;
}
