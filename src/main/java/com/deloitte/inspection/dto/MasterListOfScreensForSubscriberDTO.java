package com.deloitte.inspection.dto;

public class MasterListOfScreensForSubscriberDTO {

	private Integer masterListId;
	private Integer subscriberId;
	private String userId;
	private String screenName;
	private String screenNumber;
	private char isDeleted;
	private char noChage;
	
	public char getNoChage() {
		return noChage;
	}
	public void setNoChage(char noChage) {
		this.noChage = noChage;
	}
	public Integer getMasterListId() {
		return masterListId;
	}
	public void setMasterListId(Integer masterListId) {
		this.masterListId = masterListId;
	}
	public Integer getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getScreenNumber() {
		return screenNumber;
	}
	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}
	public char getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(char isDeleted) {
		this.isDeleted = isDeleted;
	}
}
