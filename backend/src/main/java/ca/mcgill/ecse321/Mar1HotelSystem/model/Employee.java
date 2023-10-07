// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.*;
/**
 * The Employee class for all employees in the system.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 */
@Entity
public class Employee extends Account {    
    int hoursWorked;

    // Default constructor
    public Employee() {
        super(null, null, null, 0, null);
    }
    // Employee constructor requiring requiring firstName, lastName, email, phoneNumber, password and hoursWorked
    public Employee(String firstName, String lastName, String email, int phoneNumber, String password,
            int hoursWorked) {
        super(firstName, lastName, email, phoneNumber, password);
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