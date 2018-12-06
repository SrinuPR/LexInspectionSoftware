package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.dto.InspectionMasterDTO;
import com.deloitte.inspection.model.LISInspectionMaster;

public interface InspectionMasterDAO {
	
	public LISInspectionMaster getInspectionStage(InspectionMasterDTO inspectionDTO);
	public void saveInspectionMaster(LISInspectionMaster inspectionMaster);
	public List<LISInspectionMaster> getInspectionMasterList(String userId);

}
