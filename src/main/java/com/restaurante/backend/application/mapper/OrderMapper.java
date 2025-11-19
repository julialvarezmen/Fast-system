package com.restaurante.app.application.mapper;

import com.restaurante.app.application.dto.OrderItemRequest;
import com.restaurante.app.application.dto.OrderItemResponse;
import com.restaurante.app.application.dto.OrderRequest;
import com.restaurante.app.application.dto.OrderResponse;
import com.restaurante.app.infrastructure.persistence.entity.OrderEntity;
import com.restaurante.app.infrastructure.persistence.entity.OrderItemEntity;
import com.restaurante.app.infrastructure.persistence.entity.ProductEntity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderMapper {

    // Convertir OrderRequest -> OrderEntity (solo datos base)
    public static OrderEntity toNewOrderEntity(OrderRequest request, List<ProductEntity> productSnapshot) {

        OrderEntity entity = new OrderEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setCustomerName(request.getCustomerName());
        entity.setCustomerPhone(request.getCustomerPhone());
        entity.setAddress(request.getAddress());
        entity.setNeighborhood(request.getNeighborhood());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setStatus("RECIBIDO");
        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(Instant.now());

        // Crear los items a partir del snapshot de productos
        List<OrderItemEntity> items = mapItems(request.getItems(), productSnapshot, entity);
        entity.setItems(items);

        // Calcular total
        entity.calculateTotal();

        return entity;
    }

    // Convertir lista de items DTO → entidad usando snapshot de productos
    private static List<OrderItemEntity> mapItems(List<OrderItemRequest> requestItems,
                                                  List<ProductEntity> products,
                                                  OrderEntity parent) {

        return requestItems.stream().map(reqItem -> {
            ProductEntity prod = products.stream()
                    .filter(p -> p.getId().equals(reqItem.getProductId()))
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException("Producto no encontrado: " + reqItem.getProductId()));

            OrderItemEntity item = new OrderItemEntity();
            item.setProductId(prod.getId());
            item.setProductName(prod.getName());
            item.setProductPrice(prod.getPrice());
            item.setQuantity(reqItem.getQuantity());
            item.setSubtotal(
                    prod.getPrice().multiply(
                            java.math.BigDecimal.valueOf(reqItem.getQuantity())
                    )
            );
            item.setOrder(parent);
            return item;
        }).collect(Collectors.toList());
    }

    // OrderEntity -> OrderResponse
    public static OrderResponse toResponse(OrderEntity entity) {
        OrderResponse dto = new OrderResponse();

        dto.setId(entity.getId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setCustomerPhone(entity.getCustomerPhone());
        dto.setAddress(entity.getAddress());
        dto.setNeighborhood(entity.getNeighborhood());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setTotal(entity.getTotal());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        dto.setItems(toItemResponses(entity.getItems()));

        return dto;
    }

    public static List<OrderResponse> toResponseList(List<OrderEntity> entities) {
        return entities.stream().map(OrderMapper::toResponse).collect(Collectors.toList());
    }

    // OrderItemEntity -> OrderItemResponse
    private static List<OrderItemResponse> toItemResponses(List<OrderItemEntity> items) {
        return items.stream().map(item -> {
            OrderItemResponse dto = new OrderItemResponse();
            dto.setProductId(item.getProductId());
            dto.setName(item.getProductName());
            dto.setPrice(item.getProductPrice());
            dto.setQuantity(item.getQuantity());
            dto.setSubtotal(item.getSubtotal());
            return dto;
        }).collect(Collectors.toList());
    }
}
