package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.InspectionMeasurementDTO;
import com.deloitte.inspection.dto.WorkJobOrderDTO;

public class InspectionMeasurementResponseDTO extends CommonDTO{

	private List<InspectionMeasurementDTO> results;
	private List<ComponentMasterDataDTO> componentData;
	private List<WorkJobOrderDTO> workOrderList;

	public List<InspectionMeasurementDTO> getResults() {
		return results;
	}

	public void setResults(List<InspectionMeasurementDTO> results) {
		this.results = results;
	}

	public List<ComponentMasterDataDTO> getComponentData() {
		return componentData;
	}

	public void setComponentData(List<ComponentMasterDataDTO> componentData) {
		this.componentData = componentData;
	}

	public List<WorkJobOrderDTO> getWorkOrderList() {
		return workOrderList;
	}

	public void setWorkOrderList(List<WorkJobOrderDTO> workOrderList) {
		this.workOrderList = workOrderList;
	}

}
