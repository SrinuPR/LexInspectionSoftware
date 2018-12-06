package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.InspectionMasterDTO;

public class InspectionMasterResponseDataDTO extends CommonDTO {
	
	private List<InspectionMasterDTO> results;
	private List<ComponentMasterDataDTO> componentData;

	public List<InspectionMasterDTO> getResults() {
		return results;
	}
	public void setResults(List<InspectionMasterDTO> results) {
		this.results = results;
	}
	public List<ComponentMasterDataDTO> getComponentData() {
		return componentData;
	}
	public void setComponentData(List<ComponentMasterDataDTO> componentData) {
		this.componentData = componentData;
	}

}
