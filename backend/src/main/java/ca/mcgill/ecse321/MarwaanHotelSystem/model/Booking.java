package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;
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
    private MarwaanHotelSystemApplication marwaanHotelSystemApplication;

    // Booking constructor requiring bookingId, payment, user, room and marwaanHotelSystemApplication
    public Booking(int bookingId, Payment payment, User user, Room room,
            MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
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
        if (setMarwaanHotelSystemApplication(marwaanHotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to marwaanHotelSystemApplication");
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
    public boolean setBookingId(int bookingId) { 
        this.bookingId = bookingId;
        return true;
    }

    public boolean setPayment(Payment payment) {
        if (payment != null) {
            this.payment = payment;
            return true;
        } else {
            return false;
        }
    }

    public boolean setUser(User user) {
        if (user != null) {
            this.user = user;
            return true;
        } else {
            return false;
        }
    }

    public boolean setRoom(Room room) {
        if (room != null) {
            this.room = room;
            return true;
        } else {
            return false;
        }
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
            existingMarwaanHotelSystemApplication.removeBooking(this);
            return false;
        }
        marwaanHotelSystemApplication.addBooking(this);
        return true;
    }

    public void delete() {
        MarwaanHotelSystemApplication placeholderMarwaanHotelSystemApplication = marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = null;
        if (placeholderMarwaanHotelSystemApplication != null) {
            placeholderMarwaanHotelSystemApplication.removeBooking(this);
        }
        this.payment = null;
        this.user = null;
        this.room = null;
    }

}