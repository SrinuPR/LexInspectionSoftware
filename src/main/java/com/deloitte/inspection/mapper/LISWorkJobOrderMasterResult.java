package com.deloitte.inspection.mapper;

import java.util.Date;

import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;

public class LISWorkJobOrderMasterResult {
	
	private String _id;
	
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

	private LISSubscriberMaster subscriberMaster;
	
	private LISUserMasterCreate userMasterCreate;
	
	private LISMaintainMasterDataComponent maintainMasterDataComponent;
	
	private LISPurchaseOrderMaster purchaseOrderMaster;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

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

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public LISUserMasterCreate getUserMasterCreate() {
		return userMasterCreate;
	}

	public void setUserMasterCreate(LISUserMasterCreate userMasterCreate) {
		this.userMasterCreate = userMasterCreate;
	}

	public LISMaintainMasterDataComponent getMaintainMasterDataComponent() {
		return maintainMasterDataComponent;
	}

	public void setMaintainMasterDataComponent(LISMaintainMasterDataComponent maintainMasterDataComponent) {
		this.maintainMasterDataComponent = maintainMasterDataComponent;
	}

	public LISPurchaseOrderMaster getPurchaseOrderMaster() {
		return purchaseOrderMaster;
	}

	public void setPurchaseOrderMaster(LISPurchaseOrderMaster purchaseOrderMaster) {
		this.purchaseOrderMaster = purchaseOrderMaster;
	}

}
