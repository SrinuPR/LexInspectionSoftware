package com.deloitte.inspection.response.dto;

import java.util.List;
import java.util.Set;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.InspectionMasterDTO;

public class InspectionMasterResponseDataDTO extends CommonDTO {
	
	private List<InspectionMasterDTO> results;
	private Set<Integer> inspTypeList;
	private Set<Integer> inspStageList;
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
	public Set<Integer> getInspTypeList() {
		return inspTypeList;
	}
	public void setInspTypeList(Set<Integer> inspTypeList) {
		this.inspTypeList = inspTypeList;
	}
	public Set<Integer> getInspStageList() {
		return inspStageList;
	}
	public void setInspStageList(Set<Integer> inspStageList) {
		this.inspStageList = inspStageList;
	}
}
