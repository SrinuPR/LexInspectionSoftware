package com.deloitte.inspection.dao;

import java.util.List;
import java.util.Set;

import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISInspectionMeasurements;
import com.deloitte.inspection.model.LISInspectionReportMaster;
import com.deloitte.inspection.model.LISPartIdentification;

public interface InspectionMeasurementDAO {

	public List<LISInspectionReportMaster> getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> getProducedQuantityListByBatchNumber(String manufacturingBatchNumber) throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> getProducedQuantityListByLotchNumber(String lotNumber) throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> getProducedQuantityListByWJandPO(String workJobOrderNumber,
			String customerPONumber) throws InspectionMeasurementException;

	public LISInspectionReportMaster getWorkJobOrderByReportNum(Integer reportNumber)  throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> getAllMeasurementList(String userId) throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> validatePartIdentification(String partIdententificationId,Integer subscriberId) throws InspectionMeasurementException;

	public void saveMeasurementsToDataBase(LISInspectionMeasurements inspectionMeasurements) throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> getProducedQuantityListByWJandSubId(Set<String> wjoNum,
			Integer subscriberId) throws InspectionMeasurementException;

	public LISPartIdentification getMeasurementRecord(Integer partVerifId) throws InspectionMeasurementException;

	public void saveMeasurementRecord(LISPartIdentification partIdentification) throws InspectionMeasurementException;

	public LISInspectionMeasurements getRecordById(Integer inspectionMeasurementId)  throws InspectionMeasurementException;

}
