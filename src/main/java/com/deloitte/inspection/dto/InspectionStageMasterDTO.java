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
public class InspectionStageMasterDTO extends CommonDTO{
	
	private Integer inspStageId;
	private String inspStageName;
	private Integer subscriberId;
	private String subscriberName;
	private Date createdTimestamp;
	private Date updatedTimestamp;
	private String createdBy;
	private String updatedBy;
	private List<InspectionStageMasterDTO> inspStageMasterList;
	
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
	 * @return the inspStageMasterList
	 */
	public List<InspectionStageMasterDTO> getInspStageMasterList() {
		return inspStageMasterList;
	}
	/**
	 * @param inspStageMasterList the inspStageMasterList to set
	 */
	public void setInspStageMasterList(List<InspectionStageMasterDTO> inspStageMasterList) {
		this.inspStageMasterList = inspStageMasterList;
	}
}
