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
@Document(collection = "LIS_ITMCS")
public class LISInspectionTypeMaster implements Serializable{
	
	private static final long serialVersionUID = 382945870712132280L;
	
	@Id
	// @Field(value = "INSPECTION_TYPE_UNIQUE_ID")
	private BigInteger inspTypeUniqueId;
	
	// @Field(value = "INSPECTION_TYPE_ID")
	private Integer inspTypeId;
	
	// @Field(value = "INSPECTION_TYPE_NAME")
	private String inspTypeName;
	
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
	 * @return the inspTypeId
	 */
	public Integer getInspTypeId() {
		return inspTypeId;
	}

	/**
	 * @param inspTypeId the inspTypeId to set
	 */
	public void setInspTypeId(Integer inspTypeId) {
		this.inspTypeId = inspTypeId;
	}

	/**
	 * @return the inspTypeName
	 */
	public String getInspTypeName() {
		return inspTypeName;
	}

	/**
	 * @param inspTypeName the inspTypeName to set
	 */
	public void setInspTypeName(String inspTypeName) {
		this.inspTypeName = inspTypeName;
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

	public BigInteger getInspTypeUniqueId() {
		return inspTypeUniqueId;
	}

	public void setInspTypeUniqueId(BigInteger inspTypeUniqueId) {
		this.inspTypeUniqueId = inspTypeUniqueId;
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
