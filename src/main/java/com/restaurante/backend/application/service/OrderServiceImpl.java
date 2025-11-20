package com.restaurante.backend.application.service;

import com.restaurante.backend.application.dto.OrderRequest;
import com.restaurante.backend.application.dto.OrderResponse;
import com.restaurante.backend.application.mapper.OrderMapper;
import com.restaurante.backend.application.mapper.ProductMapper;
import com.restaurante.backend.domain.model.Order;
import com.restaurante.backend.domain.model.Product;
import com.restaurante.backend.infrastructure.messaging.RabbitOrderPublisher;
import com.restaurante.backend.infrastructure.persistence.entity.OrderEntity;
import com.restaurante.backend.infrastructure.persistence.repository.OrderRepository;
import com.restaurante.backend.infrastructure.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repositorioOrden;
    private final ProductRepository repositorioProducto;
    private final RabbitOrderPublisher publicadorOrden;

    public OrderServiceImpl(OrderRepository repositorioOrden,
                            ProductRepository repositorioProducto,
                            RabbitOrderPublisher publicadorOrden) {
        this.repositorioOrden = repositorioOrden;
        this.repositorioProducto = repositorioProducto;
        this.publicadorOrden = publicadorOrden;
    }

    @Override
    public OrderResponse crearOrden(OrderRequest request) {

        // 1. Obtener snapshot de productos
        List<String> idsProductos = request.getItems()
                .stream()
                .map(i -> i.getIdProducto())
                .toList();

        List<Product> productos = repositorioProducto.findAllById(idsProductos)
                .stream()
                .map(ProductMapper::toDomain)
                .toList();

        if (productos.size() != idsProductos.size()) {
            throw new RuntimeException("Algunos productos no existen o están inactivos");
        }

        // 2. Crear Orden (dominio)
        Order ordenDominio = OrderMapper.toNuevaOrden(request, productos);

        // 3. Mapear dominio → entidad
        OrderEntity entidad = OrderMapper.aEntidad(ordenDominio);

        // 4. Guardar
        OrderEntity entidadGuardada = repositorioOrden.save(entidad);

        // 5. Convertir entidad → dominio
        Order ordenGuardada = OrderMapper.aDominio(entidadGuardada);

        // 6. Enviar a RabbitMQ
        publicadorOrden.sendOrder(ordenGuardada);

        return OrderMapper.aRespuesta(ordenGuardada);
    }

    @Override
    public OrderResponse obtenerOrdenPorId(String id) {

        OrderEntity entidad = repositorioOrden.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        return OrderMapper.aRespuesta(OrderMapper.aDominio(entidad));
    }

    @Override
    public List<OrderResponse> obtenerTodasLasOrdenes() {
        return repositorioOrden.findAll()
                .stream()
                .map(OrderMapper::aDominio)
                .map(OrderMapper::aRespuesta)
                .toList();
    }

    @Override
    public OrderResponse cancelarOrden(String id) {

        OrderEntity entidad = repositorioOrden.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (entidad.getEstado().equals("COMPLETADO")) {
            throw new RuntimeException("No se puede cancelar un pedido completado");
        }

        entidad.setEstado("CANCELADO");
        entidad.setFechaActualizacion(Instant.now());

        OrderEntity guardada = repositorioOrden.save(entidad);

        return OrderMapper.aRespuesta(OrderMapper.aDominio(guardada));
    }

    @Override
    public void actualizarEstadoOrden(String id, String nuevoEstado) {

        OrderEntity entidad = repositorioOrden.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        entidad.setEstado(nuevoEstado);
        entidad.setFechaActualizacion(Instant.now());

        repositorioOrden.save(entidad);
    }
}
