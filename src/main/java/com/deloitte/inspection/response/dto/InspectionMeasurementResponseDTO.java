package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.dto.InspectionMeasurementDTO;
import com.deloitte.inspection.dto.InspectionReportMasterDTO;
import com.deloitte.inspection.dto.ShiftMasterDTO;

public class InspectionMeasurementResponseDTO extends CommonDTO{

	private List<InspectionMeasurementDTO> results;
	private List<ComponentMasterDataDTO> componentData;
	private List<FacilityMasterDTO> facilityData;
	private List<InspectionReportMasterDTO> reportData;
	private List<ShiftMasterDTO> shiftData;
	
	public List<InspectionMeasurementDTO> getResults() {
		return results;
	}

	public void setResults(List<InspectionMeasurementDTO> results) {
		this.results = results;
	}

	public List<ComponentMasterDataDTO> getComponentData() {
		return componentData;
	}

	public void setComponentData(List<ComponentMasterDataDTO> componentData) {
		this.componentData = componentData;
	}
	
	public List<FacilityMasterDTO> getFacilityData() {
		return facilityData;
	}

	public void setFacilityData(List<FacilityMasterDTO> facilityData) {
		this.facilityData = facilityData;
	}

	public List<InspectionReportMasterDTO> getReportData() {
		return reportData;
	}

	public void setReportData(List<InspectionReportMasterDTO> reportData) {
		this.reportData = reportData;
	}

	public List<ShiftMasterDTO> getShiftData() {
		return shiftData;
	}

	public void setShiftData(List<ShiftMasterDTO> shiftData) {
		this.shiftData = shiftData;
	}
		
}
