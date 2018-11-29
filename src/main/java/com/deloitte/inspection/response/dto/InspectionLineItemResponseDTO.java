package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.InspectionLineItemDTO;

public class InspectionLineItemResponseDTO extends CommonDTO{
	
	private List<InspectionLineItemDTO> results;
	private List<String> componentDrawNumbers;

	public List<InspectionLineItemDTO> getResults() {
		return results;
	}

	public void setResults(List<InspectionLineItemDTO> results) {
		this.results = results;
	}
	public List<String> getComponentDrawNumbers() {
		return componentDrawNumbers;
	}

	public void setComponentDrawNumbers(List<String> componentDrawNumbers) {
		this.componentDrawNumbers = componentDrawNumbers;
	}
}
