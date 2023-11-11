package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

public class OperatingHoursResponseDto {
    private int operatingHoursId;
    private DayOfWeek dayOfWeek;
    private int openingHour;
    private int closingHour;

    public OperatingHoursResponseDto() {
    }

    public OperatingHoursResponseDto(OperatingHours operatingHours) {
        this.operatingHoursId = operatingHours.getOperatingHoursId();
        this.dayOfWeek = operatingHours.getDayOfWeek();
        this.openingHour = operatingHours.getOpeningHour();
        this.closingHour = operatingHours.getClosingHour();
    }

    public int getOperatingHoursId() {
        return this.operatingHoursId;
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
