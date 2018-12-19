package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISInspectionMeasurements;
import com.deloitte.inspection.model.LISInspectionReportMaster;

public interface InspectionMeasurementDAO {

	public List<LISInspectionReportMaster> getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> getProducedQuantityListByBatchNumber(String manufacturingBatchNumber) throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> getProducedQuantityListByLotchNumber(String lotNumber) throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> getProducedQuantityListByWJandPO(String workJobOrderNumber,
			String customerPONumber) throws InspectionMeasurementException;

	public LISInspectionReportMaster getWorkJobOrderByReportNum(Integer reportNumber)  throws InspectionMeasurementException;

	public List<LISInspectionMeasurements> getAllMeasurementList(String userId) throws InspectionMeasurementException;

}
