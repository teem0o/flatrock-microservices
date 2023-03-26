package com.flatrock.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DeliveryNotFoundException extends RuntimeException {

    private final Long deliveryId;

    public DeliveryNotFoundException(Long deliveryId) {
        super(String.format("Delivery with id %s is not found", deliveryId));
        this.deliveryId = deliveryId;
    }

}
