// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

/**
 * The Request class for all requestes with a booking in the system.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 */

@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;

    private String description;
    private boolean isFulfilled;

    @ManyToOne
    private Booking booking;

    public Request() {

    }

    // Request constructor requiring description, employee, booking, and isFufilled
    public Request(String description, Booking booking, boolean isFulfilled) {
        this.description = description;
        this.isFulfilled = isFulfilled;

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
    public Boolean getIsFulfilled() {
        return this.isFulfilled;
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

    // Method to set isFulfilled, returns true if isFulfilled set
    public boolean setIsFulfilled(boolean isFulfilled) {
        this.isFulfilled = isFulfilled;
        return true;
    }

    // Method to set booking, returns true if booking set
    public boolean setBooking(Booking booking) {
        if (booking == null) {
            return false;
        } else {
            this.booking = booking;
            return true;
        }
    }

    public int getRequestId() {
        return this.requestId;
    }


    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }


    public void delete() {
        this.booking = null;
    }
}