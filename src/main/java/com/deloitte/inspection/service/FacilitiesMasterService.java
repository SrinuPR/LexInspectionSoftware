/**
 * 
 */
package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.exception.FacilityMasterException;
import com.deloitte.inspection.response.dto.FacilityMasterResponseDataDTO;

/**
 * @author rnarne
 *
 */
public interface FacilitiesMasterService {
	public FacilityMasterResponseDataDTO getFacilityNumber(String facilityNum) throws FacilityMasterException;
	public FacilityMasterResponseDataDTO createFacilities(FacilityMasterDTO inspTypeMasterDTO, String userName) throws FacilityMasterException;
	public List<FacilityMasterDTO> getFacilitiesMasterData() throws FacilityMasterException;
}
