package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISInspectionReportMaster;

public interface InspectionMeasurementDAO {

	List<LISInspectionReportMaster> getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException;

}
