package com.deloitte.inspection.dto;

import java.util.Map;

public class CreateUserDTO extends AbstractDTO{
	
	private Integer subscriberId;
	private String userTypeId;
	private String userName;
	private String password;
	private String confirmPassword;
	private String userId;
	private Map<Integer,String > subscriberMap;
	private Map<Integer,String> userTypeMap;
	private String errorMessage;
	private String status; 
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<Integer, String> getSubscriberMap() {
		return subscriberMap;
	}
	public void setSubscriberMap(Map<Integer, String> subscriberMap) {
		this.subscriberMap = subscriberMap;
	}
	public Map<Integer, String> getUserTypeMap() {
		return userTypeMap;
	}
	public void setUserTypeMap(Map<Integer, String> userTypeMap) {
		this.userTypeMap = userTypeMap;
	}

	
}
