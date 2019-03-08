package com.deloitte.inspection.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LIS_PIFIM")
public class LISPartIdentification {
	
	@Id
	private String _id;
	
	private String partVerifId;
	
	private String measurementName;

	private String measuredValue;

	private String actualBaseMeasure;
	
	private String actualUpperLimit;
	
	private String actualLowerLimit;
	
	private String deviation;
	
	private String status;
	
	private String partIdentificationNumber;
	
	private String inspectionMeasurementsId;//foreign key

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getPartVerifId() {
		return partVerifId;
	}

	public void setPartVerifId(String partVerifId) {
		this.partVerifId = partVerifId;
	}

	public String getMeasurementName() {
		return measurementName;
	}

	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
	}

	public String getMeasuredValue() {
		return measuredValue;
	}

	public void setMeasuredValue(String measuredValue) {
		this.measuredValue = measuredValue;
	}

	public String getActualBaseMeasure() {
		return actualBaseMeasure;
	}

	public void setActualBaseMeasure(String actualBaseMeasure) {
		this.actualBaseMeasure = actualBaseMeasure;
	}

	public String getActualUpperLimit() {
		return actualUpperLimit;
	}

	public void setActualUpperLimit(String actualUpperLimit) {
		this.actualUpperLimit = actualUpperLimit;
	}

	public String getActualLowerLimit() {
		return actualLowerLimit;
	}

	public void setActualLowerLimit(String actualLowerLimit) {
		this.actualLowerLimit = actualLowerLimit;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
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

	public String getInspectionMeasurementsId() {
		return inspectionMeasurementsId;
	}

	public void setInspectionMeasurementsId(String inspectionMeasurementsId) {
		this.inspectionMeasurementsId = inspectionMeasurementsId;
	}

}
