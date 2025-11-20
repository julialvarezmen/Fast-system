package com.restaurante.backend.application.service;

import com.restaurante.backend.application.dto.ProductRequest;
import com.restaurante.backend.application.dto.ProductResponse;
import com.restaurante.backend.application.mapper.ProductMapper;
import com.restaurante.backend.domain.model.Product;
import com.restaurante.backend.infrastructure.persistence.entity.ProductEntity;
import com.restaurante.backend.infrastructure.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repositorioProducto;

    public ProductServiceImpl(ProductRepository repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public ProductResponse crearProducto(ProductRequest request) {
        // DTO → Domain
        Product productoDominio = ProductMapper.toDomain(request);

        // Domain → Entity
        ProductEntity entidad = ProductMapper.toEntity(productoDominio);

        // Guardar Entity
        ProductEntity entidadGuardada = repositorioProducto.save(entidad);

        // Entity → Domain
        Product productoGuardado = ProductMapper.toDomain(entidadGuardada);

        // Domain → Response DTO
        return ProductMapper.toResponse(productoGuardado);
    }

    @Override
    public ProductResponse actualizarProducto(String id, ProductRequest request) {
        ProductEntity entidad = repositorioProducto.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProductMapper.updateEntity(entidad, request); // actualizar campos desde DTO
        entidad.setFechaActualizacion(Instant.now());

        ProductEntity entidadGuardada = repositorioProducto.save(entidad);
        Product productoGuardado = ProductMapper.toDomain(entidadGuardada);

        return ProductMapper.toResponse(productoGuardado);
    }

    @Override
    public ProductResponse obtenerProductoPorId(String id) {
        ProductEntity entidad = repositorioProducto.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Product productoDominio = ProductMapper.toDomain(entidad);
        return ProductMapper.toResponse(productoDominio);
    }

    @Override
    public List<ProductResponse> obtenerTodosLosProductos() {
        List<ProductEntity> entidades = repositorioProducto.findAll();
        List<Product> productos = entidades.stream()
                .map(ProductMapper::toDomain)
                .toList();
        return ProductMapper.toResponseList(productos);
    }

    @Override
    public void deshabilitarProducto(String id) {
        ProductEntity entidad = repositorioProducto.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        entidad.setActivo(false);
        entidad.setFechaActualizacion(Instant.now());

        repositorioProducto.save(entidad);
    }
}
