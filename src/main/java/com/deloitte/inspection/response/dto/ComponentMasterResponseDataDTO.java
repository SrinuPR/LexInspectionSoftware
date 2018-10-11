package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;

public class ComponentMasterResponseDataDTO extends CommonDTO{
	
	private List<ComponentMasterDataDTO> result;
	
	public List<ComponentMasterDataDTO> getResult() {
		return result;
	}
	public void setResult(List<ComponentMasterDataDTO> result) {
		this.result = result;
	}
	
}
