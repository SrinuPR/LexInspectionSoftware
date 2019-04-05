package com.deloitte.inspection.mapper;

import java.util.Date;
import java.util.List;

import com.deloitte.inspection.model.LISPartIdentification;

public class LISInspectionMeasurementsResult {
	
	private String _id;
	
	private String inspectionMeasurementId;
	
	private Integer subscriberId;
	
	private String recordInProcess;
	
	private String subscriberName;
	
	private String userId;

	private String irmcsCompProductDrawNum;

	private String compProductDrawNum;

	private String inspectionReportNumber;

	private String workJobOrderNumber;

	private String lotNumber;
	
	private Integer lotSize;
	
	private String manufacturingBatchNumber;

	private Integer manufacturingBatchSize;
	
	private String componentProductName;
	
	private String inspectionType;
	
	private Integer InspectionStage;
	
	private Integer facilityMachineNumber;

	private String facilityMachineName;
	
	private String userName;
	
	private Date inspectionDate;
	
	private String shiftID;
	
	private String shiftName;
	
	private String customerPONumber;
	
	private Date customerPODate;
	
	private Integer customerPOQuantity;
	
	private String customerNameAddress;

	private String partIdentificationNumber;
	
	private String measurementRecordstatus;
	
	private String createdBy;
	
	private Date createdTimestamp;
	
	private String isActive;
	
	private List<LISPartIdentification> partIdentifications;
	
	private Integer inspectedQuantity;
	
	private Integer producedQuantity;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private String partStatus;
	

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public String getPartStatus() {
		return partStatus;
	}

	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String getInspectionMeasurementId() {
		return inspectionMeasurementId;
	}

	public void setInspectionMeasurementId(String inspectionMeasurementId) {
		this.inspectionMeasurementId = inspectionMeasurementId;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getRecordInProcess() {
		return recordInProcess;
	}

	public void setRecordInProcess(String recordInProcess) {
		this.recordInProcess = recordInProcess;
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

	public String getIrmcsCompProductDrawNum() {
		return irmcsCompProductDrawNum;
	}

	public void setIrmcsCompProductDrawNum(String irmcsCompProductDrawNum) {
		this.irmcsCompProductDrawNum = irmcsCompProductDrawNum;
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

	public String getFacilityMachineName() {
		return facilityMachineName;
	}

	public void setFacilityMachineName(String facilityMachineName) {
		this.facilityMachineName = facilityMachineName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
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

	public Date getCustomerPODate() {
		return customerPODate;
	}

	public void setCustomerPODate(Date customerPODate) {
		this.customerPODate = customerPODate;
	}

	public Integer getCustomerPOQuantity() {
		return customerPOQuantity;
	}

	public void setCustomerPOQuantity(Integer customerPOQuantity) {
		this.customerPOQuantity = customerPOQuantity;
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

	public String getMeasurementRecordstatus() {
		return measurementRecordstatus;
	}

	public void setMeasurementRecordstatus(String measurementRecordstatus) {
		this.measurementRecordstatus = measurementRecordstatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public List<LISPartIdentification> getPartIdentifications() {
		return partIdentifications;
	}

	public void setPartIdentifications(List<LISPartIdentification> partIdentifications) {
		this.partIdentifications = partIdentifications;
	}

	public Integer getInspectedQuantity() {
		return inspectedQuantity;
	}

	public void setInspectedQuantity(Integer inspectedQuantity) {
		this.inspectedQuantity = inspectedQuantity;
	}

	public Integer getProducedQuantity() {
		return producedQuantity;
	}

	public void setProducedQuantity(Integer producedQuantity) {
		this.producedQuantity = producedQuantity;
	}

}
