package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.dto.InspectionLineItemDTO;
import com.deloitte.inspection.exception.InspectionLineItemMasterException;
import com.deloitte.inspection.model.LISInspectionLineItemMaster;
import com.deloitte.inspection.model.LISInspectionMaster;

public interface InspectionLineItemMasterDAO {
	
	public void saveInspectionLineItem(List<LISInspectionLineItemMaster> lineItemsList) throws InspectionLineItemMasterException;

	public List<LISInspectionMaster> getComponentProductDrawNumbers(Integer subscriberId) throws InspectionLineItemMasterException;

	public List<LISInspectionLineItemMaster> getAllInspectionLineItems(String userId) throws InspectionLineItemMasterException;

	public LISInspectionLineItemMaster validateMeasurementName(InspectionLineItemDTO inspectionLineItem) throws InspectionLineItemMasterException;

	public LISInspectionLineItemMaster getInspectionItem(Integer inspectionLineItemId) throws InspectionLineItemMasterException;

}
