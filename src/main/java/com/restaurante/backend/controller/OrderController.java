package com.restaurante.backend.controller;

import com.restaurante.backend.application.dto.OrderRequest;
import com.restaurante.backend.application.dto.OrderResponse;
import com.restaurante.backend.application.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Crear un pedido (cliente)
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrden(request);
        return ResponseEntity.ok(response);
    }

    // Obtener todos los pedidos (admin)
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    // Obtener detalle de un pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // Cancelar pedido
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.cancelarOrden(id));
    }
}
