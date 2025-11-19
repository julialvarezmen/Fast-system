package com.restaurante.app.application.service;

import com.restaurante.app.application.dto.ProductRequest;
import com.restaurante.app.application.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(String id, ProductRequest request);

    ProductResponse getProductById(String id);

    List<ProductResponse> getAllProducts();

    void disableProduct(String id);
}
