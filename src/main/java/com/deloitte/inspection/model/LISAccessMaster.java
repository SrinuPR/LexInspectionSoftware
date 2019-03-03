package com.deloitte.inspection.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_ACMDS")
public class LISAccessMaster implements Serializable{
	
	private static final long serialVersionUID = 5112717685665555840L;
	
	@Id
	// @Field(value = "ACMDS_ID")
	private BigInteger accessMasterId;
	
	// @Field(value = "SCREEN_NUMBER")
	private String screenNumber;
	
	// @Field(value = "CREATED_TIMESTAMP")
	private Date createdTimeStamp;
	
	// @Field(value = "CREATED_BY")
	private String createdBy;
	
	// @Field(value = "UPDATED_TIMESTAMP")
	private Date updatedTimeStamp;
	
	// @Field(value = "UPDATED_BY")
	private String updatedBy;
	
	private LISSubscriberMaster subscriber;
	
	private LISUserTypeMaster userType;
	
	// @Field(value = "IS_ACTIVE")
	private String isActive;
	
	public String getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}

	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimestamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}

	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public BigInteger getAccessMasterId() {
		return accessMasterId;
	}

	public void setAccessMasterId(BigInteger accessMasterId) {
		this.accessMasterId = accessMasterId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}

	public LISUserTypeMaster getUserType() {
		return userType;
	}

	public void setUserType(LISUserTypeMaster userType) {
		this.userType = userType;
	}
}
