package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LIS_IMDES")
public class LISInspectionMeasurements implements Serializable{

	private static final long serialVersionUID = -7719637509662433018L;
	
	@Id
	@Column(name = "IMDES_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer inspectionMeasurementId;
	
	@Column(name = "SUBSCRIBER_ID", length = 5, nullable = false)
	private Integer subscriberId;
	
	@Column(name = "RECORD_IN_PROCESS")
	private char recordInProcess;
	
	@Column(name = "SUBSCRIBER_NAME", length = 50, nullable = false)
	private String subscriberName;
	
	@Column(name = "USER_ID", length = 10, nullable = false)
	private String userId;
	
	@Column(name = "IRMCS_COMP_PRD_DRAW_NUM", length = 50, nullable = false)
	private String compProductDrawNum;
	
	@Column(name = "INSPECTION_REPORT_NUMBER", length = 30, nullable= false)
	private String inspectionReportNumber;
	
	@Column(name = "WORK_JOB_ORDER_NUMBER", length = 30, nullable = false)
	private String workJobOrderNumber;
	
	@Column(name = "LOT_NUMBER", length = 10)
	private String lotNumber;
	
	@Column(name = "LOT_SIZE", length = 5)
	private Integer lotSize;
	
	@Column(name = "MANUFACTURING_BATCH_NUMBER", length = 30)
	private String manufacturingBatchNumber;
	
	@Column(name = "MANUFACTURING_BATCH_SIZE", length = 5)
	private Integer manufacturingBatchSize;
	
	@Column(name = "COMP_PROD_NAME", length = 50)
	private String componentProductName;
	
	@Column(name = "INSPECTION_TYPE", length = 30)
	private String inspectionType;
	
	@Column(name = "INSPECTION_STAGE", length = 10)
	private Integer InspectionStage;
	
	@Column(name = "FACILITY_MACHINE_NUMBER", length = 10, nullable = false)
	private Integer facilityMachineNumber;
	
	@Column(name = "FACILITY_MACHINE_NAME", length = 150)
	private Integer facilityMachineName;
	
	@Column(name = "USER_NAME", length = 150)
	private Integer userName;
	
	@Column(name = "INSPECTION_DATE")
	private Date inspectionDate;
	
	@Column(name = "SHIFT_ID", length = 10, nullable = false)
	private String shiftID;
	
	@Column(name = "SHIFT_NAME", length = 10)
	private String shiftName;
	
	@Column(name = "CUSTOMER_PO_NUMBER", length = 30)
	private String customerPONumber;
	
	@Column(name = "CUSTOMER_PO_DATE")
	private Date customerPODate;
	
	@Column(name = "CUSTOMER_PO_QUANTITY", length = 5)
	private Integer customerPOQuantity;
	
	@Column(name = "CUSTOMER_NAME_ADDRESS", length = 250)
	private String customerNameAddress;
	
	@Column(name = "PART_IDENTIFICATION_NUMBER", length = 30, nullable = false)
	private String partIdentificationNumber;
	
	@Column(name = "MEASUREMENT_RECORD_STATUS", length = 20)
	private String measurementRecordstatus;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@Column(name = "IS_ACTIVE")
	private char isActive;
	
	@Column(name = "PART_STATUS", length = 10, nullable = false)
	private String partStatus;
	
	@OneToMany(mappedBy="inspectionMeasurements", cascade = CascadeType.ALL)
	private List<LISPartIdentification> partIdentifications;

	@Column(name = "INSPECTED_QUANTITY", length = 3, nullable = false)
	private Integer inspectedQuantity;
	
	@Column(name = "PRODUCED_QUANTITY", length = 3, nullable = false)
	private Integer producedQuantity;
	
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
