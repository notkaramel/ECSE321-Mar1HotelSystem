package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;
import jakarta.persistence.Entity;
@Entity
public abstract class Account extends User {

    // Defining Variables
    private String password;

    // Account Constructor requiring firstName, lastName, email, phoneNUmber, password and marwaanHotelSystemApplication
    public Account(String firstName, String lastName, String email, int phoneNumber, String password,
            MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        super(firstName, lastName, email, phoneNumber, marwaanHotelSystemApplication);
        this.password = password;
    }

    // Method to set password, returns true if password succesfully set
    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

    // Method to get password, returns password
    public String getPassword() {
        return this.password;
    }

    // Delete Method
    public void delete() {
        super.delete();
    }

}