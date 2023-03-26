package com.flatrock.deliveryservice.controller;

import com.flatrock.deliveryservice.dto.DeliveryResponse;
import com.flatrock.deliveryservice.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("{delivery-id}")
    public ResponseEntity<DeliveryResponse> getDelivery(@PathVariable("delivery-id") Long deliveryId){
        DeliveryResponse deliveryResponse = deliveryService.getDeliveryById(deliveryId);
        return ResponseEntity.ok(deliveryResponse);
    }


    @GetMapping
    public ResponseEntity<List<DeliveryResponse>> getAllDeliveries(){
        List<DeliveryResponse> allDeliveries = deliveryService.getAllDeliveries();
        return ResponseEntity.ok(allDeliveries);
    }

    //todo: write functionality to get all deliveries based on a filter
//    @GetMapping
//    public ResponseEntity<List<DeliveryResponse>> getAllDeliveries(){
//        List<DeliveryResponse> allDeliveries = deliveryService.getAllDeliveries();
//        return ResponseEntity.ok(allDeliveries);
//    }
}
