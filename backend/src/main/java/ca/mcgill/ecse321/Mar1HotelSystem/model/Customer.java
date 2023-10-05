// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import jakarta.persistence.Entity;

@Entity
public class Customer extends Account {
    // Customer constructor requiring firstName, lastName, email, phoneNumber, password and
    // mar1HotelSystemApplication
    public Customer(String firstName, String lastName, String email, int phoneNumber, String password,
            Mar1HotelSystemApplication mar1HotelSystemApplication) {
        super(firstName, lastName, email, phoneNumber, password, mar1HotelSystemApplication);
    }

    // Method to delete
    public void delete() {
        super.delete();
    }

}