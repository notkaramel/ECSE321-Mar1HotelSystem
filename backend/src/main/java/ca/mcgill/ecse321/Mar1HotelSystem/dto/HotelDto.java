package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.List;

public class HotelDto {
    
    // VARIABLES
    final private String hotelName = "Mar-1 Hotel";
    private HotelScheduleDto hotelSchedule;
    private List<RoomResponseDto> allRooms;

    // CONSTRUCTORS
    public HotelDto() {
    }

    public HotelDto(List<RoomResponseDto> allRooms) {
        this.allRooms = allRooms;
    }

    public HotelDto(HotelScheduleDto hotelScheduleDto, List<RoomResponseDto> allRooms) {
        this.hotelSchedule = hotelScheduleDto;
        this.allRooms = allRooms;
    }

    // GETTERS
    // Method to get hotelSchedule, returns hotelSchedule
    public HotelScheduleDto getHotelSchedule() {
        return this.hotelSchedule;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public List<RoomResponseDto> getRooms() {
        return this.allRooms;
    }
}
