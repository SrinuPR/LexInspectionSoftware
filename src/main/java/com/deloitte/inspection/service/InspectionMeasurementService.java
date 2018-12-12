package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.response.dto.WorkJobOrderResponseDTO;

public interface InspectionMeasurementService {

	public List<String> getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException;

	public WorkJobOrderResponseDTO getWorkJobOrderList(String compDrawNum) throws InspectionMeasurementException;

}
