package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.Collections;
import java.util.List;

public class HotelScheduleDto {
   
    // VARIABLES
    private int year;
    private List<CustomHoursDto> customHoursList;
    private List<OperatingHoursDto> operatingHoursList;

    // CONSTRUCTORS
    public HotelScheduleDto() {
    }

    // HotelSchedule constructor requiring year, list of operatingHours, list
    // customHours
    public HotelScheduleDto(int year, OperatingHoursDto[] operatingHoursList, CustomHoursDto[] customHoursList) {
        this.year = year;
        this.customHoursList = new ArrayList<CustomHoursDto>(Arrays.asList(customHoursList));
        this.operatingHoursList = new ArrayList<OperatingHoursDto>(Arrays.asList(operatingHoursList));
    }

    // GETTERS 
    // Method to get year, returns year
    public int getYear() {
        return this.year;
    }

    // Methods for 1 to 7 associationn between HotelSchedule and OperatingHours
    public List<OperatingHoursDto> getOperatingHours() {
        List<OperatingHoursDto> newOperatingHours = Collections.unmodifiableList(this.operatingHoursList);
        return newOperatingHours;
    }

    // Methods for 1 to 365 associations between Hotel to CustomHours
    public List<CustomHoursDto> getCustomHours() {
        List<CustomHoursDto> newCustomHours = Collections.unmodifiableList(this.customHoursList);
        return newCustomHours;
    }

    // SETTERS
    // Method to set year, returns true if year set
    public boolean setYear(int year) {
        this.year = year;
        return true;
    }

    public boolean setOperatingHours(OperatingHoursDto... newOperatingHours) {
        ArrayList<OperatingHoursDto> theOperatingHoursList = new ArrayList<OperatingHoursDto>();

        for (OperatingHoursDto aOperatingHour : newOperatingHours) {
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

    public boolean setCustomHours(CustomHoursDto... newCustomHours) {
        boolean wasSet = false;
        ArrayList<CustomHoursDto> verifiedCustomHours = new ArrayList<CustomHoursDto>();
        for (CustomHoursDto aCustomHour : newCustomHours) {
            if (verifiedCustomHours.contains(aCustomHour)) {
                continue;
            }
            verifiedCustomHours.add(aCustomHour);
        }

        if (verifiedCustomHours.size() != newCustomHours.length
                || verifiedCustomHours.size() > maximumNumberOfCustomHours()) {
            return wasSet;
        }

        customHoursList.clear();
        customHoursList.addAll(verifiedCustomHours);
        wasSet = true;
        return wasSet;
    }


}
