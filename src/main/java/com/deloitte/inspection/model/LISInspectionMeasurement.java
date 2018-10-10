package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIS_IMDES")
public class LISInspectionMeasurement implements Serializable{
	
	private static final long serialVersionUID = 382945870712132280L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INSPECTION_ID")
	private Integer inspectionId;
	
	
	@Column(name = "USER_ID" , length = 20)
	private String userId;
	
	/*@Column(name = "COMPONENT_PRODUCT_DRAWING_NUM", length=50 )
	private String componentProductDrawingNum;
	*/
	@Column(name = "INSPECTION_REPORT_NUM", length=30 )
	private String inspection_report_num;
	
	@Column(name = "WORK_JOB_ORDER_NUM" )
	private Date workJobOrderNum;
	
	@Column(name = "LOT_NUMBER" , length = 10)
	private String lotNumber;
	
	@Column(name = "LOT_SIZE" )
	private Integer lotSize;
	
	@Column(name = "MANUFACTURING_BATCH_NUM" , length = 30)
	private String manufacturingBatchNum;
	
	@Column(name = "MANUFACTURING_BATCH_SIZE")
	private Integer manufacturingBatchSize;
	
	@Column(name = "COMPONENT_PRODUCT_NAME" , length = 50)
	private String componentProductName;
	
	@Column(name = "INSPECTION_TYPE_NAME" , length = 30)
	private String inspectionTypeName;
	
	@Column(name = "INSPECTION_STAGE_ID" )
	private Integer inspectionStageId;
	
	@Column(name = "FACILITY_MACHINE_NUMBER" )
	private Integer facilityMachineNumber;
	
	@Column(name = "FACILITY_MACHINE_NAME" , length = 150)
	private String facilityMachineName;
	
	@Column(name = "USER_NAME" , length = 150)
	private String userName;
	
	@Column(name = "INSPECTION_DATE" )
	private Date inspectionDate;
	
	@Column(name = "SHIFT_ID" )
	private Integer shift_id;
	
	@Column(name = "PART_IDENTIFICATION_NUM" , length = 30)
	private String partIdentificationNum;
	
	@Column(name = "ACTUAL_MEASUREMENT" )
	private Double actual_measurement;
	
	@Column(name = "ACTUAL_BASE_MEASURE" , length = 5)
	private String actualBaseMeasure;
	
	@Column(name = "ACTUAL_UPPER_LIMIT" )
	private Double actualUpperLimit;
	
	@Column(name = "ACTUAL_LOWER_LIMIT" )
	private Double actualLowerLimit;
	
	@Column(name = "DEVIATION" )
	private Double devation;
	
	@Column(name = "ACCEPT_REJECT" , length = 8)
	private String acceptReject;
	
	@Column(name = "INSPECTED_QUANTITY" )
	private Integer inspectdQuantity;
	
	@Column(name = "PRODUCT_QUANTITY" )
	private Integer productQuantity;
	
	@Column(name = "CREATED_BY" , length = 50)
	private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Column(name = "UPDATED_BY", length = 50)
	private String updatedBy;
	
	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="SUBSCRIBER_ID")
    private LISSubscriberMaster subscriberMaster;

	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="COMPONENT_PRODUCT_DRAWING_NUM")
    private LISWorkOrderMaster workOrderMaster;

	public Integer getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(Integer inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*public String getComponentProductDrawingNum() {
		return componentProductDrawingNum;
	}

	public void setComponentProductDrawingNum(String componentProductDrawingNum) {
		this.componentProductDrawingNum = componentProductDrawingNum;
	}*/

	public String getInspection_report_num() {
		return inspection_report_num;
	}

	public void setInspection_report_num(String inspection_report_num) {
		this.inspection_report_num = inspection_report_num;
	}

	public Date getWorkJobOrderNum() {
		return workJobOrderNum;
	}

	public void setWorkJobOrderNum(Date workJobOrderNum) {
		this.workJobOrderNum = workJobOrderNum;
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

	public String getManufacturingBatchNum() {
		return manufacturingBatchNum;
	}

	public void setManufacturingBatchNum(String manufacturingBatchNum) {
		this.manufacturingBatchNum = manufacturingBatchNum;
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

	public String getInspectionTypeName() {
		return inspectionTypeName;
	}

	public void setInspectionTypeName(String inspectionTypeName) {
		this.inspectionTypeName = inspectionTypeName;
	}

	public Integer getInspectionStageId() {
		return inspectionStageId;
	}

	public void setInspectionStageId(Integer inspectionStageId) {
		this.inspectionStageId = inspectionStageId;
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

	public Integer getShift_id() {
		return shift_id;
	}

	public void setShift_id(Integer shift_id) {
		this.shift_id = shift_id;
	}

	public String getPartIdentificationNum() {
		return partIdentificationNum;
	}

	public void setPartIdentificationNum(String partIdentificationNum) {
		this.partIdentificationNum = partIdentificationNum;
	}

	public Double getActual_measurement() {
		return actual_measurement;
	}

	public void setActual_measurement(Double actual_measurement) {
		this.actual_measurement = actual_measurement;
	}

	public String getActualBaseMeasure() {
		return actualBaseMeasure;
	}

	public void setActualBaseMeasure(String actualBaseMeasure) {
		this.actualBaseMeasure = actualBaseMeasure;
	}

	public Double getActualUpperLimit() {
		return actualUpperLimit;
	}

	public void setActualUpperLimit(Double actualUpperLimit) {
		this.actualUpperLimit = actualUpperLimit;
	}

	public Double getActualLowerLimit() {
		return actualLowerLimit;
	}

	public void setActualLowerLimit(Double actualLowerLimit) {
		this.actualLowerLimit = actualLowerLimit;
	}

	public Double getDevation() {
		return devation;
	}

	public void setDevation(Double devation) {
		this.devation = devation;
	}

	public String getAcceptReject() {
		return acceptReject;
	}

	public void setAcceptReject(String acceptReject) {
		this.acceptReject = acceptReject;
	}

	public Integer getInspectdQuantity() {
		return inspectdQuantity;
	}

	public void setInspectdQuantity(Integer inspectdQuantity) {
		this.inspectdQuantity = inspectdQuantity;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
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

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public LISWorkOrderMaster getWorkOrderMaster() {
		return workOrderMaster;
	}

	public void setWorkOrderMaster(LISWorkOrderMaster workOrderMaster) {
		this.workOrderMaster = workOrderMaster;
	}

	
}
