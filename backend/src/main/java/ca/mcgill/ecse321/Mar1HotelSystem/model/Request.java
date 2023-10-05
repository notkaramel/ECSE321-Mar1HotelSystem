// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

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

    // 
    public Request(String description, Employee employee, Booking booking, boolean isFufilled) {
        this.description = description;
        this.isFufilled = isFufilled;
        if (setService(service) == false) {
            throw new RuntimeException("Need an service class to be instatiated; need an service");
        }

        if (setBooking(booking) == false) {
            throw new RuntimeException("Need an booking class to be instatiated; need a booking");
        }
    }

    // Getters
    // Method to get description, returns description
    public String getDescription() {
        return this.description;
    }

    // Method to get isFufilled, returns isFufilled
    public Boolean getIsFufilled() {
        return this.isFufilled;
    }

    // Method to get service, returns service
    public Service getService() {
        return this.service;
    }

    // Method to get booking, returns booking
    public Booking getBooking() {
        return this.booking;
    }

    // Setters
    // Method to set description, returns true if description set
    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    // Method to set isFufilled, returns true if isFufilled set
    public boolean setIsFufilled(boolean isFufilled) {
        this.isFufilled = isFufilled;
        return true;
    }

    // Method to set service, returns true if service set
    public boolean setService(Service service) {
        if (service != null) {
            this.service = service;
            return true;
        } else {
            return false;
        }
    }

    // Method to set booking, returns true if booking set
    public boolean setBooking(Booking booking) {
        if (booking != null) {
            this.booking = booking;
            return true;
        } else {
            return false;
        }
    }

    public void delete() {
        this.booking = null;
    }
}