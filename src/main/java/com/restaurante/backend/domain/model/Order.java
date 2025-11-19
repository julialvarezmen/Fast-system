package com.restaurante.app.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class Order {

    private String id;

    private String customerName;
    private String customerPhone;
    private String address;
    private String neighborhood;
    private String paymentMethod;

    private BigDecimal total;
    private String status;

    private Instant createdAt;
    private Instant updatedAt;

    private List<OrderItem> items;

    public Order() {}

    public Order(String id, String customerName, String customerPhone,
                 String address, String neighborhood, String paymentMethod,
                 BigDecimal total, String status,
                 Instant createdAt, Instant updatedAt,
                 List<OrderItem> items) {

        this.id = id;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.address = address;
        this.neighborhood = neighborhood;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.items = items;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
