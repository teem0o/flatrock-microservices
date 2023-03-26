package com.flatrock.productservice.service.impl;

import com.flatrock.productservice.dto.ProductRequest;
import com.flatrock.productservice.dto.ProductResponse;
import com.flatrock.productservice.entity.Product;
import com.flatrock.productservice.exception.ProductNotFoundException;
import com.flatrock.productservice.repository.ProductRepository;
import com.flatrock.productservice.service.ProductsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    @Override
    public ProductResponse getProductBySkuCode(String skuCode) {

        Product product = productRepository.findBySkuCode(skuCode).orElseThrow(
                () -> new ProductNotFoundException(skuCode)
        );

        return modelMapper.map(product, ProductResponse.class);

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(product -> modelMapper.map(product, ProductResponse.class)).toList();
    }

    @Override
    public ProductResponse updateProduct(String skuCode, ProductRequest productRequest) {

        Product existingProduct = productRepository.findBySkuCode(skuCode).orElseThrow(
                () -> new ProductNotFoundException(skuCode)
        );

        existingProduct.setProductName(productRequest.getProductName());
        existingProduct.setProductDescription(productRequest.getProductDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setQuantity(productRequest.getQuantity());
        existingProduct.setSkuCode(productRequest.getSkuCode());
        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductResponse.class);
    }
}
