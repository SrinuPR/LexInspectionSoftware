/**
 * 
 */
package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.InspectionStageMasterDTO;
import com.deloitte.inspection.exception.InspectionStageMasterException;

/**
 * @author rnarne
 *
 */
public interface InspectionStageMasterService {

	public InspectionStageMasterDTO getInspStageId(Integer inspStageId) throws InspectionStageMasterException;
	public InspectionStageMasterDTO createInspectionStage(InspectionStageMasterDTO inspTypeMasterDTO) throws InspectionStageMasterException;
	public List<InspectionStageMasterDTO> getAllInspStageMasterData() throws InspectionStageMasterException;
	

}
