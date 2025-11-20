package com.restaurante.backend.application.mapper;

import com.restaurante.backend.application.dto.ProductRequest;
import com.restaurante.backend.application.dto.ProductResponse;
import com.restaurante.backend.domain.model.Product;
import com.restaurante.backend.infrastructure.persistence.entity.ProductEntity;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    // DTO → Dominio
    public static Product toDomain(ProductRequest request) {
        Product dominio = new Product();
        dominio.setNombre(request.getName());
        dominio.setDescripcion(request.getDescription());
        dominio.setPrecio(request.getPrice());
        dominio.setImagenUrl(request.getImageUrl());
        dominio.setActivo(true);
        dominio.setFechaCreacion(Instant.now());
        dominio.setFechaActualizacion(Instant.now());
        return dominio;
    }

    // Dominio → Entidad
    public static ProductEntity toEntity(Product dominio) {
        ProductEntity entidad = new ProductEntity();
        entidad.setNombre(dominio.getNombre());
        entidad.setDescripcion(dominio.getDescripcion());
        entidad.setPrecio(dominio.getPrecio());
        entidad.setImagenUrl(dominio.getImagenUrl());
        entidad.setActivo(dominio.isActivo());
        entidad.setFechaCreacion(dominio.getFechaCreacion());
        entidad.setFechaActualizacion(dominio.getFechaActualizacion());
        return entidad;
    }

    // Entidad → Dominio
    public static Product toDomain(ProductEntity entidad) {
        Product dominio = new Product();
        dominio.setId(entidad.getId());
        dominio.setNombre(entidad.getNombre());
        dominio.setDescripcion(entidad.getDescripcion());
        dominio.setPrecio(entidad.getPrecio());
        dominio.setImagenUrl(entidad.getImagenUrl());
        dominio.setActivo(entidad.isActivo());
        dominio.setFechaCreacion(entidad.getFechaCreacion());
        dominio.setFechaActualizacion(entidad.getFechaActualizacion());
        return dominio;
    }

    // Dominio → Response DTO
    public static ProductResponse toResponse(Product dominio) {
        ProductResponse dto = new ProductResponse();
        dto.setId(dominio.getId());
        dto.setNombre(dominio.getNombre());
        dto.setDescripcion(dominio.getDescripcion());
        dto.setPrecio(dominio.getPrecio());
        dto.setImagenUrl(dominio.getImagenUrl());
        dto.setActivo(dominio.isActivo());
        dto.setCreatedAt(dominio.getFechaCreacion());
        dto.setUpdatedAt(dominio.getFechaActualizacion());
        return dto;
    }

    public static List<ProductResponse> toResponseList(List<Product> dominios) {
        return dominios.stream().map(ProductMapper::toResponse).collect(Collectors.toList());
    }

    // Actualizar entidad desde DTO
    public static void updateEntity(ProductEntity entidad, ProductRequest request) {
        entidad.setNombre(request.getName());
        entidad.setDescripcion(request.getDescription());
        entidad.setPrecio(request.getPrice());
        entidad.setImagenUrl(request.getImageUrl());
        entidad.setFechaActualizacion(Instant.now());
    }
}
