package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class Booking {
    private String bookingId;
    private Payment payment;
    private User user;
    private Room room;

    public Booking(String bookingId, Payment payment, User user, Room room) {
        this.bookingId = bookingId;
        if (payment == null || payment == false) {
            throw new RuntimeException("Need an payment class to be instatiated; need a payment");
        }

        if (user == null || user == false) {
            throw new RuntimeException("Need an user class to be instatiated; need a user");
        }

        if (room == null || room == false) {
            throw new RuntimeException("Need an room class to be instatiated; need a room");
        }
    }

    // Getters
    public String getBookingId() {
        return this.bookingId;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public User getUser() {
        return this.user;
    }

    public Room getRoom() {
        return this.room;
    }

    // Setters
    public boolean setBookingId(String bookingId) {
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

    public void delete() {
        this.payment = null;
        this.user = null;
        this.room = null;
    }

}