package com.restaurante.backend.application.mapper;

import com.restaurante.backend.application.dto.OrderItemRequest;
import com.restaurante.backend.application.dto.OrderItemResponse;
import com.restaurante.backend.application.dto.OrderRequest;
import com.restaurante.backend.application.dto.OrderResponse;
import com.restaurante.backend.domain.model.Order;
import com.restaurante.backend.domain.model.OrderItem;
import com.restaurante.backend.domain.model.Product;
import com.restaurante.backend.infrastructure.persistence.entity.OrderEntity;
import com.restaurante.backend.infrastructure.persistence.entity.OrderItemEntity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderMapper {
    // ===============================
    // 1. REQUEST → DOMAIN
    // ===============================
    public static Order toNuevaOrden(OrderRequest request, List<Product> productos) {
        Order orden = new Order();
        orden.setId(UUID.randomUUID().toString());
        orden.setNombreCliente(request.getNombreCliente());
        orden.setTelefonoCliente(request.getTelefonoCliente());
        orden.setDireccion(request.getDireccion());
        orden.setBarrio(request.getBarrio());
        orden.setMetodoPago(request.getMetodoPago());
        orden.setEstado("RECIBIDO");
        orden.setFechaCreacion(Instant.now());
        orden.setFechaActualizacion(Instant.now());
        // Crear los ítems
        List<OrderItem> items = mapearItemsDesdeRequest(request.getItems(), productos);
        orden.setItems(items);
        // Calcular total
        orden.calcularTotal();
        return orden;
    }

    private static List<OrderItem> mapearItemsDesdeRequest(List<OrderItemRequest> itemsRequest, List<Product> productos) {
        return itemsRequest.stream().map(reqItem -> {
            Product prod = productos.stream()
                    .filter(p -> p.getId().equals(reqItem.getIdProducto()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + reqItem.getIdProducto()));
            OrderItem item = new OrderItem();
            item.setId(UUID.randomUUID().toString());
            item.setIdProducto(prod.getId());
            item.setNombreProducto(prod.getNombre());
            item.setPrecioProducto(prod.getPrecio());
            item.setCantidad(reqItem.getCantidad());
            item.setSubtotal(prod.getPrecio().multiply(java.math.BigDecimal.valueOf(reqItem.getCantidad())));
            return item;
        }).collect(Collectors.toList());
    }

    // ===============================
    // 2. DOMAIN → ENTITY
    // ===============================
    public static OrderEntity aEntidad(Order orden) {
        OrderEntity entidad = new OrderEntity();
        entidad.setId(orden.getId());
        entidad.setNombreCliente(orden.getNombreCliente());
        entidad.setTelefonoCliente(orden.getTelefonoCliente());
        entidad.setDireccion(orden.getDireccion());
        entidad.setBarrio(orden.getBarrio());
        entidad.setMetodoPago(orden.getMetodoPago());
        entidad.setEstado(orden.getEstado());
        entidad.setTotal(orden.getTotal());
        entidad.setFechaCreacion(orden.getFechaCreacion());
        entidad.setFechaActualizacion(orden.getFechaActualizacion());
        List<OrderItemEntity> items = orden.getItems().stream().map(item -> {
            OrderItemEntity e = new OrderItemEntity();
            e.setIdProducto(item.getIdProducto());
            e.setNombreProducto(item.getNombreProducto());
            e.setPrecioProducto(item.getPrecioProducto());
            e.setCantidad(item.getCantidad());
            e.setSubtotal(item.getSubtotal());
            e.setOrden(entidad);
            return e;
        }).collect(Collectors.toList());
        entidad.setItems(items);
        return entidad;
    }

    // ===============================
    // 3. ENTITY → DOMAIN
    // ===============================
    public static Order aDominio(OrderEntity entidad) {
        Order dominio = new Order();
        dominio.setId(entidad.getId());
        dominio.setNombreCliente(entidad.getNombreCliente());
        dominio.setTelefonoCliente(entidad.getTelefonoCliente());
        dominio.setDireccion(entidad.getDireccion());
        dominio.setBarrio(entidad.getBarrio());
        dominio.setMetodoPago(entidad.getMetodoPago());
        dominio.setEstado(entidad.getEstado());
        dominio.setTotal(entidad.getTotal());
        dominio.setFechaCreacion(entidad.getFechaCreacion());
        dominio.setFechaActualizacion(entidad.getFechaActualizacion());
        List<OrderItem> items = entidad.getItems().stream().map(e -> {
            OrderItem item = new OrderItem();
            item.setIdProducto(e.getIdProducto());
            item.setNombreProducto(e.getNombreProducto());
            item.setPrecioProducto(e.getPrecioProducto());
            item.setCantidad(e.getCantidad());
            item.setSubtotal(e.getSubtotal());
            return item;
        }).collect(Collectors.toList());
        dominio.setItems(items);
        return dominio;
    }

    // ===============================
    // 4. DOMAIN → RESPONSE DTO
    // ===============================
    public static OrderResponse aRespuesta(Order entidad) {
        OrderResponse dto = new OrderResponse();
        dto.setId(entidad.getId());
        dto.setNombreCliente(entidad.getNombreCliente());
        dto.setTelefonoCliente(entidad.getTelefonoCliente());
        dto.setDireccion(entidad.getDireccion());
        dto.setBarrio(entidad.getBarrio());
        dto.setMetodoPago(entidad.getMetodoPago());
        dto.setTotal(entidad.getTotal());
        dto.setEstado(entidad.getEstado());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setFechaActualizacion(entidad.getFechaActualizacion());
        dto.setItems(aRespuestasItems(entidad.getItems()));
        return dto;
    }

    public static List<OrderResponse> aListaRespuestas(List<Order> entidades) {
        return entidades.stream().map(OrderMapper::aRespuesta).collect(Collectors.toList());
    }

    private static List<OrderItemResponse> aRespuestasItems(List<OrderItem> items) {
        return items.stream().map(item -> {
            OrderItemResponse dto = new OrderItemResponse();
            dto.setIdProducto(item.getIdProducto());
            dto.setNombreProducto(item.getNombreProducto());
            dto.setPrecioProducto(item.getPrecioProducto());
            dto.setCantidad(item.getCantidad());
            dto.setSubtotal(item.getSubtotal());
            return dto;
        }).collect(Collectors.toList());
    }
}
