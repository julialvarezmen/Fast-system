package com.restaurante.backend.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(name = "ProductoRequest", description = "Datos para crear o actualizar un producto")
public class ProductRequest {

    @Schema(description = "Nombre del producto", example = "Pizza Margarita")
    private String nombre;
    @Schema(description = "Descripción del producto", example = "Pizza con salsa de tomate y queso mozzarella")
    private String descripcion;
    @Schema(description = "Precio del producto", example = "25000")
    private BigDecimal precio;
    @Schema(description = "URL de la imagen del producto", example = "https://ejemplo.com/pizza.jpg")
    private String imagenUrl;

    // Getters y Setters

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return descripcion;
    }

    public void setDescription(String description) {
        this.descripcion = description;
    }

    public BigDecimal getPrice() {
        return precio;
    }

    public void setPrice(BigDecimal price) {
        this.precio = precio;
    }

    public String getImageUrl() {
        return imagenUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imagenUrl = imagenUrl;
    }
}
