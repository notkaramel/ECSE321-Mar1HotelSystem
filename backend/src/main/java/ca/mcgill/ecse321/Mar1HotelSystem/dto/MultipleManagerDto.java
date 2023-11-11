package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import java.util.List;
import java.util.ArrayList;

public class MultipleManagerDto {
	private List<ManagerDto> managerList;

	// CONSTRUCTORS
	public MultipleManagerDto() {
	}

	public MultipleManagerDto(List<Manager> managersList) {
		List<ManagerDto> managerDtoList = new ArrayList<ManagerDto>();
		for (Manager manager : managersList) {
			managerDtoList.add(new ManagerDto(manager));
		}
		this.managerList = managerDtoList;
	}

	public List<ManagerDto> getManagerList() {
		return this.managerList;
	}
}
