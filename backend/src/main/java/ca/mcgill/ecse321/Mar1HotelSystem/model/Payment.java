// Umple was used a guide and generated some code in this project
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
    // Method to get amount, returns amount
    public int getAmount() {
        return this.amount;
    }

    // Method to get paymentId, returns paymentId
    public int getPaymentId() {
        return this.paymentId;
    }

    // Setters
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

    public void delete() {
    }
}