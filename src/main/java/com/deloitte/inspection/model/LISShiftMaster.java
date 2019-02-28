package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_SHMCS")
public class LISShiftMaster implements Serializable{
		
	private static final long serialVersionUID = 382945870712132210L;

	@Id
	private long id;
	
	@Field(value =  "SHIFT_ID")
	private String shiftId;
	
	@Field(value =  "SHIFT_NAME")
	private String shiftName;
	
	@Field(value =  "CREATED_BY")
	private String createdBy;
	
	@Field(value =  "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Field(value =  "UPDATED_BY")
	private String updatedBy;
	
	@Field(value =  "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@Field(value =  "IS_ACTIVE")
	private String isActive;
	
	@Field(value =  "RECORD_IN_PROCESS")
	private char recordInProcess;
	
	private LISSubscriberMaster subscriber;
	
	@Field(value =  "USER_ID")
	private String userId;	

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public char getRecordInProcess() {
		return recordInProcess;
	}

	public void setRecordInProcess(char recordInProcess) {
		this.recordInProcess = recordInProcess;
	}

	public long getId() {
		return id;
	}

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}
	
}
