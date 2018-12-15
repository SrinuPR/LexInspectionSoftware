package com.deloitte.inspection.service;

import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.response.dto.InspectionMeasurementResponseDTO;

public interface InspectionMeasurementService {

	public InspectionMeasurementResponseDTO getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException;

	public InspectionMeasurementResponseDTO getInspectionReportList(String compDrawNum) throws InspectionMeasurementException;

}
