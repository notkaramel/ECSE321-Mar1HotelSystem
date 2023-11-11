package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import jakarta.validation.constraints.NotNull;

public class RequestRequestDto {

    // VARIABLES
    @NotNull
    private String description;
    private int bookingId;
    private boolean isFulfilled;

    // CONSTRUCTORS
    public RequestRequestDto() {
    }

    // Request constructor requiring description, employee, booking, and isFufilled
    public RequestRequestDto(String description, int bookingId, boolean isFulfilled) {
        this.description = description;
        this.bookingId = bookingId;
        this.isFulfilled = isFulfilled;
    }

    // GETTERS
    // Method to get description, returns description
    public String getDescription() {
        return this.description;
    }

    // Method to get isFulfilled, returns isFulfilled
    public boolean getIsFulfilled() {
        return this.isFulfilled;
    }

    // Method to get booking, returns booking
    public int getBookingId() {
        return this.bookingId;
    }
}