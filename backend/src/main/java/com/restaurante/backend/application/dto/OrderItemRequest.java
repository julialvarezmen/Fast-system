package com.restaurante.backend.application.dto;

public class OrderItemRequest {
    private String idProducto;
    private Integer cantidad;

    // Getters y Setters en español
    public String getIdProducto() { return idProducto; }
    public void setIdProducto(String idProducto) { this.idProducto = idProducto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
