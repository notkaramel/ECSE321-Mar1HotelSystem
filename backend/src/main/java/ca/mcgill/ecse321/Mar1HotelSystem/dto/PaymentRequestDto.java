package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import jakarta.annotation.Nonnull;

public class PaymentRequestDto {
    @Nonnull
    private int amount;

    public PaymentRequestDto() {
    }

    public PaymentRequestDto(int amount) {
        this.amount = amount;
    }

    // Getters and Setters
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
