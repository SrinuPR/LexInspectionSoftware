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
@Table(name = "LIS_WOMCS")
public class LISWorkOrderMaster implements Serializable{
	
	private static final long serialVersionUID = 382945870712132280L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WORK_ORDER_ID")
	private Integer wordOrderId;
	
	
	@Column(name = "USER_ID" , length = 20)
	private String userId;
	
	@Column(name = "COMPONENT_PRODUCT_DRAWING_NUM", length=50 )
	private String componentProductDrawingNum;
	
	@Column(name = "WORK_JOB_ORDER_NUM", length=30 )
	private String workJobOrderNum;
	
	@Column(name = "WORK_JOB_ORDER_DATE" )
	private Date workJobOrderDate;
	
	@Column(name = "LOT_NUMBER" , length = 10)
	private String lotNumber;
	
	@Column(name = "LOT_SIZE" )
	private Integer lotSize;
	
	@Column(name = "LOT_SIZE_UNITS" )
	private Integer lotSizeUnits;
	
	@Column(name = "MANUFACTURING_BATCH_NUM" , length = 30)
	private String manufacturingBatchNum;
	
	@Column(name = "MANUFACTURING_BATCH_SIZE")
	private Integer manufacturingBatchSize;
	
	@Column(name = "MANUFACTURING_BATCH_UNITS" , length = 5)
	private String manufacturingBatchUnits;
	
	@Column(name = "NOTES" , length = 250)
	private String notes;
	
	@Column(name = "CREATED_BY" , length = 50)
	private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Column(name = "UPDATED_BY", length = 50)
	private String updatedBy;
	
	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="SUBSCRIBER_ID")
    private LISSubscriberMaster subscriberMaster;

	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="CUSTOMERPO_NUMBER")
    private LISPurchaseOrderMaster purchaseOrderMaster;

	public Integer getWordOrderId() {
		return wordOrderId;
	}

	public void setWordOrderId(Integer wordOrderId) {
		this.wordOrderId = wordOrderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComponentProductDrawingNum() {
		return componentProductDrawingNum;
	}

	public void setComponentProductDrawingNum(String componentProductDrawingNum) {
		this.componentProductDrawingNum = componentProductDrawingNum;
	}

	public String getWorkJobOrderNum() {
		return workJobOrderNum;
	}

	public void setWorkJobOrderNum(String workJobOrderNum) {
		this.workJobOrderNum = workJobOrderNum;
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

	public Integer getLotSizeUnits() {
		return lotSizeUnits;
	}

	public void setLotSizeUnits(Integer lotSizeUnits) {
		this.lotSizeUnits = lotSizeUnits;
	}

	public String getManufacturingBatchNum() {
		return manufacturingBatchNum;
	}

	public void setManufacturingBatchNum(String manufacturingBatchNum) {
		this.manufacturingBatchNum = manufacturingBatchNum;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public LISPurchaseOrderMaster getPurchaseOrderMaster() {
		return purchaseOrderMaster;
	}

	public void setPurchaseOrderMaster(LISPurchaseOrderMaster purchaseOrderMaster) {
		this.purchaseOrderMaster = purchaseOrderMaster;
	}

}
