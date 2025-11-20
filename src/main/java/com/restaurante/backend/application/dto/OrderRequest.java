package com.restaurante.backend.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(name = "OrdenRequest", description = "Datos para crear una orden")
public class OrderRequest {
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
    @Schema(description = "Lista de ítems de la orden")
    private List<OrderItemRequest> items;

    // Getters y Setters en español
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
    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
