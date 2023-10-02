package ca.mcgill.ecse321.MarwaanHotelSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ca.mcgill.ecse321.MarwaanHotelSystem.model.*;

@SpringBootApplication
public class MarwaanHotelSystemApplication {

	private List<User> users;
	private List<Request> requests;
	private List<Booking> bookings;
	private List<Payment> payments;
	private List<HotelSchedule> hotelSchedules;
	private List<OperatingHours> operatingHourses;
	private List<CustomHours> customHourses;
	private List<Hotel> hotels;
	private List<Shift> shifts;
	private List<Room> rooms;

	public MarwaanHotelSystemApplication() {
		users = new ArrayList<User>();
		requests = new ArrayList<Request>();
		bookings = new ArrayList<Booking>();
		hotelSchedules = new ArrayList<HotelSchedule>();
		operatingHourses = new ArrayList<OperatingHours>();
		customHourses = new ArrayList<CustomHours>();
		shifts = new ArrayList<Shift>();
		rooms = new ArrayList<Room>();
	}

	// Room
	public Room getRoom(int index) {
		Room room = rooms.get(index);
		return room;
	}

	public List<Room> getRooms() {
		List<Room> newRooms = Collections.unmodifiableList(rooms);
		return newRooms;
	}

	public int numberOfRooms() {
		return rooms.size();
	}

	public boolean hasRooms() {
		boolean has = rooms.size() > 0;
		return has;
	}

	public int indexOfRoom(Room room) {
		return rooms.indexOf(room);
	}

	public static int minimumNumberOfRooms() {
		return 0;
	}

	public Room addRoom(RoomType roomType, BedType bedType, boolean isAvailable, int pricePerNight, int maxCapacity,
			Hotel hotel) {
		return new Room(roomType, bedType, isAvailable, pricePerNight, maxCapacity, hotel, this);
	}

	public boolean addRoom(Room room) {
		if (rooms.contains(room)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = room.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			room.setMarwaanHotelSystemApplication(this);

		} else {
			rooms.add(room);

		}
		return true;
	}

	public boolean removeRoom(Room room) {
		if (!this.equals(room.getMarwaanHotelSystemApplication())) {
			rooms.remove(room);
			return true;
		}
		return false;
	}

