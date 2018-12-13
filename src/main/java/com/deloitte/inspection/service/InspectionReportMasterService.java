package com.deloitte.inspection.service;

import com.deloitte.inspection.dto.InspectionReportMasterDTO;
import com.deloitte.inspection.exception.InspectionReportMasterException;
import com.deloitte.inspection.response.dto.InspectionReportMasterResponseDTO;

public interface InspectionReportMasterService {
	public InspectionReportMasterResponseDTO saveBlankReport(InspectionReportMasterDTO inspReportMasterDTO, String userName, String userId) throws InspectionReportMasterException;

	public InspectionReportMasterResponseDTO validateInspReportNumber(Integer inspReportNum) throws InspectionReportMasterException;
}