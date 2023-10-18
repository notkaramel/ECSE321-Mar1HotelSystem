package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class RequestDto {
    
    // VARIABLES
    private int requestId;
    private String description;
    private boolean isFufilled;
    private BookingDto booking;

    // CONSTRUCTORS
    public RequestDto() {
    }

    // Request constructor requiring description, employee, booking, and isFufilled
    public RequestDto(String description, BookingDto booking, boolean isFufilled) {
        this.description = description;
        this.isFufilled = isFufilled;

        if (setBooking(booking) == false) {
            throw new RuntimeException("Need an booking class to be instatiated; need a booking");
        }
    }

    // GETTERS 
    // Method to get description, returns description
    public String getDescription() {
        return this.description;
    }

    // Method to get isFufilled, returns isFufilled
    public Boolean getIsFufilled() {
        return this.isFufilled;
    }

    // Method to get booking, returns booking
    public BookingDto getBooking() {
        return this.booking;
    }

    public int getRequestId() {
        return this.requestId;
    }

}