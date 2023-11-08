package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import jakarta.annotation.Nonnull;

public class BookingResponseDto{
    @Nonnull private int bookingId;
    @Nonnull private Room room;
    @Nonnull private GeneralUser generalUser;
    @Nonnull private Payment payment;

    // CONSTRUCTORS
     public BookingResponseDto() {
        super();
    }

    public BookingResponseDto(Booking booking) {
        this.bookingId = booking.getBookingId();
        this.room = booking.getRoom();
        this.generalUser = booking.getGeneralUser();
        this.payment = booking.getPayment();
        

    }

    public int getBookingId() {
        return this.bookingId;
    }

    public Room getRoom() {
        return this.room;
    }

    public GeneralUser getGeneralUser() {
        return this.generalUser;
    }

    public Payment getPayment() {
        return this.payment;
    }
}

