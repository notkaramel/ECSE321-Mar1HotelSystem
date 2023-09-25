**Customer stories**

**Requirement:** The MHS system shall allow a customer with or without and account to book a specific room type and pay
for their room booking.

**Primary steps**
1. The system displays 2 calendars with all the dates that the hotel is open along with a dropbox for the check-in date,
the check-out date and the amount of customer/guests respectively. At the bottom of the page the system also displays
a "confirm" button.
2. The customer/guest selects the check-in date, the check-out date, and enters the total amount of customers/guests
using the room. Once the customer/guest validates the entered info, he clicks the "confirm" button.
3. The system displays a list of rooms number available between the dates and the number of customers/guests entered. It also
displays the price of each room (along with a picture of said room?) and a short description for the room.
4. The customer/guest selects the room he wants by clicking on the name(/picture) of the room.

**Alternative/Exceptional steps**
2. 
   1. The system times out.
       1. The system displays a timeout error message along with an "ok" button.
       2. The customer presses the "ok" button and returns to step 1.
   2. The customer/guest selects a check-in date after the check-out date.
      1. The system displays an error stating: "The check-in date cannot be after the check-out date" along with an 
         "ok" button.
      2. The customer presses the "ok" button and returns to step 1.

