package ca.mcgill.ecse321.Mar1HotelSystem.model;

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

    // SETTERS 
    // Method to set roomType, returns true if roomType set
    public boolean setRoomType(RoomTypeDto roomType) {
        this.roomType = roomType;
        return true;
    }

    // Method to set bedType, returns true if bedType set
    public boolean setBedType(BedTypeDto bedType) {
        this.bedType = bedType;
        return true;
    }

    // Method to set isAvailable, returns true if isAvailable set
    public boolean setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
        return true;
    }

    // Method to set pricePerNight, returns true if pricePerNight set
    public boolean setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
        return true;
    }

    // Method to set maxCapacity, returns true if macCapacity set
    public boolean setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        return true;
    }

    // Method to set hotel, returns true if hotel set
    public boolean setHotel(HotelDto hotel) {
        if (hotel == null) {
            return false;
        }
        if (hotel.getNumberOfRooms() >= Hotel.maximumNumberOfRooms()) {
            return false;
        }

        HotelDto existingHotel = this.hotel;
        this.hotel = hotel;
        if (existingHotel != null && existingHotel.equals(hotel) == false) {
            if (existingHotel.removeRoom(this) == false) {
                this.hotel = existingHotel;
                return false;
            }

        }

        this.hotel.addRoom(this);
        return true;

    }

}