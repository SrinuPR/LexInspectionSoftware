package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ShiftMasterDTO;

public class ShiftMasterResponseDTO extends CommonDTO{
	
	private List<ShiftMasterDTO> result;
	
	public List<ShiftMasterDTO> getResult() {
		return result;
	}
	public void setResult(List<ShiftMasterDTO> result) {
		this.result = result;
	}
	
}
