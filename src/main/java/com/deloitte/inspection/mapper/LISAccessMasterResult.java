package com.deloitte.inspection.mapper;

import java.util.Date;

import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserTypeMaster;

public class LISAccessMasterResult {
	
	private String _id;
	
	private String accessMasterId;
	
	private String screenNumber;
	
	private Date createdTimeStamp;
	
	private String createdBy;
	
	private Date updatedTimeStamp;
	
	private String updatedBy;
	
	private LISSubscriberMaster subscriberMaster;
	
	private LISUserTypeMaster userTypeMaster;
	
	private String isActive;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

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

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public LISUserTypeMaster getUserTypeMaster() {
		return userTypeMaster;
	}

	public void setUserTypeMaster(LISUserTypeMaster userTypeMaster) {
		this.userTypeMaster = userTypeMaster;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
