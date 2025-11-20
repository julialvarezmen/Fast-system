package com.restaurante.backend.application.service;

import com.restaurante.backend.application.dto.ProductRequest;
import com.restaurante.backend.application.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse crearProducto(ProductRequest request);
    ProductResponse actualizarProducto(String id, ProductRequest request);
    ProductResponse obtenerProductoPorId(String id);
    List<ProductResponse> obtenerTodosLosProductos();
    void deshabilitarProducto(String id);
}
