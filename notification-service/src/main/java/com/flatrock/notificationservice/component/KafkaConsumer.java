package com.flatrock.notificationservice.component;

import com.flatrock.notificationservice.event.OrderPlacedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topics = "orderTopic", groupId = "notificationGroup")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent){
        // sending out an email notification based on the information from the order
        // sending out an SMS message based on the information from the order
        log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderId());
    }
}
