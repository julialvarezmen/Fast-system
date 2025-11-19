package com.restaurante.app.application.mapper;

import com.restaurante.app.application.dto.ProductRequest;
import com.restaurante.app.application.dto.ProductResponse;
import com.restaurante.app.infrastructure.persistence.entity.ProductEntity;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity toEntity(ProductRequest request) {
        ProductEntity entity = new ProductEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setImageUrl(request.getImageUrl());
        entity.setActive(true);
        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(Instant.now());
        return entity;
    }

    public static void updateEntity(ProductEntity entity, ProductRequest request) {
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setImageUrl(request.getImageUrl());
        entity.setUpdatedAt(Instant.now());
    }

    public static ProductResponse toResponse(ProductEntity entity) {
        ProductResponse dto = new ProductResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setImageUrl(entity.getImageUrl());
        dto.setActive(entity.isActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public static List<ProductResponse> toResponseList(List<ProductEntity> entities) {
        return entities.stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }
}
