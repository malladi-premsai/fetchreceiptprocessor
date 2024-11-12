package com.example.fetchReceiptprocessor.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



public class Receipt {

    private String retailer;
    private BigDecimal total;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime purchaseTime;

    private List<ReceiptItem1> items;

    // Getters and Setters
    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<ReceiptItem1> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItem1> items) {
        this.items = items;
    }

    // Nested class for ReceiptItem
    public static class ReceiptItem1 {

        private String shortDescription;
        private BigDecimal price;

        // Getters and Setters
        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }


    }
}



