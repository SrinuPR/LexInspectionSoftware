package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_IMDES")
public class LISInspectionMeasurements implements Serializable{

	private static final long serialVersionUID = -7719637509662433018L;
	
	@Id
	private long id;
	
	@Field(value =  "IMDES_ID")
	private Integer inspectionMeasurementId;
	
	@Field(value =  "SUBSCRIBER_ID")
	private Integer subscriberId;
	
	@Field(value =  "RECORD_IN_PROCESS")
	private char recordInProcess;
	
	@Field(value =  "SUBSCRIBER_NAME")
	private String subscriberName;
	
	@Field(value =  "USER_ID")
	private String userId;
	
	@Field(value =  "IRMCS_COMP_PRD_DRAW_NUM")
	private String irmcsCompProductDrawNum;
	
	@Field(value =  "COMP_PROD_DRAW_NUM")
	private String compProductDrawNum;
	
	@Field(value =  "INSPECTION_REPORT_NUMBER")
	private String inspectionReportNumber;
	
	@Field(value =  "WORK_JOB_ORDER_NUMBER")
	private String workJobOrderNumber;
	
	@Field(value =  "LOT_NUMBER")
	private String lotNumber;
	
	@Field(value =  "LOT_SIZE")
	private Integer lotSize;
	
	@Field(value =  "MANUFACTURING_BATCH_NUMBER")
	private String manufacturingBatchNumber;
	
	@Field(value =  "MANUFACTURING_BATCH_SIZE")
	private Integer manufacturingBatchSize;
	
	@Field(value =  "COMP_PROD_NAME")
	private String componentProductName;
	
	@Field(value =  "INSPECTION_TYPE")
	private String inspectionType;
	
	@Field(value =  "INSPECTION_STAGE")
	private Integer InspectionStage;
	
	@Field(value =  "FACILITY_MACHINE_NUMBER")
	private Integer facilityMachineNumber;
	
	@Field(value =  "FACILITY_MACHINE_NAME")
	private String facilityMachineName;
	
	@Field(value =  "USER_NAME")
	private String userName;
	
	@Field(value =  "INSPECTION_DATE")
	private Date inspectionDate;
	
	@Field(value =  "SHIFT_ID")
	private String shiftID;
	
	@Field(value =  "SHIFT_NAME")
	private String shiftName;
	
	@Field(value =  "CUSTOMER_PO_NUMBER")
	private String customerPONumber;
	
	@Field(value =  "CUSTOMER_PO_DATE")
	private Date customerPODate;
	
	@Field(value =  "CUSTOMER_PO_QUANTITY")
	private Integer customerPOQuantity;
	
	@Field(value =  "CUSTOMER_NAME_ADDRESS")
	private String customerNameAddress;
	
	@Field(value =  "PART_IDENTIFICATION_NUMBER")
	private String partIdentificationNumber;
	
	@Field(value =  "MEASUREMENT_RECORD_STATUS")
	private String measurementRecordstatus;
	
	@Field(value =  "CREATED_BY")
	private String createdBy;
	
	@Field(value =  "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Field(value =  "UPDATED_BY")
	private String updatedBy;
	
	@Field(value =  "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@Field(value =  "IS_ACTIVE")
	private char isActive;
	
	@Field(value =  "PART_STATUS")
	private String partStatus;

	@Field(value =  "INSPECTED_QUANTITY")
	private Integer inspectedQuantity;
	
	@Field(value =  "PRODUCED_QUANTITY")
	private Integer producedQuantity;
	
	private List<LISPartIdentification> partIdentifications;
	
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

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public String getShiftID() {
		return shiftID;
	}

	public void setShiftID(String shiftID) {
		this.shiftID = shiftID;
	}

	public char getRecordInProcess() {
		return recordInProcess;
	}

	public void setRecordInProcess(char recordInProcess) {
		this.recordInProcess = recordInProcess;
	}

	public String getMeasurementRecordstatus() {
		return measurementRecordstatus;
	}

	public void setMeasurementRecordstatus(String measurementRecordstatus) {
		this.measurementRecordstatus = measurementRecordstatus;
	}

	public String getPartStatus() {
		return partStatus;
	}

	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
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

	public long getId() {
		return id;
	}

	public String getIrmcsCompProductDrawNum() {
		return irmcsCompProductDrawNum;
	}

	public void setIrmcsCompProductDrawNum(String irmcsCompProductDrawNum) {
		this.irmcsCompProductDrawNum = irmcsCompProductDrawNum;
	}

	public List<LISPartIdentification> getPartIdentifications() {
		return partIdentifications;
	}

	public void setPartIdentifications(List<LISPartIdentification> partIdentifications) {
		this.partIdentifications = partIdentifications;
	}
	
}
