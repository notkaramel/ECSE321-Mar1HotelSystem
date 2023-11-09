package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;

public class BookingResponseDto {
    
    private int bookingId;
    private Payment payment;
    private GeneralUser generalUser;
    private Room room;

    // Default constructor
    public BookingResponseDto() {
    }

    // Constructor based on the Booking domain model
    public BookingResponseDto(Booking booking) {
        if (booking != null) {
            this.bookingId = booking.getBookingId();
            this.payment = booking.getPayment();
            this.generalUser = booking.getGeneralUser();
            this.room = booking.getRoom();
        }
    }

    // Getters and setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public GeneralUser getGeneralUser() {
        return generalUser;
    }

    public void setGeneralUser(GeneralUser generalUser) {
        this.generalUser = generalUser;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
