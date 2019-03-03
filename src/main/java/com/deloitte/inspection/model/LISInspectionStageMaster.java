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
@Document(collection = "LIS_ISMCS")
public class LISInspectionStageMaster implements Serializable{
		
	private static final long serialVersionUID = 382945870712132210L;

	@Id	
	// @Field(value = "INSPECTION_STAGE_UNIQUE_ID")
	private BigInteger inspStageUniqueId;
	
	// @Field(value = "INSPECTION_STAGE_ID")
	private Integer inspStageId;
	
	// @Field(value = "INSPECTION_STAGE_NAME")
	private String inspStageName;
	
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
	
    private LISSubscriberMaster subscriber;
	
	// @Field(value = "USER_ID")
	private String userId;

	/**
	 * @return the inspStageId
	 */
	public Integer getInspStageId() {
		return inspStageId;
	}

	/**
	 * @param inspStageId the inspStageId to set
	 */
	public void setInspStageId(Integer inspStageId) {
		this.inspStageId = inspStageId;
	}

	/**
	 * @return the inspStageName
	 */
	public String getInspStageName() {
		return inspStageName;
	}

	/**
	 * @param inspStageName the inspStageName to set
	 */
	public void setInspStageName(String inspStageName) {
		this.inspStageName = inspStageName;
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

	/**
	 * @return the subscriberMaster
	 */
	public LISSubscriberMaster getSubscriber() {
		return this.subscriber;
	}

	/**
	 * @param subscriberMaster the subscriberMaster to set
	 */
	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}

	public BigInteger getInspStageUniqueId() {
		return inspStageUniqueId;
	}

	public void setInspStageUniqueId(BigInteger inspStageUniqueId) {
		this.inspStageUniqueId = inspStageUniqueId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
