package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;

public class BookingResponseDto {
    
    private int bookingId;
    private PaymentDto payment;
    private GeneralUserDto generalUser;
    private RoomDto room;

    // Default constructor
    public BookingResponseDto() {
    }

    // Constructor based on the Booking domain model
    public BookingResponseDto(Booking booking) {
        if (booking != null) {
            this.bookingId = booking.getBookingId();
            this.payment = new PaymentDto(booking.getPayment()); // Assuming PaymentDto constructor takes a Payment model
            this.generalUser = new GeneralUserDto(booking.getGeneralUser().getFirstName(),booking.getGeneralUser().getLastName(),booking.getGeneralUser().getEmail(),booking.getGeneralUser().getPhoneNumber()); // Assuming GeneralUserDto constructor takes a GeneralUser model
            this.room = new RoomDto(booking.getRoom()); // Assuming RoomDto constructor takes a Room model
        }
    }

    // Getters and setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public void setPayment(PaymentDto payment) {
        this.payment = payment;
    }

    public GeneralUserDto getGeneralUser() {
        return generalUser;
    }

    public void setGeneralUser(GeneralUserDto generalUser) {
        this.generalUser = generalUser;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }
}
