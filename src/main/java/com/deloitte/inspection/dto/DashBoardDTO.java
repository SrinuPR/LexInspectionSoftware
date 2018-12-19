package com.deloitte.inspection.dto;

public class DashBoardDTO {

	private Integer componentCount;
	private Integer customerPOCount;
	private Integer WorkJobOrderCount;
	private Integer inspectionLineItemCount;
	private Integer inspectionReportCount;
	private Integer InspectionMeasurementCount;
	
	public Integer getComponentCount() {
		return componentCount;
	}
	public void setComponentCount(Integer componentCount) {
		this.componentCount = componentCount;
	}
	public Integer getCustomerPOCount() {
		return customerPOCount;
	}
	public void setCustomerPOCount(Integer customerPOCount) {
		this.customerPOCount = customerPOCount;
	}
	public Integer getWorkJobOrderCount() {
		return WorkJobOrderCount;
	}
	public void setWorkJobOrderCount(Integer workJobOrderCount) {
		WorkJobOrderCount = workJobOrderCount;
	}
	public Integer getInspectionLineItemCount() {
		return inspectionLineItemCount;
	}
	public void setInspectionLineItemCount(Integer inspectionLineItemCount) {
		this.inspectionLineItemCount = inspectionLineItemCount;
	}
	public Integer getInspectionReportCount() {
		return inspectionReportCount;
	}
	public void setInspectionReportCount(Integer inspectionReportCount) {
		this.inspectionReportCount = inspectionReportCount;
	}
	public Integer getInspectionMeasurementCount() {
		return InspectionMeasurementCount;
	}
	public void setInspectionMeasurementCount(Integer inspectionMeasurementCount) {
		InspectionMeasurementCount = inspectionMeasurementCount;
	}
}
