/**
 * 
 */
package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author rnarne
 *
 */
@Document(collection = "LIS_FMACS")
public class LISFacilityMaster {
	
	@Id
	private String facilityId;

	private String facilityNumber;

	private String facilityName;

	private String createdBy;

	private Date createdTimestamp;

	private String updatedBy;

	private Date updatedTimestamp;

	private String isActive;

	private String userId;
	
	private String subscriberMasterId;//foreign key

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityNumber() {
		return facilityNumber;
	}

	public void setFacilityNumber(String facilityNumber) {
		this.facilityNumber = facilityNumber;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(String subscriberMasterId) {
		this.subscriberMasterId = subscriberMasterId;
	}

}
