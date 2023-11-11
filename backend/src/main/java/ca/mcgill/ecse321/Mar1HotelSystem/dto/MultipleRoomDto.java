package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Room;

public class MultipleRoomDto {
	private Iterable<RoomResponseDto> roomList;

	public MultipleRoomDto() {
	}

	public MultipleRoomDto(Iterable<Room> rooms) {
		List<RoomResponseDto> roomDtoList = new ArrayList<RoomResponseDto>();
		for (Room r : rooms) {
			roomDtoList.add(new RoomResponseDto(r));
		}
		this.roomList = roomDtoList;
	}

	public Iterable<RoomResponseDto> getRoomList() {
		return this.roomList;
	}
}
