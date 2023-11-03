package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

public class MultipleRoomDto {
    private Iterable<RoomDto> roomList;
	
	public MultipleRoomDto(Iterable<Room> rooms) {
		List<RoomDto> roomDtoList = new ArrayList<RoomDto>();
		for (Room r : rooms) {
			roomDtoList.add(new RoomDto(r));
		}
		this.roomList = roomDtoList;
	}
	
	public Iterable<RoomDto> getRoomList() {
		return this.roomList;
	}
}
