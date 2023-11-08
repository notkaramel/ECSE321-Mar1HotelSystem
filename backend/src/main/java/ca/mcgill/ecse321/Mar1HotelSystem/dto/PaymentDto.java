package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;



public class PaymentDto {
    //VARIABLES
    private int paymentId;

    @Nonnull
    @Min(0)
    private int amount;

    public PaymentDto() {
    }

    public PaymentDto(Payment payment) {
        if (payment != null) {
            this.paymentId = payment.getPaymentId();
            this.amount = payment.getAmount();
        }
    }

    public PaymentDto(int amount) {
        this.amount = amount;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
