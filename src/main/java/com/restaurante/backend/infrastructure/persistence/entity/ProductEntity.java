package com.restaurante.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "productos")
@Schema(name = "Producto", description = "Entidad que representa un producto")
public class ProductEntity {
    @Id
    @Column(name = "id_producto")
    @Schema(description = "ID único del producto", example = "abc123")
    private String id;
    @Column(name = "nombre")
    @Schema(description = "Nombre del producto", example = "Pizza Margarita")
    private String nombre;
    @Column(name = "descripcion")
    @Schema(description = "Descripción del producto", example = "Pizza con salsa de tomate y queso mozzarella")
    private String descripcion;
    @Column(name = "precio")
    @Schema(description = "Precio del producto", example = "25000")
    private BigDecimal precio;
    @Column(name = "imagen_url")
    @Schema(description = "URL de la imagen del producto", example = "https://ejemplo.com/pizza.jpg")
    private String imagenUrl;
    @Column(name = "activo")
    @Schema(description = "Si el producto está activo", example = "true")
    private boolean activo = true;
    @Column(name = "fecha_creacion")
    @Schema(description = "Fecha de creación", example = "2025-11-20T10:00:00Z")
    private Instant fechaCreacion;
    @Column(name = "fecha_actualizacion")
    @Schema(description = "Fecha de actualización", example = "2025-11-20T10:05:00Z")
    private Instant fechaActualizacion;

    public ProductEntity() {
        this.id = UUID.randomUUID().toString();
        this.fechaCreacion = Instant.now();
        this.fechaActualizacion = Instant.now();
        this.activo = true;
    }

    // Getters y Setters en español
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public Instant getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Instant fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Instant getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(Instant fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}
