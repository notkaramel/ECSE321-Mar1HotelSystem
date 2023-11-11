package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.BedType;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Room.RoomType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

/**
 * A response that contains all the information about a room.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public class RoomRequestDto {

    // VARIABLES
    @NotBlank(message = "room_type must be Deluxe, Regular, or Suite")
    private Room.RoomType roomType;

    @NotBlank(message = "bed_type must be Queen, King, or Doubles")
    private Room.BedType bedType;

    @NotBlank
    private boolean isAvailable;
    @Digits(fraction = 2, integer = 10)
    private int pricePerNight;
    @DecimalMax("20")
    private int maxCapacity;

    public RoomRequestDto() {
    }

    public RoomRequestDto(Room.RoomType roomType, Room.BedType bedType, boolean isAvailable, int pricePerNight,
            int maxCapacity) {
        this.roomType = roomType;
        this.bedType = bedType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
    }

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