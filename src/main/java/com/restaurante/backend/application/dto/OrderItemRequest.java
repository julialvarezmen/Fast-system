package com.restaurante.app.application.dto;

import java.math.BigDecimal;

public class OrderItemRequest {

    private String productId;
    private Integer quantity;

    // Getters y Setters

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
