package com.paymentservice.api.payload;

public class PaymentPayload{
    private String description;
    private double amount;

    // Constructors, getters, and setters

    public PaymentPayload() {
    }

    public PaymentPayload(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // Getters and setters for description and amount

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
