package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;

import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;

public class OperatingHoursMultipleResponseDto {
     private Iterable<OperatingHoursResponseDto> operatingHours;

     public OperatingHoursMultipleResponseDto(Iterable<OperatingHours> operatingHours) {
        List<OperatingHoursResponseDto> operatingHoursDtos = new ArrayList<OperatingHoursResponseDto>();
        for (OperatingHours i : operatingHours) {
            operatingHoursDtos.add(new OperatingHoursResponseDto(i));
        }
        this.operatingHours = operatingHoursDtos;
     }

     public Iterable<OperatingHoursResponseDto> getAllOperatingHours() {
         return this.operatingHours;
     } 
}
