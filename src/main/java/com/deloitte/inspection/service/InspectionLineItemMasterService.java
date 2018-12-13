package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.InspectionLineItemDTO;
import com.deloitte.inspection.exception.InspectionLineItemMasterException;
import com.deloitte.inspection.response.dto.InspectionLineItemResponseDTO;

public interface InspectionLineItemMasterService {
	
	public InspectionLineItemResponseDTO getComponentProductDrawNumbers(Integer subscriberId) 
			throws InspectionLineItemMasterException;

	public InspectionLineItemResponseDTO getAllInspectionLineItems(String userId)
			throws InspectionLineItemMasterException;

	public InspectionLineItemResponseDTO reportSave(List<InspectionLineItemDTO> inspectionLineItems, String userId,
			String userName) throws InspectionLineItemMasterException;

	public InspectionLineItemResponseDTO measureItemSave(List<InspectionLineItemDTO> inspectionLineItems, String userId,
			String userName) throws InspectionLineItemMasterException;

	public InspectionLineItemResponseDTO validateMeasurementName(InspectionLineItemDTO inspectionLineItem)
			throws InspectionLineItemMasterException;

	public InspectionLineItemResponseDTO updateInspectionData(List<InspectionLineItemDTO> inspectionLineItem, String userName, String userId) throws InspectionLineItemMasterException;

	public InspectionLineItemResponseDTO getLineItemByCompDraNum(String compDraNum) 
			throws InspectionLineItemMasterException;
}
