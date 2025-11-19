package com.restaurante.app.application.service;

import com.restaurante.app.application.dto.OrderRequest;
import com.restaurante.app.application.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    OrderResponse getOrderById(String id);

    List<OrderResponse> getAllOrders();

    OrderResponse cancelOrder(String id);

    // Llamado desde el Worker para actualizar estado
    void updateOrderStatus(String id, String newStatus);
}
