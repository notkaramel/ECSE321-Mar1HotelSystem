package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class RoomDto {
    
    // VARIABLES
    private int roomId;
    private RoomTypeDto roomType;
    private BedTypeDto bedType;
    private boolean isAvailable;
    private int pricePerNight;
    private int maxCapacity;
    private HotelDto hotel;

    // CONSTRUCTORS
    public RoomDto() {
    }

    public RoomDto(RoomTypeDto roomType, BedTypeDto bedType, boolean isAvailable, int pricePerNight, int maxCapacity,
            HotelDto hotel) {
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
    public RoomTypeDto getRoomType() {
        return this.roomType;
    }

    public int getRoomId() {
        return this.roomId;
    }

    // Method to get bedType, returns bedType
    public BedTypeDto getBedType() {
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