package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;


public class RequestResponseDto {
    
    // VARIABLES
    private int requestId;
    private String description;
    private boolean isFufilled;
    private Booking booking;

    // CONSTRUCTORS
    public RequestResponseDto() {
    }

    // Request constructor requiring description, employee, booking, and isFufilled
    public RequestResponseDto(int requestId, String description, Booking booking, boolean isFufilled) {
        this.requestId = requestId;
        this.description = description;
        this.booking = booking;
        this.isFufilled = isFufilled;
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
    public Booking getBooking() {
        return this.booking;
    }

    public int getRequestId() {
        return this.requestId;
    }

}