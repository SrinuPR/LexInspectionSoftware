package com.deloitte.inspection.mapper;

import java.util.Date;
import java.util.List;

import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;

public class LISPurchaseOrderMasterResult {
	
	private String customerPoId;

	private String customerPONumber;

	private Date customerPODate;
	
	private Integer customerPOQuantity;
	
	private String notesPO;

	private String createdBy;
	
	private Date createdTimestamp;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private String isActive;
	    
	private String recordInProcess;
	
	private LISSubscriberMaster subscriberMaster;

    private LISUserMasterCreate userMasterCreate;
    
    private LISMaintainMasterDataComponent maintainMasterDataComponent;
    
    private List<LISWorkJobOrderMaster> workJobOrderMasters;

	public String getCustomerPoId() {
		return customerPoId;
	}

	public void setCustomerPoId(String customerPoId) {
		this.customerPoId = customerPoId;
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

	public String getNotesPO() {
		return notesPO;
	}

	public void setNotesPO(String notesPO) {
		this.notesPO = notesPO;
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

	public List<LISWorkJobOrderMaster> getWorkJobOrderMasters() {
		return workJobOrderMasters;
	}

	public void setWorkJobOrderMasters(List<LISWorkJobOrderMaster> workJobOrderMasters) {
		this.workJobOrderMasters = workJobOrderMasters;
	}

}
