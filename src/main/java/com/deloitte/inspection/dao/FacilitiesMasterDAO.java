/**
 * 
 */
package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.exception.FacilityMasterException;
import com.deloitte.inspection.model.LISFacilityMaster;

/**
 * @author rnarne
 *
 */
public interface FacilitiesMasterDAO {

	public LISFacilityMaster getFacilityNumber(String facilityNum) throws FacilityMasterException;
	
	public FacilityMasterDTO createFacility(FacilityMasterDTO inspTypeMasterDTO) throws FacilityMasterException;
	
	public List<LISFacilityMaster> getFacilitiesMasterData() throws FacilityMasterException;
}
