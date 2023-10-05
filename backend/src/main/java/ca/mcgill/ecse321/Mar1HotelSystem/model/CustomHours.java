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

    public Mar1HotelSystemApplication getMar1HotelSystemApplication() {
        return mar1HotelSystemApplication;
    }

    protected void clear_mar1HotelSystemApplication() {
        mar1HotelSystemApplication = null;
    }

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

    public void delete() {
        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeCustomHours(this);
        }

    }

}