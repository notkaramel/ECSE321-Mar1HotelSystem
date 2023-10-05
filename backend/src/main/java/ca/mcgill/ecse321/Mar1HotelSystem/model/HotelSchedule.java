// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class HotelSchedule {
    private int year;
    @OneToMany
    private List<CustomHours> customHoursList;
    @OneToMany
    private List<OperatingHours> operatingHoursList;
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    public HotelSchedule(int year, OperatingHours[] operatingHoursList, CustomHours[] customHoursList,
            Mar1HotelSystemApplication mar1HotelSystemApplication) {
        this.year = year;
        this.customHoursList = new ArrayList<CustomHours>();
        this.operatingHoursList = new ArrayList<OperatingHours>();
        if (setCustomHours(customHoursList) == false) {
            throw new RuntimeException("Need an customHours class to be instatiated; need an custom hours");
        }

        if (setOperatingHours(operatingHoursList) == false) {
            throw new RuntimeException("Need an operatingHours class to be instatiated; need a operating hours");
        }

        if (setMar1HotelSystemApplication(mar1HotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to mar1HotelSystemApplication");
        }

    }

    // Getters
    public int getYear() {
        return this.year;
    }

    // Setters
    public boolean setYear(int year) {
        this.year = year;
        return true;
    }

    // 7 association
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

    // 365 association to CustomHour
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
            existingMar1HotelSystemApplication.removeHotelSchedule(this);
            return false;
        }
        mar1HotelSystemApplication.addHotelSchedule(this);
        return true;
    }

    public void delete() {
        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeHotelSchedule(this);
        }
        this.operatingHoursList = null;
        this.customHoursList = null;
    }

}