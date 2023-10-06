// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.Entity;

@Entity
public class Customer extends Account {
    // Default constructor
    public Customer() {
        super(null, null, null, 0, null);
    }

    // Customer constructor requiring firstName, lastName, email, phoneNumber, password
    public Customer(String firstName, String lastName, String email, int phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber, password);
    }

    // Method to delete
    public void delete() {
        super.delete();
    }

}