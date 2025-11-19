package com.restaurante.app.application.service;

import com.restaurante.app.application.dto.OrderRequest;
import com.restaurante.app.application.dto.OrderResponse;
import com.restaurante.app.application.mapper.OrderMapper;
import com.restaurante.app.infrastructure.messaging.RabbitOrderPublisher;
import com.restaurante.app.infrastructure.persistence.entity.OrderEntity;
import com.restaurante.app.infrastructure.persistence.entity.ProductEntity;
import com.restaurante.app.infrastructure.persistence.repository.OrderRepository;
import com.restaurante.app.infrastructure.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final RabbitOrderPublisher orderPublisher;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            RabbitOrderPublisher orderPublisher) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderPublisher = orderPublisher;
    }

    @Override
    public OrderResponse createOrder(OrderRequest request) {

        // Obtener snapshot de productos según IDs del request
        List<String> productIds = request.getItems()
                .stream()
                .map(i -> i.getProductId())
                .toList();

        List<ProductEntity> productSnapshot = productRepository.findAllById(productIds);

        if (productSnapshot.size() != productIds.size()) {
            throw new RuntimeException("Algunos productos no existen o están inactivos");
        }

        // Convertimos el request en entidad Order
        OrderEntity order = OrderMapper.toNewOrderEntity(request, productSnapshot);

        // Guardar en BD
        OrderEntity saved = orderRepository.save(order);

        // Enviar a RabbitMQ (al worker)
        orderPublisher.sendOrder(saved);

        return OrderMapper.toResponse(saved);
    }


    @Override
    public OrderResponse getOrderById(String id) {
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        return OrderMapper.toResponse(entity);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return OrderMapper.toResponseList(orderRepository.findAll());
    }

    @Override
    public OrderResponse cancelOrder(String id) {
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (entity.getStatus().equals("COMPLETADO")) {
            throw new RuntimeException("No se puede cancelar un pedido completado");
        }

        entity.setStatus("CANCELADO");
        entity.setUpdatedAt(Instant.now());

        OrderEntity saved = orderRepository.save(entity);
        return OrderMapper.toResponse(saved);
    }

    @Override
    public void updateOrderStatus(String id, String newStatus) {
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        entity.setStatus(newStatus);
        entity.setUpdatedAt(Instant.now());

        orderRepository.save(entity);
    }
}
