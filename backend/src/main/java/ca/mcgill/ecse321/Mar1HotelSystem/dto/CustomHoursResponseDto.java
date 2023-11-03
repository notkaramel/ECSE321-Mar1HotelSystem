package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.Date;

import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;

public class CustomHoursResponseDto {
    private Date date;
    private int openingHour;
    private int closingHour;

    public CustomHoursResponseDto(CustomHours customHour) {
        this.date = customHour.getDate();
        this.openingHour = customHour.getOpeningHour();
        this.closingHour = customHour.getClosingHour();
    }

    public Date getDate() {
        return this.date;
    }
    public int getOpeningHour() {
        return this.openingHour;
    }
    public int getClosingHour() {
        return this.closingHour;
    }

}