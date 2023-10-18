package ca.mcgill.ecse321.Mar1HotelSystem.model;

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

    // SETTERS
    // Method to set amount, returns true if amount set
    public boolean setAmount(int amount) {
        this.amount = amount;
        return true;
    }

    // Method to set payment, returns true if payment set
    public boolean setPaymentId(int paymentId) {
        this.paymentId = paymentId;
        return true;
    }
}