package com.deloitte.inspection.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_LOGIN")
public class LISLogin implements Serializable {

	private static final long serialVersionUID = 5112717685665555840L;

	@Id
	private String id;
	
	// // @Field(value = "LOGIN_ID")
	private BigInteger loginId;

	// // @Field(value = "PASSWORD")
	private String password;

	// // @Field(value = "CREATED_TIMESTAMP")
	private Date createdTimestamp;

	// // @Field(value = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;

	// // @Field(value = "CREATED_BY")
	private String createdBy;

	// // @Field(value = "UPDATED_BY")
	private String updatedBy;

	private LISSubscriberMaster subscriber;

	private String adminId;

	// // @Field(value = "IS_ACTIVE")
	private String isActive;

	// // @Field(value = "IS_SESSION_ACTIVE")
	private String isSessionActive;
	
	// // @Field(value = "user")
	private LISUserMasterCreate user;

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}

	public LISUserMasterCreate getUser() {
		return user;
	}

	public void setUser(LISUserMasterCreate user) {
		this.user = user;
	}

	public BigInteger getLoginId() {
		return loginId;
	}

	public void setLoginId(BigInteger loginId) {
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getIsSessionActive() {
		return isSessionActive;
	}

	public void setIsSessionActive(String isSessionActive) {
		this.isSessionActive = isSessionActive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
