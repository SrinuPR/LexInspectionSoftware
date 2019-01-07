package com.deloitte.inspection.service;

import com.deloitte.inspection.dto.InspectionMeasurementDTO;
import com.deloitte.inspection.dto.PartIdentificationDTO;
import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.response.dto.InspectionMeasurementResponseDTO;

public interface InspectionMeasurementService {

	public InspectionMeasurementResponseDTO getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException;

	public InspectionMeasurementResponseDTO getInspectionReportList(String compDrawNum) throws InspectionMeasurementException;

	public InspectionMeasurementResponseDTO validatePartIdentification(String partIdententificationId, InspectionMeasurementDTO inspectionMeasurementDTO) throws InspectionMeasurementException;

	public InspectionMeasurementResponseDTO saveMeasurement(PartIdentificationDTO partIdentificationDTO) throws InspectionMeasurementException;

	public InspectionMeasurementResponseDTO savePart(InspectionMeasurementDTO inspectionMeasurementDTO) throws InspectionMeasurementException;

	public InspectionMeasurementResponseDTO save(InspectionMeasurementDTO inspectionMeasurementDTO) throws InspectionMeasurementException;

}
