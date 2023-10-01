import java.util.*;

public class Employee extends Account{

//Defining Variables
//EmployeeType employeeType;
int hoursWorked;

    public Employee(String firstName, String lastName, String email, int phoneNumber, String password, int hoursWorked){ //EmployeeType employeeType){
        super(firstName, lastName, email, phoneNumber, password);
        //this.employeeType = employeeType;
        this.hourWorked = hoursWorked;
    }

    // public boolean setEmployeeType(EmployeeType employeeType){
    //     this.employeeType = employeeType;
    //     return true;
    // }

    // public EmployeeType getEmployeeType(){
    //     return this.employeeType;
    // }
    public boolean setHoursWorked(int hoursWorked){
        this.hoursWorked = hoursWorked;
        return true;
    }

    public int getHourWorked(){
        return this.hourWorked;
    }

     public void delete(){
        super.delete();
    }

}