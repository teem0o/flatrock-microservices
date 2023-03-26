package com.flatrock.notificationservice.event;

import com.flatrock.notificationservice.dto.OrderLineItemsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {
    private Long orderId;
    private List<OrderLineItemsDto> orderLineItemsDto;
}
