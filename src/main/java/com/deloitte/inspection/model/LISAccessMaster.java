package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LIS_ACMDS")
public class LISAccessMaster{
	
	private String accessMasterId;
	
	private String screenNumber;
	
	private Date createdTimeStamp;
	
	private String createdBy;
	
	private Date updatedTimeStamp;
	
	private String updatedBy;
	
	private Integer subscriberMasterId;//foreign key
	
	private Integer userTypeMasterId;//foreign key
	
	private String isActive;

	public String getAccessMasterId() {
		return accessMasterId;
	}

	public void setAccessMasterId(String accessMasterId) {
		this.accessMasterId = accessMasterId;
	}

	public String getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}

	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public Integer getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(Integer subscriberMasterId) {
		this.subscriberMasterId = subscriberMasterId;
	}

	public Integer getUserTypeMasterId() {
		return userTypeMasterId;
	}

	public void setUserTypeMasterId(Integer userTypeMasterId) {
		this.userTypeMasterId = userTypeMasterId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	

}
