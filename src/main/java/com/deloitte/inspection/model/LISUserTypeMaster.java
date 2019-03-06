/**
 * 
 */
package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author rnarne
 * This class will be used to map User Type Master table data
 */

@Document(collection = "LIS_UTMCS")
public class LISUserTypeMaster {

	@Id
	private String id;
	
	private Integer userTypeId;
	
	private String userTypeName;
	
	private Date createdTimestamp;
	
	private Date updatedTimestamp;

	private String createdBy;
	
	private String updatedBy;
	
	private String isActive;
	
	private Integer subscriberMasterId;//foreign key

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
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

	public Integer getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(Integer subscriberMasterId) {
		this.subscriberMasterId = subscriberMasterId;
	}
	
}
