package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

import java.util.List;
import java.util.ArrayList;

public class HotelDto {
    
    // VARIABLES
    private String hotelName = "Mar-1 Hotel";
    private HotelScheduleRequestDto hotelSchedule;
    private List<Room> rooms;

    // CONSTRUCTORS
    public HotelDto() {
    }

    // Hotel constructor requiring hotelSchedule
    public HotelDto(HotelScheduleRequestDto hotelSchedule) {
        this.hotelSchedule = hotelSchedule;
        rooms = new ArrayList<Room>();
    }

    // GETTERS
    // Method to get hotelSchedule, returns hotelSchedule
    public HotelScheduleRequestDto getHotelSchedule() {
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

    public List<RoomDto> getRooms() {
        List<RoomDto> room = new ArrayList<RoomDto>();
        return room;
    }

    public int getNumberOfRooms() {
        return this.rooms.size();
    }

}
