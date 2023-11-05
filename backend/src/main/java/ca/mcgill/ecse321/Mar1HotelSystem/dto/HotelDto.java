package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.List;

public class HotelDto {
    
    // VARIABLES
    private String hotelName = "Mar-1 Hotel";
    private HotelScheduleDto hotelSchedule;
    private List<RoomResponseDto> rooms;

    // CONSTRUCTORS
    public HotelDto() {
    }

    // GETTERS
    // Method to get hotelSchedule, returns hotelSchedule
    public HotelScheduleDto getHotelSchedule() {
        return this.hotelSchedule;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    // Methods for Composition with Room
    // public RoomDto getRoom(int index) {
    //     RoomDto room = this.rooms.get(index);
    //     return room;
    // }

    public List<RoomResponseDto> getRooms() {
        return this.rooms;
    }

    public int getNumberOfRooms() {
        return this.rooms.size();
    }

}
