/**
 * 
 */
package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.exception.FacilityMasterException;

/**
 * @author rnarne
 *
 */
public interface FacilitiesMasterService {
	public FacilityMasterDTO getFacilityNumber(String facilityNum) throws FacilityMasterException;
	public FacilityMasterDTO createFacilities(FacilityMasterDTO inspTypeMasterDTO) throws FacilityMasterException;
	public List<FacilityMasterDTO> getFacilitiesMasterData() throws FacilityMasterException;
}
