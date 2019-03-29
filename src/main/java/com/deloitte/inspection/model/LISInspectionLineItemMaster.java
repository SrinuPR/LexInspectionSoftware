package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LIS_ILIMC")
public class LISInspectionLineItemMaster {
	
	@Id
	private String _id;
	
	private String inspectionLineItemId;
	
	private String inspectionID;
	
	private String inspectionLineID;
	
	private String componentProductDrawNumber;
	
	private String measurmentName;

	private String CompProdDrawNum;
			
	private String baseMeasure;
	
	private String baseMeasureUnits;
	
	private String upperLimit;
	
	private String lowerLimit;
	
	private String userID;
	
	private Integer subscriberId;
	
	private String subscriberName;
	
	private String createdBy;

	private Date createdTimestamp;
	
	private String updatedBy;

	private Date updatedTimestamp;
	
	private String isActive;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getInspectionLineItemId() {
		return inspectionLineItemId;
	}

	public void setInspectionLineItemId(String inspectionLineItemId) {
		this.inspectionLineItemId = inspectionLineItemId;
	}

	public String getInspectionID() {
		return inspectionID;
	}

	public void setInspectionID(String inspectionID) {
		this.inspectionID = inspectionID;
	}

	public String getInspectionLineID() {
		return inspectionLineID;
	}

	public void setInspectionLineID(String inspectionLineID) {
		this.inspectionLineID = inspectionLineID;
	}

	public String getComponentProductDrawNumber() {
		return componentProductDrawNumber;
	}

	public void setComponentProductDrawNumber(String componentProductDrawNumber) {
		this.componentProductDrawNumber = componentProductDrawNumber;
	}

	public String getMeasurmentName() {
		return measurmentName;
	}

	public void setMeasurmentName(String measurmentName) {
		this.measurmentName = measurmentName;
	}

	public String getCompProdDrawNum() {
		return CompProdDrawNum;
	}

	public void setCompProdDrawNum(String compProdDrawNum) {
		CompProdDrawNum = compProdDrawNum;
	}

	public String getBaseMeasure() {
		return baseMeasure;
	}

	public void setBaseMeasure(String baseMeasure) {
		this.baseMeasure = baseMeasure;
	}

	public String getBaseMeasureUnits() {
		return baseMeasureUnits;
	}

	public void setBaseMeasureUnits(String baseMeasureUnits) {
		this.baseMeasureUnits = baseMeasureUnits;
	}

	public String getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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

}
