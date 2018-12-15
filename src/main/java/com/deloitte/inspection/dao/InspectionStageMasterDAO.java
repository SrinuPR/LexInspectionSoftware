/**
 * 
 */
package com.deloitte.inspection.dao;

import java.util.List;
import com.deloitte.inspection.dto.InspectionStageMasterDTO;
import com.deloitte.inspection.exception.InspectionStageMasterException;
import com.deloitte.inspection.model.LISInspectionStageMaster;

/**
 * @author rnarne
 *
 */
public interface InspectionStageMasterDAO {

	public LISInspectionStageMaster getInspSatgeId(Integer inspStageId) throws InspectionStageMasterException;
	
	public InspectionStageMasterDTO createInspectionStage(InspectionStageMasterDTO inspTypeMasterDTO) throws InspectionStageMasterException;
	
	public List<LISInspectionStageMaster> getAllInspStageMasterData() throws InspectionStageMasterException;

}
