package com.example.fetchReceiptProcessor.model;

import java.math.BigDecimal;

public class ReceiptItem1 {

    private String description;
    private BigDecimal price;

    public ReceiptItem1() {}

    public ReceiptItem1(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReceiptItem{" +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

