package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.MasterListOfScreensForSubscriberDTO;

public class MasterListOfScreensForSubscriberResponseDTO extends CommonDTO {
	
	private List<MasterListOfScreensForSubscriberDTO> results;

	public List<MasterListOfScreensForSubscriberDTO> getResults() {
		return results;
	}

	public void setResults(List<MasterListOfScreensForSubscriberDTO> results) {
		this.results = results;
	}
	
}
