package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIS_MLOSS")
public class LISMasterListOfScreensForSubscriber implements Serializable{

	private static final long serialVersionUID = 8730811844910414462L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MLOSS_ID")
	private Integer masterListId;
	
	@Column(name = "SUBSCRIBER_ID" , nullable = false)
	private Integer subscriberId;
	
	@Column(name = "IS_ACTIVE")
	private char isActive;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Column(name = "SCREEN_NAME" , length = 150, nullable = false)
	private String screenName;
	
	@Column(name = "SCREEN_NUMBER" , length = 4, nullable = false)
	private String screenNumber;

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

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
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

}
