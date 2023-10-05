package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
    // Defining variables
    private int amount;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    public Payment(int amount, int paymentId) {
        this.amount = amount;
        this.paymentId = paymentId;
    }

    // Getters
    public int getAmount() {
        return this.amount;
    }

    public int getPaymentId() {
        return this.paymentId;
    }

    // Setters
    public boolean setAmount(int amount) {
        this.amount = amount;
        return true;
    }

    public boolean setPaymentId(int paymentId) {
        this.paymentId = paymentId;
        return true;
    }

    public void delete() {
    }
}