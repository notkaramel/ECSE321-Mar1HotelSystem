package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Hotel;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

public class RoomDto {
    
    // VARIABLES
    private int roomId;
    private Room.RoomType roomType;
    private Room.BedType bedType;
    private boolean isAvailable;
    private int pricePerNight;
    private int maxCapacity;
    private Hotel hotel;

    // CONSTRUCTORS
    public RoomDto() {
    }

    public RoomDto(Room.RoomType roomType, Room.BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity,
            Hotel hotel) {
        this.roomType = roomType;
        this.bedType = bedType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
        this.hotel = hotel;
    }

    public RoomDto(Room room) {
        this.roomType = room.getRoomType();
        this.bedType = room.getBedType();
        this.isAvailable = room.getIsAvailable();
        this.pricePerNight = room.getPricePerNight();
        this.maxCapacity = room.getMaxCapacity();
        this.hotel = room.getHotel();
    }
    // ENUM
    // BedType enum
    // public enum BedTypeDto {
    //     Queen, King, Doubles
    // }

    // // RoomType enum
    // public enum RoomTypeDto {
    //     Suite, Deluxe, Regular
    // }

    // GETTERS
    // Method to get roomType, returns roomType
    // public RoomTypeDto getRoomType() {
    //     return this.roomType;
    // }

    public int getRoomId() {
        return this.roomId;
    }

    // Method to get bedType, returns bedType
    // public BedTypeDto getBedType() {
    //     return this.bedType;
    // }

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

    // Method to get hotel, returns hotel
    // public HotelDto getHotel() {
    //     return this.hotel;
    // }

}