package com.restaurante.backend.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Schema(name = "OrdenResponse", description = "Respuesta con los datos de una orden")
public class OrderResponse {
    @Schema(description = "ID de la orden", example = "a1b2c3d4")
    private String id;
    @Schema(description = "Nombre del cliente", example = "Juan Pérez")
    private String nombreCliente;
    @Schema(description = "Teléfono del cliente", example = "3001234567")
    private String telefonoCliente;
    @Schema(description = "Dirección de entrega", example = "Calle 123 #45-67")
    private String direccion;
    @Schema(description = "Barrio de entrega", example = "Laureles")
    private String barrio;
    @Schema(description = "Método de pago", example = "EFECTIVO")
    private String metodoPago;
    @Schema(description = "Total de la orden", example = "50000")
    private BigDecimal total;
    @Schema(description = "Estado de la orden", example = "RECIBIDO")
    private String estado;
    @Schema(description = "Fecha de creación de la orden", example = "2025-11-20T10:00:00Z")
    private Instant fechaCreacion;
    @Schema(description = "Fecha de última actualización", example = "2025-11-20T10:05:00Z")
    private Instant fechaActualizacion;
    @Schema(description = "Lista de ítems de la orden")
    private List<OrderItemResponse> items;

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
    public List<OrderItemResponse> getItems() { return items; }
    public void setItems(List<OrderItemResponse> items) { this.items = items; }
}
