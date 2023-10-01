package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class OperatingHours {
    // Defining variables
    private DayOfWeek day;
    private int openingHour;
    private int closingHour;

    public OperatingHours(DayOfWeek day, int openingHour, int closingHour) {
        this.day = day;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    // Getters
    public DayOfWeek getDayOfWeek() {
        return this.day;
    }

    public int getOpeningHour() {
        return this.openingHour;
    }

    public int getClosingHour() {
        return this.closingHour;
    }

    // Setters
    public boolean setDayOfWeek(DayOfWeek day) {
        this.day = day;
        return true;
    }

    public boolean setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
        return true;
    }

    public boolean setClosingHour(int closingHour) {
        this.closingHour = closingHour;
        return true;
    }

    public void delete() {

    }

}