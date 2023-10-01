package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class DayOfWeek {

    public enum DayOfWeeks {Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday};
        
    private DayOfWeeks dayOfWeek;

    public DayOfWeek(DayOfWeeks dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    //Getters
    public DayOfWeeks getDayOfWeek(){
        return this.dayOfWeek;
    }

    //Setters
   public boolean setDayOfWeek(DayOfWeeks dayOfWeek){
        this.dayOfWeek = dayOfWeek;
        return true;
    }

    public void delete(){
        
    }

}