package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_ILIMC")
public class LISInspectionLineItemMaster implements Serializable{

	private static final long serialVersionUID = -236523268575818079L;
	
	@Id
	@Field(value = "ILIMC_ID")
	private Integer InspectionLineItemId;
	
	@Field(value = "INSPECTION_ID")
	private Integer inspectionID;
	
	@Field(value = "INSPECTION_LINE_ID")
	private Integer inspectionLineID;
	
	@Field(value = "INMDC_COMPONENT_PRODUCT_DRAW_NUM")
	private String componentProductDrawNumber;
	
	@Field(value = "MEASUREMENT_NAME")
	private String measurmentName;
	
	@Field(value = "COMP_PROD_DRAW_NUM")
	private String CompProdDrawNum;
			
	@Field(value = "BASE_MEASURE")
	private String baseMeasure;
	
	@Field(value = "BASE_MEASURE_UNITS")
	private String baseMeasureUnits;
	
	@Field(value = "UPPER_LIMIT")
	private Float upperLimit;
	
	@Field(value = "LOWER_LIMIT")
	private Float lowerLimit;
	
	@Field(value = "USER_ID")
	private String userID;
	
	@Field(value = "SUBSCRIBER_ID")
	private Integer subscriberId;
	
	@Field(value = "SUBSCRIBER_NAME")
	private String subscriberName;
	
	@Field(value = "CREATED_BY")
	private String createdBy;
	
	@Field(value = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Field(value = "UPDATED_BY")
	private String updatedBy;
	
	@Field(value = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@Field(value = "IS_ACTIVE")
	private char isActive;

	public Integer getInspectionLineItemId() {
		return InspectionLineItemId;
	}

	public void setInspectionLineItemId(Integer inspectionLineItemId) {
		InspectionLineItemId = inspectionLineItemId;
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

	public Float getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Float upperLimit) {
		this.upperLimit = upperLimit;
	}

	public Float getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Float lowerLimit) {
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

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public Integer getInspectionID() {
		return inspectionID;
	}

	public void setInspectionID(Integer inspectionID) {
		this.inspectionID = inspectionID;
	}

	public Integer getInspectionLineID() {
		return inspectionLineID;
	}

	public void setInspectionLineID(Integer inspectionLineID) {
		this.inspectionLineID = inspectionLineID;
	}

	public String getCompProdDrawNum() {
		return CompProdDrawNum;
	}

	public void setCompProdDrawNum(String compProdDrawNum) {
		CompProdDrawNum = compProdDrawNum;
	}
	
}
