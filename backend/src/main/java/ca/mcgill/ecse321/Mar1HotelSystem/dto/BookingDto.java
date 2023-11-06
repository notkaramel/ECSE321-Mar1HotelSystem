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

    public BookingDto(int bookingId, PaymentDto payment, GeneralUserDto generalUser, RoomDto room) {
        this.bookingId = bookingId;
        this.payment = payment;
        this.generalUser = generalUser;
        this.room = room;
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

}