package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import jakarta.persistence.Entity;

@Entity
public class Manager extends Account {

    public Manager(String firstName, String lastName, String email, int phoneNumber, String password,
            Mar1HotelSystemApplication mar1HotelSystemApplication) {
        super(firstName, lastName, email, phoneNumber, password, mar1HotelSystemApplication);
    }

    public void delete() {
        super.delete();
    }
}