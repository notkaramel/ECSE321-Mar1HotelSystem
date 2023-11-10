package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;

public class HotelScheduleRequestDto {

    private int year;
    private CustomHours[] customHoursList;
    private OperatingHours[] operatingHoursList;

    public HotelScheduleRequestDto() {
    }

    public int getYear() {
        return this.year;
    }
    public CustomHours[] getCustomHoursList() {
        return this.customHoursList;
    }
    public OperatingHours[] getOperatingHoursList() {
        return this.operatingHoursList;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public void setCustomHoursList(CustomHours[] customHoursList) {
        this.customHoursList = customHoursList;
    }
    public void setOperatingHoursList(OperatingHours[] operatingHoursList) {
        this.operatingHoursList = operatingHoursList;
    }


    public HotelSchedule toModel() {
        HotelSchedule hotelSchedule = new HotelSchedule();
        hotelSchedule.setYear(this.year);
        hotelSchedule.setCustomHours(this.customHoursList);
        hotelSchedule.setOperatingHours(this.operatingHoursList);
        return hotelSchedule;
    }

}
