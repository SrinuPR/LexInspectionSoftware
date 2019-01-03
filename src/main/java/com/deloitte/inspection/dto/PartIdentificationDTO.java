package com.deloitte.inspection.dto;

public class PartIdentificationDTO {
	
	private Integer partVerifId;
	private String measurementName;
	private Float measuredValue;
	private String actualBaseMeasure;
	private Float actualUpperLimit;
	private Float actualLowerLimit;
	private Float deviation;
	private String status;
	private String partIdentificationNumber;
	
	public Integer getPartVerifId() {
		return partVerifId;
	}
	public void setPartVerifId(Integer partVerifId) {
		this.partVerifId = partVerifId;
	}
	public String getMeasurementName() {
		return measurementName;
	}
	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
	}
	public Float getMeasuredValue() {
		return measuredValue;
	}
	public void setMeasuredValue(Float measuredValue) {
		this.measuredValue = measuredValue;
	}
	public String getActualBaseMeasure() {
		return actualBaseMeasure;
	}
	public void setActualBaseMeasure(String actualBaseMeasure) {
		this.actualBaseMeasure = actualBaseMeasure;
	}
	public Float getActualUpperLimit() {
		return actualUpperLimit;
	}
	public void setActualUpperLimit(Float actualUpperLimit) {
		this.actualUpperLimit = actualUpperLimit;
	}
	public Float getActualLowerLimit() {
		return actualLowerLimit;
	}
	public void setActualLowerLimit(Float actualLowerLimit) {
		this.actualLowerLimit = actualLowerLimit;
	}
	public Float getDeviation() {
		return deviation;
	}
	public void setDeviation(Float deviation) {
		this.deviation = deviation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPartIdentificationNumber() {
		return partIdentificationNumber;
	}
	public void setPartIdentificationNumber(String partIdentificationNumber) {
		this.partIdentificationNumber = partIdentificationNumber;
	}

}
