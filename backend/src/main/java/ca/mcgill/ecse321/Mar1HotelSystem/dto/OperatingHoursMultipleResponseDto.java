package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;

import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;

public class OperatingHoursMultipleResponseDto {
    private List<OperatingHoursResponseDto> operatingHoursList;

    public OperatingHoursMultipleResponseDto() {
    }

    public OperatingHoursMultipleResponseDto(List<OperatingHours> operatingHours) {
        List<OperatingHoursResponseDto> operatingHoursDtoList = new ArrayList<>();
        for (OperatingHours oh_i : operatingHours) {
            operatingHoursDtoList.add(new OperatingHoursResponseDto(oh_i));
        }
        this.operatingHoursList = operatingHoursDtoList;
    }

    public List<OperatingHoursResponseDto> getOperatingHoursList() {
        return this.operatingHoursList;
    }
}
