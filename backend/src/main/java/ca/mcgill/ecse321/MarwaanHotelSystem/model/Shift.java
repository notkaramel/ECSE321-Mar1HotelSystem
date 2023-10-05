package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import java.util.Date;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;
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
    private MarwaanHotelSystemApplication marwaanHotelSystemApplication;

    public Shift(Employee employee, Date date, int startTime, int endTime,
            MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        if (setEmployee(employee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }

        if (setMarwaanHotelSystemApplication(marwaanHotelSystemApplication) == false) {
            throw new RuntimeException("Unable to create account due to marwaanHotelSystemApplication");
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

    public MarwaanHotelSystemApplication getMarwaanHotelSystemApplication() {
        return marwaanHotelSystemApplication;
    }

    protected void clear_marwaanHotelSystemApplication() {
        marwaanHotelSystemApplication = null;
    }

    public boolean setMarwaanHotelSystemApplication(MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        if (marwaanHotelSystemApplication == null) {
            return false;
        }

        MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = this.marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = marwaanHotelSystemApplication;
        if (existingMarwaanHotelSystemApplication != null
                && !existingMarwaanHotelSystemApplication.equals(marwaanHotelSystemApplication)) {
            existingMarwaanHotelSystemApplication.removeShift(this);
            return false;
        }
        marwaanHotelSystemApplication.addShift(this);
        return true;
    }

    public void delete() {
        MarwaanHotelSystemApplication placeholderMarwaanHotelSystemApplication = marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = null;
        if (placeholderMarwaanHotelSystemApplication != null) {
            placeholderMarwaanHotelSystemApplication.removeShift(this);
        }
        this.employee = null;
    }
}