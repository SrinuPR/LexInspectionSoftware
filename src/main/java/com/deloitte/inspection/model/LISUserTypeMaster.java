/**
 * 
 */
package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author rnarne
 * This class will be used to map User Type Master table data
 */

@Document(collection = "LIS_UTMCS")
public class LISUserTypeMaster implements Serializable {

	
	private static final long serialVersionUID = 5112717685665555841L;

	@Id
	private String id;
	
	// @Field(value =  "USER_TYPE_ID")
	private Integer userTypeId;
	
	// @Field(value =  "USER_TYPE_NAME")
	private String userTypeName;
	
	// @Field(value =  "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	// @Field(value =  "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	// @Field(value =  "CREATED_BY")
	private String createdBy;
	
	// @Field(value =  "UPDATED_BY")
	private String updatedBy;
	
	// @Field(value =  "IS_ACTIVE")
	private String isActive;
	
	private LISAccessMaster access;
	
	private LISSubscriberMaster subscriber;
	
	/**
	 * @return the userTypeId
	 */
	public Integer getUserTypeId() {
		return userTypeId;
	}

	/**
	 * @param userTypeId the userTypeId to set
	 */
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	/**
	 * @return the userTypeName
	 */
	public String getUserTypeName() {
		return userTypeName;
	}

	/**
	 * @param userTypeName the userTypeName to set
	 */
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	/**
	 * @return the createdTimestamp
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * @param createdTimestamp the createdTimestamp to set
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * @return the updatedTimestamp
	 */
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	/**
	 * @param updatedTimestamp the updatedTimestamp to set
	 */
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getIsActive() {
		return isActive;
	}

	public LISAccessMaster getAccess() {
		return access;
	}

	public void setAccess(LISAccessMaster access) {
		this.access = access;
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
