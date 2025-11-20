package com.restaurante.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_orden")
@Schema(name = "DetalleOrden", description = "Entidad que representa un ítem de una orden")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    @Schema(description = "ID único del detalle de la orden", example = "1")
    private Long id;

    @Column(name = "id_producto")
    @Schema(description = "ID del producto", example = "abc123")
    private String idProducto;
    @Column(name = "nombre_producto")
    @Schema(description = "Nombre del producto", example = "Pizza Margarita")
    private String nombreProducto;
    @Column(name = "precio_producto")
    @Schema(description = "Precio unitario del producto", example = "25000")
    private BigDecimal precioProducto;
    @Column(name = "cantidad")
    @Schema(description = "Cantidad solicitada", example = "2")
    private Integer cantidad;
    @Column(name = "subtotal")
    @Schema(description = "Subtotal del ítem", example = "50000")
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden")
    @Schema(description = "Orden asociada")
    private OrderEntity orden;

    // Getters y Setters en español
    public Long getId() { return id; }
    public String getIdProducto() { return idProducto; }
    public void setIdProducto(String idProducto) { this.idProducto = idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public BigDecimal getPrecioProducto() { return precioProducto; }
    public void setPrecioProducto(BigDecimal precioProducto) { this.precioProducto = precioProducto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public OrderEntity getOrden() { return orden; }
    public void setOrden(OrderEntity orden) { this.orden = orden; }
}
