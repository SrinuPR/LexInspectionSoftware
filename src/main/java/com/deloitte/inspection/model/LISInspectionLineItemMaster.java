package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIS_ILIMC")
public class LISInspectionLineItemMaster implements Serializable{

	private static final long serialVersionUID = -236523268575818079L;

	@Id
	@Column(name = "ILIMC_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer InspectionLineItemId;
	
	@Column(name = "INMDC_COMPONENT_PRODUCT_DRAW_NUM", length = 50, nullable = false)
	private String componentProductDrawNumber;
	
	@Column(name = "MEASUREMENT_NAME", length =50 ,nullable = false)
	private String measurmentName;
			
	@Column(name = "BASE_MEASURE" , nullable = false, length = 5)
	private String baseMeasure;
	
	@Column(name = "BASE_MEASURE_UNITS" , nullable = false, length = 5)
	private String baseMeasureUnits;
	
	@Column(name = "UPPER_LIMIT" , nullable = false)
	private Float upperLimit;
	
	@Column(name = "LOWER_LIMIT", nullable = false)
	private Float lowerLimit;
	
	@Column(name = "USER_ID" , nullable = false)
	private String userID;
	
	@Column(name="SUBSCRIBER_ID", length = 5 , nullable = false)
	private Integer subscriberId;
	
	@Column(name = "SUBSCRIBER_NAME" , length = 150)
	private String subscriberName;
	
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
	
}
