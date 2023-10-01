package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class BedType{
    
    private String queen;
    private String king;
    private String doubles;

    public BedType(String queen, String king, String doubles){
        this.queen = queen;
        this.king = king;
        this.doubles = doubles;
    }

    //Getters
    public String getQueen(){
            return this.queen;
        }

    public String getKing(){
            return this.king;
        }

    public String getDouble(){
            return this.doubles;
        }

    //Setters
    public boolean setQueen(String queen){
        this.queen = queen;
        return true;
    }

    public boolean setKing(String king){
        this.king = king;
        return true;
    }

    public boolean setDouble(String doubles){
        this.doubles = doubles;
        return true;
    }

    public void delete(){
        
    }
}