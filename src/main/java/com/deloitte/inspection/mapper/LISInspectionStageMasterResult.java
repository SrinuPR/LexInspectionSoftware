package com.deloitte.inspection.mapper;

import java.util.Date;

import com.deloitte.inspection.model.LISSubscriberMaster;

public class LISInspectionStageMasterResult {
	
	private String inspStageUniqueId;
	
	private Integer inspStageId;
	
	private String inspStageName;
	
	private String createdBy;
	
	private Date createdTimestamp;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private String isActive;
	
    private LISSubscriberMaster subscriberMaster;
    
	private String userId;

	public String getInspStageUniqueId() {
		return inspStageUniqueId;
	}

	public void setInspStageUniqueId(String inspStageUniqueId) {
		this.inspStageUniqueId = inspStageUniqueId;
	}

	public Integer getInspStageId() {
		return inspStageId;
	}

	public void setInspStageId(Integer inspStageId) {
		this.inspStageId = inspStageId;
	}

	public String getInspStageName() {
		return inspStageName;
	}

	public void setInspStageName(String inspStageName) {
		this.inspStageName = inspStageName;
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

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
