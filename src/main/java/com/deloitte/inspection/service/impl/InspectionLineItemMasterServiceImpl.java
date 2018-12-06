package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.InspectionLineItemConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.InspectionLineItemMasterDAO;
import com.deloitte.inspection.dto.InspectionLineItemDTO;
import com.deloitte.inspection.exception.InspectionLineItemMasterException;
import com.deloitte.inspection.model.LISInspectionLineItemMaster;
import com.deloitte.inspection.model.LISInspectionMaster;
import com.deloitte.inspection.response.dto.InspectionLineItemResponseDTO;
import com.deloitte.inspection.service.InspectionLineItemMasterService;

@Service
public class InspectionLineItemMasterServiceImpl implements InspectionLineItemMasterService{
	
	private static final Logger logger = LogManager.getLogger(InspectionLineItemMasterServiceImpl.class);
	
	@Autowired
	private InspectionLineItemMasterDAO inspectionLineMasterDAO;
	
	private InspectionLineItemResponseDTO saveInspectionLineItem(List<InspectionLineItemDTO> inspectionLineItemDTOs, String userId, String userName)
			throws InspectionLineItemMasterException {
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		logger.info("Inside saveInspectionLineItem Service");
		try{
			if(null != inspectionLineItemDTOs && inspectionLineItemDTOs.size() > 0){
				List<LISInspectionLineItemMaster> lineMasterList = new ArrayList<LISInspectionLineItemMaster>();
				for(InspectionLineItemDTO inspectionLineItemDTO : inspectionLineItemDTOs){
					LISInspectionLineItemMaster inspectionLineItemMaster = null;
					if(inspectionLineItemDTO.getInspectionLineItemId() != null){
						inspectionLineItemMaster = inspectionLineMasterDAO.getInspectionItem(inspectionLineItemDTO.getInspectionLineItemId());
						if(null != inspectionLineItemMaster){
							inspectionLineItemMaster = transformToModel(inspectionLineItemMaster, inspectionLineItemDTO, null, userName, InspectionLineItemConstants.UPDATE);
						}
					}else{
						inspectionLineItemMaster = new LISInspectionLineItemMaster();
						inspectionLineItemMaster = transformToModel(inspectionLineItemMaster,inspectionLineItemDTO, userId, userName,InspectionLineItemConstants.INSERT);
					}
					lineMasterList.add(inspectionLineItemMaster);
				}
				inspectionLineMasterDAO.saveInspectionLineItem(lineMasterList);
				inspectionLineItemResponseDTO.setStatus(StatusConstants.SUCCESS);
				inspectionLineItemResponseDTO.setResults(getAllInspectionLineItemDTOs(userId));
			}else{
				inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
				inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.INPUT_MISS);
			}
		}catch (Exception exception) {
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
			logger.info("Exception in saveInspectionLineItem "+exception.getMessage());
		}
		return inspectionLineItemResponseDTO;
	}

	private LISInspectionLineItemMaster transformToModel(LISInspectionLineItemMaster inspectionLineItemMaster,
			InspectionLineItemDTO inspectionLineItemDTO, String userId, String userName, String action) {
		logger.info("Entered into transformToModel");
		if(InspectionLineItemConstants.INSERT.equals(action)){
			inspectionLineItemMaster.setCreatedBy(userName);
			inspectionLineItemMaster.setCreatedTimestamp(new Date());
			inspectionLineItemMaster.setUserID(userId);
			inspectionLineItemMaster.setSubscriberId(inspectionLineItemDTO.getSubscriberId());
			inspectionLineItemMaster.setSubscriberName(inspectionLineItemDTO.getSubscriberName());
			inspectionLineItemMaster.setComponentProductDrawNumber(inspectionLineItemDTO.getComponentProductDrawNumber());
			inspectionLineItemMaster.setMeasurmentName(inspectionLineItemDTO.getMeasurementName());
			inspectionLineItemMaster.setIsActive(StatusConstants.IS_ACTIVE);
		}else{
			inspectionLineItemMaster.setUpdatedBy(userName);
			inspectionLineItemMaster.setUpdatedTimestamp(new Date());
			inspectionLineItemMaster.setMeasurmentName(inspectionLineItemMaster.getMeasurmentName());
		}
		inspectionLineItemMaster.setBaseMeasure(inspectionLineItemDTO.getBaseMeasure());
		inspectionLineItemMaster.setBaseMeasureUnits(inspectionLineItemDTO.getBaseMeasureUnits());
		inspectionLineItemMaster.setLowerLimit(inspectionLineItemDTO.getLowerLimit());
		inspectionLineItemMaster.setUpperLimit(inspectionLineItemDTO.getUpperLimit());
		return inspectionLineItemMaster;
	}

	@Override
	public InspectionLineItemResponseDTO getComponentProductDrawNumbers(Integer subscriberId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getComponentProductDrawNumbers");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			Set<String> componentProductDrawNumList = new HashSet<String>();
			List<LISInspectionMaster> mastersList = inspectionLineMasterDAO.getComponentProductDrawNumbers(subscriberId);
			if(null != mastersList && mastersList.size() > 0){
				for(LISInspectionMaster lisInspectionMaster : mastersList){
					componentProductDrawNumList.add(lisInspectionMaster.getComponentMasterData().getComponentProductDrawNumber());
				}
			}
			List<String> ProductDrawNumList = new ArrayList<String>(componentProductDrawNumList);
			inspectionLineItemResponseDTO.setComponentDrawNumbers(ProductDrawNumList);
			inspectionLineItemResponseDTO.setStatus(StatusConstants.SUCCESS);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.FETCH_COMPONENT_PROD_DRAW_NUM_SUCCESS);
			return inspectionLineItemResponseDTO;
		}catch(Exception exception){
			logger.error("Exception while fetching component Drawing Numbers "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
		}
		return inspectionLineItemResponseDTO;
	}

	@Override
	public InspectionLineItemResponseDTO getAllInspectionLineItems(String userId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getAllInspectionLineItems service");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			List<InspectionLineItemDTO> inspectionLineItemDTOs =getAllInspectionLineItemDTOs(userId);
			inspectionLineItemResponseDTO.setResults(inspectionLineItemDTOs);
			inspectionLineItemResponseDTO.setStatus(StatusConstants.SUCCESS);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.FETCH_INSPECTION_LINE_ITEM_SUCCESS);
			return inspectionLineItemResponseDTO;
		}catch(Exception exception){
			logger.error("Exception while fetching Inspection Line Item Data "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
		}
		return inspectionLineItemResponseDTO;
	}

	private InspectionLineItemDTO transformToDTO(InspectionLineItemDTO inspectionLineItemDTO,
			LISInspectionLineItemMaster lineItemMaster) {
		logger.info("Entered into transformToDTO ");
		inspectionLineItemDTO.setSubscriberId(lineItemMaster.getSubscriberId());
		inspectionLineItemDTO.setComponentProductDrawNumber(lineItemMaster.getComponentProductDrawNumber());
		inspectionLineItemDTO.setInspectionLineItemId(lineItemMaster.getInspectionLineItemId());
		inspectionLineItemDTO.setUserId(lineItemMaster.getUserID());
		inspectionLineItemDTO.setBaseMeasure(lineItemMaster.getBaseMeasure());
		inspectionLineItemDTO.setBaseMeasureUnits(lineItemMaster.getBaseMeasureUnits());
		inspectionLineItemDTO.setLowerLimit(lineItemMaster.getLowerLimit());
		inspectionLineItemDTO.setMeasurementName(lineItemMaster.getMeasurmentName());
		inspectionLineItemDTO.setUpperLimit(lineItemMaster.getUpperLimit());
		inspectionLineItemDTO.setSubscriberName(lineItemMaster.getSubscriberName());
		return inspectionLineItemDTO;
	}	

	public List<InspectionLineItemDTO> getAllInspectionLineItemDTOs(String userId) throws InspectionLineItemMasterException{
		List<InspectionLineItemDTO> inspectionLineItemDTOs = new ArrayList<InspectionLineItemDTO>();
		List<LISInspectionLineItemMaster> inspectionLineItemMasters = inspectionLineMasterDAO.getAllInspectionLineItems(userId.toLowerCase());
		if(null != inspectionLineItemMasters && inspectionLineItemMasters.size()>0){
			for(LISInspectionLineItemMaster lineItemMaster : inspectionLineItemMasters){
				InspectionLineItemDTO inspectionLineItemDTO = new InspectionLineItemDTO();
				inspectionLineItemDTO = transformToDTO(inspectionLineItemDTO,lineItemMaster);
				inspectionLineItemDTOs.add(inspectionLineItemDTO);
			}
		}
		return inspectionLineItemDTOs;
	}

	@Override
	public InspectionLineItemResponseDTO reportSave(List<InspectionLineItemDTO> inspectionLineItems, String userId,
			String userName) throws InspectionLineItemMasterException {
		logger.info("Inside reportSave Service");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			inspectionLineItemResponseDTO = saveInspectionLineItem(inspectionLineItems,userId,userName);
			if(null != inspectionLineItemResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionLineItemResponseDTO.getStatus())){
				inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.REPORT_MEASURE_ITEM_SAVE_SUCCESS);
			}else{
				inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.REPORT_MEASURE_ITEM_SAVE_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception while reportSave "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
		}
		return inspectionLineItemResponseDTO;
	}

	@Override
	public InspectionLineItemResponseDTO measureItemSave(List<InspectionLineItemDTO> inspectionLineItems, String userId,
			String userName) throws InspectionLineItemMasterException {
		logger.info("Inside measureItemSave Service");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			inspectionLineItemResponseDTO = saveInspectionLineItem(inspectionLineItems,userId,userName);
			if(null != inspectionLineItemResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionLineItemResponseDTO.getStatus())){
				inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.MEASURE_ITEM_SAVE_SUCCESS);
			}else{
				inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.MEASURE_ITEM_SAVE_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception measureItemSave "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
		}
		return inspectionLineItemResponseDTO;
	}

	@Override
	public InspectionLineItemResponseDTO validateMeasurementName(InspectionLineItemDTO inspectionLineItem)
			throws InspectionLineItemMasterException {
		logger.info("Entered into validateMeasurementName service");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			if(null != inspectionLineItem && null != inspectionLineItem.getComponentProductDrawNumber() && null != inspectionLineItem.getMeasurementName()){
				LISInspectionLineItemMaster lisInspectionLineItemMaster = inspectionLineMasterDAO.validateMeasurementName(inspectionLineItem);
				if(null != lisInspectionLineItemMaster){
					inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
					inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.MEASUREMENT_NAME_EXIST);
				}else{
					inspectionLineItemResponseDTO.setStatus(StatusConstants.SUCCESS);
					inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.MEASUREMENT_NAME_NOT_EXIST);
				}
			}else{
				inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
				inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.INPUT_MISS);
			}
		}catch(Exception exception){
			logger.error("Exception validateMeasurementName "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
		}
		return inspectionLineItemResponseDTO;
	}

	@Override
	public InspectionLineItemResponseDTO updateInspectionData(List<InspectionLineItemDTO> inspectionLineItem, String userName, String userId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into validateMeasurementName service");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			List<LISInspectionLineItemMaster> updateList = new ArrayList<LISInspectionLineItemMaster>();
			if(null != inspectionLineItem && inspectionLineItem.size() > 0){
				for(InspectionLineItemDTO inspectionLineItemDTO : inspectionLineItem){
					LISInspectionLineItemMaster lisInspectionLineItemMaster = inspectionLineMasterDAO.getInspectionItem(inspectionLineItemDTO.getInspectionLineItemId());
					if(null != lisInspectionLineItemMaster){
						lisInspectionLineItemMaster = transformToModel(lisInspectionLineItemMaster, inspectionLineItemDTO, null, userName, InspectionLineItemConstants.UPDATE);
						updateList.add(lisInspectionLineItemMaster);
					}
				}
				if(updateList.size() > 0){
					inspectionLineMasterDAO.saveInspectionLineItem(updateList);
					inspectionLineItemResponseDTO.setResults(getAllInspectionLineItemDTOs(userId));
					inspectionLineItemResponseDTO.setStatus(StatusConstants.SUCCESS);
					inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UPDATE_SUCCESS);
				}else{
					inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
					inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UPDATE_FAILED);
				}
			}else{
				inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
				inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.INPUT_MISS);
			}
		}catch(Exception exception){
			logger.error("Exception updateInspectionData "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
		}
		return inspectionLineItemResponseDTO;
	}
}
