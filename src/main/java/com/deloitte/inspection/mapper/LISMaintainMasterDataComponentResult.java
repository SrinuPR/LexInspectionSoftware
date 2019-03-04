package com.deloitte.inspection.mapper;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.deloitte.inspection.model.LISInspectionMaster;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;

public class LISMaintainMasterDataComponentResult {

	private String cmdcsId;
	
	private String componentProductDrawNumber;
	
	private String customerNameAddress;
	
	private String componentProductName;
	
	private String componentProductNumber;
	
	private String componentProductMeterial;
	
	private String componentProductManufacturerUnits;
	
	private String componentProductNotes;
	
	private String createdBy;
	
	private Date createdTimestamp;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private LISSubscriberMaster subscriberMaster;
	
	private LISUserMasterCreate userMasterCreate;
	
	private List<LISPurchaseOrderMaster> purchaseOrderMaster;
	
	private Set<LISInspectionMaster> inspectionMaster;
	
	private List<LISWorkJobOrderMaster> workJobOrderMasters;
	
	private String isActive;
	
	private String recordInProcess;

	public String getCmdcsId() {
		return cmdcsId;
	}

	public void setCmdcsId(String cmdcsId) {
		this.cmdcsId = cmdcsId;
	}

	public String getComponentProductDrawNumber() {
		return componentProductDrawNumber;
	}

	public void setComponentProductDrawNumber(String componentProductDrawNumber) {
		this.componentProductDrawNumber = componentProductDrawNumber;
	}

	public String getCustomerNameAddress() {
		return customerNameAddress;
	}

	public void setCustomerNameAddress(String customerNameAddress) {
		this.customerNameAddress = customerNameAddress;
	}

	public String getComponentProductName() {
		return componentProductName;
	}

	public void setComponentProductName(String componentProductName) {
		this.componentProductName = componentProductName;
	}

	public String getComponentProductNumber() {
		return componentProductNumber;
	}

	public void setComponentProductNumber(String componentProductNumber) {
		this.componentProductNumber = componentProductNumber;
	}

	public String getComponentProductMeterial() {
		return componentProductMeterial;
	}

	public void setComponentProductMeterial(String componentProductMeterial) {
		this.componentProductMeterial = componentProductMeterial;
	}

	public String getComponentProductManufacturerUnits() {
		return componentProductManufacturerUnits;
	}

	public void setComponentProductManufacturerUnits(String componentProductManufacturerUnits) {
		this.componentProductManufacturerUnits = componentProductManufacturerUnits;
	}

	public String getComponentProductNotes() {
		return componentProductNotes;
	}

	public void setComponentProductNotes(String componentProductNotes) {
		this.componentProductNotes = componentProductNotes;
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

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public LISUserMasterCreate getUserMasterCreate() {
		return userMasterCreate;
	}

	public void setUserMasterCreate(LISUserMasterCreate userMasterCreate) {
		this.userMasterCreate = userMasterCreate;
	}

	public List<LISPurchaseOrderMaster> getPurchaseOrderMaster() {
		return purchaseOrderMaster;
	}

	public void setPurchaseOrderMaster(List<LISPurchaseOrderMaster> purchaseOrderMaster) {
		this.purchaseOrderMaster = purchaseOrderMaster;
	}

	public Set<LISInspectionMaster> getInspectionMaster() {
		return inspectionMaster;
	}

	public void setInspectionMaster(Set<LISInspectionMaster> inspectionMaster) {
		this.inspectionMaster = inspectionMaster;
	}

	public List<LISWorkJobOrderMaster> getWorkJobOrderMasters() {
		return workJobOrderMasters;
	}

	public void setWorkJobOrderMasters(List<LISWorkJobOrderMaster> workJobOrderMasters) {
		this.workJobOrderMasters = workJobOrderMasters;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getRecordInProcess() {
		return recordInProcess;
	}

	public void setRecordInProcess(String recordInProcess) {
		this.recordInProcess = recordInProcess;
	}
}
