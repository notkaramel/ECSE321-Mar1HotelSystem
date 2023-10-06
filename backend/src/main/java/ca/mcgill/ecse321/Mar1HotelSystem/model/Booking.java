// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;
    @OneToOne
    private Payment payment;
    @OneToOne
    private User user;
    @ManyToOne
    private Room room;

    // Booking constructor requiring bookingId, payment, user, and room
    public Booking(int bookingId, Payment payment, User user, Room room) {
        this.bookingId = bookingId;
        if (setPayment(payment) == false) {
            throw new RuntimeException("Need an payment class to be instatiated; need a payment");
        }

        if (setUser(user) == false) {
            throw new RuntimeException("Need an user class to be instatiated; need a user");
        }

        if (setRoom(room) == false) {
            throw new RuntimeException("Need an room class to be instatiated; need a room");
        }
    }

    // Getters
    // Method getting bookingId, returns bookingId
    public int getBookingId() {
        return this.bookingId;
    }

    // Method getting payment, returns payment
    public Payment getPayment() {
        return this.payment;
    }

    // Method getting user, returns user
    public User getUser() {
        return this.user;
    }

    // Method getting room, returns room
    public Room getRoom() {
        return this.room;
    }

    // Setters
    // Method to set bookingId, returns true if bookingId set
    public boolean setBookingId(int bookingId) {
        this.bookingId = bookingId;
        return true;
    }

    // Method to set payment, returns true if payment set
    public boolean setPayment(Payment payment) {
        if (payment != null) {
            this.payment = payment;
            return true;
        } else {
            return false;
        }
    }

    // Method to set user, returns true if user set
    public boolean setUser(User user) {
        if (user != null) {
            this.user = user;
            return true;
        } else {
            return false;
        }
    }

    // Method to set room, returns true if room set
    public boolean setRoom(Room room) {
        if (room != null) {
            this.room = room;
            return true;
        } else {
            return false;
        }
    }

    public void delete() {
        this.payment = null;
        this.user = null;
        this.room = null;
    }

}