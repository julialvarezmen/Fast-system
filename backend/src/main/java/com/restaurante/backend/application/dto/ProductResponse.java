package com.restaurante.backend.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.Instant;

@Schema(name = "ProductoResponse", description = "Respuesta con los datos de un producto")
public class ProductResponse {
    @Schema(description = "ID del producto", example = "1a2b3c4d")
    private String id;
    @Schema(description = "Nombre del producto", example = "Pizza Margarita")
    private String nombre;
    @Schema(description = "Descripción del producto", example = "Pizza con salsa de tomate y queso mozzarella")
    private String descripcion;
    @Schema(description = "Precio del producto", example = "25000")
    private BigDecimal precio;
    @Schema(description = "URL de la imagen del producto", example = "https://ejemplo.com/pizza.jpg")
    private String imagenUrl;
    @Schema(description = "Si el producto está activo", example = "true")
    private boolean activo;
    @Schema(description = "Fecha de creación", example = "2025-11-20T10:00:00Z")
    private Instant createdAt;
    @Schema(description = "Fecha de actualización", example = "2025-11-20T10:00:00Z")
    private Instant updatedAt;

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
