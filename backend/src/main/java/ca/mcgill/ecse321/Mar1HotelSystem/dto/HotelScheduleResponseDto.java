package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;

public class HotelScheduleResponseDto {
    private int year;
    private List<OperatingHours> operatingHoursList;
    private List<CustomHours> customHoursList;

    public HotelScheduleResponseDto() {
    }

    public HotelScheduleResponseDto(HotelSchedule hotelSchedule) {
        this.year = hotelSchedule.getYear();
        this.operatingHoursList = hotelSchedule.getOperatingHours();
        this.customHoursList = hotelSchedule.getCustomHours();
    }

    public int getYear() {
        return this.year;
    }

    public List<OperatingHours> getOperatingHoursList() {
        return this.operatingHoursList;
    }

    public List<CustomHours> getCustomHoursList() {
        return this.customHoursList;
    }

}
