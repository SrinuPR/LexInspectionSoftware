/**
 * 
 */
package com.deloitte.inspection.dao;

import java.util.List;
import com.deloitte.inspection.dto.InspectionStageMasterDTO;
import com.deloitte.inspection.exception.InspectionStageMasterException;
import com.deloitte.inspection.mapper.LISInspectionStageMasterResult;
import com.deloitte.inspection.model.LISInspectionStageMaster;

/**
 * @author rnarne
 *
 */
public interface InspectionStageMasterDAO {

	public LISInspectionStageMaster getInspSatgeId(Integer inspStageId) throws InspectionStageMasterException;
	
	public InspectionStageMasterDTO createInspectionStage(InspectionStageMasterDTO inspTypeMasterDTO, String userId) throws InspectionStageMasterException;
	
	public List<LISInspectionStageMasterResult> getAllInspStageMasterData(Integer subscriberId) throws InspectionStageMasterException;

}
