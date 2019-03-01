package com.deloitte.inspection.model;
import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_MLOSS")
public class LISMasterListOfScreensForSubscriber implements Serializable {

	private static final long serialVersionUID = 8730811844910414462L;

	@Id
	private long id;
	
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Field(value = "MLOSS_ID")
	private Integer masterListId;
	
	private Integer subscriberId;

	private LISSubscriberMaster subscriber;

	@Field(value = "IS_ACTIVE")
	private char isActive;

	@Field(value = "CREATED_BY")
	private String createdBy;

	@Field(value = "CREATED_TIMESTAMP")
	private Date createdTimestamp;

	@Field(value = "SCREEN_NAME")
	private String screenName;

	@Field(value = "SCREEN_NUMBER")
	private String screenNumber;

	public Integer getMasterListId() {
		return masterListId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public void setMasterListId(Integer masterListId) {
		this.masterListId = masterListId;
	}

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
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

	public long getId() {
		return id;
	}

}
