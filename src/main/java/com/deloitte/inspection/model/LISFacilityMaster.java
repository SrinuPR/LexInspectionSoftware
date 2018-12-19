/**
 * 
 */
package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rnarne
 *
 */@Entity
 @Table(name = "LIS_FMACS")
 public class LISFacilityMaster implements Serializable{
 		
 	private static final long serialVersionUID = 382945870712132210L;

 	@Column(name = "FACILITY_ID", unique = true)
 	private Integer facilityId;
 	
 	@Id
 	@Column(name = "FACILITY_NUMBER", length = 10)
 	private String facilityNumber;
 	
 	@Column(name = "FACILITY_NAME" , length = 150)
 	private String facilityName;
 	
 	@Column(name = "CREATED_BY")
 	private String createdBy;
 	
 	@Column(name = "CREATED_TIMESTAMP")
 	private Date createdTimestamp;
 	
 	@Column(name = "UPDATED_BY")
 	private String updatedBy;
 	
 	@Column(name = "UPDATED_TIMESTAMP")
 	private Date updatedTimestamp;
 	
 	@Column(name = "IS_ACTIVE")
 	private char isActive;
 	
 	@Column(name = "USER_ID")
 	private String userId;
 	
 	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
 	@JoinColumn(name="SUBSCRIBER_ID")
    private LISSubscriberMaster subscriberMaster;

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
	public char getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the subscriberMaster
	 */
	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	/**
	 * @param subscriberMaster the subscriberMaster to set
	 */
	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public Integer getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
