// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.*;

/**
 * The Payment class for all customers with a booking in the system.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 */

@Entity
public class Payment {
    // Defining variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    private int amount;

    public Payment() {

    }

    // Payment constructor requiring amount and paymentId
    public Payment(int amount) {
        this.amount = amount;
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