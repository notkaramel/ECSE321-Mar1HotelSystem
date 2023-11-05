package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;

public class HotelScheduleMultipleResponseDto {
    
    private Iterable<HotelScheduleResponseDto> hotelSchedule;

    public HotelScheduleMultipleResponseDto(Iterable<HotelSchedule> hotelSchedule) {
        List<HotelScheduleResponseDto> hotelScheduleDtos = new ArrayList<HotelScheduleResponseDto>();
        for (HotelSchedule i : hotelSchedule) {
            hotelScheduleDtos.add(new HotelScheduleResponseDto(i));
        }
        this.hotelSchedule = hotelScheduleDtos;
    }

    public Iterable<HotelScheduleResponseDto> getAllHotelSchedule() {
        return this.hotelSchedule;
    }
}
