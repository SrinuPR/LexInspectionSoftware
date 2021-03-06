/**
 * 
 */
package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.InspectionTypeMasterDTO;
import com.deloitte.inspection.exception.InspectionTypeMasterException;

/**
 * @author rnarne
 *
 */
public interface InspectionTypeMasterService {
	public InspectionTypeMasterDTO getInspTypeId(Integer inspTypeId) throws InspectionTypeMasterException;
	public InspectionTypeMasterDTO createInspectionType(InspectionTypeMasterDTO inspTypeMasterDTO, String userId) throws InspectionTypeMasterException;
	public List<InspectionTypeMasterDTO> getAllInspTypeMasterData(Integer subscriberId) throws InspectionTypeMasterException;
	
}
