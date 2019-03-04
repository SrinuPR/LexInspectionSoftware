package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.dto.InspectionLineItemDTO;
import com.deloitte.inspection.exception.InspectionLineItemMasterException;
import com.deloitte.inspection.mapper.LISInspectionMasterResult;
import com.deloitte.inspection.model.LISInspectionLineItemMaster;
import com.deloitte.inspection.model.LISInspectionMaster;

public interface InspectionLineItemMasterDAO {
	
	public void saveInspectionLineItem(List<LISInspectionLineItemMaster> lineItemsList) throws InspectionLineItemMasterException;

	public List<LISInspectionMasterResult> getComponentProductDrawNumbers(Integer subscriberId) throws InspectionLineItemMasterException;

	public List<LISInspectionLineItemMaster> getAllInspectionLineItems(Integer subscriberId) throws InspectionLineItemMasterException;

	public LISInspectionLineItemMaster validateMeasurementName(InspectionLineItemDTO inspectionLineItem) throws InspectionLineItemMasterException;

	public LISInspectionLineItemMaster getInspectionItem(Integer inspectionLineItemId) throws InspectionLineItemMasterException;

	public List<LISInspectionLineItemMaster>  getComponentProductDrawNumbers(String compDraNum) throws InspectionLineItemMasterException;

	public List<LISInspectionLineItemMaster> getAllInspectionLineItemByUserID(String userId) throws InspectionLineItemMasterException;

	public List<LISInspectionLineItemMaster> getAllInspectionLineItemsByDrawNum(String compDrawNum) throws InspectionLineItemMasterException;
}
