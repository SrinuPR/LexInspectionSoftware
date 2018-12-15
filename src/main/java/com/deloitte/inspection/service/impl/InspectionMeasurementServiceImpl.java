package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.InspectionMeasurementConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.FacilitiesMasterDAO;
import com.deloitte.inspection.dao.InspectionMeasurementDAO;
import com.deloitte.inspection.dao.InspectionReportMasterDAO;
import com.deloitte.inspection.dao.InspectionStageMasterDAO;
import com.deloitte.inspection.dao.InspectionTypeMasterDAO;
import com.deloitte.inspection.dao.ShiftMasterDAO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.dto.InspectionReportMasterDTO;
import com.deloitte.inspection.dto.ShiftMasterDTO;
import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISFacilityMaster;
import com.deloitte.inspection.model.LISInspectionReportMaster;
import com.deloitte.inspection.model.LISInspectionStageMaster;
import com.deloitte.inspection.model.LISInspectionTypeMaster;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISShiftMaster;
import com.deloitte.inspection.response.dto.InspectionMeasurementResponseDTO;
import com.deloitte.inspection.service.InspectionMeasurementService;
import com.deloitte.inspection.util.InspectionUtils;

@Service
public class InspectionMeasurementServiceImpl implements InspectionMeasurementService{

	private static final Logger logger = LogManager.getLogger(InspectionMeasurementServiceImpl.class);
	
	@Autowired
	private InspectionMeasurementDAO inspectionMeasurementDAO;
	
	@Autowired
	private ComponentMasterDataDAO componentMasterDataDAO;
	
	@Autowired
	private InspectionReportMasterDAO inspectionReportMasterDAO;
	
	@Autowired
	private FacilitiesMasterDAO facilityDAO;
	
	@Autowired
	private ShiftMasterDAO shiftMasterDAO;
	
	@Autowired
	private InspectionStageMasterDAO inspectionStageMasterDAO;
	
	@Autowired
	private InspectionTypeMasterDAO inspectionTypeMasterDAO;
		
	@Override
	public InspectionMeasurementResponseDTO getCompDrawNumList(Integer subscriberId) throws InspectionMeasurementException {
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		logger.info("Entered into getCompDrawNumList service");
		try{
			List<LISInspectionReportMaster> inspectionReportMasters = inspectionMeasurementDAO.getCompDrawNumList(subscriberId);
			if(null != inspectionReportMasters && inspectionReportMasters.size() > 0){
				logger.info("Entered into Component Data");
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
			List<LISFacilityMaster> facilityMasters = facilityDAO.getFacilityDetailsBySubscriberID(subscriberId);
			if(null != facilityMasters && facilityMasters.size() >0 ){
				logger.info("Entered into facility Data");
				List<FacilityMasterDTO> facilityMasterDTOs = new ArrayList<FacilityMasterDTO>();
				for(LISFacilityMaster facilityMaster : facilityMasters){
					FacilityMasterDTO facilityMasterDTO = new FacilityMasterDTO();
					facilityMasterDTO.setFacilityId(facilityMaster.getFacilityId());
					facilityMasterDTO.setFacilityName(facilityMaster.getFacilityName());
					facilityMasterDTO.setFacilityNumber(facilityMaster.getFacilityNumber());
					facilityMasterDTOs.add(facilityMasterDTO);
				}
				inspectionMeasurementResponseDTO.setFacilityData(facilityMasterDTOs);
			}
			List<LISShiftMaster> shiftMasters = shiftMasterDAO.findBySubscriberId(subscriberId);
			if(null != shiftMasters && shiftMasters.size() > 0){
				logger.info("Entered into Shift Data");
				List<ShiftMasterDTO> shiftMasterDTOs = new ArrayList<ShiftMasterDTO>();
				for(LISShiftMaster shiftMaster : shiftMasters){
					ShiftMasterDTO shiftMasterDTO = new ShiftMasterDTO();
					shiftMasterDTO.setShiftId(shiftMaster.getShiftId());
					shiftMasterDTO.setShiftName(shiftMaster.getShiftName());
					shiftMasterDTOs.add(shiftMasterDTO);
				}
				inspectionMeasurementResponseDTO.setShiftData(shiftMasterDTOs);
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
	public InspectionMeasurementResponseDTO getInspectionReportList(String compDrawNum)
			throws InspectionMeasurementException {
		logger.info("Entered into getInspectionReportList service");
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		try{
			List<LISInspectionReportMaster> inspectionReportMasters = inspectionReportMasterDAO.getInspectionReportList(compDrawNum.toLowerCase());
			if(null != inspectionReportMasters && inspectionReportMasters.size() > 0){
				logger.info("Entered into IRMCS Data");
				List<InspectionReportMasterDTO> inspectionReportMasterDTOs = new ArrayList<InspectionReportMasterDTO>();
				for(LISInspectionReportMaster report:inspectionReportMasters){
					InspectionReportMasterDTO inspectionReportMasterDTO = new InspectionReportMasterDTO();
					inspectionReportMasterDTO.setInspRptMasterId(report.getInspRptMasterId());
					inspectionReportMasterDTO.setInspReportNumber(report.getInspReportNumber());
					inspectionReportMasterDTO.setWorkJobOrderId(report.getWorkJobOrderId());
					inspectionReportMasterDTO.setWorkJobOrderNumber(report.getWorkJobOrderNumber());
					inspectionReportMasterDTO.setLotSize(report.getLotSize());
					inspectionReportMasterDTO.setLotNumber(report.getLotNumber());
					inspectionReportMasterDTO.setManufacturingBatchNumber(report.getManufacturingBatchNumber());
					inspectionReportMasterDTO.setManufacturingBatchSize(report.getManufacturingBatchSize());
					inspectionReportMasterDTO.setComponentProdcuctName(report.getComponentProdcuctName());
					inspectionReportMasterDTO.setCustomerPoDate(InspectionUtils.convertDateToString(report.getCustomerPoDate()));
					inspectionReportMasterDTO.setCustomerPoNumber(report.getCustomerPoNumber());
					inspectionReportMasterDTO.setCustomerPoQuantity(report.getCustomerPoQuantity());
					LISInspectionStageMaster inspectionStageMaster = inspectionStageMasterDAO.getInspSatgeId(report.getInspectionStageId());
					LISInspectionTypeMaster inspectionTypeMaster = inspectionTypeMasterDAO.getInspTypeId(report.getInspectionTypeId());
					inspectionReportMasterDTO.setInspectionDate(InspectionUtils.convertDateToString(new Date()));
					//inspectionReportMasterDTO.set
					if(null != inspectionStageMaster){
						inspectionReportMasterDTO.setInspectionStageId(inspectionStageMaster.getInspStageId());
						inspectionReportMasterDTO.setInspectionStageName(inspectionStageMaster.getInspStageName());
					}
					if(null != inspectionTypeMaster){
						inspectionReportMasterDTO.setInspectionTypeId(inspectionTypeMaster.getInspTypeId());
						inspectionReportMasterDTO.setInspectiontypeName(inspectionTypeMaster.getInspTypeName());
					}
					inspectionReportMasterDTOs.add(inspectionReportMasterDTO);
				}
				inspectionMeasurementResponseDTO.setReportData(inspectionReportMasterDTOs);
			}
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.SUCCESS);
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.INSPECTION_REPORT_LIST);
		}catch(Exception exception){
			logger.error("Exception Occured in getInspectionReportList service :"+exception.getMessage());
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.FAILURE);
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.INSPECTION_REPORT_LIST);
		}
		return inspectionMeasurementResponseDTO;
	}	

}
