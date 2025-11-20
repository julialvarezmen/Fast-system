package com.restaurante.backend.application.service;

import com.restaurante.backend.application.dto.OrderRequest;
import com.restaurante.backend.application.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse crearOrden(OrderRequest request);

    OrderResponse obtenerOrdenPorId(String id);

    List<OrderResponse> obtenerTodasLasOrdenes();

    OrderResponse cancelarOrden(String id);

    void actualizarEstadoOrden(String id, String nuevoEstado);

    OrderResponse createOrden(OrderRequest request);
    List<OrderResponse> getAll();
    OrderResponse getOrderById(String id);
    OrderResponse cancelarOrden(String id);
}
}
