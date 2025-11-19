package com.restaurante.app.infrastructure.messaging;

import com.restaurante.app.application.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitOrderConsumer {

    private final OrderService orderService;

    public RabbitOrderConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    // Escuchar mensajes del worker (estado del pedido)
    @RabbitListener(queues = RabbitConfig.ORDER_STATUS_QUEUE)
    public void receiveStatusUpdate(Map<String, String> message) {

        String orderId = message.get("orderId");
        String newStatus = message.get("status");

        System.out.println("📥 Estado recibido desde el worker: "
                + orderId + " → " + newStatus);

        orderService.updateOrderStatus(orderId, newStatus);
    }
}
