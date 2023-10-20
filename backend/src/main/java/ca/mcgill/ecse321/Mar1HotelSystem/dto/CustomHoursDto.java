package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.sql.Date;

import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;

public class CustomHoursDto {
    // VARIABLES
    private int customHoursId;
    private java.util.Date date;
    private int openingHour;
    private int closingHour;

    // CONSTRUCTORS
    public CustomHoursDto() {
    }

    // CustomHours constructor requiring date, openingHour, closingHour
    public CustomHoursDto(CustomHours customHours) {
        this.date = customHours.getDate();
        this.openingHour = customHours.getOpeningHour();
        this.closingHour = customHours.getClosingHour();
    }

    // GETTERS
    // Method to get date, returns date
    public java.util.Date getDate() {
        return  this.date;
    }

    // Method to get OpeningHour, returns OpeningHour
    public int getOpeningHour() {
        return this.openingHour;
    }

    // Method to get ClosingHour, returns ClosingHour
    public int getClosingHour() {
        return this.closingHour;
    }

}