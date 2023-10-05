package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import jakarta.persistence.Id;

public class OperatingHours {
    // Defining variables
    @Id
    private DayOfWeek day;
    private int openingHour;
    private int closingHour;
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    public OperatingHours(DayOfWeek day, int openingHour, int closingHour,
            Mar1HotelSystemApplication mar1HotelSystemApplication) {
        this.day = day;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        if (setMar1HotelSystemApplication(mar1HotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to mar1HotelSystemApplication");
        }
    }

    // Enum
    public enum DayOfWeek {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
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

    public Mar1HotelSystemApplication getMar1HotelSystemApplication() {
        return mar1HotelSystemApplication;
    }

    protected void clear_mar1HotelSystemApplication() {
        mar1HotelSystemApplication = null;
    }

    public boolean setMar1HotelSystemApplication(Mar1HotelSystemApplication mar1HotelSystemApplication) {
        if (mar1HotelSystemApplication == null) {
            return false;
        }

        Mar1HotelSystemApplication existingMar1HotelSystemApplication = this.mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = mar1HotelSystemApplication;
        if (existingMar1HotelSystemApplication != null
                && !existingMar1HotelSystemApplication.equals(mar1HotelSystemApplication)) {
            existingMar1HotelSystemApplication.removeOperatingHours(this);
            return false;
        }
        mar1HotelSystemApplication.addOperatingHours(this);
        return true;
    }

    public void delete() {
        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeOperatingHours(this);
        }

    }

}