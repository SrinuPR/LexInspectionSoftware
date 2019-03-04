package com.deloitte.inspection.mapper;

import java.util.Date;
import java.util.List;

import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;

public class LISUserMasterCreateResult {
	
    private String _id;
    
	private String userId;
	
	private String userName;
	
	private String oldPassword1;
	
	private String oldPassword2;
	
	private String activePassword;
	
	private Integer userTypeId;
	
	private String createdBy;

	private Date createdTimestamp;
	
	private String updatedBy;

	private Date updatedTimestamp;
	
	private String isActive;
	
	private LISSubscriberMaster subscriberMaster;
	
	private LISLogin loginDetails;
	
	private List<LISMaintainMasterDataComponent> maintainMasterDataComponents;
	
	private List<LISPurchaseOrderMaster> purchaseOrderMaster;
	
	private List<LISWorkJobOrderMaster> workJobOrderMasters;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

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

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
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

	public List<LISMaintainMasterDataComponent> getMaintainMasterDataComponents() {
		return maintainMasterDataComponents;
	}

	public void setMaintainMasterDataComponents(List<LISMaintainMasterDataComponent> maintainMasterDataComponents) {
		this.maintainMasterDataComponents = maintainMasterDataComponents;
	}

	public List<LISPurchaseOrderMaster> getPurchaseOrderMaster() {
		return purchaseOrderMaster;
	}

	public void setPurchaseOrderMaster(List<LISPurchaseOrderMaster> purchaseOrderMaster) {
		this.purchaseOrderMaster = purchaseOrderMaster;
	}

	public List<LISWorkJobOrderMaster> getWorkJobOrderMasters() {
		return workJobOrderMasters;
	}

	public void setWorkJobOrderMasters(List<LISWorkJobOrderMaster> workJobOrderMasters) {
		this.workJobOrderMasters = workJobOrderMasters;
	}


}
