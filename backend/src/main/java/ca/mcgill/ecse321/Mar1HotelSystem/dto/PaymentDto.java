package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class PaymentDto {
    // VARIABLES
    private int paymentId;
    private int amount;

    // CONSTRUCTORS
    public PaymentDto() {
    }

    public PaymentDto(int amount) {
        this.amount = amount;
    }

    // GETTERS 
    // Method to get amount, returns amount
    public int getAmount() {
        return this.amount;
    }

    // Method to get paymentId, returns paymentId
    public int getPaymentId() {
        return this.paymentId;
    }

}