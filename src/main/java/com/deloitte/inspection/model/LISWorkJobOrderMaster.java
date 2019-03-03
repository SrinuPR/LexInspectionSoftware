package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_WOMCS")
public class LISWorkJobOrderMaster implements Serializable{

	private static final long serialVersionUID = 917212548437079358L;
	
	@Id
	// // @Field(value =  "WOMCS_ID")
	private Integer wjOrderId;
	
	// // @Field(value =  "WORK_JOB_ORDER_NUMBER")
	private String workJobOrderNumber;
	
	// // @Field(value =  "WORK_JOB_ORDER_DATE")
	private Date workJobOrderDate;
	
	// // @Field(value =  "LOT_NUMBER")
	private String lotNumber;
	
	// // @Field(value =  "LOT_SIZE")
	private Integer lotSize;
	
	// // @Field(value =  "LOT_SIZE_UNITS")
	private String lotSizeUnits;
	
	// // @Field(value =  "MANUFACTURING_BATCH_NUMBER")
	private String manufacturingBatchNumber;
	
	// @Field(value =  "MANUFACTURING_BATCH_SIZE")
	private Integer manufacturingBatchSize;
	
	// @Field(value =  "MANUFACTURING_BATCH_UNITS")
	private String manufacturingBatchUnits;
	
	// @Field(value =  "WORK_ORDER_JOB_NOTES")
	private String workOrderJobNotes;
	
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
	
	// @Field(value =  "RECORD_IN_PROCESS")
	private char recordInProcess;

	private LISSubscriberMaster subscriber;
	
	private LISUserMasterCreate user;
	
	private LISMaintainMasterDataComponent componentData;
	
	private LISPurchaseOrderMaster purchaseOrder;

	public Integer getWjOrderId() {
		return wjOrderId;
	}

	public void setWjOrderId(Integer wjOrderId) {
		this.wjOrderId = wjOrderId;
	}

	public String getWorkJobOrderNumber() {
		return workJobOrderNumber;
	}

	public void setWorkJobOrderNumber(String workJobOrderNumber) {
		this.workJobOrderNumber = workJobOrderNumber;
	}

	public Date getWorkJobOrderDate() {
		return workJobOrderDate;
	}

	public void setWorkJobOrderDate(Date workJobOrderDate) {
		this.workJobOrderDate = workJobOrderDate;
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

	public String getLotSizeUnits() {
		return lotSizeUnits;
	}

	public void setLotSizeUnits(String lotSizeUnits) {
		this.lotSizeUnits = lotSizeUnits;
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

	public String getManufacturingBatchUnits() {
		return manufacturingBatchUnits;
	}

	public void setManufacturingBatchUnits(String manufacturingBatchUnits) {
		this.manufacturingBatchUnits = manufacturingBatchUnits;
	}

	public String getWorkOrderJobNotes() {
		return workOrderJobNotes;
	}

	public void setWorkOrderJobNotes(String workOrderJobNotes) {
		this.workOrderJobNotes = workOrderJobNotes;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public char getRecordInProcess() {
		return recordInProcess;
	}

	public void setRecordInProcess(char recordInProcess) {
		this.recordInProcess = recordInProcess;
	}

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}

	public LISUserMasterCreate getUser() {
		return user;
	}

	public void setUser(LISUserMasterCreate user) {
		this.user = user;
	}

	public LISMaintainMasterDataComponent getComponentData() {
		return componentData;
	}

	public void setComponentData(LISMaintainMasterDataComponent componentData) {
		this.componentData = componentData;
	}

	public LISPurchaseOrderMaster getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(LISPurchaseOrderMaster purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
}
