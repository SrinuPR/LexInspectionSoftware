package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LIS_CPMCS")
public class LISPurchaseOrderMaster {

	@Id
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
	
	private String subscriberMasterId;//foreign key

    private String userMasterCreateId;//foreign key
    
    private String maintainMasterDataComponentId;//foreign key
    

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

	public String getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(String subscriberMasterId) {
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


}
