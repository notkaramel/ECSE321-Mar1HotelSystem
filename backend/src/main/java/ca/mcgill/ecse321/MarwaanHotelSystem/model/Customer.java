package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;
import jakarta.persistence.Entity;
@Entity
public class Customer extends Account {

    public Customer(String firstName, String lastName, String email, int phoneNumber, String password,
            MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        super(firstName, lastName, email, phoneNumber, password, marwaanHotelSystemApplication);
    }

    public void delete() {
        super.delete();
    }

}