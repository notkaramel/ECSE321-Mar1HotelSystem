package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class BookingDto {
   
    // VARIABLES
    private int bookingId;
    private PaymentDto payment;
    private GeneralUserDto generalUser;
    private RoomResponseDto room;

    // CONSTRUCTORS
    public BookingDto() {
    }


    public BookingDto(PaymentDto payment, GeneralUserDto generalUser, RoomResponseDto room) {
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
    public RoomResponseDto getRoom() {
        return this.room;
    }

}