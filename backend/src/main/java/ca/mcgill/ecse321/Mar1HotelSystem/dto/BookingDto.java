package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class BookingDto {
   
    // VARIABLES
    private int bookingId;
    private PaymentDto payment;
    private GeneralUserDto generalUser;
    private RoomDto room;

    // CONSTRUCTORS
    public BookingDto() {
    }

    public BookingDto(PaymentDto payment, GeneralUserDto generalUser, RoomDto room) {
        if (setPayment(payment) == false) {
            throw new RuntimeException("Need an payment class to be instatiated; need a payment");
        }

        if (setGeneralUser(generalUser) == false) {
            throw new RuntimeException("Need an user class to be instatiated; need a user");
        }

        if (setRoom(room) == false) {
            throw new RuntimeException("Need an room class to be instatiated; need a room");
        }
    }


    // GETTERS
    // Method getting bookingId, returns bookingId
    public int getBookingId() {
        return this.bookingId;
    }

    // Method getting payment, returns payment
    public PaymentDto getPayment() {
        return this.payment;
    }

    // Method getting user, returns user
    public GeneralUserDto getGeneralUser() {
        return this.generalUser;
    }

    // Method getting room, returns room
    public RoomDto getRoom() {
        return this.room;
    }

    // SETTERS
    // Method to set bookingId, returns true if bookingId set
    public boolean setBookingId(int bookingId) {
        this.bookingId = bookingId;
        return true;
    }

    // Method to set payment, returns true if payment set
    public boolean setPayment(PaymentDto payment) {
        if (payment != null) {
            this.payment = payment;
            return true;
        } else {
            return false;
        }
    }

    // Method to set user, returns true if user set
    public boolean setGeneralUser(GeneralUserDto user) {
        if (user != null) {
            this.generalUser = user;
            return true;
        } else {
            return false;
        }
    }

    // Method to set room, returns true if room set
    public boolean setRoom(RoomDto room) {
        if (room != null) {
            this.room = room;
            return true;
        } else {
            return false;
        }
    }

}