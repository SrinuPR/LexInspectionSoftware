package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rnarne
 * This class will be used to map Subscriber Master table data
 */

@Entity
@Table(name = "LIS_SUMAS")
public class LISSubscriberMaster implements Serializable {
	
	private static final long serialVersionUID = 5112717685665555841L;

	@Id
	@Column(name = "SUBSCRIBER_ID", length = 5)
	private Integer subscriberId;
	
	@Column(name = "SUBSCRIBER_NAME" , length = 150)
	private String subscriberName;
	
	@Column(name  = "SUBSCRIBER_ADDRESS" , length = 250)
	private String subscriberAddress;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@OneToMany(mappedBy="subscriberMaster", cascade = CascadeType.ALL)
	private Set<LISUserMasterCreate> userMasterCreateList;
	
	@OneToMany(mappedBy="subscriberMaster", cascade = CascadeType.ALL)
	private List<LISMaintainMasterDataComponent> maintainMasterDataComponents;
		
	@OneToMany(mappedBy="subscriberMaster", cascade = CascadeType.ALL)
	private Set<LISUserTypeMaster> userTypeMaster;

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getSubscriberAddress() {
		return subscriberAddress;
	}

	public void setSubscriberAddress(String subscriberAddress) {
		this.subscriberAddress = subscriberAddress;
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

	public Set<LISUserMasterCreate> getUserMasterCreateList() {
		return userMasterCreateList;
	}

	public void setUserMasterCreateList(Set<LISUserMasterCreate> userMasterCreateList) {
		this.userMasterCreateList = userMasterCreateList;
	}

	public List<LISMaintainMasterDataComponent> getMaintainMasterDataComponents() {
		return maintainMasterDataComponents;
	}

	public void setMaintainMasterDataComponents(List<LISMaintainMasterDataComponent> maintainMasterDataComponents) {
		this.maintainMasterDataComponents = maintainMasterDataComponents;
	}

	public Set<LISUserTypeMaster> getUserTypeMaster() {
		return userTypeMaster;
	}

	public void setUserTypeMaster(Set<LISUserTypeMaster> userTypeMaster) {
		this.userTypeMaster = userTypeMaster;
	}
		
}
