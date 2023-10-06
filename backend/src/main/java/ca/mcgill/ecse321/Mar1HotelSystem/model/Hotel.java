// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Hotel {
    @Id
    private String name = "Mar-1 Hotel";

    @OneToOne
    @Id
    private HotelSchedule hotelSchedule;
    private List<Room> rooms;

    // Hotel constructor requiring hotelSchedule
    public Hotel(HotelSchedule hotelSchedule) {
        rooms = new ArrayList<Room>();
        if (setHotelSchedule(hotelSchedule) == false) {
            throw new RuntimeException("Need an hotelSchedule class to be instatiated; need an Hotel Schedule");
        }
    }

    // Getters
    // Method to get hotelSchedule, returns hotelSchedule
    public HotelSchedule getHotelSchedule() {
        return this.hotelSchedule;
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