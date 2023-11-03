package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

public class OperatingHoursResponseDto {
    private DayOfWeek day;
    private int openingHour;
    private int closingHour;

    // Enum DayOfWeek
    public enum DayOfWeekDto {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    public OperatingHoursResponseDto(OperatingHours operatingHours) {
        this.day = operatingHours.getDayOfWeek();
        this.openingHour = operatingHours.getOpeningHour();
        this.closingHour = operatingHours.getClosingHour();
    }

    public DayOfWeek getDayOfWeekDto() {
        return this.day;
    }
    public int getOpeningHour() {
        return this.openingHour;
    }
    public int getClosingHour() {
        return this.closingHour;
    }
}
