// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.*;

@Entity
public class Account extends User {

    // Defining Variables
    private String password;

    // Account constructor requiring firstName, lastName, email, phoneNumber,
    // password
    public Account(String firstName, String lastName, String email, int phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber);
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