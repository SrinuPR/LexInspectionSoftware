package com.deloitte.inspection.dto;

import java.util.List;

public class AccessMasterDTO {
	
	private Integer subscriberId;
	private Integer userTypeId;
	private String screenNames;
	private String status;
	private String message;
	private String createdBy;
	private String updatedBy;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Integer getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getScreenNames() {
		return screenNames;
	}
	public void setScreenNames(String screenNames) {
		this.screenNames = screenNames;
	}

}
