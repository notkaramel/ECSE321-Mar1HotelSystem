package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import jakarta.validation.constraints.NotBlank;

public class PaymentRequestDto {
    @NotBlank
    private int amount;

    public PaymentRequestDto() {
    }

    public PaymentRequestDto(int amount) {
        this.amount = amount;
    }

    // Getters
    public int getAmount() {
        return amount;
    }
}
