package com.deloitte.inspection.service;

import com.deloitte.inspection.dto.InspectionMasterDTO;
import com.deloitte.inspection.response.dto.ComponentMasterResponseDataDTO;
import com.deloitte.inspection.response.dto.InspectionMasterResponseDataDTO;

public interface InspectionMasterService {
	
	public InspectionMasterResponseDataDTO validateInspectionStage(InspectionMasterDTO masterDTO);
	
	public InspectionMasterResponseDataDTO saveInspectionMaster(InspectionMasterDTO masterDTO);
	
	public InspectionMasterResponseDataDTO updateInspectionMaster(InspectionMasterDTO masterDTO);
	
	public InspectionMasterResponseDataDTO getInspectionMasterData(Integer subscriberId);
	
	public String deleteInspectionMaster(Integer inspectionMasterId);

	public InspectionMasterResponseDataDTO getInspectionTypesByCompProdDrawNum(String compProdDrawNum);

	public ComponentMasterResponseDataDTO getCompDrawNumsBySubscriberId(Integer subscriberId);

}
