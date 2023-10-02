package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Shift {

    private String date;
    private String startTime;
    private String endTime;
    private Employee employee;

    public Shift(Employee employee, String date, String startTime, String endTime) {
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

    public String getDate() {
        return this.date;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    // Setters

    public boolean setDate(String date) {
        this.date = date;
        return true;
    }

    public boolean setStartTime(String startTime) {
        this.startTime = startTime;
        return true;
    }

    public boolean setEndTime(String endTime) {
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
        this.employee = null;
    }
}