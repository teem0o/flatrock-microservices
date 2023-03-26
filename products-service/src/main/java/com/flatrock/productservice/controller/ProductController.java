package com.flatrock.productservice.controller;

import com.flatrock.productservice.dto.ProductRequest;
import com.flatrock.productservice.dto.ProductResponse;
import com.flatrock.productservice.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductsService productsService;

    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productDto){
        ProductResponse savedProduct = productsService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("{sku-code}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("sku-code") String skuCode){
        ProductResponse productDto = productsService.getProductBySkuCode(skuCode);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("{sku-code}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("sku-code") String skuCode, @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productsService.updateProduct(skuCode, productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> allProducts = productsService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }
}
