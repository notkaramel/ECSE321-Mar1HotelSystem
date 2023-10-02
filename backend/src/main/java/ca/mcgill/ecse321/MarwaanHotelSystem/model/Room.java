package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;

public class Room {

    // Defining Variables
    RoomType roomType;
    BedType bedType;
    boolean isAvailable;
    int pricePerNight;
    int maxCapacity;
    private Hotel hotel;
    private MarwaanHotelSystemApplication marwaanHotelSystemApplication;

    public Room(RoomType roomType, BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity, Hotel hotel, MarwaanHotelSystemApplication marwaanHotelSystemApplication){
        this.roomType = roomType;
        this.bedType = bedType;
        this.isAvailable = isAvailable;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
        if (setMarwaanHotelSystemApplication(marwaanHotelSystemApplication) == false){
            throw new RuntimeException("Unable to create account due to marwaanHotelSystemApplication");
          }

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

    public boolean setHotel(Hotel hotel){
        if(hotel == null){
            return false;
        }
        if(hotel.getNumberOfRooms() >= Hotel.maximumNumberOfRooms()){
            return false;
        }

        Hotel existingHotel = this.hotel;
        this.hotel = hotel;
        if(existingHotel != null && existingHotel.equals(hotel) == false){
            if(existingHotel.removeRoom(this) == false){
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

    public Hotel getHotel(){
        return this.hotel;
    }

    public MarwaanHotelSystemApplication getMarwaanHotelSystemApplication()
    {
      return marwaanHotelSystemApplication;
    }
   
    protected void clear_marwaanHotelSystemApplication()
    {
      marwaanHotelSystemApplication = null;
    }
   
   
    public boolean setMarwaanHotelSystemApplication(MarwaanHotelSystemApplication marwaanHotelSystemApplication)
    {
      if (marwaanHotelSystemApplication == null)
      {
        return false;
      }
  
      MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = this.marwaanHotelSystemApplication;
      this.marwaanHotelSystemApplication = marwaanHotelSystemApplication;
      if (existingMarwaanHotelSystemApplication != null && !existingMarwaanHotelSystemApplication.equals(marwaanHotelSystemApplication))
      {
        existingMarwaanHotelSystemApplication.removeRoom(this);
        return false;
      }
      marwaanHotelSystemApplication.addRoom(this);
      return true;
    }

    public void delete(){
        Hotel hotel = this.hotel;
        this.hotel = null;
        if(hotel != null){
        hotel.removeRoom(this);
        }
        MarwaanHotelSystemApplication placeholderMarwaanHotelSystemApplication = marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = null;
        if(placeholderMarwaanHotelSystemApplication != null)
        {
        placeholderMarwaanHotelSystemApplication.removeRoom(this);
        }
    }
}