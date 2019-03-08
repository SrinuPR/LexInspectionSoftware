package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LIS_WOMCS")
public class LISWorkJobOrderMaster {
	
	private String wjOrderId;
	
	private String workJobOrderNumber;
	
	private Date workJobOrderDate;
	
	private String lotNumber;

	private Integer lotSize;

	private String lotSizeUnits;
	
	private String manufacturingBatchNumber;
	
	private Integer manufacturingBatchSize;
	
	private String manufacturingBatchUnits;
	
	private String workOrderJobNotes;
	
	private String createdBy;
	
	private Date createdTimestamp;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private String isActive;
	
	private String recordInProcess;

	private Integer subscriberMasterId;//foreign key
	
	private String userMasterCreateId;//foreign key
	
	private String maintainMasterDataComponentId;//foreign key
	
	private String purchaseOrderMasterId;//foreign key

	public String getWjOrderId() {
		return wjOrderId;
	}

	public void setWjOrderId(String wjOrderId) {
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

	public String getRecordInProcess() {
		return recordInProcess;
	}

	public void setRecordInProcess(String recordInProcess) {
		this.recordInProcess = recordInProcess;
	}

	public Integer getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(Integer subscriberMasterId) {
		this.subscriberMasterId = subscriberMasterId;
	}

	public String getUserMasterCreateId() {
		return userMasterCreateId;
	}

	public void setUserMasterCreateId(String userMasterCreateId) {
		this.userMasterCreateId = userMasterCreateId;
	}

	public String getMaintainMasterDataComponentId() {
		return maintainMasterDataComponentId;
	}

	public void setMaintainMasterDataComponentId(String maintainMasterDataComponentId) {
		this.maintainMasterDataComponentId = maintainMasterDataComponentId;
	}

	public String getPurchaseOrderMasterId() {
		return purchaseOrderMasterId;
	}

	public void setPurchaseOrderMasterId(String purchaseOrderMasterId) {
		this.purchaseOrderMasterId = purchaseOrderMasterId;
	}
}
