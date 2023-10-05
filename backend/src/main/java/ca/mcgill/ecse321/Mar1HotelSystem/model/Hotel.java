// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Hotel {

    @OneToOne
    private HotelSchedule hotelSchedule;
    private List<Room> rooms;
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    public Hotel(HotelSchedule hotelSchedule, Mar1HotelSystemApplication mar1HotelSystemApplication) {
        rooms = new ArrayList<Room>();
        if (setHotelSchedule(hotelSchedule) == false) {
            throw new RuntimeException("Need an hotelSchedule class to be instatiated; need an Hotel Schedule");
        }

        if (setMar1HotelSystemApplication(mar1HotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to mar1HotelSystemApplication");
        }

    }

    // Getters
    public HotelSchedule getHotelSchedule() {
        return this.hotelSchedule;
    }

    // Setters
    public boolean setHotelSchedule(HotelSchedule hotelSchedule) {
        if (hotelSchedule != null) {
            this.hotelSchedule = hotelSchedule;
            return true;
        } else {
            return false;
        }
    }

    // For Composition
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
            existingMar1HotelSystemApplication.removeHotel(this);
            return false;
        }
        mar1HotelSystemApplication.addHotel(this);
        return true;
    }

    public void delete() {
        this.hotelSchedule = null;
        while (this.rooms.size() > 0) {
            Room room = this.rooms.get(this.rooms.size() - 1);
            room.delete();
            this.rooms.remove(room);
        }
        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeHotel(this);
        }
    }

}