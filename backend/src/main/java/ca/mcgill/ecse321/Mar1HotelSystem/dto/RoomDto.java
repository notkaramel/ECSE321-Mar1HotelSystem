package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;

public class RoomDto {
    
    // VARIABLES
    private int roomId;
    private RoomType roomType;
    private BedType bedType;
    private boolean isAvailable;
    private int pricePerNight;
    private int maxCapacity;
    private HotelDto hotel;

    // CONSTRUCTORS
    public RoomDto() {
    }

    public RoomDto(RoomType roomType, BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity,
            HotelDto hotel) {
        this.roomType = roomType;
        this.bedType = bedType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
        this.hotel = hotel;
    }


    // GETTERS
    // Method to get roomType, returns roomType
    public RoomType getRoomType() {
        return this.roomType;
    }

    public int getRoomId() {
        return this.roomId;
    }

    // Method to get bedType, returns bedType
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

    // Method to get hotel, returns hotel
    public HotelDto getHotel() {
        return this.hotel;
    }

}