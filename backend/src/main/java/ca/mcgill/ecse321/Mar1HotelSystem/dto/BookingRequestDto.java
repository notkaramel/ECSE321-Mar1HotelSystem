package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RoomRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import jakarta.annotation.Nonnull;

public class BookingRequestDto {
    
    // VARIABLES
    private int paymentId;
    private String generalUserEmail;
    private int roomId;


    // CONSTRUCTORS
    public BookingRequestDto() {
    }

    public BookingRequestDto(int paymentId, String generalUserEmail, int roomId) {
        this.paymentId = paymentId;
        this.generalUserEmail = generalUserEmail;
        this.roomId = roomId;
    }

    // GETTERS AND SETTERS

    public String getGeneralUserEmail() {
        return this.generalUserEmail;
    }
    
    public int getRoomId() {
        return this.roomId;
    }

    public int getPaymentId() {
        return this.paymentId;
    }
}