package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;

public class HotelScheduleResponseDto {
    private int year;
    private CustomHours[] customHoursList;
    private OperatingHours[] operatingHoursList;

    public HotelScheduleResponseDto(int year, CustomHours[] customHoursList, OperatingHours[] operatingHoursList) {
        this.year = year;
        this.customHoursList = customHoursList;
        this.operatingHoursList = operatingHoursList;
    }

    public HotelScheduleResponseDto(HotelSchedule i) {
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

    
}
