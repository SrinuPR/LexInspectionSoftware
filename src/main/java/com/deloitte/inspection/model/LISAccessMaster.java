package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIS_ACMDS")
public class LISAccessMaster implements Serializable{
	
	private static final long serialVersionUID = 5112717685665555840L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACMDS_ID")
	private Integer accessMasterId;
	
	@Column(name = "SCREEN_NUMBER")
	private String screenNumber;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimeStamp;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedTimeStamp;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="SUBSCRIBER_ID")
	private LISSubscriberMaster subscriberMaster;
	
	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="USER_TYPE_ID")
	private LISUserTypeMaster userTypeMaster;
	
	@Column(name = "IS_ACTIVE")
	private char isActive;
	
	public String getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}

	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimestamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}

	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getAccessMasterId() {
		return accessMasterId;
	}

	public void setAccessMasterId(Integer accessMasterId) {
		this.accessMasterId = accessMasterId;
	}

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public LISUserTypeMaster getUserTypeMaster() {
		return userTypeMaster;
	}

	public void setUserTypeMaster(LISUserTypeMaster userTypeMaster) {
		this.userTypeMaster = userTypeMaster;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	
}
