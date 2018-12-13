package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.dto.InspectionMasterDTO;
import com.deloitte.inspection.model.LISInspectionMaster;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;

public interface InspectionMasterDAO {
	
	public LISInspectionMaster getInspectionStage(InspectionMasterDTO inspectionDTO);
	public void saveInspectionMaster(LISInspectionMaster inspectionMaster);
	public List<LISInspectionMaster> getInspectionMasterList(String userId);
	public LISInspectionMaster getInspectionMasterById(Integer inspectionMasterId);
	public LISInspectionMaster getInspectionStageOtherThanCurrent(InspectionMasterDTO inspectionDTO);
	public String deleteInspectionMaster(Integer inspectionMasterId);
	public List<LISInspectionMaster> getInspectionTypesByCompProdDrawNum(String compProdDrawNum);
	public List<LISMaintainMasterDataComponent> getCompDrawNumsBySubscriberId(Integer subscriberId);

}
