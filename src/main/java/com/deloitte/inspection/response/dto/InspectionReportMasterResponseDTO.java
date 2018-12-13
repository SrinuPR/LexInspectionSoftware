package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.InspectionReportMasterDTO;

public class InspectionReportMasterResponseDTO extends CommonDTO {
	
	private List<InspectionReportMasterDTO> result;

	/**
	 * @return the result
	 */
	public List<InspectionReportMasterDTO> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(List<InspectionReportMasterDTO> result) {
		this.result = result;
	}	
}
