package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class HotelDto {
    
    // VARIABLES
    private String hotelName = "Mar-1 Hotel";
    private HotelScheduleDto hotelSchedule;
    private List<Room> rooms;

    // CONSTRUCTORS
    public HotelDto() {
    }

    // Hotel constructor requiring hotelSchedule
    public HotelDto(HotelScheduleDto hotelSchedule) {
        rooms = new ArrayList<Room>();
        if (setHotelSchedule(hotelSchedule) == false) {
            throw new RuntimeException("Need an hotelSchedule class to be instatiated; need an Hotel Schedule");
        }
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
    public RoomDto getRoom(int index) {
        RoomDto room = this.rooms.get(index);
        return room;
    }

    public List<RoomDto> getRooms() {
        List<RoomDto> room = new ArrayList<RoomDto>();
        return room;
    }

    public int getNumberOfRooms() {
        return this.rooms.size();
    }

    // SETTERS 
    // Method to set hotelSchedule, returns true if hotelSchedule set
    public boolean setHotelSchedule(HotelScheduleDto hotelSchedule) {
        if (hotelSchedule != null) {
            this.hotelSchedule = hotelSchedule;
            return true;
        } else {
            return false;
        }
    }
 
}
