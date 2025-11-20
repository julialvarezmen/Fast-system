package com.restaurante.backend.infrastructure.messaging;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Exchange principal del sistema
    public static final String ORDER_EXCHANGE = "orders.exchange";

    // Cola para enviar pedidos al worker
    public static final String ORDER_CREATED_QUEUE = "orders.created.queue";
    public static final String ORDER_CREATED_ROUTING_KEY = "orders.created";

    // Cola para recibir actualizaciones desde el worker
    public static final String ORDER_STATUS_QUEUE = "orders.status.queue";
    public static final String ORDER_STATUS_ROUTING_KEY = "orders.status";

    // Exchange tipo direct
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    // Cola para enviar pedidos al worker
    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(ORDER_CREATED_QUEUE, true);
    }

    // Cola para recibir estados desde el worker
    @Bean
    public Queue orderStatusQueue() {
        return new Queue(ORDER_STATUS_QUEUE, true);
    }

    // Binding: pedidos creados → worker
    @Bean
    public Binding bindOrderCreated(Queue orderCreatedQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(orderCreatedQueue)
                .to(exchange)
                .with(ORDER_CREATED_ROUTING_KEY);
    }

    // Binding: worker → estado actualizado
    @Bean
    public Binding bindOrderStatus(Queue orderStatusQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(orderStatusQueue)
                .to(exchange)
                .with(ORDER_STATUS_ROUTING_KEY);
    }
}
