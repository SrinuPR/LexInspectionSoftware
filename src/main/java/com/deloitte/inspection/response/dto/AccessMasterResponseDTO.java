package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.AccessMasterDTO;
import com.deloitte.inspection.dto.CommonDTO;

public class AccessMasterResponseDTO extends CommonDTO{
	
	private List<AccessMasterDTO> result;

	public List<AccessMasterDTO> getResult() {
		return result;
	}

	public void setResult(List<AccessMasterDTO> result) {
		this.result = result;
	}
}
