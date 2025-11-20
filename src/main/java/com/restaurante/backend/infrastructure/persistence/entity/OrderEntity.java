package com.restaurante.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ordenes")
@Schema(name = "Orden", description = "Entidad que representa una orden o pedido")
public class OrderEntity {
    @Id
    @Column(name = "id_orden")
    @Schema(description = "ID único de la orden", example = "a1b2c3d4")
    private String id;

    @Column(name = "nombre_cliente")
    @Schema(description = "Nombre del cliente", example = "Juan Pérez")
    private String nombreCliente;
    @Column(name = "telefono_cliente")
    @Schema(description = "Teléfono del cliente", example = "3001234567")
    private String telefonoCliente;
    @Column(name = "direccion")
    @Schema(description = "Dirección de entrega", example = "Calle 123 #45-67")
    private String direccion;
    @Column(name = "barrio")
    @Schema(description = "Barrio de entrega", example = "Laureles")
    private String barrio;
    @Column(name = "metodo_pago")
    @Schema(description = "Método de pago", example = "EFECTIVO")
    private String metodoPago;
    @Column(name = "total")
    @Schema(description = "Total de la orden", example = "50000")
    private BigDecimal total = BigDecimal.ZERO;
    @Column(name = "estado")
    @Schema(description = "Estado de la orden", example = "RECIBIDO")
    private String estado;
    @Column(name = "fecha_creacion")
    @Schema(description = "Fecha de creación de la orden", example = "2025-11-20T10:00:00Z")
    private Instant fechaCreacion;
    @Column(name = "fecha_actualizacion")
    @Schema(description = "Fecha de última actualización", example = "2025-11-20T10:05:00Z")
    private Instant fechaActualizacion;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de ítems de la orden")
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity() {
        this.id = UUID.randomUUID().toString();
        this.fechaCreacion = Instant.now();
        this.fechaActualizacion = Instant.now();
    }

    // ------- Cálculo del total --------
    public void calcularTotal() {
        this.total = items.stream()
                .map(OrderItemEntity::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // ------- Getters y Setters --------
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
    public List<OrderItemEntity> getItems() { return items; }
    public void setItems(List<OrderItemEntity> items) { this.items = items; this.items.forEach(i -> i.setOrden(this)); }
}
