package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;


public class RequestResponseDto {
    
    // VARIABLES
    private int requestId;
    private String description;
    private boolean isFulfilled;
    private Booking booking;

    // CONSTRUCTORS
    public RequestResponseDto() {
    }

    // Request constructor requiring description, employee, booking, and isFufilled
    public RequestResponseDto(int requestId, String description, Booking booking, boolean isFulfilled) {
        this.requestId = requestId;
        this.description = description;
        this.booking = booking;
        this.isFulfilled = isFulfilled;
    }

    // GETTERS 
    // Method to get description, returns description
    public String getDescription() {
        return this.description;
    }

    // Method to get isFulfilled, returns isFulfilled
    public Boolean getIsFulfilled() {
        return this.isFulfilled;
    }

    // Method to get booking, returns booking
    public Booking getBooking() {
        return this.booking;
    }

    public int getRequestId() {
        return this.requestId;
    }

}