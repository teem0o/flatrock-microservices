package com.flatrock.deliveryservice.service.impl;

import com.flatrock.deliveryservice.dto.DeliveryResponse;
import com.flatrock.deliveryservice.entity.Delivery;
import com.flatrock.deliveryservice.exception.DeliveryNotFoundException;
import com.flatrock.deliveryservice.repository.DeliveryRepository;
import com.flatrock.deliveryservice.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final ModelMapper modelMapper;

    @Override
    public Delivery saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<DeliveryResponse> getAllDeliveries() {
        List<Delivery> allDeliveries =  deliveryRepository.findAll();
        return allDeliveries.stream().map(delivery -> modelMapper.map(delivery, DeliveryResponse.class)).toList();
    }

    //todo: write functionality to get all deliveries based on a filter
//    @Override
//    public List<DeliveryResponse> getAllDeliveriesByStatus() {
//
//    }

    @Override
    public DeliveryResponse getDeliveryById(Long id) {

        Delivery delivery = deliveryRepository.findById(id).orElseThrow(
                () -> new DeliveryNotFoundException(id)
        );

        return modelMapper.map(delivery, DeliveryResponse.class);
    }

}
