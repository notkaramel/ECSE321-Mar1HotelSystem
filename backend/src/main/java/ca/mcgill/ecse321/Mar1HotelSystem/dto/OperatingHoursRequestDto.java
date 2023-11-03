package ca.mcgill.ecse321.Mar1HotelSystem.dto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

public class OperatingHoursRequestDto{
    
    private DayOfWeek day;
    private int openingHour;
    private int closingHour;

    public void setDayOfWeek(DayOfWeek day) {
        this.day = day;
    }
    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }
    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
    }

    public DayOfWeek getDayOfWeek() {
        return this.day;
    }
    public int getOpeningHour() {
        return this.openingHour;
    }
    public int getClosingHour() {
        return this.closingHour;
    }

    public OperatingHours toModel() {
        OperatingHours operatingHours = new OperatingHours();
        operatingHours.setDayOfWeek(this.day);
        operatingHours.setOpeningHour(this.openingHour);
        operatingHours.setClosingHour(this.closingHour);
        return operatingHours;
    }
}