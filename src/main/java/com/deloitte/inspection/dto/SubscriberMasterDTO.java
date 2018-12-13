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
public class SubscriberMasterDTO extends CommonDTO{
	
	private Integer subscriberId;
	private String subscriberName;
	private String subscriberAddress;
	private Date createdTimestamp;
	private Date updatedTimestamp;
	private String createdBy;
	private String updatedBy;
	private List<String> userId;
	private int userCount;
	
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	List<SubscriberMasterDTO> subMasterList;
		
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
	 * @return the subscriberAddress
	 */
	public String getSubscriberAddress() {
		return subscriberAddress;
	}
	/**
	 * @param subscriberAddress the subscriberAddress to set
	 */
	public void setSubscriberAddress(String subscriberAddress) {
		this.subscriberAddress = subscriberAddress;
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
	 * @return the subMasterList
	 */
	public List<SubscriberMasterDTO> getSubMasterList() {
		return subMasterList;
	}
	/**
	 * @param subMasterList the subMasterList to set
	 */
	public void setSubMasterList(List<SubscriberMasterDTO> subMasterList) {
		this.subMasterList = subMasterList;
	}
	public List<String> getUserId() {
		return userId;
	}
	public void setUserId(List<String> userId) {
		this.userId = userId;
	}
	
}
