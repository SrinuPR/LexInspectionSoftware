package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LIS_CMDCS")
public class LISMaintainMasterDataComponent implements Serializable{
	
	private static final long serialVersionUID = 382945870712132280L;

	@Id
	@Column(name = "CMDCS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cmdcsId;
	
	@Column(name = "COMP_PROD_DRAW_NUM" , length = 50)
	private String componentProductDrawNumber;
	
	@Column(name = "CUST_NAME_ADDR" , length = 250)
	private String customerNameAddress;
	
	@Column(name = "COMP_PROD_NAME" , length = 50)
	private String componentProductName;
	
	@Column(name = "COMP_PROD_NUM" , length = 50)
	private String componentProductNumber;
	
	@Column(name = "COMP_PROD_MATERIAL" , length = 50)
	private String componentProductMeterial;
	
	@Column(name = "COMP_PROD_MANUFAC_UNITS" , length = 5)
	private String componentProductManufacturerUnits;
	
	@Column(name = "COMP_PROD_NOTES" , length = 250)
	private String componentProductNotes;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="SUBSCRIBER_ID")
	private LISSubscriberMaster subscriberMaster;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
	private LISUserMasterCreate userMasterCreate;
	
	@OneToMany(mappedBy="componentMasterData", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<LISPurchaseOrderMaster> purchaseOrderMaster;
	
	@Column(name = "IS_ACTIVE")
	private char isActive;	

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

	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
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
}
