package ca.mcgill.ecse321.Mar1HotelSystem.dto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;

public class OperatingHoursRequestDto{
    
    private DayOfWeek dayOfWeek;
    private int openingHour;
    private int closingHour;

    public OperatingHoursRequestDto() {
    }
    
    public void setDayOfWeek(DayOfWeek day) {
        this.dayOfWeek = day;
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

    public OperatingHours toModel() {
        OperatingHours operatingHours = new OperatingHours();
        operatingHours.setDayOfWeek(this.dayOfWeek);
        operatingHours.setOpeningHour(this.openingHour);
        operatingHours.setClosingHour(this.closingHour);
        return operatingHours;
    }
}