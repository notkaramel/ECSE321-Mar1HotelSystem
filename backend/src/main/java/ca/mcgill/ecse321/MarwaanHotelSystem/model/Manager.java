package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;

public class Manager extends Account {

    public Manager(String firstName, String lastName, String email, int phoneNumber, String password,
            MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        super(firstName, lastName, email, phoneNumber, password, marwaanHotelSystemApplication);
    }

    public void delete() {
        super.delete();
    }
}