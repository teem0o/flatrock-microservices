package com.flatrock.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deliveries")
@Builder
public class Delivery {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long orderId;
    @Column(nullable = false)
    private String deliveryAddress;
    @Column(nullable = false)
    private OrderStatus orderStatus;
    @Column(nullable = false)
    private LocalDate expectedDeliveryDate;
}
