package ca.mcgill.ecse321.Mar1HotelSystem.model;

import java.util.Date;
import jakarta.persistence.Entity;

@Entity
public class CustomHours {
    // Defining variables
    private Date date;
    private int openingHour;
    private int closingHour;

    public CustomHours(Date date, int openingHour, int closingHour) {
        this.date = date;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    // Getters
    public Date getDate() {
        return this.date;
    }

    public int getOpeningHour() {
        return this.openingHour;
    }

    public int getClosingHour() {
        return this.closingHour;
    }

    // Setters
    public boolean setDate(Date date) {
        this.date = date;
        return true;
    }

    public boolean setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
        return true;
    }

    public boolean setClosingHour(int closingHour) {
        this.closingHour = closingHour;
        return true;
    }

    public void delete() {
    }

}