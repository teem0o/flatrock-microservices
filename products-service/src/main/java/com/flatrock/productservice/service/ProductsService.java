package com.flatrock.productservice.service;

import com.flatrock.productservice.dto.ProductRequest;
import com.flatrock.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductsService {
    ProductResponse createProduct(ProductRequest productDto);
    ProductResponse getProductBySkuCode(String skuCode);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(String skuCode, ProductRequest productRequest);
}
