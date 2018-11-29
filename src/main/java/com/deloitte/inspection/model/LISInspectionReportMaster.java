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
@Table(name = "LIS_IRMCS")
public class LISInspectionReportMaster implements Serializable{

	private static final long serialVersionUID = 382945870712132210L;
	
	@Id
	@Column(name = "IRMCS_ID", unique= true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer inspRptMasterId;
		
	@Column(name = "WORK_JOB_ORDER_NUMBER" , length = 30)
	private String workJobOrderNumber;
	
	@Column(name = "LOT_NUMBER" , length = 10)
	private String lotNumber;
	
	@Column(name = "LOT_SIZE" , length = 5)
	private Integer lotSize;
	
	@Column(name = "MANUFACTURING_BATCH_NUMBER" , length = 30)
	private String manufacturingBatchNumber;
	
	@Column(name = "MANUFACTURING_BATCH_SIZE" , length = 5)
	private Integer manufacturingBatchSize;
	
	@Column(name = "CUSTOMER_PO_NUMBER" , length = 20)
	private String customerPoNumber;
	
	@Column(name = "CUSTOMER_PO_DATE" )
	private Date customerPoDate;
	
	@Column(name = "CUSTOMER_PO_QUANTITY" , length = 5)
	private Integer customerPoQuantity;
	
	@Column(name = "CUST_NAME_ADDR" , length = 250)
	private String customerNameAddress;
	
	@Column(name = "INSPECTION_TYPE_ID")
	private Integer inspTypeId; 
	
	@Column(name = "INSPECTION_STAGE_ID")
	private Integer inspStageId;
	
	@Column(name = "INSPECTION_REPORT_NUMBER" , length = 50)
	private Integer inspReportNumber;
	
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
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="COMP_PROD_DRAW_NUM")
    private LISMaintainMasterDataComponent componentMasterData;
	
	/*@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="WOMCS_ID")
    private LISWorkJobOrderMaster workJobOrderMaster;*/

	/**
	 * @return the workJobOrderNumber
	 */
	public String getWorkJobOrderNumber() {
		return workJobOrderNumber;
	}

	/**
	 * @param workJobOrderNumber the workJobOrderNumber to set
	 */
	public void setWorkJobOrderNumber(String workJobOrderNumber) {
		this.workJobOrderNumber = workJobOrderNumber;
	}

	/**
	 * @return the lotNumber
	 */
	public String getLotNumber() {
		return lotNumber;
	}

	/**
	 * @param lotNumber the lotNumber to set
	 */
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	/**
	 * @return the lotSize
	 */
	public Integer getLotSize() {
		return lotSize;
	}

	/**
	 * @param lotSize the lotSize to set
	 */
	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	/**
	 * @return the manufacturingBatchNumber
	 */
	public String getManufacturingBatchNumber() {
		return manufacturingBatchNumber;
	}

	/**
	 * @param manufacturingBatchNumber the manufacturingBatchNumber to set
	 */
	public void setManufacturingBatchNumber(String manufacturingBatchNumber) {
		this.manufacturingBatchNumber = manufacturingBatchNumber;
	}

	/**
	 * @return the manufacturingBatchSize
	 */
	public Integer getManufacturingBatchSize() {
		return manufacturingBatchSize;
	}

	/**
	 * @param manufacturingBatchSize the manufacturingBatchSize to set
	 */
	public void setManufacturingBatchSize(Integer manufacturingBatchSize) {
		this.manufacturingBatchSize = manufacturingBatchSize;
	}

	/**
	 * @return the customerPoNumber
	 */
	public String getCustomerPoNumber() {
		return customerPoNumber;
	}

	/**
	 * @param customerPoNumber the customerPoNumber to set
	 */
	public void setCustomerPoNumber(String customerPoNumber) {
		this.customerPoNumber = customerPoNumber;
	}

	/**
	 * @return the customerPoDate
	 */
	public Date getCustomerPoDate() {
		return customerPoDate;
	}

	/**
	 * @param customerPoDate the customerPoDate to set
	 */
	public void setCustomerPoDate(Date customerPoDate) {
		this.customerPoDate = customerPoDate;
	}


	/**
	 * @return the customerPoQuantity
	 */
	public Integer getCustomerPoQuantity() {
		return customerPoQuantity;
	}

	/**
	 * @param customerPoQuantity the customerPoQuantity to set
	 */
	public void setCustomerPoQuantity(Integer customerPoQuantity) {
		this.customerPoQuantity = customerPoQuantity;
	}

	/**
	 * @return the customerNameAddress
	 */
	public String getCustomerNameAddress() {
		return customerNameAddress;
	}

	/**
	 * @param customerNameAddress the customerNameAddress to set
	 */
	public void setCustomerNameAddress(String customerNameAddress) {
		this.customerNameAddress = customerNameAddress;
	}

	/**
	 * @return the inspTypeId
	 */
	public Integer getInspTypeId() {
		return inspTypeId;
	}

	/**
	 * @param inspTypeId the inspTypeId to set
	 */
	public void setInspTypeId(Integer inspTypeId) {
		this.inspTypeId = inspTypeId;
	}

	/**
	 * @return the inspStageId
	 */
	public Integer getInspStageId() {
		return inspStageId;
	}

	/**
	 * @param inspStageId the inspStageId to set
	 */
	public void setInspStageId(Integer inspStageId) {
		this.inspStageId = inspStageId;
	}

	/**
	 * @return the inspReportNumber
	 */
	public Integer getInspReportNumber() {
		return inspReportNumber;
	}

	/**
	 * @param inspReportNumber the inspReportNumber to set
	 */
	public void setInspReportNumber(Integer inspReportNumber) {
		this.inspReportNumber = inspReportNumber;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdTimestamp
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * @param createdTimestamp the createdTimestamp to set
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedTimestamp
	 */
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	/**
	 * @param updatedTimestamp the updatedTimestamp to set
	 */
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	/**
	 * @return the isActive
	 */
	public char getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the subscriberMaster
	 */
	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	/**
	 * @param subscriberMaster the subscriberMaster to set
	 */
	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	/**
	 * @return the componentMasterData
	 */
	public LISMaintainMasterDataComponent getComponentMasterData() {
		return componentMasterData;
	}

	/**
	 * @param componentMasterData the componentMasterData to set
	 */
	public void setComponentMasterData(LISMaintainMasterDataComponent componentMasterData) {
		this.componentMasterData = componentMasterData;
	}

	/**
	 * @return the inspRptMasterId
	 */
	public Integer getInspRptMasterId() {
		return inspRptMasterId;
	}

	/**
	 * @param inspRptMasterId the inspRptMasterId to set
	 */
	public void setInspRptMasterId(Integer inspRptMasterId) {
		this.inspRptMasterId = inspRptMasterId;
	}
}
