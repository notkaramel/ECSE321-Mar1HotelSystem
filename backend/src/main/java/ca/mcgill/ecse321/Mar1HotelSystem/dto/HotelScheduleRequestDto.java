package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;
import java.util.List;

public class HotelScheduleRequestDto {
   
    // VARIABLES
    private int year;
    private List<CustomHoursResponseDto> customHoursList;
    private List<OperatingHoursRequestDto> operatingHoursList;

    // CONSTRUCTORS
    public HotelScheduleRequestDto() {
    }

    // HotelSchedule constructor requiring year, list of operatingHours, list
    // customHours
    public HotelScheduleRequestDto(int year, OperatingHoursRequestDto[] operatingHoursList, CustomHoursResponseDto[] customHoursList) {
        this.year = year;
        this.customHoursList = new ArrayList<CustomHoursResponseDto>(Arrays.asList(customHoursList));
        this.operatingHoursList = new ArrayList<OperatingHoursRequestDto>(Arrays.asList(operatingHoursList));
    }

    // GETTERS 
    // Method to get year, returns year
    public int getYear() {
        return this.year;
    }

    // Methods for 1 to 7 associationn between HotelSchedule and OperatingHours
    public List<OperatingHoursRequestDto> getOperatingHours() {
        List<OperatingHoursRequestDto> newOperatingHours = Collections.unmodifiableList(this.operatingHoursList);
        return newOperatingHours;
    }

    // Methods for 1 to 365 associations between Hotel to CustomHours
    public List<CustomHoursResponseDto> getCustomHours() {
        List<CustomHoursResponseDto> newCustomHours = Collections.unmodifiableList(this.customHoursList);
        return newCustomHours;
    }

}
