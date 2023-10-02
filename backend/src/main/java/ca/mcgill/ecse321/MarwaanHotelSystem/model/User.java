package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;

public class User {
    // Defining variables
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private MarwaanHotelSystemApplication marwaanHotelSystemApplication;

    public User(String firstName, String lastName, String email, int phoneNumber, MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        if (setMarwaanHotelSystemApplication(marwaanHotelSystemApplication) == false){
            throw new RuntimeException("Unable to create account due to marwaanHotelSystemApplication");
          }
    }

    // Getters
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    // Setters
    public boolean setFistName(String firstName) {
        this.firstName = firstName;
        return true;
    }

    public boolean setLastName(String lastName) {
        this.lastName = lastName;
        return true;
    }

    public boolean setEmail(String email) {
        this.email = email;
        return true;
    }

    public boolean setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return true;
    }

    public MarwaanHotelSystemApplication getMarwaanHotelSystemApplication()
  {
    return marwaanHotelSystemApplication;
  }
 
  protected void clear_marwaanHotelSystemApplication()
  {
    marwaanHotelSystemApplication = null;
  }
 
 
  public boolean setMarwaanHotelSystemApplication(MarwaanHotelSystemApplication marwaanHotelSystemApplication)
  {
    if (marwaanHotelSystemApplication == null)
    {
      return false;
    }

    MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = this.marwaanHotelSystemApplication;
    this.marwaanHotelSystemApplication = marwaanHotelSystemApplication;
    if (existingMarwaanHotelSystemApplication != null && !existingMarwaanHotelSystemApplication.equals(marwaanHotelSystemApplication))
    {
      existingMarwaanHotelSystemApplication.removeUser(this);
      return false;
    }
    marwaanHotelSystemApplication.addUser(this);
    return true;
  }

    public void delete() {
        MarwaanHotelSystemApplication placeholderMarwaanHotelSystemApplication = marwaanHotelSystemApplication;
        this.marwaanHotelSystemApplication = null;
        if(placeholderMarwaanHotelSystemApplication != null)
        {
        placeholderMarwaanHotelSystemApplication.removeUser(this);
        }
    }
}