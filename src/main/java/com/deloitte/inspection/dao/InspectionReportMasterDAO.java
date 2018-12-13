package com.deloitte.inspection.dao;

import com.deloitte.inspection.exception.InspectionReportMasterException;
import com.deloitte.inspection.model.LISInspectionReportMaster;

public interface InspectionReportMasterDAO {
	public void saveReport(LISInspectionReportMaster inspRptMaster) throws InspectionReportMasterException;

	public LISInspectionReportMaster validateInspReportNumber(Integer inspReportNum) throws InspectionReportMasterException;;
}
