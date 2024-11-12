package com.example.fetchReceiptprocessor.view;

import com.example.fetchReceiptprocessor.model.Receipt;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class ReceiptService {

    private final Map<String, Integer> receiptData = new HashMap<>();

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        int points = calculatePoints(receipt);
        receiptData.put(id, points);

        return id;
    }

    public Integer getPoints(String receiptId) {
        return receiptData.get(receiptId);
    }


    public int calculatePoints(@org.jetbrains.annotations.NotNull Receipt receipt) {
        int points = 0;

        // 1. Points for alphanumeric characters in retailer name
        if (receipt.getRetailer() != null) {
            points += countAlphanumeric(receipt.getRetailer());
        }

        // 2. Points if total is a round dollar amount
        BigDecimal total = receipt.getTotal();
        if (total != null) {
            if (total.scale() == 0) { // No cents
                points += 50;
            }
            if (total.remainder(BigDecimal.valueOf(0.25)).compareTo(BigDecimal.ZERO) == 0) { // Multiple of 0.25
                points += 25;
            }
        }

        // 3. Points for every two items on the receipt
        if (receipt.getItems() != null) {
            points += (receipt.getItems().size() / 2) * 5;

            // 4. Points based on item description length and price
            for (Receipt.ReceiptItem1 item : receipt.getItems()) {
                String description = item.getShortDescription().trim();
                if (description.length() % 3 == 0) {
                    BigDecimal itemPoints = item.getPrice().multiply(BigDecimal.valueOf(0.2));
                    points += itemPoints.setScale(0, RoundingMode.UP).intValue();
                }
            }
        }

        // 5. Points if the day is odd
        if (receipt.getPurchaseDate() != null && receipt.getPurchaseDate().getDayOfMonth() % 2 != 0) {
            points += 6;
        }

        // 6. Points if the purchase time is after 2:00pm and before 4:00pm
        if (receipt.getPurchaseTime() != null && receipt.getPurchaseTime().isAfter(LocalTime.of(14, 0)) &&
                receipt.getPurchaseTime().isBefore(LocalTime.of(16, 0))) {
            points += 10;
        }

        return points;
    }

    // Helper function to count alphanumeric characters
    private int countAlphanumeric(String retailer) {
        int count = 0;
        for (char c : retailer.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                count++;
            }
        }
        return count;
    }
}

