package com.flatrock.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductOutOfStockException extends RuntimeException {

    public ProductOutOfStockException(String errorMessage) {
        super(errorMessage);
    }

}
