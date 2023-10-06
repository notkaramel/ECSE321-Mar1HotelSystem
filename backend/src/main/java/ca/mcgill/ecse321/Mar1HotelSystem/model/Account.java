// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.*;

/**
 * The Account superclass for all registered users of the system (Customer,
 * Employee, Manager)
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 */

@Entity
public class Account extends GeneralUser {

    // Defining Variables
    private String password;

    public Account() {
        super();
    }

    // Account constructor requiring firstName, lastName, email, phoneNumber,
    // password
    public Account(String firstName, String lastName, String email, int phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber);
        this.password = password;
    }

    // Method to set password, returns true if password successfully set
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