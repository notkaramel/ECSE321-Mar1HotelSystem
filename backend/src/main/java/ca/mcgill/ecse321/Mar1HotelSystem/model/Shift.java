// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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

    public Shift(Employee employee, Date date, int startTime, int endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        if (setEmployee(employee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }
    }

    // Getters
    public Employee getEmployee() {
        return this.employee;
    }

    public Date getDate() {
        return this.date;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }

    // Setters

    public boolean setDate(Date date) {
        this.date = date;
        return true;
    }

    public boolean setStartTime(int startTime) {
        this.startTime = startTime;
        return true;
    }

    public boolean setEndTime(int endTime) {
        this.endTime = endTime;
        return true;
    }

    public boolean setEmployee(Employee employee) {
        if (employee != null) {
            this.employee = employee;
            return true;
        } else {
            return false;
        }
    }

    public void delete() {
    }
}