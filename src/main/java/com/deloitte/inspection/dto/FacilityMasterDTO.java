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
public class FacilityMasterDTO extends CommonDTO{
	
	private String facilityNumber;
	private String facilityName;
	private Integer subscriberId;
	private String subscriberName;
	private Date createdTimestamp;
	private Date updatedTimestamp;
	private String createdBy;
	private String updatedBy;
	private List<FacilityMasterDTO> facilityMasterList;
	
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
	 * @return the facilityMasterList
	 */
	public List<FacilityMasterDTO> getFacilityMasterList() {
		return facilityMasterList;
	}
	/**
	 * @param facilityMasterList the facilityMasterList to set
	 */
	public void setFacilityMasterList(List<FacilityMasterDTO> facilityMasterList) {
		this.facilityMasterList = facilityMasterList;
	}
}
