package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

public class RoomDto {
    
    // VARIABLES
    private int roomId;
    private Room.RoomType roomType;
    private Room.BedType bedType;
    private boolean isAvailable;
    private int pricePerNight;
    private int maxCapacity;
    private HotelDto hotel;

    // CONSTRUCTORS
    public RoomDto() {
    }

    public RoomDto(
            int roomId,
            Room.RoomType roomType,
            Room.BedType bedType,
            boolean isAvailable,
            int pricePerNight,
            int maxCapacity,
            HotelDto hotel) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.bedType = bedType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
        this.hotel = hotel;
    }

    // ENUM
    // BedType enum
    public enum BedTypeDto {
        Queen, King, Doubles
    }

    // RoomType enum
    public enum RoomTypeDto {
        Suite, Deluxe, Regular
    }

    // GETTERS
    // Method to get roomType, returns roomType
    public Room.RoomType getRoomType() {
        return this.roomType;
    }

    public int getRoomId() {
        return this.roomId;
    }

    // Method to get bedType, returns bedType
    public Room.BedType getBedType() {
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

    // Method to get hotel, returns hotel
    public HotelDto getHotel() {
        return this.hotel;
    }

}