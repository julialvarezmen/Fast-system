package com.restaurante.backend.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class Order {
    private String id;
    private String nombreCliente;
    private String telefonoCliente;
    private String direccion;
    private String barrio;
    private String metodoPago;
    private BigDecimal total;
    private String estado;
    private Instant fechaCreacion;
    private Instant fechaActualizacion;
    private List<OrderItem> items;

    public Order() {}

    public Order(String id, String nombreCliente, String telefonoCliente,
                 String direccion, String barrio, String metodoPago,
                 BigDecimal total, String estado,
                 Instant fechaCreacion, Instant fechaActualizacion,
                 List<OrderItem> items) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccion = direccion;
        this.barrio = barrio;
        this.metodoPago = metodoPago;
        this.total = total;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.items = items;
    }

    // Getters y Setters en español
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getTelefonoCliente() { return telefonoCliente; }
    public void setTelefonoCliente(String telefonoCliente) { this.telefonoCliente = telefonoCliente; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getBarrio() { return barrio; }
    public void setBarrio(String barrio) { this.barrio = barrio; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Instant getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Instant fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Instant getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(Instant fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public void calcularTotal() {
        if (items != null) {
            this.total = items.stream()
                    .map(OrderItem::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            this.total = BigDecimal.ZERO;
        }
    }
}
