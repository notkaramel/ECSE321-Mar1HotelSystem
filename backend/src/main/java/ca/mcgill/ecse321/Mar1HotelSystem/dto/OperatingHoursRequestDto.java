package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

public class OperatingHoursRequestDto {

    private DayOfWeek dayOfWeek;
    private int openingHour;
    private int closingHour;

    public OperatingHoursRequestDto() {
    }

    public OperatingHoursRequestDto(DayOfWeek day, int openingHour, int closingHour) {
        this.dayOfWeek = day;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public int getOpeningHour() {
        return this.openingHour;
    }

    public int getClosingHour() {
        return this.closingHour;
    }
}