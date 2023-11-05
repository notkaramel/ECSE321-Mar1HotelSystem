// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

/**
 * The Room class for all rooms of the system.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 * @author ZiXu Liu (@ARandomPi) - JPA Annotations
 */

@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
public class Room {
    // Defining Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    // Default constructor
    public Room() {
    }

    public Room(RoomType roomType, BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity,
            Hotel hotel) {
        this.roomType = roomType;
        this.bedType = bedType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
        this.hotel = hotel;
    }

    // BedType enum
    public enum BedType {
        Queen, King, Doubles
    }

    // RoomType enum
    public enum RoomType {
        Suite, Deluxe, Regular
    }

    // Setters
    // Method to set roomType, returns true if roomType set
    public boolean setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return true;
    }

    // Method to set bedType, returns true if bedType set
    public boolean setBedType(BedType bedType) {
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

    public boolean setRoomId(int roomId) {
        this.roomId = roomId;
        return true;
    }

    // Getters
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