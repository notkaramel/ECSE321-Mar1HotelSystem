package ca.mcgill.ecse321.MarwaanHotelSystem.model;

public class Employee extends Account {

    // Defining Variables
    // EmployeeType employeeType;
    int hoursWorked;

    public Employee(String firstName, String lastName, String email, int phoneNumber, String password,
            int hoursWorked) { // EmployeeType employeeType){
        super(firstName, lastName, email, phoneNumber, password);
        // this.employeeType = employeeType;
        this.hoursWorked = hoursWorked;
    }

    // public boolean setEmployeeType(EmployeeType employeeType){
    // this.employeeType = employeeType;
    // return true;
    // }

    // public EmployeeType getEmployeeType(){
    // return this.employeeType;
    // }
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