package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISInspectionMeasurements;
import com.deloitte.inspection.model.LISInspectionReportMaster;

public interface InspectionMeasurementDAO {

	List<LISInspectionReportMaster> getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException;

	List<LISInspectionMeasurements> getProducedQuantityListByBatchNumber(String manufacturingBatchNumber) throws InspectionMeasurementException;

	List<LISInspectionMeasurements> getProducedQuantityListByLotchNumber(String lotNumber) throws InspectionMeasurementException;

	List<LISInspectionMeasurements> getProducedQuantityListByWJandPO(String workJobOrderNumber,
			String customerPONumber) throws InspectionMeasurementException;

}
