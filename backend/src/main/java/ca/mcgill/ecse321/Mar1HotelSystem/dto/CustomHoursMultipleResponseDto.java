package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;

public class CustomHoursMultipleResponseDto {

    private Iterable<CustomHoursResponseDto> customHoursList;

    public CustomHoursMultipleResponseDto() {
    }

    public CustomHoursMultipleResponseDto(Iterable<CustomHours> customHours) {
        List<CustomHoursResponseDto> customHoursDtos = new ArrayList<CustomHoursResponseDto>();
        for (CustomHours i : customHours) {
            customHoursDtos.add(new CustomHoursResponseDto(i));
        }
        this.customHoursList = customHoursDtos;
    }

    public Iterable<CustomHoursResponseDto> getAllCustomHours() {
        return this.customHoursList;
    }
}
