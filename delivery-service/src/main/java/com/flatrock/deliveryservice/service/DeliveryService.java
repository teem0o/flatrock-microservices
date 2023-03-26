package com.flatrock.deliveryservice.service;

import com.flatrock.deliveryservice.dto.DeliveryResponse;
import com.flatrock.deliveryservice.entity.Delivery;

import java.util.List;

public interface DeliveryService {
    Delivery saveDelivery(Delivery delivery);
    List<DeliveryResponse> getAllDeliveries();
    DeliveryResponse getDeliveryById(Long id);
    //todo: write functionality to get all deliveries based on a filter

}
