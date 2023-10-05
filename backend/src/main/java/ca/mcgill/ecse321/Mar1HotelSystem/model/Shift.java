package ca.mcgill.ecse321.Mar1HotelSystem.model;

import ca.mcgill.ecse321.Mar1HotelSystem.Mar1HotelSystemApplication;
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
    private Mar1HotelSystemApplication mar1HotelSystemApplication;

    public Shift(Employee employee, Date date, int startTime, int endTime,
            Mar1HotelSystemApplication mar1HotelSystemApplication) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        if (setEmployee(employee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }

        if (setMar1HotelSystemApplication(mar1HotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to mar1HotelSystemApplication");
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

    public Mar1HotelSystemApplication getMar1HotelSystemApplication() {
        return mar1HotelSystemApplication;
    }

    protected void clear_mar1HotelSystemApplication() {
        mar1HotelSystemApplication = null;
    }

    public boolean setMar1HotelSystemApplication(Mar1HotelSystemApplication mar1HotelSystemApplication) {
        if (mar1HotelSystemApplication == null) {
            return false;
        }

        Mar1HotelSystemApplication existingMar1HotelSystemApplication = this.mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = mar1HotelSystemApplication;
        if (existingMar1HotelSystemApplication != null
                && !existingMar1HotelSystemApplication.equals(mar1HotelSystemApplication)) {
            existingMar1HotelSystemApplication.removeShift(this);
            return false;
        }
        mar1HotelSystemApplication.addShift(this);
        return true;
    }

    public void delete() {
        Mar1HotelSystemApplication placeholderMar1HotelSystemApplication = mar1HotelSystemApplication;
        this.mar1HotelSystemApplication = null;
        if (placeholderMar1HotelSystemApplication != null) {
            placeholderMar1HotelSystemApplication.removeShift(this);
        }
        this.employee = null;
    }
}