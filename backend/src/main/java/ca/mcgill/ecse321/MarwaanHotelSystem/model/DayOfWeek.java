package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class DayOfWeek {

    public enum DayOfWeek {Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday};
        
    private DayOfWeek dayOfWeek;

    public DayOfWeek(DayOfWeek dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    //Getters
    public DayOfWeek getDayOfWeek{
        return this.dayOfWeek;
    }

    //Setters
   public boolean setDayOfWeek(DayOfWeek dayOfWeek){
        this.dayOfWeek = dayOfWeek;
        return true;
    }

    public void delete(){
        
    }

}