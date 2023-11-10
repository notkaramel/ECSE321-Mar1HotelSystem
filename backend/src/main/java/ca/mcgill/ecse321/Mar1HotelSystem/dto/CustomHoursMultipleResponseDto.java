package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;

public class CustomHoursMultipleResponseDto {

    private Iterable<CustomHoursResponseDto> customHours;

    public CustomHoursMultipleResponseDto() {
    }

    public CustomHoursMultipleResponseDto(Iterable<CustomHours> customHours) {
        List<CustomHoursResponseDto> customHoursDtos = new ArrayList<CustomHoursResponseDto>();
        for (CustomHours i : customHours) {
            customHoursDtos.add(new CustomHoursResponseDto(i));
        }
        this.customHours = customHoursDtos;
    }

    public Iterable<CustomHoursResponseDto> getAllCustomHours() {
        return this.customHours;
    }
}
