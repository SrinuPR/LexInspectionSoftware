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
@Document(collection = "LIS_ITMCS")
public class LISInspectionTypeMaster {
	
	@Id
	private String _id;
	
	private String inspTypeUniqueId;
	
	private Integer inspTypeId;
	
	private String inspTypeName;
	
	private String createdBy;
	
	private Date createdTimestamp;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private String isActive;
	
	private String userId;
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	private Integer subscriberMasterId;//foreign key

	public String getInspTypeUniqueId() {
		return inspTypeUniqueId;
	}

	public void setInspTypeUniqueId(String inspTypeUniqueId) {
		this.inspTypeUniqueId = inspTypeUniqueId;
	}

	public Integer getInspTypeId() {
		return inspTypeId;
	}

	public void setInspTypeId(Integer inspTypeId) {
		this.inspTypeId = inspTypeId;
	}

	public String getInspTypeName() {
		return inspTypeName;
	}

	public void setInspTypeName(String inspTypeName) {
		this.inspTypeName = inspTypeName;
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

	public Integer getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(Integer subscriberMasterId) {
		this.subscriberMasterId = subscriberMasterId;
	}

	
}
