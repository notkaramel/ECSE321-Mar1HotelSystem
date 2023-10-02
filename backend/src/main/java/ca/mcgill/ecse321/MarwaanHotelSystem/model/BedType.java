package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class BedType {

    public enum BedTypes {
        Queen, King, Doubles
    }

    private BedTypes bedType;

    public BedType(BedTypes bedType) {
        this.bedType = bedType;
    }

    // Getters
    public BedTypes getBedType() {
        return this.bedType;
    }

    // Setters
    public boolean setBedType(BedTypes bedType) {
        this.bedType = bedType;
        return true;
    }

    public void delete() {

    }
}