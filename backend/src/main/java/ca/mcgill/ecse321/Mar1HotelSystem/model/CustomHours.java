// Umple was used a guide and generated some code in this project
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
    // Method to get date, returns date
    public Date getDate() {
        return this.date;
    }

    // Method to get OpeningHour, returns OpeningHour
    public int getOpeningHour() {
        return this.openingHour;
    }

     // Method to get ClosingHour, returns ClosingHour
    public int getClosingHour() {
        return this.closingHour;
    }

    // Setters
    // Method to set date, returns true if date set
    public boolean setDate(Date date) {
        this.date = date;
        return true;
    }

    // Method to set OpeningHour, returns true if OpeningHour set
    public boolean setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
        return true;
    }

    // Method to set ClosingHour, returns true if ClosingHour set
    public boolean setClosingHour(int closingHour) {
        this.closingHour = closingHour;
        return true;
    }

    public void delete() {
    }

}