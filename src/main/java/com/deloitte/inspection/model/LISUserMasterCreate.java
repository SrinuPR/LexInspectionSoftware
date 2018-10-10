package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIS_UMACS")
public class LISUserMasterCreate implements Serializable{

	private static final long serialVersionUID = 6533302101506254629L;
	
	@Id
	@Column(name = "USER_ID" , length = 10)
	private String userId;
	
	@Column(name = "USER_NAME" , length = 20)
	private String userName;
	
	@Column(name = "OLD_PASSWORD1" , length = 20)
	private String oldPassword1;
	
	@Column(name = "OLD_PASSWORD2" , length = 20)
	private String oldPassword2;
	
	@Column(name = "ACTIVE_PASSWORD" , length = 20)
	private String activePassword;
	
	@Column(name = "USER_TYPE_ID", length =30)
	private String userTypeId;
	
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
	
	@OneToOne(mappedBy="userMasterCreate", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private LISLogin loginDetails;
	
	@OneToMany(mappedBy="userMasterCreate", cascade = CascadeType.ALL)
	private List<LISMaintainMasterDataComponent> maintainMasterDataComponents;

	@OneToMany(mappedBy="userMasterCreate", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<LISPurchaseOrderMaster> purchaseOrderMaster;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPassword1() {
		return oldPassword1;
	}

	public void setOldPassword1(String oldPassword1) {
		this.oldPassword1 = oldPassword1;
	}

	public String getOldPassword2() {
		return oldPassword2;
	}

	public void setOldPassword2(String oldPassword2) {
		this.oldPassword2 = oldPassword2;
	}

	public String getActivePassword() {
		return activePassword;
	}

	public void setActivePassword(String activePassword) {
		this.activePassword = activePassword;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
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

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public LISLogin getLoginDetails() {
		return loginDetails;
	}

	public void setLoginDetails(LISLogin loginDetails) {
		this.loginDetails = loginDetails;
	}

	public List<LISPurchaseOrderMaster> getPurchaseOrderMaster() {
		return purchaseOrderMaster;
	}

	public void setPurchaseOrderMaster(List<LISPurchaseOrderMaster> purchaseOrderMaster) {
		this.purchaseOrderMaster = purchaseOrderMaster;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public List<LISMaintainMasterDataComponent> getMaintainMasterDataComponents() {
		return maintainMasterDataComponents;
	}

	public void setMaintainMasterDataComponents(List<LISMaintainMasterDataComponent> maintainMasterDataComponents) {
		this.maintainMasterDataComponents = maintainMasterDataComponents;
	}
	

}
