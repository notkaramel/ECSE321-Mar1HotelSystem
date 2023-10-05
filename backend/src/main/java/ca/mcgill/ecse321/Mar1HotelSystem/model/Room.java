// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    public Room(RoomType roomType, BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity,
            Hotel hotel, Mar1HotelSystemApplication mar1HotelSystemApplication) {
        this.roomType = roomType;
        this.bedType = bedType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
        if (setMar1HotelSystemApplication(mar1HotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to mar1HotelSystemApplication");
        }

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

    public Mar1HotelSystemApplication getMar1HotelSystemApplication() {
        return mar1HotelSystemApplication;
    }

    protected void clear_mar1HotelSystemApplication() {
        mar1HotelSystemApplication = null;
    }

    public boolean setMar1HotelSystemApplication(Mar1HotelSystemApplication mar1HotelSystemApplication) {
        if (mar1HotelSystemApplication == null) {
            return false;
        }

        Mar1HotelSystemApplication existingMar1HotelSystemApplication = this.mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = mar1HotelSystemApplication;
        if (existingMar1HotelSystemApplication != null
                && !existingMar1HotelSystemApplication.equals(mar1HotelSystemApplication)) {
            existingMar1HotelSystemApplication.removeRoom(this);
            return false;
        }
        mar1HotelSystemApplication.addRoom(this);
        return true;
    }

    public void delete() {
        Hotel hotel = this.hotel;
        this.hotel = null;
        if (hotel != null) {
            hotel.removeRoom(this);
        }
        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeRoom(this);
        }
    }
}