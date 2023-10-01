package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class RoomType {

    private String suite;
    private String deluxe;
    private String luxury;
    private String regular;

    public RoomType(String suite, String deluxe, String luxury, String regular) {
        this.suite = suite;
        this.deluxe = deluxe;
        this.luxury = luxury;
        this.regular = regular;
    }

    // Getters
    public String getSuite() {
        return this.suite;
    }

    public String getDeluxe() {
        return this.deluxe;
    }

    public String getLuxury() {
        return this.luxury;
    }

    public String getRegular() {
        return this.regular;
    }

    // Setters
    public boolean setSuite(String suite) {
        this.suite = suite;
        return true;
    }

    public boolean setDeluxe(String deluxe) {
        this.deluxe = deluxe;
        return true;
    }

    public boolean setLuxury(String luxury) {
        this.luxury = luxury;
        return true;
    }

    public boolean setRegular(String regular) {
        this.regular = regular;
        return true;
    }

    public void delete() {

    }

}