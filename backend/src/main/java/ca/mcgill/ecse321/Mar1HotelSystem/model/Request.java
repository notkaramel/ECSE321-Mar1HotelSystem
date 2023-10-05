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

    public void delete() {
        this.booking = null;
    }
}