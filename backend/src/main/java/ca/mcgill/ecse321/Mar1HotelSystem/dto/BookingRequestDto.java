package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import jakarta.annotation.Nonnull;

public class BookingRequestDto {
    
    // VARIABLES
    @Nonnull
    private PaymentRequestDto payment;

    @Nonnull
    private GeneralUserDto generalUser;

    @Nonnull
    private RoomDto room;

    // CONSTRUCTORS
    public BookingRequestDto() {
    }

    public BookingRequestDto(PaymentRequestDto payment, GeneralUserDto generalUser, RoomDto room) {
        this.payment = payment;
        this.generalUser = generalUser;
        this.room = room;
    }

    // GETTERS AND SETTERS
    public PaymentRequestDto getPayment() {
        return this.payment;
    }

    public void setPayment(PaymentRequestDto payment) {
        this.payment = payment;
    }

    public GeneralUserDto getGeneralUser() {
        return this.generalUser;
    }

    public void setGeneralUser(GeneralUserDto generalUser) {
        this.generalUser = generalUser;
    }

    public RoomDto getRoom() {
        return this.room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }
}