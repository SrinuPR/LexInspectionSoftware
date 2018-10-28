package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.FacilityMasterDTO;

public class FacilityMasterResponseDataDTO extends CommonDTO{
	
private List<FacilityMasterDTO> result;

/**
 * @return the result
 */
public List<FacilityMasterDTO> getResult() {
	return result;
}

/**
 * @param result the result to set
 */
public void setResult(List<FacilityMasterDTO> result) {
	this.result = result;
}



}
