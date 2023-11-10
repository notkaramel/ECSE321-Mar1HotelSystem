package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.Date;

import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;

public class CustomHoursRequestDto {
    private Date date;
    private int openingHour;
    private int closingHour;

    public CustomHoursRequestDto() {
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setOpeningHour(int operatingHour) {
        this.openingHour = operatingHour;
    }
    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
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
    
    public CustomHours toModel() {
        CustomHours customHours = new CustomHours();
        customHours.setDate(this.date);
        customHours.setOpeningHour(this.openingHour);
        customHours.setClosingHour(this.closingHour);
        return customHours;
    }
}
