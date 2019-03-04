package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LIS_IRMCS")
public class LISInspectionReportMaster {
	
	@Id
	private String inspRptMasterId;
	
	private Integer inspReportNumber;

	private Integer subscriberId;
	
	private String subscriberName;
		
	private String compProdDrawNum;
	
	private String componentProdcuctName;
		
	private String workJobOrderNumber;
	
	private Integer workJobOrderId;
	
	private String lotNumber;

	private Integer lotSize;
	
	private Integer inspectionTypeId;

	private Integer InspectionStageId;

	private String manufacturingBatchNumber;

	private Integer manufacturingBatchSize;
	
	private String customerPoNumber;
	
	private Date customerPoDate;
	
	private Integer customerPoQuantity;
	
	private String userID;
	
	private String createdBy;
	
	private Date createdTimestamp;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private String isActive;

	private String reportStatus;

	public String getInspRptMasterId() {
		return inspRptMasterId;
	}

	public void setInspRptMasterId(String inspRptMasterId) {
		this.inspRptMasterId = inspRptMasterId;
	}

	public Integer getInspReportNumber() {
		return inspReportNumber;
	}

	public void setInspReportNumber(Integer inspReportNumber) {
		this.inspReportNumber = inspReportNumber;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getCompProdDrawNum() {
		return compProdDrawNum;
	}

	public void setCompProdDrawNum(String compProdDrawNum) {
		this.compProdDrawNum = compProdDrawNum;
	}

	public String getComponentProdcuctName() {
		return componentProdcuctName;
	}

	public void setComponentProdcuctName(String componentProdcuctName) {
		this.componentProdcuctName = componentProdcuctName;
	}

	public String getWorkJobOrderNumber() {
		return workJobOrderNumber;
	}

	public void setWorkJobOrderNumber(String workJobOrderNumber) {
		this.workJobOrderNumber = workJobOrderNumber;
	}

	public Integer getWorkJobOrderId() {
		return workJobOrderId;
	}

	public void setWorkJobOrderId(Integer workJobOrderId) {
		this.workJobOrderId = workJobOrderId;
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

	public Integer getInspectionTypeId() {
		return inspectionTypeId;
	}

	public void setInspectionTypeId(Integer inspectionTypeId) {
		this.inspectionTypeId = inspectionTypeId;
	}

	public Integer getInspectionStageId() {
		return InspectionStageId;
	}

	public void setInspectionStageId(Integer inspectionStageId) {
		InspectionStageId = inspectionStageId;
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

	public String getCustomerPoNumber() {
		return customerPoNumber;
	}

	public void setCustomerPoNumber(String customerPoNumber) {
		this.customerPoNumber = customerPoNumber;
	}

	public Date getCustomerPoDate() {
		return customerPoDate;
	}

	public void setCustomerPoDate(Date customerPoDate) {
		this.customerPoDate = customerPoDate;
	}

	public Integer getCustomerPoQuantity() {
		return customerPoQuantity;
	}

	public void setCustomerPoQuantity(Integer customerPoQuantity) {
		this.customerPoQuantity = customerPoQuantity;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	
}
