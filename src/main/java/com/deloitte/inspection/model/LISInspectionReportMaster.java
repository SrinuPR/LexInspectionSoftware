package com.deloitte.inspection.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_IRMCS")
public class LISInspectionReportMaster implements Serializable{

	private static final long serialVersionUID = 382945870712132210L;
	
	@Id
	// @Field(value =  "IRMCS_ID")
	private BigInteger inspRptMasterId;
	
	// @Field(value =  "INSPECTION_REPORT_NUMBER")
	private Integer inspReportNumber;
	
	// @Field(value =  "SUBSCRIBER_ID")
	private Integer subscriberId;
	
	// @Field(value =  "SUBSCRIBER_NAME")
	private String subscriberName;
		
	// @Field(value =  "INMDC_COMPONENT_PRODUCT_DRAW_NUM")
	private String compProdDrawNum;
	
	// @Field(value =  "COMP_PROD_NAME")
	private String componentProdcuctName;
		
	// @Field(value =  "WORK_JOB_ORDER_NUMBER")
	private String workJobOrderNumber;
	
	// @Field(value =  "WORK_JOB_ORDER_ID")
	private Integer workJobOrderId;
	
	// @Field(value =  "LOT_NUMBER")
	private String lotNumber;
	
	// @Field(value =  "LOT_SIZE")
	private Integer lotSize;
	
	// @Field(value =  "INSPECTION_TYPE_ID")
	private Integer inspectionTypeId;
	
	// @Field(value =  "INSPECTION_STAGE_ID")
	private Integer InspectionStageId;
	
	// @Field(value =  "MANUFACTURING_BATCH_NUMBER")
	private String manufacturingBatchNumber;
	
	// @Field(value =  "MANUFACTURING_BATCH_SIZE")
	private Integer manufacturingBatchSize;
	
	// @Field(value =  "CUSTOMER_PO_NUMBER")
	private String customerPoNumber;
	
	// @Field(value =  "CUSTOMER_PO_DATE" )
	private Date customerPoDate;
	
	// @Field(value =  "CUSTOMER_PO_QUANTITY")
	private Integer customerPoQuantity;
	
	// @Field(value =  "USER_ID")
	private String userID;
	
	// @Field(value =  "CREATED_BY")
	private String createdBy;
	
	// @Field(value =  "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	// @Field(value =  "UPDATED_BY")
	private String updatedBy;
	
	// @Field(value =  "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	// @Field(value =  "IS_ACTIVE")
	private String isActive;
	
	// @Field(value =  "REPORT_STATUS")
	private String reportStatus;
	
	/**
	 * @return the workJobOrderNumber
	 */
	public String getWorkJobOrderNumber() {
		return workJobOrderNumber;
	}

	/**
	 * @param workJobOrderNumber the workJobOrderNumber to set
	 */
	public void setWorkJobOrderNumber(String workJobOrderNumber) {
		this.workJobOrderNumber = workJobOrderNumber;
	}

	/**
	 * @return the lotNumber
	 */
	public String getLotNumber() {
		return lotNumber;
	}

	/**
	 * @param lotNumber the lotNumber to set
	 */
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	/**
	 * @return the lotSize
	 */
	public Integer getLotSize() {
		return lotSize;
	}

	/**
	 * @param lotSize the lotSize to set
	 */
	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	/**
	 * @return the manufacturingBatchNumber
	 */
	public String getManufacturingBatchNumber() {
		return manufacturingBatchNumber;
	}

	/**
	 * @param manufacturingBatchNumber the manufacturingBatchNumber to set
	 */
	public void setManufacturingBatchNumber(String manufacturingBatchNumber) {
		this.manufacturingBatchNumber = manufacturingBatchNumber;
	}

	/**
	 * @return the manufacturingBatchSize
	 */
	public Integer getManufacturingBatchSize() {
		return manufacturingBatchSize;
	}

