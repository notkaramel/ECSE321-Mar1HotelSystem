package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class RoomType {

    public enum RoomType {Suite, Deluxe, Regular};
        
    private RoomType roomType;

    public RoomType(RoomType roomType){
        this.roomType = roomType;
    }

    //Getters
    public RoomType getRoomType{
        return this.roomType;
    }

    //Setters
   public boolean setRoomType(RoomType roomType){
        this.roomType = roomType;
        return true;
    }

    public void delete(){
        
    }

}