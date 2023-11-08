package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import jakarta.annotation.Nonnull;

/**
 * A request that contains all the necessary information to create a booking.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public class BookingRequestDto {
    
    // VARIABLES
    @Nonnull
    private PaymentDto payment;

    @Nonnull
    private GeneralUserDto generalUser;

    @Nonnull
    private RoomDto room;

    // CONSTRUCTORS
    public BookingRequestDto() {
    }

    public BookingRequestDto(PaymentDto payment, GeneralUserDto generalUser, RoomDto room) {
        this.payment = payment;
        this.generalUser = generalUser;
        this.room = room;
    }

    // GETTERS AND SETTERS
    public PaymentDto getPayment() {
        return this.payment;
    }

    public void setPayment(PaymentDto payment) {
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
