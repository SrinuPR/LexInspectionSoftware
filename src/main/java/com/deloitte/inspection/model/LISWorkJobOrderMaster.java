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
import javax.persistence.Table;

@Entity
@Table(name = "LIS_WOMCS")
public class LISWorkJobOrderMaster implements Serializable{

	private static final long serialVersionUID = 917212548437079358L;
	
	@Id
	@Column(name = "WOMCS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer wjOrderId;
	
	@Column(name = "WORK_JOB_ORDER_NUMBER" , length = 30)
	private String workJobOrderNumber;
	
	@Column(name = "WORK_JOB_ORDER_DATE")
	private Date workJobOrderDate;
	
	@Column(name = "LOT_NUMBER" , length = 10)
	private String lotNumber;
	
	@Column(name = "LOT_SIZE" , length = 5)
	private Integer lotSize;
	
	@Column(name = "LOT_SIZE_UNITS" , length = 5)
	private String lotSizeUnits;
	
	@Column(name = "MANUFACTURING_BATCH_NUMBER" , length = 30)
	private String manufacturingBatchNumber;
	
	@Column(name = "MANUFACTURING_BATCH_SIZE" , length = 5)
	private Integer manufacturingBatchSize;
	
	@Column(name = "MANUFACTURING_BATCH_UNITS" , length = 5)
	private String manufacturingBatchUnits;
	
	@Column(name = "WORK_ORDER_JOB_NOTES" , length = 250)
	private String workOrderJobNotes;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@Column(name = "IS_ACTIVE" , length = 1)
	private char isActive;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="SUBSCRIBER_ID")
	private LISSubscriberMaster subscriberMaster;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
	private LISUserMasterCreate userMasterCreate;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="COMP_PROD_DRAW_NUM")
    private LISMaintainMasterDataComponent componentMasterData;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="CUSTOMERPO_NUMBER")
    private LISPurchaseOrderMaster purchaseOrderMaster;

	public Integer getWjOrderId() {
		return wjOrderId;
	}

	public void setWjOrderId(Integer wjOrderId) {
		this.wjOrderId = wjOrderId;
	}

	public String getWorkJobOrderNumber() {
		return workJobOrderNumber;
	}

	public void setWorkJobOrderNumber(String workJobOrderNumber) {
		this.workJobOrderNumber = workJobOrderNumber;
	}

	public Date getWorkJobOrderDate() {
		return workJobOrderDate;
	}

	public void setWorkJobOrderDate(Date workJobOrderDate) {
		this.workJobOrderDate = workJobOrderDate;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public Integer getLotSize() {
		return lotSize;
	}

	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	public String getLotSizeUnits() {
		return lotSizeUnits;
	}

	public void setLotSizeUnits(String lotSizeUnits) {
		this.lotSizeUnits = lotSizeUnits;
	}

	public String getManufacturingBatchNumber() {
		return manufacturingBatchNumber;
	}

	public void setManufacturingBatchNumber(String manufacturingBatchNumber) {
		this.manufacturingBatchNumber = manufacturingBatchNumber;
	}

	public Integer getManufacturingBatchSize() {
		return manufacturingBatchSize;
	}

	public void setManufacturingBatchSize(Integer manufacturingBatchSize) {
		this.manufacturingBatchSize = manufacturingBatchSize;
	}

	public String getManufacturingBatchUnits() {
		return manufacturingBatchUnits;
	}

	public void setManufacturingBatchUnits(String manufacturingBatchUnits) {
		this.manufacturingBatchUnits = manufacturingBatchUnits;
	}

	public String getWorkOrderJobNotes() {
		return workOrderJobNotes;
	}

	public void setWorkOrderJobNotes(String workOrderJobNotes) {
		this.workOrderJobNotes = workOrderJobNotes;
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

	public LISMaintainMasterDataComponent getComponentMasterData() {
		return componentMasterData;
	}

	public void setComponentMasterData(LISMaintainMasterDataComponent componentMasterData) {
		this.componentMasterData = componentMasterData;
	}

	public LISPurchaseOrderMaster getPurchaseOrderMaster() {
		return purchaseOrderMaster;
	}

	public void setPurchaseOrderMaster(LISPurchaseOrderMaster purchaseOrderMaster) {
		this.purchaseOrderMaster = purchaseOrderMaster;
	}
	
}
