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
 */
@Entity
@Table(name = "LIS_ITMCS")
public class LISInspectionTypeMaster implements Serializable{
	
	private static final long serialVersionUID = 382945870712132280L;

	@Column(name = "INSPECTION_TYPE_UNIQUE_ID" , unique = true)
	private Integer inspTypeUniqueId;
	
	@Id
	@Column(name = "INSPECTION_TYPE_ID", length = 5)
	private Integer inspTypeId;
	
	@Column(name = "INSPECTION_TYPE_NAME" , length = 150)
	private String inspTypeName;
	
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
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="SUBSCRIBER_ID")
    private LISSubscriberMaster subscriberMaster;

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

	public Integer getInspTypeUniqueId() {
		return inspTypeUniqueId;
	}

	public void setInspTypeUniqueId(Integer inspTypeUniqueId) {
		this.inspTypeUniqueId = inspTypeUniqueId;
	}
	
}
