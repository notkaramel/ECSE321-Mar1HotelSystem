package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class BookingRequestDto {
    
    // VARIABLES
    private String generalUserEmail;
    private int roomId;
    private int paymentId;

    // CONSTRUCTORS
    public BookingRequestDto() {
    }

    public BookingRequestDto(String generalUserEmail, int roomId, int paymentId) {
        this.generalUserEmail = generalUserEmail;
        this.roomId = roomId;
        this.paymentId = paymentId;
    }

    // GETTERS
    public String getGeneralUserEmail() {
        return generalUserEmail;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getPaymentId() {
        return paymentId;
    }
}