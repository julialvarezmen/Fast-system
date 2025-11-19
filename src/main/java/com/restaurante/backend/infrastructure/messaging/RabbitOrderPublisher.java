package com.restaurante.app.infrastructure.messaging;

import com.restaurante.app.infrastructure.persistence.entity.OrderEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitOrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Enviar pedido al worker
    public void sendOrder(OrderEntity order) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.ORDER_EXCHANGE,
                RabbitConfig.ORDER_CREATED_ROUTING_KEY,
                order
        );

        System.out.println("📤 Pedido enviado al worker: " + order.getId());
    }
}
