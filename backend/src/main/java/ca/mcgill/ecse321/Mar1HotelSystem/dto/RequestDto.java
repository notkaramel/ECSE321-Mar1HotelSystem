package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;

public class RequestDto {
    
    // VARIABLES
    private int requestId;
    private String description;
    private boolean isFufilled;
    private Booking booking;

    // CONSTRUCTORS
    public RequestDto() {
    }

    // Request constructor requiring description, employee, booking, and isFufilled
    public RequestDto(Request request) {
        this.description = request.getDescription();
        this.booking = request.getBooking();
        this.isFufilled = request.getIsFufilled();
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