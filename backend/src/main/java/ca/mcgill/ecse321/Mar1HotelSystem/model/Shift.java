// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shiftId;
    
    private Date date;
    private int startTime;
    private int endTime;
    @ManyToOne
    private Employee employee;

    public Shift(){

    }
    // Shift constructor requiring employee, date, startTime, endTime
    public Shift(Employee employee, Date date, int startTime, int endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        if (setEmployee(employee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }
    }

    // Getters
    // Method to get employee, returns employee
    public Employee getEmployee() {
        return this.employee;
    }

    // Method to get date, returns date
    public Date getDate() {
        return this.date;
    }

    // Method to get startTime, returns startTime
    public int getStartTime() {
        return this.startTime;
    }

    // Method to get endTime, returns endTime
    public int getEndTime() {
        return this.endTime;
    }

    // Setters
    // Method to set date, returns true if date set
    public boolean setDate(Date date) {
        this.date = date;
        return true;
    }

    // Method to set startTime, returns true if startTime set
    public boolean setStartTime(int startTime) {
        this.startTime = startTime;
        return true;
    }

    // Method to set endTime, returns true if endTime set
    public boolean setEndTime(int endTime) {
        this.endTime = endTime;
        return true;
    }

    // Method to set employee, returns true if employee set
    public boolean setEmployee(Employee employee) {
        if (employee != null) {
            this.employee = employee;
            return true;
        } else {
            return false;
        }
    }

    public int getShiftId() {
        return this.shiftId;
    }

    public void delete() {
    }
    
}