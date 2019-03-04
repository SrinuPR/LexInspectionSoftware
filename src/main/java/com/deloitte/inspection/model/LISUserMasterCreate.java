package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LIS_UMACS")
public class LISUserMasterCreate {

    @Id
    private String _id;
    
	private String userId;
	
	private String userName;
	
	private String oldPassword1;
	
	private String oldPassword2;
	
	private String activePassword;
	
	private Integer userTypeId;
	
	private String createdBy;

	private Date createdTimestamp;
	
	private String updatedBy;

	private Date updatedTimestamp;
	
	private String isActive;
	
	private String subscriberMasterId;//foreign key

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPassword1() {
		return oldPassword1;
	}

	public void setOldPassword1(String oldPassword1) {
		this.oldPassword1 = oldPassword1;
	}

	public String getOldPassword2() {
		return oldPassword2;
	}

	public void setOldPassword2(String oldPassword2) {
		this.oldPassword2 = oldPassword2;
	}

	public String getActivePassword() {
		return activePassword;
	}

	public void setActivePassword(String activePassword) {
		this.activePassword = activePassword;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
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

	public String getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(String subscriberMasterId) {
		this.subscriberMasterId = subscriberMasterId;
	}

}
