// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

public class OperatingHours {
    // Defining variables
    @Id
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    private int openingHour;
    private int closingHour;

    // OperatingHours constructor day, openingHour, closingHour
    public OperatingHours(DayOfWeek day, int openingHour, int closingHour) {
        this.day = day;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    // Enum DayOfWeek
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

    public void delete() {
    }

}