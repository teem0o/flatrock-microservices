package com.flatrock.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String productName;
    private String productDescription;
    private String skuCode;
    private Double price;
    private Integer quantity;
}
