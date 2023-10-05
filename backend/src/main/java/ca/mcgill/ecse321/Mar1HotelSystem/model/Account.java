// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import jakarta.persistence.Entity;

@Entity
public abstract class Account extends User {

    // Defining Variables
    private String password;

    // Account Constructor requiring firstName, lastName, email, phoneNUmber,
    // password and mar1HotelSystemApplication
    public Account(String firstName, String lastName, String email, int phoneNumber, String password,
            Mar1HotelSystemApplication marw1HotelSystemApplication) {
        super(firstName, lastName, email, phoneNumber, marw1HotelSystemApplication);
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