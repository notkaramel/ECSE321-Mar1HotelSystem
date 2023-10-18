package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class EmployeeDto extends AccountDto {
    // VARIABLES
    private int hoursWorked;

    // CONSTRUCTORS
    public EmployeeDto() {
        super();
    }
    // Employee constructor requiring requiring firstName, lastName, email, phoneNumber, password and hoursWorked
    public EmployeeDto(String firstName, String lastName, String email, int phoneNumber, String password,
            int hoursWorked) {
        super(firstName, lastName, email, phoneNumber, password);
        this.hoursWorked = hoursWorked;
    }

    // GETTERS
    // Method to get hoursWorked, returns hoursWorked
    public int getHoursWorked() {
        return this.hoursWorked;
    }
    
    // SETTERS 
    // Method to set hoursWorked, returns true if hoursWorked set
    public boolean setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
        return true;
    }

    
}
