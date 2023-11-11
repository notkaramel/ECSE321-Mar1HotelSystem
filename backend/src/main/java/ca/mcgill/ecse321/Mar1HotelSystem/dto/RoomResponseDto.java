package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;
import jakarta.validation.constraints.NotEmpty;

/**
 * A response that contains all the information about a room.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public class RoomResponseDto {

    // VARIABLES
    @NotEmpty(message = "Room ID cannot be empty")
    private int roomId;
    private Room.RoomType roomType;
    private Room.BedType bedType;
    private boolean isAvailable;
    private int pricePerNight;
    private int maxCapacity;

    public RoomResponseDto() {
    }

    public RoomResponseDto(Room room) {
        this.roomId = room.getRoomId();
        this.roomType = room.getRoomType();
        this.bedType = room.getBedType();
        this.isAvailable = room.getIsAvailable();
        this.pricePerNight = room.getPricePerNight();
        this.maxCapacity = room.getMaxCapacity();
    }

    public int getRoomId() {
        return this.roomId;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public BedType getBedType() {
        return this.bedType;
    }

    // Method to get isAvailable, returns isAvailable
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    // Method to get pricePerNight, returns pricePerNight
    public int getPricePerNight() {
        return this.pricePerNight;
    }

    // Method to get maxCapacity, returns maxCapacity
    public int getMaxCapacity() {
        return this.maxCapacity;
    }
}