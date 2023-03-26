package com.flatrock.deliveryservice.component;

import com.flatrock.deliveryservice.entity.Delivery;
import com.flatrock.deliveryservice.entity.OrderStatus;
import com.flatrock.deliveryservice.event.OrderPlacedEvent;
import com.flatrock.deliveryservice.service.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaConsumer {

    private DeliveryService deliveryService;

    @KafkaListener(topics = "orderTopic", groupId = "deliveryGroup")
    public void handleDelivery(OrderPlacedEvent orderPlacedEvent){
        log.info("Received an order - {}", orderPlacedEvent.getOrderId());
        //Hardcoding delivery status, address and expected delivery date, since the implementation of those is out of the scope of this project
        Delivery delivery = Delivery.builder()
                .orderId(orderPlacedEvent.getOrderId())
                .expectedDeliveryDate(LocalDate.of(2023, 4, 14))
                .deliveryAddress("132, My Street, Kingston, New York 12401")
                .orderStatus(OrderStatus.PENDING)
                .build();
        deliveryService.saveDelivery(delivery);
    }
}
