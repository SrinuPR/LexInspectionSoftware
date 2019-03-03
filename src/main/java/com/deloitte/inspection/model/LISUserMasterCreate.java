package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_UMACS")
public class LISUserMasterCreate implements Serializable {

	private static final long serialVersionUID = 6533302101506254629L;

    @Id
    private String id;
    
	// @Field(value = "USER_ID")
	private String userId;
	
	// @Field(value = "USER_NAME")
	private String userName;
	
	// @Field(value = "OLD_PASSWORD1")
	private String oldPassword1;
	
	// @Field(value = "OLD_PASSWORD2")
	private String oldPassword2;
	
	// @Field(value = "ACTIVE_PASSWORD")
	private String activePassword;
	
	// @Field(value = "USER_TYPE_ID")
	private Integer userTypeId;
	
	// @Field(value = "CREATED_BY")
	private String createdBy;
	
	// @Field(value = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	// @Field(value = "UPDATED_BY")
	private String updatedBy;

	// @Field(value = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	// @Field(value = "IS_ACTIVE")
	private String isActive;
	
	private LISSubscriberMaster subscriber;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
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

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
