package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class Employee extends Account {

    // Defining Variables
    int hoursWorked;

    public Employee(String firstName, String lastName, String email, int phoneNumber, String password,
            int hoursWorked) {
        super(firstName, lastName, email, phoneNumber, password);
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