package com.deloitte.inspection.response.dto;

import java.util.List;
import java.util.Map;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.WorkJobOrderDTO;

public class WorkJobOrderResponseDTO extends CommonDTO{
	
	private List<WorkJobOrderDTO> results;
	private Integer lotSize;
	private Map<String,String> componentData;

	public List<WorkJobOrderDTO> getResults() {
		return results;
	}

	public void setResults(List<WorkJobOrderDTO> results) {
		this.results = results;
	}

	public Integer getLotSize() {
		return lotSize;
	}

	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	public Map<String, String> getComponentData() {
		return componentData;
	}

	public void setComponentData(Map<String, String> componentData) {
		this.componentData = componentData;
	}
}