	public boolean addRoomAt(Room room, int index) {

		if (addRoom(room)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfRooms()) {
				index = numberOfRooms() - 1;
			}
			rooms.remove(room);
			rooms.add(index, room);
			return true;
		}
		return false;
	}

	public boolean addOrMoveRoomAt(Room room, int index) {
		if (rooms.contains(room)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfRooms()) {
				index = numberOfRooms() - 1;
			}
			rooms.remove(room);
			rooms.add(index, room);
			return true;
		} else {
			return addRoomAt(room, index);
		}
	}

	// Shift
	public Shift getShift(int index) {
		Shift shift = shifts.get(index);
		return shift;
	}

	public List<Shift> getShifts() {
		List<Shift> newShifts = Collections.unmodifiableList(shifts);
		return newShifts;
	}

	public int numberOfShifts() {
		return shifts.size();
	}

	public boolean hasShifts() {
		boolean has = shifts.size() > 0;
		return has;
	}

	public int indexOfShift(Shift shift) {
		return shifts.indexOf(shift);
	}

	public static int minimumNumberOfShifts() {
		return 0;
	}

	public Shift addShift(Employee employee, Date date, int startTime, int endTime) {
		return new Shift(employee, date, startTime, endTime, this);
	}

	public boolean addShift(Shift shift) {
		if (shifts.contains(shift)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = shift.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			shift.setMarwaanHotelSystemApplication(this);

		} else {
			shifts.add(shift);

		}
		return true;
	}

	public boolean removeShift(Shift shift) {
		if (!this.equals(shift.getMarwaanHotelSystemApplication())) {
			shifts.remove(shift);
			return true;
		}
		return false;
	}

	public boolean addShiftAt(Shift shift, int index) {

		if (addShift(shift)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfShifts()) {
				index = numberOfShifts() - 1;
			}
			shifts.remove(shift);
			shifts.add(index, shift);
			return true;
		}
		return false;
	}

	public boolean addOrMoveShiftAt(Shift shift, int index) {
		if (shifts.contains(shift)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfShifts()) {
				index = numberOfShifts() - 1;
			}
			shifts.remove(shift);
			shifts.add(index, shift);
			return true;
		} else {
			return addShiftAt(shift, index);
		}
	}

	// Hotel
	public Hotel getHotel(int index) {
		Hotel hotel = hotels.get(index);
		return hotel;
	}

	public List<Hotel> getHotels() {
		List<Hotel> newHotels = Collections.unmodifiableList(hotels);
		return newHotels;
	}

	public int numberOfHotels() {
		return hotels.size();
	}

	public boolean hasHotels() {
		boolean has = hotels.size() > 0;
		return has;
	}

	public int indexOfHotel(Hotel hotel) {
		return hotels.indexOf(hotel);
	}

	public static int minimumNumberOfHotels() {
		return 0;
	}

	public Hotel addHotel(HotelSchedule hotelSchedule) {
		return new Hotel(hotelSchedule, this);
	}

	public boolean addHotel(Hotel hotel) {
		if (hotels.contains(hotel)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = hotel.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			hotel.setMarwaanHotelSystemApplication(this);

		} else {
			hotels.add(hotel);

		}
		return true;
	}

	public boolean removeHotel(Hotel hotel) {
		if (!this.equals(hotel.getMarwaanHotelSystemApplication())) {
			hotels.remove(hotel);
			return true;
		}
		return false;
	}

	public boolean addHotelAt(Hotel hotel, int index) {

		if (addHotel(hotel)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfHotels()) {
				index = numberOfHotels() - 1;
			}
			hotels.remove(hotel);
			hotels.add(index, hotel);
			return true;
		}
		return false;
	}

	public boolean addOrMoveHotelAt(Hotel hotel, int index) {
		if (hotels.contains(hotel)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfHotels()) {
				index = numberOfHotels() - 1;
			}
			hotels.remove(hotel);
			hotels.add(index, hotel);
			return true;
		} else {
			return addHotelAt(hotel, index);
		}
	}

	// CustomHours
	public CustomHours getCustomHours(int index) {
		CustomHours customHours = customHourses.get(index);
		return customHours;
	}

	public List<CustomHours> getCustomHourses() {
		List<CustomHours> newCustomHourses = Collections.unmodifiableList(customHourses);
		return newCustomHourses;
	}

	public int numberOfCustomHourses() {
		return customHourses.size();
	}

	public boolean hasCustomHourses() {
		boolean has = customHourses.size() > 0;
		return has;
	}

	public int indexOfCustomHours(CustomHours customHours) {
		return customHourses.indexOf(customHours);
	}

	public static int minimumNumberOfCustomHourses() {
		return 0;
	}

	public CustomHours addCustomHours(Date date, int openingHour, int closingHour) {
		return new CustomHours(date, openingHour, closingHour, this);
	}

	public boolean addCustomHours(CustomHours customHours) {
		if (customHourses.contains(customHours)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = customHours
				.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			customHours.setMarwaanHotelSystemApplication(this);

		} else {
			customHourses.add(customHours);

		}
		return true;
	}

	public boolean removeCustomHours(CustomHours customHours) {
		if (!this.equals(customHours.getMarwaanHotelSystemApplication())) {
			customHourses.remove(customHours);
			return true;
		}
		return false;
	}

	public boolean addCustomHoursAt(CustomHours customHours, int index) {

		if (addCustomHours(customHours)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfCustomHourses()) {
				index = numberOfCustomHourses() - 1;
			}
			customHourses.remove(customHours);
			customHourses.add(index, customHours);
			return true;
		}
		return false;
	}

	public boolean addOrMoveCustomHoursAt(CustomHours customHours, int index) {
		if (customHourses.contains(customHours)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfCustomHourses()) {
				index = numberOfCustomHourses() - 1;
			}
			customHourses.remove(customHours);
			customHourses.add(index, customHours);
			return true;
		} else {
			return addCustomHoursAt(customHours, index);
		}
	}

	// OperatingHours
	public OperatingHours getOperatingHours(int index) {
		OperatingHours operatingHours = operatingHourses.get(index);
		return operatingHours;
	}

	public List<OperatingHours> getOperatingHourses() {
		List<OperatingHours> newOperatingHourses = Collections.unmodifiableList(operatingHourses);
		return newOperatingHourses;
	}

	public int numberOfOperatingHourses() {
		return operatingHourses.size();
	}

	public boolean hasOperatingHourses() {
		boolean has = operatingHourses.size() > 0;
		return has;
	}

	public int indexOfOperatingHours(OperatingHours operatingHours) {
		return operatingHourses.indexOf(operatingHours);
	}

	public static int minimumNumberOfOperatingHourses() {
		return 0;
	}

	public OperatingHours addOperatingHours(DayOfWeek day, int openingHour, int closingHour) {
		return new OperatingHours(day, openingHour, closingHour, this);
	}

	public boolean addOperatingHours(OperatingHours operatingHours) {
		if (operatingHourses.contains(operatingHours)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = operatingHours
				.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			operatingHours.setMarwaanHotelSystemApplication(this);

		} else {
			operatingHourses.add(operatingHours);

		}
		return true;
	}

	public boolean removeOperatingHours(OperatingHours operatingHours) {
		if (!this.equals(operatingHours.getMarwaanHotelSystemApplication())) {
			operatingHourses.remove(operatingHours);
			return true;
		}
		return false;
	}

	public boolean addOperatingHoursAt(OperatingHours operatingHours, int index) {

		if (addOperatingHours(operatingHours)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfOperatingHourses()) {
				index = numberOfOperatingHourses() - 1;
			}
			operatingHourses.remove(operatingHours);
			operatingHourses.add(index, operatingHours);
			return true;
		}
		return false;
	}

	public boolean addOrMoveOperatingHoursAt(OperatingHours operatingHours, int index) {
		if (operatingHourses.contains(operatingHours)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfOperatingHourses()) {
				index = numberOfOperatingHourses() - 1;
			}
			operatingHourses.remove(operatingHours);
			operatingHourses.add(index, operatingHours);
			return true;
		} else {
			return addOperatingHoursAt(operatingHours, index);
		}
	}

	// HotelSchedule
	public HotelSchedule getHotelSchedule(int index) {
		HotelSchedule hotelSchedule = hotelSchedules.get(index);
		return hotelSchedule;
	}

	public List<HotelSchedule> getHotelSchedules() {
		List<HotelSchedule> newHotelSchedules = Collections.unmodifiableList(hotelSchedules);
		return newHotelSchedules;
	}

	public int numberOfHotelSchedules() {
		return hotelSchedules.size();
	}

	public boolean hasHotelSchedules() {
		boolean has = hotelSchedules.size() > 0;
		return has;
	}

	public int indexOfHotelSchedule(HotelSchedule hotelSchedule) {
		return hotelSchedules.indexOf(hotelSchedule);
	}

	public static int minimumNumberOfHotelSchedules() {
		return 0;
	}

	public HotelSchedule addHotelSchedule(int year, OperatingHours[] operatingHours, CustomHours[] customHours) {
		return new HotelSchedule(year, operatingHours, customHours, this);
	}

	public boolean addHotelSchedule(HotelSchedule hotelSchedule) {
		if (hotelSchedules.contains(hotelSchedule)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = hotelSchedule
				.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			hotelSchedule.setMarwaanHotelSystemApplication(this);

		} else {
			hotelSchedules.add(hotelSchedule);

		}
		return true;
	}

	public boolean removeHotelSchedule(HotelSchedule hotelSchedule) {
		if (!this.equals(hotelSchedule.getMarwaanHotelSystemApplication())) {
			hotelSchedules.remove(hotelSchedule);
			return true;
		}
		return false;
	}

	public boolean addHotelScheduleAt(HotelSchedule hotelSchedule, int index) {

		if (addHotelSchedule(hotelSchedule)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfHotelSchedules()) {
				index = numberOfHotelSchedules() - 1;
			}
			hotelSchedules.remove(hotelSchedule);
			hotelSchedules.add(index, hotelSchedule);
			return true;
		}
		return false;
	}

	public boolean addOrMoveHotelScheduleAt(HotelSchedule hotelSchedule, int index) {
		if (hotelSchedules.contains(hotelSchedule)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfHotelSchedules()) {
				index = numberOfHotelSchedules() - 1;
			}
			hotelSchedules.remove(hotelSchedule);
			hotelSchedules.add(index, hotelSchedule);
			return true;
		} else {
			return addHotelScheduleAt(hotelSchedule, index);
		}
	}

	// Payment
	public Payment getPayment(int index) {
		Payment payment = payments.get(index);
		return payment;
	}

	public List<Payment> getPayments() {
		List<Payment> newPayments = Collections.unmodifiableList(payments);
		return newPayments;
	}

	public int numberOfPayments() {
		return payments.size();
	}

	public boolean hasPayments() {
		boolean has = payments.size() > 0;
		return has;
	}

	public int indexOfPayment(Payment payment) {
		return payments.indexOf(payment);
	}

	public static int minimumNumberOfPayments() {
		return 0;
	}

	public Payment addPayment(int amount, String paymentId) {
		return new Payment(amount, paymentId, this);
	}

	public boolean addPayment(Payment payment) {
		if (payments.contains(payment)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = payment
				.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			payment.setMarwaanHotelSystemApplication(this);

		} else {
			payments.add(payment);

		}
		return true;
	}

	public boolean removePayment(Payment payment) {
		if (!this.equals(payment.getMarwaanHotelSystemApplication())) {
			payments.remove(payment);
			return true;
		}
		return false;
	}

	public boolean addPaymentAt(Payment payment, int index) {

		if (addPayment(payment)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfPayments()) {
				index = numberOfPayments() - 1;
			}
			payments.remove(payment);
			payments.add(index, payment);
			return true;
		}
		return false;
	}

	public boolean addOrMovePaymentAt(Payment payment, int index) {
		if (payments.contains(payment)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfPayments()) {
				index = numberOfPayments() - 1;
			}
			payments.remove(payment);
			payments.add(index, payment);
			return true;
		} else {
			return addPaymentAt(payment, index);
		}
	}

	// Booking
	public Booking getBooking(int index) {
		Booking booking = bookings.get(index);
		return booking;
	}

	public List<Booking> getBookings() {
		List<Booking> newBookings = Collections.unmodifiableList(bookings);
		return newBookings;
	}

	public int numberOfBookings() {
		return bookings.size();
	}

	public boolean hasBookings() {
		boolean has = bookings.size() > 0;
		return has;
	}

	public int indexOfBooking(Booking booking) {
		return bookings.indexOf(booking);
	}

	public static int minimumNumberOfBookings() {
		return 0;
	}

	public Booking addBooking(String bookingId, Payment payment, User user, Room room) {
		return new Booking(bookingId, payment, user, room, this);
	}

	public boolean addBooking(Booking booking) {
		if (bookings.contains(booking)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = booking
				.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			booking.setMarwaanHotelSystemApplication(this);

		} else {
			bookings.add(booking);

		}
		return true;
	}

	public boolean removeBooking(Booking booking) {
		if (!this.equals(booking.getMarwaanHotelSystemApplication())) {
			bookings.remove(booking);
			return true;
		}
		return false;
	}

	public boolean addBookingAt(Booking booking, int index) {

		if (addBooking(booking)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfBookings()) {
				index = numberOfBookings() - 1;
			}
			bookings.remove(booking);
			bookings.add(index, booking);
			return true;
		}
		return false;
	}

	public boolean addOrMoveBookingAt(Booking booking, int index) {
		if (bookings.contains(booking)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfBookings()) {
				index = numberOfBookings() - 1;
			}
			bookings.remove(booking);
			bookings.add(index, booking);
			return true;
		} else {
			return addBookingAt(booking, index);
		}
	}

	// Request
	public Request getRequest(int index) {
		Request request = requests.get(index);
		return request;
	}

	public List<Request> getRequests() {
		List<Request> newRequests = Collections.unmodifiableList(requests);
		return newRequests;
	}

	public int numberOfRequests() {
		return requests.size();
	}

	public boolean hasRequests() {
		boolean has = requests.size() > 0;
		return has;
	}

	public int indexOfRequest(Request request) {
		return requests.indexOf(request);
	}

	public static int minimumNumberOfRequests() {
		return 0;
	}

	public Request addRequest(String Description, Employee employee, Booking booking) {
		return new Request(Description, employee, booking, this);
	}

	public boolean addRequest(Request request) {
		if (requests.contains(request)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = request
				.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			request.setMarwaanHotelSystemApplication(this);

		} else {
			requests.add(request);

		}
		return true;
	}

	public boolean removeRequest(Request request) {
		if (!this.equals(request.getMarwaanHotelSystemApplication())) {
			requests.remove(request);
			return true;
		}
		return false;
	}

	public boolean addRequestAt(Request request, int index) {

		if (addRequest(request)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfRequests()) {
				index = numberOfRequests() - 1;
			}
			requests.remove(request);
			requests.add(index, request);
			return true;
		}
		return false;
	}

	public boolean addOrMoveRequestAt(Request request, int index) {
		if (requests.contains(request)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfRequests()) {
				index = numberOfRequests() - 1;
			}
			requests.remove(request);
			requests.add(index, request);
			return true;
		} else {
			return addRequestAt(request, index);
		}
	}

	// User
	public User getUser(int index) {
		User user = users.get(index);
		return user;
	}

	public List<User> getUsers() {
		List<User> newUsers = Collections.unmodifiableList(users);
		return newUsers;
	}

	public int numberOfUsers() {
		return users.size();
	}

	public boolean hasUsers() {
		boolean has = users.size() > 0;
		return has;
	}

	public int indexOfUser(User user) {
		return users.indexOf(user);
	}

	public static int minimumNumberOfUsers() {
		return 0;
	}

	public User addUser(String firstName, String lastName, String email, int phoneNumber) {
		return new User(firstName, lastName, email, phoneNumber, this);
	}

	public boolean addUser(User user) {
		if (users.contains(user)) {
			return false;
		}
		MarwaanHotelSystemApplication existingMarwaanHotelSystemApplication = user.getMarwaanHotelSystemApplication();
		boolean isNewMarwaanHotelSystemApplication = existingMarwaanHotelSystemApplication != null
				&& !this.equals(existingMarwaanHotelSystemApplication);
		if (isNewMarwaanHotelSystemApplication) {
			user.setMarwaanHotelSystemApplication(this);

		} else {
			users.add(user);

		}
		return true;
	}

	public boolean removeUser(User user) {
		if (!this.equals(user.getMarwaanHotelSystemApplication())) {
			users.remove(user);
			return true;
		}
		return false;
	}

	public boolean addUserAt(User user, int index) {

		if (addUser(user)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfUsers()) {
				index = numberOfUsers() - 1;
			}
			users.remove(user);
			users.add(index, user);
			return true;
		}
		return false;
	}

	public boolean addOrMoveUserAt(User user, int index) {
		if (users.contains(user)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfUsers()) {
				index = numberOfUsers() - 1;
			}
			users.remove(user);
			users.add(index, user);
			return true;
		} else {
			return addUserAt(user, index);
		}
	}

	public void delete() {
		while (users.size() > 0) {
			User user = users.get(users.size() - 1);
			user.delete();
			users.remove(user);
		}

		while (requests.size() > 0) {
			Request request = requests.get(requests.size() - 1);
			request.delete();
			requests.remove(request);
		}

		while (bookings.size() > 0) {
			Booking booking = bookings.get(bookings.size() - 1);
			booking.delete();
			bookings.remove(booking);
		}
		while (payments.size() > 0) {
			Payment payment = payments.get(payments.size() - 1);
			payment.delete();
			payments.remove(payment);
		}

		while (hotelSchedules.size() > 0) {
			HotelSchedule hotelSchedule = hotelSchedules.get(hotelSchedules.size() - 1);
			hotelSchedule.delete();
			hotelSchedules.remove(hotelSchedule);
		}

		while (operatingHourses.size() > 0) {
			OperatingHours operatingHours = operatingHourses.get(operatingHourses.size() - 1);
			operatingHours.delete();
			operatingHourses.remove(operatingHours);
		}

		while (customHourses.size() > 0) {
			CustomHours customHours = customHourses.get(customHourses.size() - 1);
			customHours.delete();
			customHourses.remove(customHours);
		}

		while (hotels.size() > 0) {
			Hotel hotel = hotels.get(hotels.size() - 1);
			hotel.delete();
			hotels.remove(hotel);
		}

		while (shifts.size() > 0) {
			Shift shift = shifts.get(shifts.size() - 1);
			shift.delete();
			shifts.remove(shift);
		}

		while (rooms.size() > 0) {
			Room room = rooms.get(rooms.size() - 1);
			room.delete();
			rooms.remove(room);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(MarwaanHotelSystemApplication.class, args);
	}

}
