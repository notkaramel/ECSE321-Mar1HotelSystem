package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import java.util.List;
import java.util.ArrayList;

public class MultipleGeneralUserDto {
	private List<GeneralUserDto> generalUserList;

	// CONSTRUCTORS
	public MultipleGeneralUserDto() {
	}

	public MultipleGeneralUserDto(List<GeneralUser> generalUsersList) {
		List<GeneralUserDto> generalUserDtoList = new ArrayList<GeneralUserDto>();
		for (GeneralUser generalUser : generalUsersList) {
			generalUserDtoList.add(new GeneralUserDto(generalUser));
		}
		this.generalUserList = generalUserDtoList;
	}

	public List<GeneralUserDto> getGeneralUserList() {
		return this.generalUserList;
	}

}
