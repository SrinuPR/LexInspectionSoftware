package com.deloitte.inspection.service.impl;

import java.util.ArrayList;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.InspectionMeasurementConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.InspectionMeasurementDAO;
import com.deloitte.inspection.dao.WorkJobOrderDAO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.WorkJobOrderDTO;
import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISInspectionReportMaster;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;
import com.deloitte.inspection.response.dto.InspectionMeasurementResponseDTO;
import com.deloitte.inspection.response.dto.WorkJobOrderResponseDTO;
import com.deloitte.inspection.service.InspectionMeasurementService;

@Service
public class InspectionMeasurementServiceImpl implements InspectionMeasurementService{

	private static final Logger logger = LogManager.getLogger(InspectionMeasurementServiceImpl.class);
	
	@Autowired
	private InspectionMeasurementDAO inspectionMeasurementDAO;
	
	@Autowired
	private WorkJobOrderDAO workJobOrderDAO;
	
	@Autowired
	private ComponentMasterDataDAO componentMasterDataDAO;
	
	WorkJobOrderServiceImpl WorkJobOrderServiceImpl = new WorkJobOrderServiceImpl();
		
	@Override
	public InspectionMeasurementResponseDTO getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException {
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		logger.info("Entered into getCompDrawNumList service");
		try{
			List<LISInspectionReportMaster> inspectionReportMasters = inspectionMeasurementDAO.getCompDrawNumList(subscriberId);
			if(null != inspectionReportMasters && inspectionReportMasters.size() > 0){
				List<ComponentMasterDataDTO> componentMasterDataDTOs = new ArrayList<ComponentMasterDataDTO>();
				for(LISInspectionReportMaster reportMaster : inspectionReportMasters){
					ComponentMasterDataDTO componentMasterDataDTO = new ComponentMasterDataDTO();
					LISMaintainMasterDataComponent component = componentMasterDataDAO.getComponentDataByDrwNum(reportMaster.getCompProdDrawNum());
					componentMasterDataDTO.setComponentId(component.getCmdcsId());
					componentMasterDataDTO.setComponentProductDrawNumber(component.getComponentProductDrawNumber());
					componentMasterDataDTO.setComponentProductName(component.getComponentProductName());
					componentMasterDataDTOs.add(componentMasterDataDTO);
				}
				inspectionMeasurementResponseDTO.setComponentData(componentMasterDataDTOs);
			}
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.SUCCESS);
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.COMPONENT_DRAWING_LIST);
		}catch(Exception exception){
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.FAILURE);
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.COMPONENT_DRAWING_LIST);
			logger.error("Exception Occured in getCompDrawNumList service :"+exception.getMessage());
		}
		return inspectionMeasurementResponseDTO;
	}

	@Override
	public InspectionMeasurementResponseDTO getWorkJobOrderList(String compDrawNum) throws InspectionMeasurementException {
		logger.info("Entered into getWorkJobOrderList service");
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		List<WorkJobOrderDTO> result = null;
		try{
			List<LISWorkJobOrderMaster> workJobOrderMasters = workJobOrderDAO.getWorkJobOrderByCompDrawNum(compDrawNum.toLowerCase());
			if(null != workJobOrderMasters){
				//result = WorkJobOrderServiceImpl.transferModelToDTO(workJobOrderMasters);
			}
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.SUCCESS);
			inspectionMeasurementResponseDTO.setWorkOrderList(result);
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.WORK_ORDER_JOB_LIST);
		}catch(Exception exception){
			logger.error("Exception Occured in workJobOrderList service :"+exception.getMessage());
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.FAILURE);
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.WORK_ORDER_JOB_LIST);
		}
		return inspectionMeasurementResponseDTO;
	}
	
	

}
