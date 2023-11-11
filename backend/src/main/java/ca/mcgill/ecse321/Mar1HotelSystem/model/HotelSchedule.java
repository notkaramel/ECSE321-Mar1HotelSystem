// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.*;

/**
 * The HotelSchedule class for the yearly schedule of the hotel.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 */

@Entity
public class HotelSchedule {
    @Id
    private int year;
    @OneToMany
    private List<CustomHours> customHoursList;
    @OneToMany
    private List<OperatingHours> operatingHoursList;

    // Default constructor
    public HotelSchedule() {
    }

    // HotelSchedule constructor requiring year, list of operatingHours, list
    // customHours
    public HotelSchedule(int year, List<OperatingHours> operatingHoursList, List<CustomHours> customHoursList) {
        this.year = year;
        this.customHoursList = customHoursList;
        this.operatingHoursList = operatingHoursList;
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

    public boolean setOperatingHours(List<OperatingHours> newOperatingHours) {
        ArrayList<OperatingHours> theOperatingHoursList = new ArrayList<OperatingHours>();

        for (OperatingHours aOperatingHour : newOperatingHours) {
            if (theOperatingHoursList.contains(aOperatingHour)) {
                continue;
            }
            theOperatingHoursList.add(aOperatingHour);
        }
        if (theOperatingHoursList.size() != newOperatingHours.size()
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

    public static int minimumNumberOfCustomHours() {
        return 0;
    }

    public static int maximumNumberOfCustomHours() {
        return 366;
    }

    public boolean setCustomHours(List<CustomHours> newCustomHours) {
        boolean wasSet = false;
        ArrayList<CustomHours> verifiedCustomHours = new ArrayList<CustomHours>();
        for (CustomHours aCustomHour : newCustomHours) {
            if (verifiedCustomHours.contains(aCustomHour)) {
                continue;
            }
            verifiedCustomHours.add(aCustomHour);
        }
        if (verifiedCustomHours.size() != newCustomHours.size()
                || verifiedCustomHours.size() != maximumNumberOfCustomHours()) {
            return wasSet;
        }
        this.customHoursList.clear();
        this.customHoursList.addAll(verifiedCustomHours);
        wasSet = true;
        return wasSet;
    }


    public void delete() {
        operatingHoursList.clear();
        this.customHoursList.clear();
    }

}