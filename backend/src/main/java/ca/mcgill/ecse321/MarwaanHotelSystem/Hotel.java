public class Hotel{
    
    private HotelSchedule hotelSchedule;
   

    public Hotel(HotelSchedule hotelSchedule){
        if(hotelSchedule == null || hotelSchedule == false){
            throw new RuntimeException("Need an hotelSchedule class to be instatiated; need an Hotel Schedule");
        }

    }

    //Getters
    public HotelSchedule getHotelSchedule(){
        return this.hotelSchedule;
    }

    //Setters
    public boolean setHotelSchedule(HotelSchedule hotelSchedule){
            if(hotelSchedule != null){
                this.hotelSchedule = hotelSchedule;
                return true;
            }
            else{
                return false;
            }
        }

    public void delete(){
        this.hotelSchedule = null;
    }

}