package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.ComponentConstants;
import com.deloitte.inspection.constant.InspectionMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.InspectionMasterDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.InspectionMasterDTO;
import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.mapper.LISInspectionMasterResult;
import com.deloitte.inspection.model.LISInspectionMaster;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.response.dto.ComponentMasterResponseDataDTO;
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
	/*@Autowired
	private InspectionStageMasterDAO inspectionStageMasterDAO;
	@Autowired
	private InspectionTypeMasterDAO inspectionTypeMasterDAO;*/
	
	@Override
	public InspectionMasterResponseDataDTO validateInspectionStage(InspectionMasterDTO masterDTO) {
		InspectionMasterResponseDataDTO inspectionResponseDTO = new InspectionMasterResponseDataDTO();
		try {
			if (masterDTO != null) {
				LISInspectionMaster inspectionMaster = null;
				if (masterDTO.getInspectionMasterId() == null) {
					inspectionMaster = inspectionDAO.getInspectionStage(masterDTO);
				} else {
					inspectionMaster = inspectionDAO.getInspectionStageOtherThanCurrent(masterDTO);
				}
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
	public InspectionMasterResponseDataDTO saveInspectionMaster(InspectionMasterDTO masterDTO) {		
		InspectionMasterResponseDataDTO responseDTO = new InspectionMasterResponseDataDTO();
		LISInspectionMaster master = toLISInspectionMaster(masterDTO);
		master.setCreatedTimestamp(new Date());
		master.setCreatedBy(masterDTO.getCreatedBy());
		inspectionDAO.saveInspectionMaster(master);
		masterDTO.setInspectionMasterId(Integer.valueOf(master.getInspId()));
		responseDTO.setStatus(StatusConstants.SUCCESS);
		responseDTO.setMessage(InspectionMasterConstants.INSPECTION_MASTER_SAVED);
		List<InspectionMasterDTO> results = new ArrayList<InspectionMasterDTO>();
		results.add(masterDTO);
		responseDTO.setResults(results);
		return responseDTO;
	}

	@Override
	public InspectionMasterResponseDataDTO updateInspectionMaster(InspectionMasterDTO masterDTO) {
		LISInspectionMaster master = this.inspectionDAO.getInspectionMasterById(masterDTO.getInspectionMasterId());
		/* if(masterDTO.getComponentProductDrawNumber() != null){
			try {
				LISMaintainMasterDataComponent maintainMasterDataComponent = componentMasterDataDAO.getComponentDataByDrwNum(masterDTO.getComponentProductDrawNumber());
				master.setComponentMasterData(maintainMasterDataComponent);
			} catch (ComponentMasterDataException exception) {
				logger.error("Error while retrieving Component Master Data " + exception.getMessage());
			}
		} */
		master.setInspStageId(masterDTO.getInspectionStage());
		master.setUpdatedTimestamp(new Date());
		master.setUpdatedBy(masterDTO.getUpdatedBy());
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
		master.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
		if(null != masterDTO.getSubscriberId()){
			try {
				LISSubscriberMaster subscriberMaster = subscriberMasterDAO.getSubscriberById(masterDTO.getSubscriberId());
				master.setSubscriberMasterId(subscriberMaster.getSubscriberId());
			} catch (SubscriberMasterException exception) {
				logger.error("Error while retrieving Subscriber details " + exception.getMessage());
			}
		}
		if(null != masterDTO.getComponentProductDrawNumber()){
			try {
				LISMaintainMasterDataComponent maintainMasterDataComponent = componentMasterDataDAO.getComponentDataByDrwNum(masterDTO.getComponentProductDrawNumber());
				master.setMaintainMasterDataComponent(maintainMasterDataComponent.getComponentProductDrawNumber());
			} catch (ComponentMasterDataException exception) {
				logger.error("Error while retrieving Component Master Data " + exception.getMessage());
			}
		}
		return master;
	}

	@Override
	public InspectionMasterResponseDataDTO getInspectionMasterData(Integer subscriberId) {
		InspectionMasterResponseDataDTO response = new InspectionMasterResponseDataDTO();
		List<LISInspectionMasterResult> masterList = inspectionDAO.getInspectionMasterList(subscriberId);
		if (masterList != null && masterList.size() > 0) {
			List<InspectionMasterDTO> results = new ArrayList<InspectionMasterDTO>();
			for (LISInspectionMasterResult master : masterList) {
				results.add(this.toInspectionMasterDTO(master));
			}
			response.setResults(results);
		}
		response.setStatus(StatusConstants.SUCCESS);
		return response;
	}
	
	public InspectionMasterDTO toInspectionMasterDTO(LISInspectionMasterResult master) {
		InspectionMasterDTO masterDTO = new InspectionMasterDTO();
		masterDTO.setComponentProductName(master.getFacilityName());
		LISMaintainMasterDataComponent componentData = master.getMaintainMasterDataComponent();
		masterDTO.setComponentProductDrawNumber(componentData.getComponentProductDrawNumber());
		masterDTO.setComponentProductMaterial(componentData.getComponentProductMeterial());
		masterDTO.setComponentProductNotes(componentData.getComponentProductNotes());
		masterDTO.setComponentProductNumber(componentData.getComponentProductNumber());
		masterDTO.setCreatedBy(master.getCreatedBy());
		masterDTO.setInspectionMasterId(Integer.valueOf(master.getInspId()));
		masterDTO.setInspectionStage(master.getInspStageId());
		masterDTO.setInspectionType(master.getInspTypeId());
		masterDTO.setSubscriberId(Integer.valueOf(master.getSubscriberMaster().getSubscriberId()));
		masterDTO.setSubscriberName(master.getSubscriberMaster().getSubscriberName());
		return masterDTO;
	}

	@Override
	public String deleteInspectionMaster(Integer inspectionMasterId) {
		try{
			return inspectionDAO.deleteInspectionMaster(inspectionMasterId);
		}catch(Exception exception){
			logger.error("Exception while deleting Inspection Master "+exception.getMessage());
		}
		return StatusConstants.FAILURE;
	}

	@Override
	public InspectionMasterResponseDataDTO getInspectionTypesByCompProdDrawNum(String compProdDrawNum) {
		InspectionMasterResponseDataDTO inspectionMasterResponseDataDTO = new InspectionMasterResponseDataDTO();
		logger.info("Inside getInspectionTypesByCompProdDrawNum start");
		try{
			List<LISInspectionMaster> list = inspectionDAO.getInspectionTypesByCompProdDrawNum(compProdDrawNum.toLowerCase());
			if(null != list && list.size() > 0){
				Set<Integer> inspTypeIds = new TreeSet<Integer>();
				Set<Integer> inspStageIds = new TreeSet<Integer>();
				for(LISInspectionMaster lisInspectionReportMaster : list){
					inspStageIds.add(lisInspectionReportMaster.getInspStageId());
					inspTypeIds.add(lisInspectionReportMaster.getInspTypeId());
					//LISInspectionStageMaster stageMaster = inspectionStageMasterDAO.getInspSatgeId(lisInspectionReportMaster.getInspStageId());
					/*if(null != stageMaster){
						inspectionMasterDTO.setInspectionStage(stageMaster.getInspStageId());
						inspectionMasterDTO.setInspectionStageName(stageMaster.getInspStageName());
					}
					LISInspectionTypeMaster inspectionTypeMaster = inspectionTypeMasterDAO.getInspTypeId(lisInspectionReportMaster.getInspTypeId());
					if(null != inspectionTypeMaster){
						inspectionMasterDTO.setInspectionType(inspectionTypeMaster.getInspTypeId());
						inspectionMasterDTO.setInspectionTypeName(inspectionTypeMaster.getInspTypeName());
					}*/
					inspectionMasterResponseDataDTO.setInspStageList(inspStageIds);
					inspectionMasterResponseDataDTO.setInspTypeList(inspTypeIds);
				}
			}
			inspectionMasterResponseDataDTO.setMessage("Inspection Results");
			inspectionMasterResponseDataDTO.setStatus(StatusConstants.SUCCESS);
		}catch(Exception exception){
			inspectionMasterResponseDataDTO.setMessage(InspectionMasterConstants.UN_EXPECTED_EXCEPTION);
			inspectionMasterResponseDataDTO.setStatus(StatusConstants.ERROR);
			logger.error("Exception at getInspectionTypesByCompProdDrawNum "+exception.getMessage());
		}
		return inspectionMasterResponseDataDTO;
	}

	public ComponentMasterResponseDataDTO getCompDrawNumsBySubscriberId(Integer subscriberId) {
		ComponentMasterResponseDataDTO componentMasterResponseDataDTO  = new ComponentMasterResponseDataDTO();
		logger.info("Entered into getCompDrawNumsBySubscriberId service");
		try{
			List<LISMaintainMasterDataComponent> inspectionMasters = inspectionDAO.getCompDrawNumsBySubscriberId(subscriberId);
			if(null != inspectionMasters){
				List<ComponentMasterDataDTO> result= new ArrayList<ComponentMasterDataDTO>();
				for(LISMaintainMasterDataComponent inspctionReport : inspectionMasters){
					ComponentMasterDataDTO componentMasterDataDTO = new ComponentMasterDataDTO();
					componentMasterDataDTO.setComponentId(Integer.valueOf(inspctionReport.getCmdcsId()));
					componentMasterDataDTO.setComponentProductDrawNumber(inspctionReport.getComponentProductDrawNumber());
					componentMasterDataDTO.setComponentProductName(inspctionReport.getComponentProductName());
					result.add(componentMasterDataDTO);
				}
				componentMasterResponseDataDTO.setResult(result);
			}
			componentMasterResponseDataDTO.setStatus(StatusConstants.SUCCESS);
			componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_LIST_SUCCESS);
		}catch(Exception exception){
			componentMasterResponseDataDTO.setStatus(StatusConstants.ERROR);
			componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_LIST_FAILED);
			logger.error("Exception Occured in getCompDrawNumsBySubscriberId service :"+exception.getMessage());
		}
		return componentMasterResponseDataDTO;
	}
}

