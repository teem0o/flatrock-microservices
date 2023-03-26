package com.flatrock.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    private final String skuCode;

    public ProductNotFoundException(String skuCode) {
        super(String.format("product with sku code %s is not found", skuCode));
        this.skuCode = skuCode;
    }

}
