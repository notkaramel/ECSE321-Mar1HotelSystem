package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class Room {

    // Defining Variables
    RoomType roomType;
    BedType bedType;
    boolean isAvailable;
    int pricePerNight;
    int maxCapacity;

    public Room(RoomType roomType, BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity){
        this.roomType = roomType;
        this.bedType = bedType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
    }

    public boolean setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return true;
    }

    public boolean setBedType(BedType bedType) {
        this.bedType = bedType;
        return true;
    }

    public boolean setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
        return true;
    }

    public boolean setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
        return true;
    }

    public boolean setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        return true;
    }

    // Getters
    public RoomType getRoomType() {
        return this.roomType;
    }

    public BedType getBedType() {
        return this.bedType;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public int getPricePerNight() {
        return this.pricePerNight;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

}