	/**
	 * @param manufacturingBatchSize the manufacturingBatchSize to set
	 */
	public void setManufacturingBatchSize(Integer manufacturingBatchSize) {
		this.manufacturingBatchSize = manufacturingBatchSize;
	}

	/**
	 * @return the customerPoNumber
	 */
	public String getCustomerPoNumber() {
		return customerPoNumber;
	}

	/**
	 * @param customerPoNumber the customerPoNumber to set
	 */
	public void setCustomerPoNumber(String customerPoNumber) {
		this.customerPoNumber = customerPoNumber;
	}

	/**
	 * @return the customerPoDate
	 */
	public Date getCustomerPoDate() {
		return customerPoDate;
	}

	/**
	 * @param customerPoDate the customerPoDate to set
	 */
	public void setCustomerPoDate(Date customerPoDate) {
		this.customerPoDate = customerPoDate;
	}

	/**
	 * @return the customerPoQuantity
	 */
	public Integer getCustomerPoQuantity() {
		return customerPoQuantity;
	}

	/**
	 * @param customerPoQuantity the customerPoQuantity to set
	 */
	public void setCustomerPoQuantity(Integer customerPoQuantity) {
		this.customerPoQuantity = customerPoQuantity;
	}

	/**
	 * @return the inspReportNumber
	 */
	public Integer getInspReportNumber() {
		return inspReportNumber;
	}

	/**
	 * @param inspReportNumber the inspReportNumber to set
	 */
	public void setInspReportNumber(Integer inspReportNumber) {
		this.inspReportNumber = inspReportNumber;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdTimestamp
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * @param createdTimestamp the createdTimestamp to set
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedTimestamp
	 */
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	/**
	 * @param updatedTimestamp the updatedTimestamp to set
	 */
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the inspRptMasterId
	 */
	public BigInteger getInspRptMasterId() {
		return inspRptMasterId;
	}

	/**
	 * @param inspRptMasterId the inspRptMasterId to set
	 */
	public void setInspRptMasterId(BigInteger inspRptMasterId) {
		this.inspRptMasterId = inspRptMasterId;
	}

	/**
	 * @return the subscriberId
	 */
	public Integer getSubscriberId() {
		return subscriberId;
	}

	/**
	 * @param subscriberId the subscriberId to set
	 */
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	/**
	 * @return the subscriberName
	 */
	public String getSubscriberName() {
		return subscriberName;
	}

	/**
	 * @param subscriberName the subscriberName to set
	 */
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	/**
	 * @return the componentProdcuctName
	 */
	public String getComponentProdcuctName() {
		return componentProdcuctName;
	}

	/**
	 * @param componentProdcuctName the componentProdcuctName to set
	 */
	public void setComponentProdcuctName(String componentProdcuctName) {
		this.componentProdcuctName = componentProdcuctName;
	}

	/**
	 * @return the inspectionTypeId
	 */
	public Integer getInspectionTypeId() {
		return inspectionTypeId;
	}

	/**
	 * @param inspectionTypeId the inspectionTypeId to set
	 */
	public void setInspectionTypeId(Integer inspectionTypeId) {
		this.inspectionTypeId = inspectionTypeId;
	}

	/**
	 * @return the inspectionStageId
	 */
	public Integer getInspectionStageId() {
		return InspectionStageId;
	}

	/**
	 * @param inspectionStageId the inspectionStageId to set
	 */
	public void setInspectionStageId(Integer inspectionStageId) {
		InspectionStageId = inspectionStageId;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the compProdDrawNum
	 */
	public String getCompProdDrawNum() {
		return compProdDrawNum;
	}

	/**
	 * @param compProdDrawNum the compProdDrawNum to set
	 */
	public void setCompProdDrawNum(String compProdDrawNum) {
		this.compProdDrawNum = compProdDrawNum;
	}

	public Integer getWorkJobOrderId() {
		return workJobOrderId;
	}

	public void setWorkJobOrderId(Integer workJobOrderId) {
		this.workJobOrderId = workJobOrderId;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	
}
