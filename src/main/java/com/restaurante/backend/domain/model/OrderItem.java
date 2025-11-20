package com.restaurante.backend.domain.model;

import java.math.BigDecimal;

public class OrderItem {
    private String id;
    private String idProducto;
    private String nombreProducto;
    private BigDecimal precioProducto;
    private Integer cantidad;
    private BigDecimal subtotal;
    private Order orden;
    public OrderItem() {}
    public OrderItem(String idProducto, String nombreProducto, BigDecimal precioProducto,
                     Integer cantidad, BigDecimal subtotal) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    // Getters y Setters en español
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Order getOrden() { return orden; }
    public void setOrden(Order orden) { this.orden = orden; }
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
}
