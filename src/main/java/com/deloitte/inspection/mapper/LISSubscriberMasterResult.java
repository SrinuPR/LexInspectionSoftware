package com.deloitte.inspection.mapper;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.deloitte.inspection.model.LISAccessMaster;
import com.deloitte.inspection.model.LISFacilityMaster;
import com.deloitte.inspection.model.LISInspectionMaster;
import com.deloitte.inspection.model.LISInspectionStageMaster;
import com.deloitte.inspection.model.LISInspectionTypeMaster;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISShiftMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISUserTypeMaster;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;

public class LISSubscriberMasterResult {
	
	private String _id;
	
	private String subscriberId;
	
	private String subscriberName;
	
	private String subscriberAddress;
	
	private String createdBy;
	
	private Date createdTimestamp;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private String isActive;
	
	private Set<LISUserMasterCreate> userMasterCreateList;
	
	private List<LISMaintainMasterDataComponent> maintainMasterDataComponents;
	
	private Set<LISUserTypeMaster> userTypeMaster;
	
	private Set<LISLogin> login;
	
	private Set<LISPurchaseOrderMaster> purchaseOrderMaster;
	
	private Set<LISWorkJobOrderMaster> workJobOrderMasters;
	
	private Set<LISInspectionTypeMaster> inspTypeMaster;
	
	private Set<LISInspectionStageMaster> inspStagesMaster;
	
	private Set<LISFacilityMaster> facilityMaster;
	
	private Set<LISShiftMaster> shiftMaster;
	
	private Set<LISInspectionMaster> inspMaster;
	
	private Set<LISAccessMaster> lisAccessMasters;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public Set<LISLogin> getLogin() {
		return login;
	}

	public void setLogin(Set<LISLogin> login) {
		this.login = login;
	}

	public Set<LISPurchaseOrderMaster> getPurchaseOrderMaster() {
		return purchaseOrderMaster;
	}

	public void setPurchaseOrderMaster(Set<LISPurchaseOrderMaster> purchaseOrderMaster) {
		this.purchaseOrderMaster = purchaseOrderMaster;
	}

	public Set<LISWorkJobOrderMaster> getWorkJobOrderMasters() {
		return workJobOrderMasters;
	}

	public void setWorkJobOrderMasters(Set<LISWorkJobOrderMaster> workJobOrderMasters) {
		this.workJobOrderMasters = workJobOrderMasters;
	}

	public Set<LISInspectionTypeMaster> getInspTypeMaster() {
		return inspTypeMaster;
	}

	public void setInspTypeMaster(Set<LISInspectionTypeMaster> inspTypeMaster) {
		this.inspTypeMaster = inspTypeMaster;
	}

	public Set<LISInspectionStageMaster> getInspStagesMaster() {
		return inspStagesMaster;
	}

	public void setInspStagesMaster(Set<LISInspectionStageMaster> inspStagesMaster) {
		this.inspStagesMaster = inspStagesMaster;
	}

	public Set<LISFacilityMaster> getFacilityMaster() {
		return facilityMaster;
	}

	public void setFacilityMaster(Set<LISFacilityMaster> facilityMaster) {
		this.facilityMaster = facilityMaster;
	}

	public Set<LISShiftMaster> getShiftMaster() {
		return shiftMaster;
	}

	public void setShiftMaster(Set<LISShiftMaster> shiftMaster) {
		this.shiftMaster = shiftMaster;
	}

	public Set<LISInspectionMaster> getInspMaster() {
		return inspMaster;
	}

	public void setInspMaster(Set<LISInspectionMaster> inspMaster) {
		this.inspMaster = inspMaster;
	}

	public Set<LISAccessMaster> getLisAccessMasters() {
		return lisAccessMasters;
	}

	public void setLisAccessMasters(Set<LISAccessMaster> lisAccessMasters) {
		this.lisAccessMasters = lisAccessMasters;
	}

}
