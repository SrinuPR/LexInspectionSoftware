package com.deloitte.inspection.service.impl;

import java.text.ParseException;
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
import com.deloitte.inspection.dao.InspectionLineItemMasterDAO;
import com.deloitte.inspection.dao.InspectionMeasurementDAO;
import com.deloitte.inspection.dao.InspectionReportMasterDAO;
import com.deloitte.inspection.dao.InspectionStageMasterDAO;
import com.deloitte.inspection.dao.InspectionTypeMasterDAO;
import com.deloitte.inspection.dao.ShiftMasterDAO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.dto.InspectionLineItemDTO;
import com.deloitte.inspection.dto.InspectionMeasurementDTO;
import com.deloitte.inspection.dto.InspectionReportMasterDTO;
import com.deloitte.inspection.dto.PartIdentificationDTO;
import com.deloitte.inspection.dto.ShiftMasterDTO;
import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISFacilityMaster;
import com.deloitte.inspection.model.LISInspectionLineItemMaster;
import com.deloitte.inspection.model.LISInspectionMeasurements;
import com.deloitte.inspection.model.LISInspectionReportMaster;
import com.deloitte.inspection.model.LISInspectionStageMaster;
import com.deloitte.inspection.model.LISInspectionTypeMaster;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISPartIdentification;
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
	
	@Autowired
	private InspectionLineItemMasterDAO inspectionLineItemMasterDAO;
		
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
			List<LISShiftMaster> shiftMasters = shiftMasterDAO.getAllShiftsBySubscriberId(subscriberId);
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
					List<LISInspectionLineItemMaster> inspectionLineItemMasters = inspectionLineItemMasterDAO.getAllInspectionLineItemsByDrawNum(compDrawNum.toLowerCase());
					List<InspectionLineItemDTO> inspectionLineItemDTOs = new ArrayList<InspectionLineItemDTO>();
					if(null != inspectionLineItemMasters && inspectionLineItemMasters.size() > 0){
						for(LISInspectionLineItemMaster inspectionLineItemMaster :inspectionLineItemMasters){
							InspectionLineItemDTO lineItemDTO = new InspectionLineItemDTO();
							lineItemDTO.setBaseMeasure(inspectionLineItemMaster.getBaseMeasure());
							lineItemDTO.setBaseMeasureUnits(inspectionLineItemMaster.getBaseMeasureUnits());
							lineItemDTO.setMeasurementName(inspectionLineItemMaster.getMeasurmentName());
							lineItemDTO.setLowerLimit(inspectionLineItemMaster.getLowerLimit());
							lineItemDTO.setUpperLimit(inspectionLineItemMaster.getUpperLimit());
							inspectionLineItemDTOs.add(lineItemDTO);
						}
					}
					inspectionReportMasterDTO.setLineItemData(inspectionLineItemDTOs);
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

	@Override
	public InspectionMeasurementResponseDTO validatePartIdentification(String partIdententificationId, InspectionMeasurementDTO inspectionMeasurementDTO)
			throws InspectionMeasurementException {
		logger.info("Inside validatePartIdentification Service");
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		try{
			List<LISInspectionMeasurements> lists = inspectionMeasurementDAO.validatePartIdentification(partIdententificationId.toLowerCase(),inspectionMeasurementDTO.getSubscriberId());
			if(null != lists && lists.size() > 0){
				inspectionMeasurementResponseDTO.setStatus(StatusConstants.WARNING);
				inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.PART_NUMBER_EXIST);
				inspectionMeasurementDTO = prefillDataOnPartConfirmation(inspectionMeasurementDTO,lists);
			}else{
				inspectionMeasurementDTO = saveInspectionMeasurementData(inspectionMeasurementDTO);
				inspectionMeasurementResponseDTO.setStatus(StatusConstants.SUCCESS);
				inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.PART_NUMBER_DOES_NOT_EXIST);
			}
			inspectionMeasurementResponseDTO.setResults(inspectionMeasurementDTO);
		}catch(Exception exception){
			logger.error("Exception Occured in validatePartIdentification service :"+exception.getMessage());
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.FAILURE);
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.UN_EXPECTED_EXCEPTION);
		}
		return inspectionMeasurementResponseDTO;
	}

	private InspectionMeasurementDTO saveInspectionMeasurementData(InspectionMeasurementDTO inspectionMeasurementDTO) {
		logger.info("Inside saveInspectionMeasurementData Service");
		try{
			LISInspectionMeasurements inspectionMeasurements = new LISInspectionMeasurements();
			inspectionMeasurements = transformToModel(inspectionMeasurementDTO,inspectionMeasurements,InspectionMeasurementConstants.INSERT);
			inspectionMeasurementDAO.saveMeasurementsToDataBase(inspectionMeasurements);
		}catch(Exception exception){
			logger.error("Exception Occured in saveInspectionMeasurementData service :"+exception.getMessage());
		}
		return inspectionMeasurementDTO;
	}
	
	private InspectionMeasurementDTO prefillDataOnPartConfirmation(InspectionMeasurementDTO inspectionMeasurementDTO, List<LISInspectionMeasurements> inspectionMeasurements2)throws Exception{
		if(null != inspectionMeasurements2 && inspectionMeasurements2.size() > 0){
			for(LISInspectionMeasurements inspectionMeasurements3:inspectionMeasurements2){
				if(null != inspectionMeasurements3.getPartIdentifications()){
					List<PartIdentificationDTO> partList = new ArrayList<PartIdentificationDTO>();
					partList = transformToDTO(partList, inspectionMeasurements3.getPartIdentifications());
					inspectionMeasurementDTO.setPartIdentifications(partList);
				}
				inspectionMeasurementDTO = inspectionData(inspectionMeasurementDTO,inspectionMeasurements3);
				break;
			}
		}
		return inspectionMeasurementDTO;
	}
	
	private InspectionMeasurementDTO inspectionData(InspectionMeasurementDTO inspectionMeasurementDTO, LISInspectionMeasurements inspectionMeasurements) throws ParseException{
		inspectionMeasurementDTO.setInspectionMeasurementId(inspectionMeasurements.getInspectionMeasurementId());
		inspectionMeasurementDTO.setComponentProductName(inspectionMeasurements.getComponentProductName());
		inspectionMeasurementDTO.setCompProductDrawNum(inspectionMeasurements.getCompProductDrawNum());
		inspectionMeasurementDTO.setCustomerNameAddress(inspectionMeasurements.getCustomerNameAddress());
		inspectionMeasurementDTO.setCustomerPODate(InspectionUtils.convertDateToString(inspectionMeasurements.getCustomerPODate()));
		inspectionMeasurementDTO.setCustomerPONumber(inspectionMeasurements.getCustomerPONumber());
		inspectionMeasurementDTO.setCustomerPOQuantity(inspectionMeasurements.getCustomerPOQuantity());
		inspectionMeasurementDTO.setFacilityMachineName(inspectionMeasurements.getFacilityMachineName());
		inspectionMeasurementDTO.setFacilityMachineNumber(inspectionMeasurements.getFacilityMachineNumber());
		inspectionMeasurementDTO.setInspectionDate(InspectionUtils.convertDateToString(inspectionMeasurements.getInspectionDate()));
		inspectionMeasurementDTO.setInspectionReportNumber(inspectionMeasurements.getInspectionReportNumber());
		inspectionMeasurementDTO.setInspectionStage(inspectionMeasurements.getInspectionStage());
		inspectionMeasurementDTO.setInspectionType(inspectionMeasurements.getInspectionType());
		inspectionMeasurementDTO.setLotNumber(inspectionMeasurements.getLotNumber());
		inspectionMeasurementDTO.setLotSize(inspectionMeasurements.getLotSize());
		inspectionMeasurementDTO.setManufacturingBatchNumber(inspectionMeasurements.getManufacturingBatchNumber());
		inspectionMeasurementDTO.setManufacturingBatchSize(inspectionMeasurements.getManufacturingBatchSize());
		inspectionMeasurementDTO.setPartIdentificationNumber(inspectionMeasurements.getPartIdentificationNumber());
		inspectionMeasurementDTO.setShiftID(inspectionMeasurements.getShiftID());
		inspectionMeasurementDTO.setShiftName(inspectionMeasurements.getShiftName());
		inspectionMeasurementDTO.setSubscriberId(inspectionMeasurements.getSubscriberId());
		inspectionMeasurementDTO.setUserId(inspectionMeasurements.getUserId());
		inspectionMeasurementDTO.setSubscriberName(inspectionMeasurements.getSubscriberName());
		inspectionMeasurementDTO.setUserName(inspectionMeasurements.getUserName());
		return inspectionMeasurementDTO;
	}

	private List<LISPartIdentification> measurementData(List<LISPartIdentification> partIdentificationList,
			List<PartIdentificationDTO> lineItemData, LISInspectionMeasurements inspectionMeasurements) {
		if(null != lineItemData && lineItemData.size() > 0){
			for(PartIdentificationDTO lineItemDTO : lineItemData){
				LISPartIdentification partIdentification = new LISPartIdentification();
				partIdentification.setStatus(lineItemDTO.getStatus());
				partIdentification.setActualBaseMeasure(lineItemDTO.getActualBaseMeasure());
				partIdentification.setActualLowerLimit(lineItemDTO.getActualLowerLimit());
				partIdentification.setActualLowerLimit(lineItemDTO.getActualUpperLimit());
				partIdentification.setStatus(InspectionMeasurementConstants.IN_ACTIVE);
				partIdentification.setPartIdentificationNumber(inspectionMeasurements.getPartIdentificationNumber());
				partIdentification.setInspectionMeasurements(inspectionMeasurements);
				partIdentificationList.add(partIdentification);
			}
		}
		return partIdentificationList;
	}	
	
	private List<PartIdentificationDTO> transformToDTO(List<PartIdentificationDTO> partList,List<LISPartIdentification> partsFromDB) throws Exception{
		for(LISPartIdentification partIdentification:partsFromDB){
			PartIdentificationDTO partIdentificationDTO = new PartIdentificationDTO();
			partIdentificationDTO.setPartVerifId(partIdentification.getPartVerifId());
			partIdentificationDTO.setActualLowerLimit(partIdentification.getActualLowerLimit());
			partIdentificationDTO.setActualUpperLimit(partIdentification.getActualUpperLimit());
			partIdentificationDTO.setPartIdentificationNumber(partIdentification.getPartIdentificationNumber());
			partIdentificationDTO.setMeasurementName(partIdentification.getMeasurementName());
			partIdentificationDTO.setMeasuredValue(partIdentification.getMeasuredValue());
			partIdentificationDTO.setActualBaseMeasure(partIdentification.getActualBaseMeasure());
			partList.add(partIdentificationDTO);
		}
		return partList;
	}
	
	private LISInspectionMeasurements transformToModel(InspectionMeasurementDTO inspectionMeasurementDTO, LISInspectionMeasurements inspectionMeasurements,String action) throws ParseException{
		if(null != action && InspectionMeasurementConstants.INSERT.equalsIgnoreCase(action)){
			inspectionMeasurements.setPartIdentificationNumber(inspectionMeasurementDTO.getPartIdentificationNumber());
			inspectionMeasurements.setCreatedBy(inspectionMeasurementDTO.getUserId());
			inspectionMeasurements.setSubscriberId(inspectionMeasurementDTO.getSubscriberId());
			inspectionMeasurements.setSubscriberName(inspectionMeasurementDTO.getSubscriberName());
			inspectionMeasurements.setCreatedTimestamp(new Date());
			inspectionMeasurements.setCustomerNameAddress(inspectionMeasurementDTO.getCustomerNameAddress());
			inspectionMeasurements.setComponentProductName(inspectionMeasurementDTO.getComponentProductName());
			inspectionMeasurements.setCompProductDrawNum(inspectionMeasurementDTO.getCompProductDrawNum());
			inspectionMeasurements.setCustomerPODate(InspectionUtils.convertStringToDate(inspectionMeasurementDTO.getCustomerPODate()));
			inspectionMeasurements.setCustomerPONumber(inspectionMeasurementDTO.getCustomerPONumber());
			inspectionMeasurements.setCustomerPOQuantity(inspectionMeasurementDTO.getCustomerPOQuantity());
			inspectionMeasurements.setFacilityMachineName(inspectionMeasurementDTO.getFacilityMachineName());
			inspectionMeasurements.setFacilityMachineNumber(inspectionMeasurementDTO.getFacilityMachineNumber());
			inspectionMeasurements.setInspectionDate(InspectionUtils.convertStringToDate(inspectionMeasurementDTO.getInspectionDate()));
			inspectionMeasurements.setInspectionReportNumber(inspectionMeasurementDTO.getInspectionReportNumber());
			inspectionMeasurements.setInspectionStage(inspectionMeasurementDTO.getInspectionStage());
			inspectionMeasurements.setInspectionType(inspectionMeasurementDTO.getInspectionType());
			inspectionMeasurements.setIsActive(StatusConstants.IS_ACTIVE);
			inspectionMeasurements.setLotNumber(inspectionMeasurementDTO.getLotNumber());
			inspectionMeasurements.setLotSize(inspectionMeasurementDTO.getLotSize());
			inspectionMeasurements.setManufacturingBatchNumber(inspectionMeasurementDTO.getManufacturingBatchNumber());
			inspectionMeasurements.setManufacturingBatchSize(inspectionMeasurementDTO.getManufacturingBatchSize());
			inspectionMeasurements.setMeasurementRecordstatus(InspectionMeasurementConstants.MEASUREMENT_INPROGRESS);
			inspectionMeasurements.setShiftID(inspectionMeasurementDTO.getShiftID());
			inspectionMeasurements.setShiftName(inspectionMeasurementDTO.getShiftName());
			inspectionMeasurements.setWorkJobOrderNumber(inspectionMeasurementDTO.getWorkJobOrderNumber());
			inspectionMeasurements.setUserName(inspectionMeasurementDTO.getUserName());
			List<LISPartIdentification> partIdentificationList = new ArrayList<LISPartIdentification>();
			partIdentificationList = measurementData(partIdentificationList,inspectionMeasurementDTO.getPartIdentifications(),inspectionMeasurements);
			inspectionMeasurements.setPartIdentifications(partIdentificationList);
		}
		return inspectionMeasurements;
	}

	@Override
	public InspectionMeasurementResponseDTO saveMeasurement(PartIdentificationDTO partIdentificationDTO) {
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		try{
			if(null != partIdentificationDTO.getPartVerifId()){
				LISPartIdentification partIdentification = inspectionMeasurementDAO.getMeasurementRecord(partIdentificationDTO.getPartVerifId());
				if(null != partIdentification){
					partIdentification.setMeasuredValue(partIdentificationDTO.getMeasuredValue());
					partIdentification.setStatus(partIdentificationDTO.getStatus());
					partIdentification.setDeviation(partIdentificationDTO.getDeviation());
					partIdentification.setActualLowerLimit(partIdentificationDTO.getActualLowerLimit());
					partIdentification.setActualUpperLimit(partIdentificationDTO.getActualUpperLimit());
					partIdentification.setActualBaseMeasure(partIdentificationDTO.getActualBaseMeasure());
					inspectionMeasurementDAO.saveMeasurementRecord(partIdentification);
					inspectionMeasurementResponseDTO.setStatus(StatusConstants.SUCCESS);
					inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.SAVE_MEASUREMENT_SUCCESS);
				}else{
					inspectionMeasurementResponseDTO.setStatus(StatusConstants.FAILURE);
					inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.SAVE_MEASUREMENT_FAILED);
				}
			}
			
		}catch(Exception exception){
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.SAVE_MEASUREMENT_FAILED);
		}
		return inspectionMeasurementResponseDTO;
	}

	@Override
	public InspectionMeasurementResponseDTO savePart(InspectionMeasurementDTO inspectionMeasurementDTO)
			throws InspectionMeasurementException {
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		if(null != inspectionMeasurementDTO.getInspectionMeasurementId()){
			LISInspectionMeasurements inspectionMeasurements = inspectionMeasurementDAO.getRecordById(inspectionMeasurementDTO.getInspectionMeasurementId());
			if(null != inspectionMeasurements){
				inspectionMeasurements.setPartStatus(InspectionMeasurementConstants.MEASUREMENT_COMPLETED);
				inspectionMeasurementDAO.saveMeasurementsToDataBase(inspectionMeasurements);
				inspectionMeasurementResponseDTO.setStatus(StatusConstants.SUCCESS);
				inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.PART_SAVE);
			}else{
				inspectionMeasurementResponseDTO.setStatus(StatusConstants.FAILURE);
				inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.PART_NOT_SAVE);
			}
		}
		return inspectionMeasurementResponseDTO;
	}

	@Override
	public InspectionMeasurementResponseDTO save(InspectionMeasurementDTO inspectionMeasurementDTO)
			throws InspectionMeasurementException {
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		if(null != inspectionMeasurementDTO.getInspectionMeasurementId()){
			LISInspectionMeasurements inspectionMeasurements = inspectionMeasurementDAO.getRecordById(inspectionMeasurementDTO.getInspectionMeasurementId());
			if(null != inspectionMeasurements){
				inspectionMeasurements.setPartStatus(InspectionMeasurementConstants.MEASUREMENT_COMPLETED);
				inspectionMeasurements.setMeasurementRecordstatus(InspectionMeasurementConstants.MEASUREMENT_COMPLETED);
				inspectionMeasurements.setProducedQuantity(inspectionMeasurementDTO.getProducedQuantity());
				inspectionMeasurements.setInspectedQuantity(inspectionMeasurementDTO.getInspectedQuantity());
				inspectionMeasurementDAO.saveMeasurementsToDataBase(inspectionMeasurements);
				inspectionMeasurementResponseDTO.setStatus(StatusConstants.SUCCESS);
				inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.SAVE);
			}else{
				inspectionMeasurementResponseDTO.setStatus(StatusConstants.FAILURE);
				inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.NOT_SAVE);
			}
		}
		return inspectionMeasurementResponseDTO;
	}

}
