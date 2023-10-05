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

    public OperatingHours(DayOfWeek day, int openingHour, int closingHour) {
        this.day = day;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
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

    public void delete() {
    }

}