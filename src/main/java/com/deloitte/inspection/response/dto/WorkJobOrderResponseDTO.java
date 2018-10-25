package com.deloitte.inspection.response.dto;

import java.util.List;
import java.util.Set;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.WorkJobOrderDTO;

public class WorkJobOrderResponseDTO extends CommonDTO{
	
	private List<WorkJobOrderDTO> results;
	private Integer lotSize;
	private List<ComponentMasterDataDTO> componentData;
	private Set<String> customerPONumberList;

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

	public Set<String> getCustomerPONumberList() {
		return customerPONumberList;
	}

	public void setCustomerPONumberList(Set<String> customerPONumberList) {
		this.customerPONumberList = customerPONumberList;
	}

	public List<ComponentMasterDataDTO> getComponentData() {
		return componentData;
	}

	public void setComponentData(List<ComponentMasterDataDTO> componentData) {
		this.componentData = componentData;
	}
	
}
