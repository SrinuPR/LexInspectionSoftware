package com.deloitte.inspection.mapper;

import java.util.Date;

import com.deloitte.inspection.model.LISAccessMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;

public class LISUserTypeMasterResult {
	
	private String _id;
	
	private String userTypeId;
	
	private String userTypeName;
	
	private Date createdTimestamp;
	
	private Date updatedTimestamp;

	private String createdBy;
	
	private String updatedBy;
	
	private String isActive;
	
	private LISSubscriberMaster subscriberMaster;
	
	private LISAccessMaster lisAccessMaster;


	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public LISAccessMaster getLisAccessMaster() {
		return lisAccessMaster;
	}

	public void setLisAccessMaster(LISAccessMaster lisAccessMaster) {
		this.lisAccessMaster = lisAccessMaster;
	}
}
