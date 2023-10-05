package ca.mcgill.ecse321.MarwaanHotelSystem.model;

import java.util.Date;

import ca.mcgill.ecse321.MarwaanHotelSystem.MarwaanHotelSystemApplication;
import jakarta.persistence.Entity;
@Entity
public class CustomHours {
  // Defining variables
  private Date date;
  private int openingHour;
  private int closingHour;
  private MarwaanHotelSystemApplication marwaanHotelSystemApplication;

  public CustomHours(Date date, int openingHour, int closingHour,
      MarwaanHotelSystemApplication marwaanHotelSystemApplication) {
    this.date = date;
    this.openingHour = openingHour;
    this.closingHour = closingHour;

    if (setMarwaanHotelSystemApplication(marwaanHotelSystemApplication) == false) {
      throw new RuntimeException("Unable to create account due to marwaanHotelSystemApplication");
    }

  }

  // Getters
  public Date getDate() {
    return this.date;
  }

  public int getOpeningHour() {
    return this.openingHour;
  }

  public int getClosingHour() {
    return this.closingHour;
  }

  // Setters
  public boolean setDate(Date date) {
    this.date = date;
    return true;
  }

  public boolean setOpeningHour(int openingHour) {
    this.openingHour = openingHour;
    return true;
  }

  public boolean setClosingHour(int closingHour) {
    this.closingHour = closingHour;
    return true;
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
      existingMarwaanHotelSystemApplication.removeCustomHours(this);
      return false;
    }
    marwaanHotelSystemApplication.addCustomHours(this);
    return true;
  }

  public void delete() {
    MarwaanHotelSystemApplication placeholderMarwaanHotelSystemApplication = marwaanHotelSystemApplication;
    this.marwaanHotelSystemApplication = null;
    if (placeholderMarwaanHotelSystemApplication != null) {
      placeholderMarwaanHotelSystemApplication.removeCustomHours(this);
    }

  }

}