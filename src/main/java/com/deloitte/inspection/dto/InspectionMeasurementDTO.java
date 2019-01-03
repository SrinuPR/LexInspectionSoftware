package com.deloitte.inspection.dto;

import java.util.List;

public class InspectionMeasurementDTO {
	
	private Integer inspectionMeasurementId;
	private Integer reportNumber;
	private Integer subscriberId;
	private String subscriberName;
	private String userId;
	private String compProductDrawNum;
	private String inspectionReportNumber;
	private Integer workOrderId;
	private String workJobOrderNumber;
	private String lotNumber;
	private Integer lotSize;
	private String manufacturingBatchNumber;
	private Integer manufacturingBatchSize;
	private String componentProductName;
	private String inspectionType;
	private Integer InspectionStage;
	private Integer facilityMachineNumber;
	private Integer facilityMachineName;
	private Integer userName;
	private String inspectionDate;
	private String shiftID;
	private String shiftName;
	private String customerPONumber;
	private String customerPODate;
	private String customerNameAddress;
	private String partIdentificationNumber;
	private List<PartIdentificationDTO> partIdentifications;
	private Integer customerPOQuantity;
	
	public Integer getCustomerPOQuantity() {
		return customerPOQuantity;
	}
	public void setCustomerPOQuantity(Integer customerPOQuantity) {
		this.customerPOQuantity = customerPOQuantity;
	}
	public Integer getInspectionMeasurementId() {
		return inspectionMeasurementId;
	}
	public void setInspectionMeasurementId(Integer inspectionMeasurementId) {
		this.inspectionMeasurementId = inspectionMeasurementId;
	}
	public Integer getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getSubscriberName() {
		return subscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompProductDrawNum() {
		return compProductDrawNum;
	}
	public void setCompProductDrawNum(String compProductDrawNum) {
		this.compProductDrawNum = compProductDrawNum;
	}
	public String getInspectionReportNumber() {
		return inspectionReportNumber;
	}
	public void setInspectionReportNumber(String inspectionReportNumber) {
		this.inspectionReportNumber = inspectionReportNumber;
	}
	public Integer getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(Integer workOrderId) {
		this.workOrderId = workOrderId;
	}
	public String getWorkJobOrderNumber() {
		return workJobOrderNumber;
	}
	public void setWorkJobOrderNumber(String workJobOrderNumber) {
		this.workJobOrderNumber = workJobOrderNumber;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public Integer getLotSize() {
		return lotSize;
	}
	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}
	public String getManufacturingBatchNumber() {
		return manufacturingBatchNumber;
	}
	public void setManufacturingBatchNumber(String manufacturingBatchNumber) {
		this.manufacturingBatchNumber = manufacturingBatchNumber;
	}
	public Integer getManufacturingBatchSize() {
		return manufacturingBatchSize;
	}
	public void setManufacturingBatchSize(Integer manufacturingBatchSize) {
		this.manufacturingBatchSize = manufacturingBatchSize;
	}
	public String getComponentProductName() {
		return componentProductName;
	}
	public void setComponentProductName(String componentProductName) {
		this.componentProductName = componentProductName;
	}
	public String getInspectionType() {
		return inspectionType;
	}
	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}
	public Integer getInspectionStage() {
		return InspectionStage;
	}
	public void setInspectionStage(Integer inspectionStage) {
		InspectionStage = inspectionStage;
	}
	public Integer getFacilityMachineNumber() {
		return facilityMachineNumber;
	}
	public void setFacilityMachineNumber(Integer facilityMachineNumber) {
		this.facilityMachineNumber = facilityMachineNumber;
	}
	public Integer getFacilityMachineName() {
		return facilityMachineName;
	}
	public void setFacilityMachineName(Integer facilityMachineName) {
		this.facilityMachineName = facilityMachineName;
	}
	public Integer getUserName() {
		return userName;
	}
	public void setUserName(Integer userName) {
		this.userName = userName;
	}
	public String getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(String inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public String getShiftID() {
		return shiftID;
	}
	public void setShiftID(String shiftID) {
		this.shiftID = shiftID;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public String getCustomerPONumber() {
		return customerPONumber;
	}
	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}
	public String getCustomerPODate() {
		return customerPODate;
	}
	public void setCustomerPODate(String customerPODate) {
		this.customerPODate = customerPODate;
	}
	public String getCustomerNameAddress() {
		return customerNameAddress;
	}
	public void setCustomerNameAddress(String customerNameAddress) {
		this.customerNameAddress = customerNameAddress;
	}
	public String getPartIdentificationNumber() {
		return partIdentificationNumber;
	}
	public void setPartIdentificationNumber(String partIdentificationNumber) {
		this.partIdentificationNumber = partIdentificationNumber;
	}
	public Integer getReportNumber() {
		return reportNumber;
	}
	public void setReportNumber(Integer reportNumber) {
		this.reportNumber = reportNumber;
	}
	public List<PartIdentificationDTO> getPartIdentifications() {
		return partIdentifications;
	}
	public void setPartIdentifications(List<PartIdentificationDTO> partIdentifications) {
		this.partIdentifications = partIdentifications;
	}
}
