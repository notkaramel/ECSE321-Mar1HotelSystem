**Customer stories**

**Requirement:** The MHS system shall allow a customer with or without and account to book a specific room type and pay
for their room booking.

**Primary steps**
1. The system displays 2 calendars with all the dates that the hotel is open along with a dropbox for the check-in date,
the check-out date and the amount of customer/guests respectively. At the bottom of the page the system also displays
a "confirm" button.
2. The customer/guest selects the check-in date, the check-out date, and enters the total amount of customers/guests
using the room. Once the customer/guest validates the entered info, he clicks the "confirm" button.
3. The system displays a list of rooms number available between the dates and the number of customers/guests entered.
It also displays a button tittled: "change information" at the bottom of the page.
It also displays the price of each room (along with a picture of said room?) and a short description for the room.
4. The customer/guest selects the room he wants by clicking on the name(/picture) of the room.
5. The system checks if the room is still available, then displays the room number, the room type, the room description 
(picture?), and the price. There are also 2 button at the end: "confirm" and "cancel".
6. The customer/guest clicks "confirm" to confirm the choice of room.
7. The system displays the payment panel with a text box for the credit card number, the email (automatically filled if
it is a customer instead of a guest), and 2 buttons: "confirm" and "cancel".
8. The customer/guest fills out the email and credit card number and clicks on "confirm" to confirm their booking.
9. The system books the room to the associated customer/guest via the email address.


**Alternative/Exceptional steps**

2.1. The system times out.
1. The system displays a timeout error message along with an "ok" button.
2. The customer presses the "ok" button and returns to step 1.

2.2. The customer/guest selects a check-in date after the check-out date.
1. The system displays an error stating: "The check-in date cannot be after the check-out date" along with an "ok" 
button.
2. The customer/guest presses the "ok" button and returns to step 1.

4.1. The system times out.
1. The system displays a timeout error message along with an "ok" button.
2. The customer/guest presses the "ok" button and returns to step 3, letting the system refresh the list of available 
rooms.

5.1 The room the customer/guest chose is no longer available.
1. The system displays an error message stating: "This room is already booked" along with an "ok" button.
2. The customer/guest presses the "ok" button and returns to step 3, letting the system refresh the list of available
rooms.

6.1. The customer/guest clicks the "cancel" button
1. The system returns the customer/guest to step 3 and refreshes the list of available rooms.

7.1. The system times out.
1. The system displays a timeout error message along with an "ok" button.
2. The customer presses the "ok" button and returns to step 1.

8.1. The credit card number entered by the customer/guest is invalid.
1. The system displays the error message stating: "The entered credit card number is erroneous" along with an "ok"
button.
2. The customer/guest clicks the "ok" button and returns to step 7 with the credit card number field cleared.

8.2.

8.3.
