package com.deloitte.inspection.response.dto;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.DashBoardDTO;

public class DashBoardResponseDTO extends CommonDTO{

	private DashBoardDTO dashBoardDTO;

	public DashBoardDTO getDashBoardDTO() {
		return dashBoardDTO;
	}

	public void setDashBoardDTO(DashBoardDTO dashBoardDTO) {
		this.dashBoardDTO = dashBoardDTO;
	}
	
}
