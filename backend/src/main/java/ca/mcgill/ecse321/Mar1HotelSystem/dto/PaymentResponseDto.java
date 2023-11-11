package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import jakarta.validation.constraints.NotBlank;

public class PaymentResponseDto {
    private int paymentId;
    @NotBlank
    private int amount;

    public PaymentResponseDto() {
    }

    public PaymentResponseDto(Payment payment) {
        if (payment != null) {
            this.paymentId = payment.getPaymentId();
            this.amount = payment.getAmount();
        }
    }

    // Getters
    public int getPaymentId() {
        return paymentId;
    }

    public int getAmount() {
        return amount;
    }
}
