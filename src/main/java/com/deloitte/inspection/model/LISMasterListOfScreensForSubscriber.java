package com.deloitte.inspection.model;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_MLOSS")
public class LISMasterListOfScreensForSubscriber implements Serializable {

	private static final long serialVersionUID = 8730811844910414462L;

	@Id
	// @Field(value = "MLOSS_ID")
	private BigInteger masterListId;
	
	private BigInteger subscriberId;

	private LISSubscriberMaster subscriber;

	// @Field(value = "IS_ACTIVE")
	private String isActive;

	// @Field(value = "CREATED_BY")
	private String createdBy;

	// @Field(value = "CREATED_TIMESTAMP")
	private Date createdTimestamp;

	// @Field(value = "SCREEN_NAME")
	private String screenName;

	// @Field(value = "SCREEN_NUMBER")
	private String screenNumber;

	public BigInteger getMasterListId() {
		return masterListId;
	}

	public BigInteger getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(BigInteger subscriberId) {
		this.subscriberId = subscriberId;
	}

	public void setMasterListId(BigInteger masterListId) {
		this.masterListId = masterListId;
	}

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
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
