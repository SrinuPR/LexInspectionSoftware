package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LIS_LOGIN")
public class LISLogin {

	@Id
	private String _id;
	
	private String loginId;

	private String password;

	private Date createdTimestamp;

	private Date updatedTimestamp;

	private String createdBy;

	private String updatedBy;

	private String subscriberMasterId;//foreign Key

	private String adminId;

	private String isActive;

	private String isSessionActive;
	
	private String userMasterCreateId;//foreign Key

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(String subscriberMasterId) {
		this.subscriberMasterId = subscriberMasterId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsSessionActive() {
		return isSessionActive;
	}

	public void setIsSessionActive(String isSessionActive) {
		this.isSessionActive = isSessionActive;
	}

	public String getUserMasterCreateId() {
		return userMasterCreateId;
	}

	public void setUserMasterCreateId(String userMasterCreateId) {
		this.userMasterCreateId = userMasterCreateId;
	}


}
