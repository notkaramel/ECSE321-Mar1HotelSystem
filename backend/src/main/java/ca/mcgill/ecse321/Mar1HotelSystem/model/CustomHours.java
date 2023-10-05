// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import java.util.Date;
import jakarta.persistence.Entity;

@Entity
public class CustomHours {
    // Defining variables
    private Date date;
    private int openingHour;
    private int closingHour;
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    // CustomHours constructor requiring date, openingHour, closingHour, and mar1HotelSystemApplication
    public CustomHours(Date date, int openingHour, int closingHour,
            Mar1HotelSystemApplication mar1HotelSystemApplication) {
        this.date = date;
        this.openingHour = openingHour;
        this.closingHour = closingHour;

        if (setMar1HotelSystemApplication(mar1HotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to mar1HotelSystemApplication");
        }

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

    // Method to get Mar1SystemAppliction, returns Mar1SystemAppliction
    public Mar1HotelSystemApplication getMar1HotelSystemApplication() {
        return mar1HotelSystemApplication;
    }

    // Method to clear Mar1SystemAppliction
    protected void clear_mar1HotelSystemApplication() {
        mar1HotelSystemApplication = null;
    }

    // Method to set Mar1SystemAppliction, returns true if Mar1SystemAppliction is set, false otherwise
    public boolean setMar1HotelSystemApplication(Mar1HotelSystemApplication mar1HotelSystemApplication) {
        if (mar1HotelSystemApplication == null) {
            return false;
        }

        Mar1HotelSystemApplication existingMar1HotelSystemApplication = this.mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = mar1HotelSystemApplication;
        if (existingMar1HotelSystemApplication != null
                && !existingMar1HotelSystemApplication.equals(mar1HotelSystemApplication)) {
            existingMar1HotelSystemApplication.removeCustomHours(this);
            return false;
        }
        mar1HotelSystemApplication.addCustomHours(this);
        return true;
    }

    // Method to delete
    public void delete() {
        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeCustomHours(this);
        }

    }

}