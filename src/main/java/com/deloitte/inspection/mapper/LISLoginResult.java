package com.deloitte.inspection.mapper;

import java.util.Date;

import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;

public class LISLoginResult {
	
	private String _id;
	
	private String loginId;

	private String password;

	private Date createdTimestamp;

	private Date updatedTimestamp;

	private String createdBy;

	private String updatedBy;

	private LISSubscriberMaster subscriberMaster;

	private String adminId;

	private String isActive;

	private String isSessionActive;
	
	private LISUserMasterCreate userMasterCreate;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
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

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
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

	public LISUserMasterCreate getUserMasterCreate() {
		return userMasterCreate;
	}

	public void setUserMasterCreate(LISUserMasterCreate userMasterCreate) {
		this.userMasterCreate = userMasterCreate;
	}

}