package com.deloitte.inspection.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_CPMCS")
public class LISPurchaseOrderMaster implements Serializable{
	
	private static final long serialVersionUID = 382945870712132280L;

	@Id
	// @Field(value =  "CUSTOMER_PO_ID")
	private BigInteger customerPoId;
	
	// @Field(value =  "CUSTOMERPO_NUMBER")
	private String customerPONumber;
	
	// @Field(value =  "CUSTOMERPO_DATE" )
	private Date customerPODate;
	
	// @Field(value =  "CUSTOMERPO_QUANTITY")
	private Integer customerPOQuantity;
	
	// @Field(value =  "NOTES_PO")
	private String notesPO;
	
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
    
    private LISMaintainMasterDataComponent component;

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

	public BigInteger getCustomerPoId() {
		return customerPoId;
	}

	public void setCustomerPoId(BigInteger customerPoId) {
		this.customerPoId = customerPoId;
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

	public LISMaintainMasterDataComponent getComponent() {
		return component;
	}

	public void setComponent(LISMaintainMasterDataComponent component) {
		this.component = component;
	}

}
