package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
@Entity
public class HotelSchedule {
    private int year;
    @OneToMany
    private List<CustomHours> customHoursList;
    @OneToMany
    private List<OperatingHours> operatingHoursList;
    private MarwaanHotelSystemApplication marwaanHotelSystemApplication;

    public HotelSchedule(int year, OperatingHours[] operatingHoursList, CustomHours[] customHoursList,
            MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        this.year = year;
        this.customHoursList = new ArrayList<CustomHours>();
        this.operatingHoursList = new ArrayList<OperatingHours>();
        if (setCustomHours(customHoursList) == false) {
            throw new RuntimeException("Need an customHours class to be instatiated; need an custom hours");
        }

        if (setOperatingHours(operatingHoursList) == false) {
            throw new RuntimeException("Need an operatingHours class to be instatiated; need a operating hours");
        }

        if (setMarwaanHotelSystemApplication(marwaanHotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to marwaanHotelSystemApplication");
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

    public MarwaanHotelSystemApplication getMarwaanHotelSystemApplication() {
        return marwaanHotelSystemApplication;
    }

    protected void clear_marwaanHotelSystemApplication() {
        marwaanHotelSystemApplication = null;
    }

    public boolean setMarwaanHotelSystemApplication(MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        if (marwaanHotelSystemApplication == null) {
            return false;
        }

        MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = this.marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = marwaanHotelSystemApplication;
        if (existingMarwaanHotelSystemApplication != null
                && !existingMarwaanHotelSystemApplication.equals(marwaanHotelSystemApplication)) {
            existingMarwaanHotelSystemApplication.removeHotelSchedule(this);
            return false;
        }
        marwaanHotelSystemApplication.addHotelSchedule(this);
        return true;
    }

    public void delete() {
        MarwaanHotelSystemApplication placeholderMarwaanHotelSystemApplication = marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = null;
        if (placeholderMarwaanHotelSystemApplication != null) {
            placeholderMarwaanHotelSystemApplication.removeHotelSchedule(this);
        }
        this.operatingHoursList = null;
        this.customHoursList = null;
    }

}