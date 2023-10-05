// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee extends Account {

    // Defining Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    int hoursWorked;

    // Employee constructor requiring requiring firstName, lastName, email, phoneNumber, password, hoursWorked and
    // mar1HotelSystemApplication
    public Employee(String firstName, String lastName, String email, int phoneNumber, String password,
            int hoursWorked, Mar1HotelSystemApplication mar1HotelSystemApplication) {
        super(firstName, lastName, email, phoneNumber, password, mar1HotelSystemApplication);
        this.hoursWorked = hoursWorked;
    }

    // Method to set hoursWorked, returns true if hoursWorked set
    public boolean setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
        return true;
    }

    // Method to get hoursWorked, returns hoursWorked
    public int getHoursWorked() {
        return this.hoursWorked;
    }

    // Method to delete
    public void delete() {
        super.delete();
    }

}