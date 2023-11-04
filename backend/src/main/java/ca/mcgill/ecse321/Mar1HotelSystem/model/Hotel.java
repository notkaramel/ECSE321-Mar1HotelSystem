// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * The Hotel class for the one-and-only hotel in the system.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 */
@Entity
public class Hotel {
    @Id
    private String hotelName = "Mar-1 Hotel";

    @OneToOne
    private HotelSchedule hotelSchedule;

    @OneToMany
    private List<Room> rooms;

    private static List<Hotel> hotelList = new ArrayList<Hotel>();

    public static boolean isExist() {
        return hotelList.size() > 0;
    }

    public static Hotel getHotel() {
        if (!isExist()) {
            Hotel.hotelList.add(new Hotel());
        }
        return hotelList.get(0);
    }

    // Default constructor
    public Hotel() {
        if (isExist() == true) {
            return;
        }
        this.hotelName = "Mar-1 Hotel";
        this.rooms = new ArrayList<Room>();
        Hotel.hotelList.add(this);
    }

    // Hotel constructor requiring hotelSchedule
    public Hotel(HotelSchedule hotelSchedule) {
        if (setHotelSchedule(hotelSchedule) == false) {
            throw new RuntimeException("Need an hotelSchedule class to be instatiated; need an Hotel Schedule");
        }
        if (isExist()) {
            setHotelSchedule(hotelSchedule);
            return;
        }
        this.hotelName = "Mar-1 Hotel";
        this.rooms = new ArrayList<Room>();
        Hotel.hotelList.add(this);
        if (setHotelSchedule(hotelSchedule) == false) {
            throw new RuntimeException("Need an hotelSchedule class to be instatiated; need an Hotel Schedule");
        }
    }

    // Getters
    // Method to get hotelSchedule, returns hotelSchedule
    public HotelSchedule getHotelSchedule() {
        return this.hotelSchedule;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    // Setters
    // Method to set hotelSchedule, returns true if hotelSchedule set
    public boolean setHotelSchedule(HotelSchedule hotelSchedule) {
        if (hotelSchedule != null) {
            this.hotelSchedule = hotelSchedule;
            return true;
        } else {
            return false;
        }
    }

    // Methods for Composition with Room
    public Room getRoom(int index) {
        Room room = this.rooms.get(index);
        return room;
    }

    public List<Room> getRooms() {
        List<Room> room = new ArrayList<Room>();
        return room;
    }

    public int getNumberOfRooms() {
        return this.rooms.size();
    }

    public boolean hasRooms() {
        if (this.rooms.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int indexOfRoom(Room room) {
        return this.rooms.indexOf(room);
    }

    public static int requiredNumberOfRooms() {
        return 70;
    }

    public static int minimumNumberOfRooms() {
        return 70;
    }

    public static int maximumNumberOfRooms() {
        return 70;
    }

    public boolean isNumberOfRoomsValid() {
        if (getNumberOfRooms() >= minimumNumberOfRooms() && getNumberOfRooms() <= maximumNumberOfRooms()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addRoom(Room room) {
        if (!isExist()) {
            return false;
        }
        if (this.rooms.contains(room)) {
            return false;
        }
        if (getNumberOfRooms() >= maximumNumberOfRooms()) {
            return false;
        }
        Hotel hotel = room.getHotel();
        if ((hotel != null && !this.equals(hotel)) && hotel.getNumberOfRooms() <= minimumNumberOfRooms()) {
            return false;
        }
        if ((hotel != null && !this.equals(hotel)) == true) {
            room.setHotel(this);
        } else {
            this.rooms.add(room);
        }
        return true;
    }

    public boolean removeRoom(Room room) {
        if (this.equals(room.getHotel())) {
            return false;
        }
        if (getNumberOfRooms() <= minimumNumberOfRooms()) {
            return false;
        }

        this.rooms.remove(room);
        return true;
    }

    public void delete() {
        this.hotelSchedule = null;
        while (this.rooms.size() > 0) {
            Room room = this.rooms.get(this.rooms.size() - 1);
            room.delete();
            this.rooms.remove(room);
        }
    }

}