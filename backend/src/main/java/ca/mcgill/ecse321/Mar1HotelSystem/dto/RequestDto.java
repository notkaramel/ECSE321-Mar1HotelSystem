package ca.mcgill.ecse321.Mar1HotelSystem.dto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;


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
    public RequestDto(String description, Booking booking, boolean isFufilled) {
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

    // SETTERS
    // Method to set description, returns true if description set
    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    // Method to set isFufilled, returns true if isFufilled set
    public boolean setIsFufilled(boolean isFufilled) {
        this.isFufilled = isFufilled;
        return true;
    }

    // Method to set booking, returns true if booking set
    public boolean setBooking(BookingDto booking) {
        if (booking != null) {
            this.booking = booking;
            return true;
        } else {
            return false;
        }
    }

}