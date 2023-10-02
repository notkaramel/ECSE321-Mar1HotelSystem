package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;

public class Account extends User {

    // Defining Variables
    private String password;
   

    public Account(String firstName, String lastName, String email, int phoneNumber, String password, MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        super(firstName, lastName, email, phoneNumber, marwaanHotelSystemApplication);
        this.password = password;
    }

    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

    public String getPassword() {
        return this.password;
    }

   

    public void delete() {
        super.delete();
    }

}