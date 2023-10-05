package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
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
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    public Payment(int amount, int paymentId, Mar1HotelSystemApplication mar1HotelSystemApplication) {
        this.amount = amount;
        this.paymentId = paymentId;
        if (setMar1HotelSystemApplication(mar1HotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to mar1HotelSystemApplication");
        }
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

    public Mar1HotelSystemApplication getMar1HotelSystemApplication() {
        return mar1HotelSystemApplication;
    }

    protected void clear_mar1HotelSystemApplication() {
        mar1HotelSystemApplication = null;
    }

    public boolean setMar1HotelSystemApplication(Mar1HotelSystemApplication mar1HotelSystemApplication) {
        if (mar1HotelSystemApplication == null) {
            return false;
        }

        Mar1HotelSystemApplication existingMar1HotelSystemApplication = this.mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = mar1HotelSystemApplication;
        if (existingMar1HotelSystemApplication != null
                && !existingMar1HotelSystemApplication.equals(mar1HotelSystemApplication)) {
            existingMar1HotelSystemApplication.removePayment(this);
            return false;
        }
        mar1HotelSystemApplication.addPayment(this);
        return true;
    }

    public void delete() {
        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removePayment(this);
        }
    }

}