package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LIS_CMDCS")
public class LISMaintainMasterDataComponent {
	
	@Id
	private String _id;
	
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
	
	private Integer subscriberMasterId;//foreign key
	
	private String userMasterCreateId;//foreign key
	
	private String isActive;
	
	private String recordInProcess;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

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

	public Integer getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(Integer subscriberMasterId) {
		this.subscriberMasterId = subscriberMasterId;
	}

	public String getUserMasterCreateId() {
		return userMasterCreateId;
	}

	public void setUserMasterCreateId(String userMasterCreateId) {
		this.userMasterCreateId = userMasterCreateId;
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
