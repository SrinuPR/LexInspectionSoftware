package com.deloitte.inspection.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "LIS_PIFIM")
public class LISPartIdentification implements Serializable{
	
	private static final long serialVersionUID = -4113989812466679809L;
	
	@Id
	private long id;
	
	@Field(value = "PIFIM_ID")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer partVerifId;
	
	@Field(value = "MEASUREMENT_NAME")
	private String measurementName;
	
	@Field(value = "MEASURED_VALUE")
	private Float measuredValue;
	
	@Field(value = "ACTUAL_BASE_MEASURE")
	private String actualBaseMeasure;
	
	@Field(value = "ACTUAL_UPPER_LIMIT")
	private Float actualUpperLimit;
	
	@Field(value = "ACTUAL_LOWER_LIMIT")
	private Float actualLowerLimit;
	
	@Field(value = "DEVIATION")
	private Float deviation;
	
	@Field(value = "STATUS")
	private String status;
	
	@Field(value = "PART_IDENTIFICATION_NUMBER")
	private String partIdentificationNumber;
	
	@Field(value = "IMDES_ID")
	private Integer inspectionMeasurementId;
	
	private LISInspectionMeasurements inspectionMeasurements;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getInspectionMeasurementId() {
		return inspectionMeasurementId;
	}

	public void setInspectionMeasurementId(Integer inspectionMeasurementId) {
		this.inspectionMeasurementId = inspectionMeasurementId;
	}

	public LISInspectionMeasurements getInspectionMeasurements() {
		return inspectionMeasurements;
	}

	public void setInspectionMeasurements(LISInspectionMeasurements inspectionMeasurements) {
		this.inspectionMeasurements = inspectionMeasurements;
	}
}
