package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    // Defining variables

    private String firstName;
    private String lastName;
    @Id
    private String email;
    private int phoneNumber;

    public User(String firstName, String lastName, String email, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    // Setters
    public boolean setFistName(String firstName) {
        this.firstName = firstName;
        return true;
    }

    public boolean setLastName(String lastName) {
        this.lastName = lastName;
        return true;
    }

    public boolean setEmail(String email) {
        this.email = email;
        return true;
    }

    public boolean setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return true;
    }

    public void delete() {
    }
}