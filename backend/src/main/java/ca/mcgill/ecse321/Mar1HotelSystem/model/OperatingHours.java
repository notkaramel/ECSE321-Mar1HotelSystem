// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

public class OperatingHours {
    // Defining variables
    @Id
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    private int openingHour;
    private int closingHour;
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    // OperatingHours constructor requiring day, operatingHour, customHour, and mar1HotelSystemApplication
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
    // Method to get dayOfWeek, returns dayOfWeek
    public DayOfWeek getDayOfWeek() {
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

    // Setters
    // Method to set dayOfWeek, returns true if dayOfWeek set
    public boolean setDayOfWeek(DayOfWeek day) {
        this.day = day;
        return true;
    }

    // Method to set openingHour, returns true if openingHour set
    public boolean setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
        return true;
    }

    // Method to set closingHour, returns true if closingHour set
    public boolean setClosingHour(int closingHour) {
        this.closingHour = closingHour;
        return true;
    }

    // Methods for composition with Mar1HotelSystemApplication
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

    // Method to delete
    public void delete() {
        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeOperatingHours(this);
        }

    }

}