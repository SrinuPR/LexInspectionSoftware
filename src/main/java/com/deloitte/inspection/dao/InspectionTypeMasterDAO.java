/**
 * 
 */
package com.deloitte.inspection.dao;

import java.util.List;
import com.deloitte.inspection.dto.InspectionTypeMasterDTO;
import com.deloitte.inspection.exception.InspectionTypeMasterException;
import com.deloitte.inspection.model.LISInspectionTypeMaster;

/**
 * @author rnarne
 *
 */
public interface InspectionTypeMasterDAO {
	public LISInspectionTypeMaster getInspTypeId(Integer inspTypeId) throws InspectionTypeMasterException;
	
	public InspectionTypeMasterDTO createInspectionType(InspectionTypeMasterDTO inspTypeMasterDTO) throws InspectionTypeMasterException;
	
	public List<LISInspectionTypeMaster> getAllInspTypeMasterData() throws InspectionTypeMasterException;
	
}
