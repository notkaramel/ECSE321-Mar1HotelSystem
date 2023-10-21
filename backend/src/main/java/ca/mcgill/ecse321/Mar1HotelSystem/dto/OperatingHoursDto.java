package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class OperatingHoursDto{
    
    // VARIABLES
    // private int operatingHoursId;
    private DayOfWeekDto day;
    private int openingHour;
    private int closingHour;

    // CONSTRUCTORS
    public OperatingHoursDto() {
    }

    // OperatingHours constructor day, openingHour, closingHour
    public OperatingHoursDto(DayOfWeekDto day, int openingHour, int closingHour) {
        this.day = day;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    // ENUM
    // Enum DayOfWeek
    public enum DayOfWeekDto {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    // GETTERS 
    // Method to get dayOfWeek, returns dayOfWeek
    public DayOfWeekDto getDayOfWeekDto() {
        return this.day;
    }

    // Method to get openingHour, returns openingHour
    public int getOpeningHour() {
        return this.openingHour;
    }

    // Method to get closingHour, returns closingHour
    public int getClosingHour() {
        return this.closingHour;
    }

}