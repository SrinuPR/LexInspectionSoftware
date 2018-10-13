/**
 * 
 */
package com.deloitte.inspection.dto;

import java.util.Date;
import java.util.List;

/**
 * @author rnarne
 *
 */
public class InspectionTypeMasterDTO extends CommonDTO{
	
	private Integer inspTypeId;
	private String inspTypeName;
	private Integer subscriberId;
	private String subscriberName;
	private Date createdTimestamp;
	private Date updatedTimestamp;
	private String createdBy;
	private String updatedBy;
	private List<InspectionTypeMasterDTO> inspTypeMasterList;
	
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
	 * @return the subscriberId
	 */
	public Integer getSubscriberId() {
		return subscriberId;
	}
	/**
	 * @param subscriberId the subscriberId to set
	 */
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	/**
	 * @return the subscriberName
	 */
	public String getSubscriberName() {
		return subscriberName;
	}
	/**
	 * @param subscriberName the subscriberName to set
	 */
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
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
	/**
	 * @return the inspTypeMasterList
	 */
	public List<InspectionTypeMasterDTO> getInspTypeMasterList() {
		return inspTypeMasterList;
	}
	/**
	 * @param inspTypeMasterList the inspTypeMasterList to set
	 */
	public void setInspTypeMasterList(List<InspectionTypeMasterDTO> inspTypeMasterList) {
		this.inspTypeMasterList = inspTypeMasterList;
	}
}
