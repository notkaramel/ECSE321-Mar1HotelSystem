package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;

public class Employee extends Account {

    // Defining Variables
    int hoursWorked;

    public Employee(String firstName, String lastName, String email, int phoneNumber, String password,
            int hoursWorked, MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        super(firstName, lastName, email, phoneNumber, password, marwaanHotelSystemApplication);
        this.hoursWorked = hoursWorked;
    }

    public boolean setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
        return true;
    }

    public int getHoursWorked() {
        return this.hoursWorked;
    }

    public void delete() {
        super.delete();
    }

}