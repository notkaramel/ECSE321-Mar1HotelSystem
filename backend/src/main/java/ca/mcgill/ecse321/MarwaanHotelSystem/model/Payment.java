package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;
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
    private MarwaanHotelSystemApplication marwaanHotelSystemApplication;

    public Payment(int amount, int paymentId, MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        this.amount = amount;
        this.paymentId = paymentId;
        if (setMarwaanHotelSystemApplication(marwaanHotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to marwaanHotelSystemApplication");
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

    public MarwaanHotelSystemApplication getMarwaanHotelSystemApplication() {
        return marwaanHotelSystemApplication;
    }

    protected void clear_marwaanHotelSystemApplication() {
        marwaanHotelSystemApplication = null;
    }

    public boolean setMarwaanHotelSystemApplication(MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        if (marwaanHotelSystemApplication == null) {
            return false;
        }

        MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = this.marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = marwaanHotelSystemApplication;
        if (existingMarwaanHotelSystemApplication != null
                && !existingMarwaanHotelSystemApplication.equals(marwaanHotelSystemApplication)) {
            existingMarwaanHotelSystemApplication.removePayment(this);
            return false;
        }
        marwaanHotelSystemApplication.addPayment(this);
        return true;
    }

    public void delete() {
        MarwaanHotelSystemApplication placeholderMarwaanHotelSystemApplication = marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = null;
        if (placeholderMarwaanHotelSystemApplication != null) {
            placeholderMarwaanHotelSystemApplication.removePayment(this);
        }
    }

}