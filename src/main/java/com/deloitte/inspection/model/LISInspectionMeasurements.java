package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIS_IMDES")
public class LISInspectionMeasurements implements Serializable{

	private static final long serialVersionUID = -7719637509662433018L;
	
	@Id
	@Column(name = "IMDES_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String inspectionMeasurementId;
	
	@Column(name = "SUBSCRIBER_ID", length = 5, nullable = false)
	private Integer subscriberId;
	
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
	private Date shiftName;
	
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
	
	@Column(name = "MEASUREMENT_NAME", length = 50)
	private String measurementName;
	
	@Column(name = "MEASURED_VALUE", nullable = false)
	private Float measuredValue;
	
	@Column(name = "ACTUAL_BASE_MEASURE", length = 5, nullable = false)
	private String actualBaseMeasure;
	
	@Column(name = "ACTUAL_UPPER_LIMIT")
	private Float actualUpperLimit;
	
	@Column(name = "ACTUAL_LOWER_LIMIT")
	private Float actualLowerLimit;
	
	@Column(name = "DEVIATION")
	private Float deviation;
	
	@Column(name = "STATUS", length = 8)
	private String status;
	
	@Column(name = "PART_STATUS", length = 10, nullable = false)
	private String partStatus;
	
	@Column(name = "INSPECTED_QUANTITY", length = 3, nullable = false)
	private Integer inspectedQuantity;
	
	@Column(name = "PRODUCED_QUANTITY", length = 3, nullable = false)
	private Integer producedQuantity;
	
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

	public Date getShiftName() {
		return shiftName;
	}

	public void setShiftName(Date shiftName) {
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

	public String getMeasurementName() {
		return measurementName;
	}

	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
	}

	public Float getMeasuredValue() {
		return measuredValue;
	}

	public void setMeasuredValue(Float measuredValue) {
		this.measuredValue = measuredValue;
	}

	public Float getActualUpperLimit() {
		return actualUpperLimit;
	}

	public void setActualUpperLimit(Float actualUpperLimit) {
		this.actualUpperLimit = actualUpperLimit;
	}

	public Float getActualLowerLimit() {
		return actualLowerLimit;
	}

	public void setActualLowerLimit(Float actualLowerLimit) {
		this.actualLowerLimit = actualLowerLimit;
	}

	public Float getDeviation() {
		return deviation;
	}

	public void setDeviation(Float deviation) {
		this.deviation = deviation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getActualBaseMeasure() {
		return actualBaseMeasure;
	}

	public void setActualBaseMeasure(String actualBaseMeasure) {
		this.actualBaseMeasure = actualBaseMeasure;
	}
	
}
