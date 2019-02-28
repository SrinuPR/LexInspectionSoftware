package com.deloitte.inspection.dto;

public class InspectionLineItemDTO {
	
	private Integer subscriberId;
	private String subscriberName;
	private Integer inspectionLineItemId;
	private String componentProductDrawNumber;
	private String measurementName;
	private String baseMeasure;
	private String baseMeasureUnits;
	private Float upperLimit;
	private Float lowerLimit;
	private String userId;
	private String userName;
	private String status;
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getInspectionLineItemId() {
		return inspectionLineItemId;
	}
	public void setInspectionLineItemId(Integer inspectionLineItemId) {
		this.inspectionLineItemId = inspectionLineItemId;
	}
	public String getComponentProductDrawNumber() {
		return componentProductDrawNumber;
	}
	public void setComponentProductDrawNumber(String componentProductDrawNumber) {
		this.componentProductDrawNumber = componentProductDrawNumber;
	}
	public String getMeasurementName() {
		return measurementName;
	}
	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
