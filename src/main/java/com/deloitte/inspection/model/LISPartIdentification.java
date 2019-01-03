package com.deloitte.inspection.model;


import java.io.Serializable;

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
@Table(name = "LIS_PIFIM")
public class LISPartIdentification implements Serializable{
	
	private static final long serialVersionUID = -4113989812466679809L;
	
	@Id	
	@Column(name = "PIFIM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer partVerifId;
	
	@Column(name = "MEASUREMENT_NAME", length = 50)
	private String measurementName;
	
	@Column(name = "MEASURED_VALUE", nullable = false)
	private Float measuredValue;
	
	@Column(name = "ACTUAL_BASE_MEASURE", length = 5, nullable = false)
	private String actualBaseMeasure;
	
	@Column(name = "ACTUAL_UPPER_LIMIT")
	private Float actualUpperLimit;
	
	@Column(name = "ACTUAL_LOWER_LIMIT")
	private Float actualLowerLimit;
	
	@Column(name = "DEVIATION")
	private Float deviation;
	
	@Column(name = "STATUS", length = 8)
	private String status;
	
	@Column(name = "PART_IDENTIFICATION_NUMBER", length = 30, nullable = false)
	private String partIdentificationNumber;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="IMDES_ID")
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

	public LISInspectionMeasurements getInspectionMeasurements() {
		return inspectionMeasurements;
	}

	public void setInspectionMeasurements(LISInspectionMeasurements inspectionMeasurements) {
		this.inspectionMeasurements = inspectionMeasurements;
	}

	public String getPartIdentificationNumber() {
		return partIdentificationNumber;
	}

	public void setPartIdentificationNumber(String partIdentificationNumber) {
		this.partIdentificationNumber = partIdentificationNumber;
	}

}
