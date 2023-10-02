package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class RoomType {

    public enum RoomTypes {
        Suite, Deluxe, Regular
    };

    private RoomTypes roomType;

    public RoomType(RoomTypes roomType) {
        this.roomType = roomType;
    }

    // Getters
    public RoomTypes getRoomType() {
        return this.roomType;
    }

    // Setters
    public boolean setRoomType(RoomTypes roomType) {
        this.roomType = roomType;
        return true;
    }

    public void delete() {

    }

}