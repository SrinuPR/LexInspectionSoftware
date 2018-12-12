package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.InspectionMeasurementDAO;
import com.deloitte.inspection.dao.WorkJobOrderDAO;
import com.deloitte.inspection.dto.WorkJobOrderDTO;
import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISInspectionReportMaster;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;
import com.deloitte.inspection.response.dto.WorkJobOrderResponseDTO;
import com.deloitte.inspection.service.InspectionMeasurementService;

public class InspectionMeasurementServiceImpl implements InspectionMeasurementService{

	private static final Logger logger = LogManager.getLogger(InspectionMeasurementServiceImpl.class);
	
	@Autowired
	private InspectionMeasurementDAO inspectionMeasurementDAO;
	
	@Autowired
	private WorkJobOrderDAO workJobOrderDAO;
	
	WorkJobOrderServiceImpl WorkJobOrderServiceImpl = new WorkJobOrderServiceImpl();
		
	@Override
	public List<String> getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException {
		List<String> list = null;
		logger.info("Entered into getCompDrawNumList service");
		try{
			List<LISInspectionReportMaster> inspectionReportMasters = inspectionMeasurementDAO.getCompDrawNumList(subscriberId);
			if(null != inspectionReportMasters){
				list = new ArrayList<String>();
				for(LISInspectionReportMaster inspctionReport : inspectionReportMasters){
					list.add(inspctionReport.getComponentMasterData().getComponentProductDrawNumber());
				}
			}
		}catch(Exception exception){
			logger.error("Exception Occured in getCompDrawNumList service :"+exception.getMessage());
		}
		return list;
	}

	@Override
	public WorkJobOrderResponseDTO getWorkJobOrderList(String compDrawNum) throws InspectionMeasurementException {
		logger.info("Entered into getWorkJobOrderList service");
		WorkJobOrderResponseDTO workJobOrder = new WorkJobOrderResponseDTO();
		List<WorkJobOrderDTO> result = null;
		try{
			List<LISWorkJobOrderMaster> workJobOrderMasters = workJobOrderDAO.getWorkJobOrderByCompDrawNum(compDrawNum.toLowerCase());
			if(null != workJobOrderMasters){
				//result = WorkJobOrderServiceImpl.transferModelToDTO(workJobOrderMasters);
			}
			workJobOrder.setStatus(StatusConstants.SUCCESS);
			workJobOrder.setResults(result);
		}catch(Exception exception){
			logger.error("Exception Occured in workJobOrderList service :"+exception.getMessage());
			workJobOrder.setStatus(StatusConstants.FAILURE);
		}
		return workJobOrder;
	}
	
	

}
