package com.restaurante.backend.domain.enums;

public enum OrderStatus {
    RECIBIDO,       // Pedido creado por el cliente
    PREPARANDO,     // El restaurante lo está preparando
    EN_REPARTO,     // El repartidor va camino al domicilio
    COMPLETADO,     // Entregado al cliente
    CANCELADO       // Cancelado (por el cliente o admin)
}
