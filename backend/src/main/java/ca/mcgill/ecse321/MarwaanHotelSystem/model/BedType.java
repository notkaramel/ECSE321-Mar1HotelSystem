package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class BedType{
    

    public enum BedType {Queen, King, Doubles};
        
    private BedType bedType;

    public BedType(BedType bedType){
        this.bedType = bedType;
    }

    //Getters
    public BedType getBedType{
        return this.bedType;
    }

    //Setters
   public boolean setBedType(BedType bedType){
        this.bedType = bedType;
        return true;
    }

    public void delete(){
        
    }
}