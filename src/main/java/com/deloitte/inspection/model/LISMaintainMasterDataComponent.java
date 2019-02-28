package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_CMDCS")
public class LISMaintainMasterDataComponent implements Serializable{
	
	private static final long serialVersionUID = 382945870712132280L;
	
	@Id
	private Long id;

	@Field(value = "CMDCS_ID")
	private Integer cmdcsId;
	
	@Field(value = "COMP_PROD_DRAW_NUM")
	private String componentProductDrawNumber;
	
	@Field(value = "CUST_NAME_ADDR")
	private String customerNameAddress;
	
	@Field(value = "COMP_PROD_NAME")
	private String componentProductName;
	
	@Field(value = "COMP_PROD_NUM")
	private String componentProductNumber;
	
	@Field(value = "COMP_PROD_MATERIAL")
	private String componentProductMeterial;
	
	@Field(value = "COMP_PROD_MANUFAC_UNITS")
	private String componentProductManufacturerUnits;
	
	@Field(value = "COMP_PROD_NOTES")
	private String componentProductNotes;
	
	@Field(value = "CREATED_BY")
	private String createdBy;
	
	@Field(value = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Field(value = "UPDATED_BY")
	private String updatedBy;
	
	@Field(value = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	private LISSubscriberMaster subscriber;
	
	private LISUserMasterCreate user;
	
	@Field(value = "IS_ACTIVE")
	private char isActive;
	
	@Field(value = "RECORD_IN_PROCESS")
	private char recordInProcess;
	
	public Integer getCmdcsId() {
		return cmdcsId;
	}

	public void setCmdcsId(Integer cmdcsId) {
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

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public char getRecordInProcess() {
		return recordInProcess;
	}

	public void setRecordInProcess(char recordInProcess) {
		this.recordInProcess = recordInProcess;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}

	public LISUserMasterCreate getUser() {
		return user;
	}

	public void setUser(LISUserMasterCreate user) {
		this.user = user;
	}	
}
