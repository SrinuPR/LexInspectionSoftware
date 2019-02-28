package com.deloitte.inspection.dto;

public class AccessMasterDTO {
	
	private Integer subscriberId;
	private Integer userTypeId;
	private String screenNumbers;
	private String userTypeName;
	private String userId;
	private Integer accessMasterId;
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public Integer getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getScreenNumbers() {
		return screenNumbers;
	}
	public void setScreenNumbers(String screenNumbers) {
		this.screenNumbers = screenNumbers;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getAccessMasterId() {
		return accessMasterId;
	}
	public void setAccessMasterId(Integer accessMasterId) {
		this.accessMasterId = accessMasterId;
	}
	
}
