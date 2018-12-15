/**
 * 
 */
package com.deloitte.inspection.dto;


/**
 * @author rnarne
 *
 */
public class InspectionReportMasterDTO {
	
	private Integer inspReportNumber;
	private Integer subscriberId;
	private String subscriberName;
	private String componentProductDrawNumber;
	private String componentProdcuctName;
	private String workJobOrderNumber;
	private Integer workJobOrderId;
	private String lotNumber;
	private Integer lotSize;
	private Integer inspectionTypeId;
	private Integer inspectionStageId;
	private String manufacturingBatchNumber;
	private Integer manufacturingBatchSize;
	private String customerPoNumber;
	private String customerPoDate;
	private Integer customerPoQuantity;
	private String userID;
	private Integer inspRptMasterId;
	//private String userName;
	
	//New fields for Inspection Master
	private String inspectionStageName;
	private String inspectiontypeName;
	private String inspectionDate;
	
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
	 * @return the subscriberId
	 */
	public Integer getSubscriberId() {
		return subscriberId;
	}
	/**
	 * @param subscriberId the subscriberId to set
	 */
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	/**
	 * @return the subscriberName
	 */
	public String getSubscriberName() {
		return subscriberName;
	}
	/**
	 * @param subscriberName the subscriberName to set
	 */
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	/**
	 * @return the componentProductDrawNumber
	 */
	public String getComponentProductDrawNumber() {
		return componentProductDrawNumber;
	}
	/**
	 * @param componentProductDrawNumber the componentProductDrawNumber to set
	 */
	public void setComponentProductDrawNumber(String componentProductDrawNumber) {
		this.componentProductDrawNumber = componentProductDrawNumber;
	}
	/**
	 * @return the componentProdcuctName
	 */
	public String getComponentProdcuctName() {
		return componentProdcuctName;
	}
	/**
	 * @param componentProdcuctName the componentProdcuctName to set
	 */
	public void setComponentProdcuctName(String componentProdcuctName) {
		this.componentProdcuctName = componentProdcuctName;
	}
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
	 * @return the inspectionTypeId
	 */
	public Integer getInspectionTypeId() {
		return inspectionTypeId;
	}
	/**
	 * @param inspectionTypeId the inspectionTypeId to set
	 */
	public void setInspectionTypeId(Integer inspectionTypeId) {
		this.inspectionTypeId = inspectionTypeId;
	}

	/**
	 * @return the inspectionStageId
	 */
	public Integer getInspectionStageId() {
		return inspectionStageId;
	}
	/**
	 * @param inspectionStageId the inspectionStageId to set
	 */
	public void setInspectionStageId(Integer inspectionStageId) {
		this.inspectionStageId = inspectionStageId;
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
	public String getCustomerPoDate() {
		return customerPoDate;
	}
	/**
	 * @param customerPoDate the customerPoDate to set
	 */
	public void setCustomerPoDate(String customerPoDate) {
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
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Integer getInspRptMasterId() {
		return inspRptMasterId;
	}
	public void setInspRptMasterId(Integer inspRptMasterId) {
		this.inspRptMasterId = inspRptMasterId;
	}
	public Integer getWorkJobOrderId() {
		return workJobOrderId;
	}
	public void setWorkJobOrderId(Integer workJobOrderId) {
		this.workJobOrderId = workJobOrderId;
	}
	public String getInspectionStageName() {
		return inspectionStageName;
	}
	public void setInspectionStageName(String inspectionStageName) {
		this.inspectionStageName = inspectionStageName;
	}
	public String getInspectiontypeName() {
		return inspectiontypeName;
	}
	public void setInspectiontypeName(String inspectiontypeName) {
		this.inspectiontypeName = inspectiontypeName;
	}
	public String getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(String inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	
}
