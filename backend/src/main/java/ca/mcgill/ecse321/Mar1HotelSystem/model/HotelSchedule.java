// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class HotelSchedule {
    @Id
    private int year;

    @OneToMany
    private List<CustomHours> customHoursList;
    @OneToMany
    private List<OperatingHours> operatingHoursList;

    // HotelSchedule constructor requiring year, list of operatingHours, list customHours
    public HotelSchedule(int year, OperatingHours[] operatingHoursList, CustomHours[] customHoursList) {
        this.year = year;
        this.customHoursList = new ArrayList<CustomHours>();
        this.operatingHoursList = new ArrayList<OperatingHours>();
        if (setCustomHours(customHoursList) == false) {
            throw new RuntimeException("Need an customHours class to be instatiated; need an custom hours");
        }

        if (setOperatingHours(operatingHoursList) == false) {
            throw new RuntimeException("Need an operatingHours class to be instatiated; need a operating hours");
        }
    }

    // Getters
    // Method to get year, returns year
    public int getYear() {
        return this.year;
    }

    // Setters
    // Method to set year, returns true if year set
    public boolean setYear(int year) {
        this.year = year;
        return true;
    }

    // Methods for 1 to 7 associationn between HotelSchedule and OperatingHours
    public List<OperatingHours> getOperatingHours() {
        List<OperatingHours> newOperatingHours = Collections.unmodifiableList(this.operatingHoursList);
        return newOperatingHours;
    }

    public static int requiredNumberOfOperatingHours() {
        return 7;
    }

    public static int minimumdNumberOfOperatingHours() {
        return 7;
    }

    public static int maximumNumberOfOperatingHours() {
        return 7;
    }

    public boolean setOperatingHours(OperatingHours... newOperatingHours) {
        ArrayList<OperatingHours> theOperatingHoursList = new ArrayList<OperatingHours>();

        for (OperatingHours aOperatingHour : newOperatingHours) {
            if (theOperatingHoursList.contains(aOperatingHour)) {
                continue;
            }
            theOperatingHoursList.add(aOperatingHour);
        }
        if (theOperatingHoursList.size() != newOperatingHours.length
                || theOperatingHoursList.size() != requiredNumberOfOperatingHours()) {
            return false;
        }
        this.operatingHoursList.clear();
        this.operatingHoursList.addAll(theOperatingHoursList);
        return true;
    }

    // Methods for 1 to 365 associations between Hotel to CustomHours
    public List<CustomHours> getCustomHours() {
        List<CustomHours> newCustomHours = Collections.unmodifiableList(this.customHoursList);
        return newCustomHours;
    }

    public static int requiredNumberOfCustomHours() {
        return 365;
    }

    public static int minimumdNumberOfCustomHours() {
        return 365;
    }

    public static int maximumNumberOfCustomHours() {
        return 365;
    }

    public boolean setCustomHours(CustomHours... newCustomHours) {
        ArrayList<CustomHours> theCustomHoursList = new ArrayList<CustomHours>();

        for (CustomHours aCustomHour : newCustomHours) {
            if (theCustomHoursList.contains(aCustomHour)) {
                continue;
            }
            theCustomHoursList.add(aCustomHour);
        }
        if (theCustomHoursList.size() != newCustomHours.length
                || theCustomHoursList.size() != requiredNumberOfCustomHours()) {
            return false;
        }
        this.customHoursList.clear();
        this.customHoursList.addAll(theCustomHoursList);
        return true;
    }

    
    public void delete() {
        this.operatingHoursList = null;
        this.customHoursList = null;
    }

}