package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_LOGIN")
public class LISLogin implements Serializable {

	private static final long serialVersionUID = 5112717685665555840L;

	@Id
	private long id;

	@Field(value = "LOGIN_ID")
	private Integer loginId;

	@Field(value = "PASSWORD")
	private String password;

	@Field(value = "CREATED_TIMESTAMP")
	private Date createdTimestamp;

	@Field(value = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;

	@Field(value = "CREATED_BY")
	private String createdBy;

	@Field(value = "UPDATED_BY")
	private String updatedBy;

	private LISSubscriberMaster subscriber;

	@Field(value = "ADMIN_ID")
	private String adminId;

	@Field(value = "IS_ACTIVE")
	private char isActive;

	@Field(value = "IS_SESSION_ACTIVE")
	private char isSessionActive;
	
	private LISUserMasterCreate user;

	public long getId() {
		return id;
	}

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

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
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

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public char getIsSessionActive() {
		return isSessionActive;
	}

	public void setIsSessionActive(char isSessionActive) {
		this.isSessionActive = isSessionActive;
	}
}
