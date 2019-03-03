package com.deloitte.inspection.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author rnarne
 * This class will be used to map Subscriber Master table data
 */

@Document(collection = "LIS_SUMAS")
public class LISSubscriberMaster implements Serializable {
	
	private static final long serialVersionUID = 5112717685665555841L;
	
	@Id
	private String id;
	
	// @Field(value = "SUBSCRIBER_ID")
	private Integer subscriberId;
	
	// @Field(value = "SUBSCRIBER_NAME")
	private String subscriberName;
	
	// @Field(value = "SUBSCRIBER_ADDRESS")
	private String subscriberAddress;
	
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
	
	private Set<LISUserMasterCreate> userMasterCreateList;

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getSubscriberAddress() {
		return subscriberAddress;
	}

	public void setSubscriberAddress(String subscriberAddress) {
		this.subscriberAddress = subscriberAddress;
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

	public Set<LISUserMasterCreate> getUserMasterCreateList() {
		return userMasterCreateList;
	}

	public void setUserMasterCreateList(Set<LISUserMasterCreate> userMasterCreateList) {
		this.userMasterCreateList = userMasterCreateList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
