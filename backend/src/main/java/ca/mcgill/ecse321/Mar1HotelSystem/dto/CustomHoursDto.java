package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.sql.Date;

public class CustomHoursDto {
    // VARIABLES
    private int customHoursId;
    private Date date;
    private int openingHour;
    private int closingHour;

    // CONSTRUCTORS
    public CustomHoursDto() {
    }

    // CustomHours constructor requiring date, openingHour, closingHour
    public CustomHoursDto(Date date, int openingHour, int closingHour) {
        this.date = date;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    // GETTERS
    // Method to get date, returns date
    public Date getDate() {
        return this.date;
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