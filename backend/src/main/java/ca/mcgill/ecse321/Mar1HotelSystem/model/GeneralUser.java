// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.*;

/**
 * The GeneralUser class for all visiting guests of the system.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GeneralUser {
    // Defining variables
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private long phoneNumber;

    public GeneralUser() {

    }

    // Shift constructor requiring firstName, lastName, email, phoneNumber
    public GeneralUser(String firstName, String lastName, String email, long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    // Method to get firstName, returns firstName
    public String getFirstName() {
        return this.firstName;
    }

    // Method to get lastName, returns lastName
    public String getLastName() {
        return this.lastName;
    }

    // Method to get email, returns email
    public String getEmail() {
        return this.email;
    }

    // Method to get phoneNumber, returns phoneNumber
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    // Setters
    // Method to set firstName, returns true if firstName set
    public boolean setFistName(String firstName) {
        this.firstName = firstName;
        return true;
    }

    // Method to set lastName, returns true if lastName set
    public boolean setLastName(String lastName) {
        this.lastName = lastName;
        return true;
    }

    // Method to set email, returns true if email set
    public boolean setEmail(String email) {
        this.email = email;
        return true;
    }

    // Method to set phoneNumber, returns true if phoneNumber set
    public boolean setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return true;
    }

    public void delete() {
    }
}