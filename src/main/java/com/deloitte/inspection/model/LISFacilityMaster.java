/**
 * 
 */
package com.deloitte.inspection.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author rnarne
 *
 */
@Document(collection = "LIS_FMACS")
public class LISFacilityMaster implements Serializable {

	private static final long serialVersionUID = 382945870712132210L;
	
	@Id
	// @Field(value = "FACILITY_ID")
	private BigInteger facilityId;

	// @Field(value = "FACILITY_NUMBER")
	private String facilityNumber;

	// @Field(value = "FACILITY_NAME")
	private String facilityName;

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

	// @Field(value = "USER_ID")
	private String userId;
	
	private LISSubscriberMaster subscriber;

	/**
	 * @return the facilityNumber
	 */
	public String getFacilityNumber() {
		return facilityNumber;
	}

	/**
	 * @param facilityNumber the facilityNumber to set
	 */
	public void setFacilityNumber(String facilityNumber) {
		this.facilityNumber = facilityNumber;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
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
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public BigInteger getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(BigInteger facilityId) {
		this.facilityId = facilityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}

}
