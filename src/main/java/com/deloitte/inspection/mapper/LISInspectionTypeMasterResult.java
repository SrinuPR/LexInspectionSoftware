package com.deloitte.inspection.mapper;

import java.util.Date;

import com.deloitte.inspection.model.LISSubscriberMaster;

public class LISInspectionTypeMasterResult {
	
	private String inspTypeUniqueId;
	
	private Integer inspTypeId;
	
	private String inspTypeName;
	
	private String createdBy;
	
	private Date createdTimestamp;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private String isActive;
	
	private String userId;
	
	private LISSubscriberMaster subscriberMaster;

	public String getInspTypeUniqueId() {
		return inspTypeUniqueId;
	}

	public void setInspTypeUniqueId(String inspTypeUniqueId) {
		this.inspTypeUniqueId = inspTypeUniqueId;
	}

	public Integer getInspTypeId() {
		return inspTypeId;
	}

	public void setInspTypeId(Integer inspTypeId) {
		this.inspTypeId = inspTypeId;
	}

	public String getInspTypeName() {
		return inspTypeName;
	}

	public void setInspTypeName(String inspTypeName) {
		this.inspTypeName = inspTypeName;
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

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

}
