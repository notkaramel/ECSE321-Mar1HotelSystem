package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import jakarta.validation.constraints.NotNull;

public class RequestRequestDto {
    
    // VARIABLES
    @NotNull
    private String description;
    @NotNull
    private boolean isFufilled;
    @NotNull
    private int bookingId;
    
    // CONSTRUCTORS
    public RequestRequestDto() {
    }
    
    // Request constructor requiring description, employee, booking, and isFufilled
    public RequestRequestDto(String description, int bookingId, boolean isFufilled) {
        this.description = description;
        this.bookingId = bookingId;
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
    public int getBookingId() {
        return this.bookingId;
    }
}