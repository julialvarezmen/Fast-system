package com.restaurante.app.application.service;

import com.restaurante.app.application.dto.ProductRequest;
import com.restaurante.app.application.dto.ProductResponse;
import com.restaurante.app.application.mapper.ProductMapper;
import com.restaurante.app.infrastructure.persistence.entity.ProductEntity;
import com.restaurante.app.infrastructure.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        ProductEntity entity = ProductMapper.toEntity(request);
        ProductEntity saved = productRepository.save(entity);
        return ProductMapper.toResponse(saved);
    }

    @Override
    public ProductResponse updateProduct(String id, ProductRequest request) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProductMapper.updateEntity(entity, request);
        entity.setUpdatedAt(Instant.now());

        ProductEntity saved = productRepository.save(entity);
        return ProductMapper.toResponse(saved);
    }

    @Override
    public ProductResponse getProductById(String id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return ProductMapper.toResponse(entity);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return ProductMapper.toResponseList(productRepository.findAll());
    }

    @Override
    public void disableProduct(String id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        entity.setActive(false);
        entity.setUpdatedAt(Instant.now());

        productRepository.save(entity);
    }
}
