// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class Room {

    // Defining Variables
    @Id
    private int roomId;

    @Enumerated(EnumType.STRING)
    RoomType roomType;

    @Enumerated(EnumType.STRING)
    BedType bedType;
    boolean isAvailable;
    int pricePerNight;
    int maxCapacity;
    @ManyToOne
    private Hotel hotel;

    public Room(RoomType roomType, BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity, Hotel hotel) {
        this.roomType = roomType;
        this.bedType = bedType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
    }

    public enum BedType {
        Queen, King, Doubles
    }

    public enum RoomType {
        Suite, Deluxe, Regular
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

    public boolean setHotel(Hotel hotel) {
        if (hotel == null) {
            return false;
        }
        if (hotel.getNumberOfRooms() >= Hotel.maximumNumberOfRooms()) {
            return false;
        }

        Hotel existingHotel = this.hotel;
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

    public Hotel getHotel() {
        return this.hotel;
    }

    public void delete() {
        Hotel hotel = this.hotel;
        this.hotel = null;
        if (hotel != null) {
            hotel.removeRoom(this);
        }
    }
}