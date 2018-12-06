package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.InspectionMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dao.InspectionMasterDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dao.UserTypeMasterDAO;
import com.deloitte.inspection.dto.InspectionMasterDTO;
import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISInspectionMaster;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.response.dto.InspectionMasterResponseDataDTO;
import com.deloitte.inspection.service.InspectionMasterService;

@Service
public class InspectionMasterServiceImpl implements InspectionMasterService {
	
	private static final Logger logger = LogManager.getLogger(InspectionMasterServiceImpl.class);
	
	@Autowired
	private InspectionMasterDAO inspectionDAO;
	@Autowired
	private SubscriberMasterDAO subscriberMasterDAO;
	@Autowired
	private ComponentMasterDataDAO componentMasterDataDAO;
	
	@Override
	public InspectionMasterResponseDataDTO validateInspectionStage(InspectionMasterDTO masterDTO) {
		InspectionMasterResponseDataDTO inspectionResponseDTO = new InspectionMasterResponseDataDTO();
		try {
			if (masterDTO != null) {
				LISInspectionMaster inspectionMaster = inspectionDAO.getInspectionStage(masterDTO);
				if (inspectionMaster != null) {
					inspectionResponseDTO.setStatus(StatusConstants.ERROR);
					inspectionResponseDTO.setMessage(InspectionMasterConstants.INSPECTION_MASTER_EXISTS);
				} else {
					inspectionResponseDTO.setStatus(StatusConstants.SUCCESS);
					inspectionResponseDTO.setMessage(InspectionMasterConstants.INSPECTION_MASTER_NOT_EXISTS);
				}
			}
		} catch(Exception exception) {
			inspectionResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionResponseDTO.setMessage(InspectionMasterConstants.UN_EXPECTED_EXCEPTION);
			logger.error("While validating the validateInspectionStage " + exception.getMessage());
		}
		return inspectionResponseDTO;		
	}

	@Override
	public InspectionMasterResponseDataDTO saveInspectionMaster(InspectionMasterDTO masterDTO, String userName, String userId, String action) {		
		InspectionMasterResponseDataDTO responseDTO = new InspectionMasterResponseDataDTO();
		LISInspectionMaster master = toLISInspectionMaster(masterDTO);
		master.setCreatedTimestamp(new Date());
		master.setCreatedBy(userName);
		inspectionDAO.saveInspectionMaster(master);
		masterDTO.setInspectionMasterId(master.getInspId());
		responseDTO.setStatus(StatusConstants.SUCCESS);
		responseDTO.setMessage(InspectionMasterConstants.INSPECTION_MASTER_SAVED);
		List<InspectionMasterDTO> results = new ArrayList<InspectionMasterDTO>();
		results.add(masterDTO);
		responseDTO.setResults(results);
		return responseDTO;
	}

	@Override
	public InspectionMasterResponseDataDTO updateInspectionMaster(InspectionMasterDTO masterDTO) {
		LISInspectionMaster master = toLISInspectionMaster(masterDTO);
		master.setInspId(masterDTO.getInspectionMasterId());
		master.setUpdatedTimestamp(new Date());
		master.setUpdatedBy(masterDTO.getCreatedBy());
		inspectionDAO.saveInspectionMaster(master);
		InspectionMasterResponseDataDTO responseDTO = new InspectionMasterResponseDataDTO();
		responseDTO.setStatus(StatusConstants.SUCCESS);
		responseDTO.setMessage(InspectionMasterConstants.INSPECTION_MASTER_SAVED);
		return responseDTO;
	}
	
	public LISInspectionMaster toLISInspectionMaster(InspectionMasterDTO masterDTO) {
		LISInspectionMaster master = new LISInspectionMaster();
		master.setInspStageId(masterDTO.getInspectionStage());
		master.setInspTypeId(masterDTO.getInspectionType());
		master.setCompMaterial(masterDTO.getComponentProductMaterial());
		master.setCompNotes(masterDTO.getComponentProductNotes());
		master.setCompNum(masterDTO.getComponentProductNumber());
		master.setFacilityName(masterDTO.getComponentProductName());
		master.setIsActive(StatusConstants.IS_ACTIVE);
		if(null != masterDTO.getSubscriberId()){
			try {
				LISSubscriberMaster subscriberMaster = subscriberMasterDAO.getSubscriberById(masterDTO.getSubscriberId());
				master.setSubscriberMaster(subscriberMaster);
			} catch (SubscriberMasterException exception) {
				logger.error("Error while retrieving Subscriber details " + exception.getMessage());
			}
		}
		if(null != masterDTO.getComponentProductDrawNumber()){
			try {
				LISMaintainMasterDataComponent maintainMasterDataComponent = componentMasterDataDAO.getComponentDataByDrwNum(masterDTO.getComponentProductDrawNumber());
				master.setComponentMasterData(maintainMasterDataComponent);
			} catch (ComponentMasterDataException exception) {
				logger.error("Error while retrieving Component Master Data " + exception.getMessage());
			}
		}
		return master;
	}

	@Override
	public InspectionMasterResponseDataDTO getInspectionMasterData(String userId) {
		InspectionMasterResponseDataDTO response = new InspectionMasterResponseDataDTO();
		List<LISInspectionMaster> masterList = inspectionDAO.getInspectionMasterList(userId);
		if (masterList != null && masterList.size() > 0) {
			List<InspectionMasterDTO> results = new ArrayList<InspectionMasterDTO>();
			for (LISInspectionMaster master : masterList) {
				results.add(this.toInspectionMasterDTO(master));
			}
			response.setResults(results);
			response.setStatus(StatusConstants.SUCCESS);
		}
		return response;
	}
	
	public InspectionMasterDTO toInspectionMasterDTO(LISInspectionMaster master) {
		InspectionMasterDTO masterDTO = new InspectionMasterDTO();
		masterDTO.setComponentProductDrawName(master.getFacilityName());
		LISMaintainMasterDataComponent componentData = master.getComponentMasterData();
		masterDTO.setComponentProductDrawNumber(componentData.getComponentProductDrawNumber());
		masterDTO.setComponentProductMaterial(componentData.getComponentProductMeterial());
		masterDTO.setComponentProductNotes(componentData.getComponentProductNotes());
		masterDTO.setComponentProductNumber(componentData.getComponentProductNumber());
		masterDTO.setCreatedBy(master.getCreatedBy());
		masterDTO.setInspectionMasterId(master.getInspId());
		masterDTO.setInspectionStage(master.getInspStageId());
		masterDTO.setInspectionType(master.getInspTypeId());
		masterDTO.setSubscriberId(master.getSubscriberMaster().getSubscriberId());
		masterDTO.setSubscriberName(master.getSubscriberMaster().getSubscriberName());
		return masterDTO;
	}

}

