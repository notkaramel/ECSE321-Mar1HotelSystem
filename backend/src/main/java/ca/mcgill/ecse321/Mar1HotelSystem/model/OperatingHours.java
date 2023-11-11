// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.*;

/**
 * The OperatingHours class for hotel of the system.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 * @author ZiXu Liu (@ARandomPi) - JPA Annotations
 */

@Entity
public class OperatingHours {
    // Defining variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int operatingHoursId;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    private int openingHour;
    private int closingHour;

    // Default constructor
    public OperatingHours() {
    }

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

    public int getOperatingHoursId() {
        return this.operatingHoursId;
    }

    // Setters
    public boolean setOpeningHoursId(int aOperatingHoursId) {
        this.operatingHoursId = aOperatingHoursId;
        return true;
    }

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