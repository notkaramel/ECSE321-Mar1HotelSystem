package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

public class OperatingHoursResponseDto {
    private DayOfWeek dayOfWeek;
    private int openingHour;
    private int closingHour;

    public OperatingHoursResponseDto() {
    }

    public OperatingHoursResponseDto(OperatingHours operatingHours) {
        this.dayOfWeek = operatingHours.getDayOfWeek();
        this.openingHour = operatingHours.getOpeningHour();
        this.closingHour = operatingHours.getClosingHour();
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }
    public void setClosingHour(int closingHour) {
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
