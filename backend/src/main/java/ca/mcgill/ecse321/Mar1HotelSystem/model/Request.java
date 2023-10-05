// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;
    private String description;
    private boolean isFufilled;
    @OneToOne
    private Service service;
    private Booking booking;
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    public Request(String description, boolean isFufilled, Service service, Booking booking,
            Mar1HotelSystemApplication mar1HotelSystemApplication) {
        this.description = description;
        this.isFufilled = isFufilled;
        if (setService(service) == false) {
            throw new RuntimeException("Need an service class to be instatiated; need an service");
        }

        if (setBooking(booking) == false) {
            throw new RuntimeException("Need an booking class to be instatiated; need a booking");
        }

        if (setMar1HotelSystemApplication(mar1HotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to mar1HotelSystemApplication");
        }

    }

    // Getters
    public String getDescription() {
        return this.description;
    }

    public Boolean getIsFufilled() {
        return this.isFufilled;
    }

    public Service getService() {
        return this.service;
    }

    public Booking getBooking() {
        return this.booking;
    }

    // Setters
    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    public boolean setIsFufilled(boolean isFufilled) {
        this.isFufilled = isFufilled;
        return true;
    }

    public boolean setService(Service service) {
        if (service != null) {
            this.service = service;
            return true;
        } else {
            return false;
        }
    }

    public boolean setBooking(Booking booking) {
        if (booking != null) {
            this.booking = booking;
            return true;
        } else {
            return false;
        }
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
            existingMar1HotelSystemApplication.removeRequest(this);
            return false;
        }
        mar1HotelSystemApplication.addRequest(this);
        return true;
    }

    public void delete() {

        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeRequest(this);
        }

        this.service = null;
        this.booking = null;
    }
}