package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.ComponentConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.mapper.LISMaintainMasterDataComponentResult;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.response.dto.ComponentMasterResponseDataDTO;
import com.deloitte.inspection.service.ComponentMasterDataService;

@Service
public class ComponentMasterDataServiceImpl implements ComponentMasterDataService{

	private static final Logger logger = LogManager.getLogger(ComponentMasterDataServiceImpl.class);
	
	@Autowired
	private ComponentMasterDataDAO componentMasterDataDAO;
	
	@Autowired
	private SubscriberMasterDAO subscriberMasterDAO;
	
	@Autowired
	private CreateUserDAO createUserDAO;

	@Override
	public String saveComponentMasterData(ComponentMasterDataDTO componentMasterDataDTO, String userName, String userId) throws ComponentMasterDataException {
		String status = StatusConstants.FAILURE;
		try{
			if(null != componentMasterDataDTO){
				logger.info(" Subscriber Id "+componentMasterDataDTO.getSubscriberId());
				LISMaintainMasterDataComponent masterDataComponent =  new LISMaintainMasterDataComponent();
				if(null != componentMasterDataDTO.getSubscriberId()){
					LISSubscriberMaster subscriberMaster = subscriberMasterDAO.getSubscriberById(componentMasterDataDTO.getSubscriberId());
					masterDataComponent.setSubscriberMasterId(subscriberMaster.getSubscriberId());
				}
				if(null != componentMasterDataDTO.getSubscriberId()){
					// LISUserMasterCreate userMasterCreate = createUserDAO.validateUserId(userId);
					masterDataComponent.setUserMasterCreateId(userId);
				}
				masterDataComponent.setComponentProductDrawNumber(componentMasterDataDTO.getComponentProductDrawNumber().trim());
				masterDataComponent.setComponentProductMeterial(componentMasterDataDTO.getComponentProductMeterial().trim());
				masterDataComponent.setComponentProductName(componentMasterDataDTO.getComponentProductName().trim());
				masterDataComponent.setComponentProductNotes(componentMasterDataDTO.getComponentProductNotes());
				masterDataComponent.setComponentProductNumber(componentMasterDataDTO.getComponentProductNumber().trim());
				masterDataComponent.setCustomerNameAddress(componentMasterDataDTO.getCustomerNameAddress().trim());
				masterDataComponent.setComponentProductManufacturerUnits(componentMasterDataDTO.getComponentProductManufacturerUnits().trim());
				masterDataComponent.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
				masterDataComponent.setCreatedBy(userName);
				masterDataComponent.setRecordInProcess(String.valueOf(StatusConstants.RECORD_IN_PROCESS_COMPLETED));
				masterDataComponent.setCreatedTimestamp(new Date());
				componentMasterDataDAO.saveComponentMasterData(masterDataComponent);
				status = StatusConstants.SUCCESS;
			}
		}catch(ComponentMasterDataException componentMasterDataException){
			logger.error("Exception While saving the Component master data "+componentMasterDataException.getMessage());
		}catch (Exception exception) {
			logger.error("Exception While saving the Component master data "+exception.getMessage());
		}
		return status;
	}

	@Override
	public ComponentMasterResponseDataDTO getComponentDataById(Integer componentId)	throws ComponentMasterDataException {
		ComponentMasterResponseDataDTO  componentMasterResponseDataDTO = new ComponentMasterResponseDataDTO();
		logger.info("component Id "+componentId);
		try{
			if(null != componentId){
				LISMaintainMasterDataComponent masterDataComponent  = componentMasterDataDAO.getComponentDataById(componentId);
				if(null != masterDataComponent){
					if(StatusConstants.RECORD_IN_PROCESS == masterDataComponent.getRecordInProcess().charAt(0)){
						componentMasterResponseDataDTO.setStatus(StatusConstants.FAILURE);
						componentMasterResponseDataDTO.setMessage(StatusConstants.RECORD_IN_PROCESS_MESSAGE);
					}else{
						masterDataComponent.setRecordInProcess(String.valueOf(StatusConstants.RECORD_IN_PROCESS));
						componentMasterDataDAO.saveComponentMasterData(masterDataComponent);
						componentMasterResponseDataDTO.setStatus(StatusConstants.SUCCESS);
						componentMasterResponseDataDTO.setMessage(StatusConstants.RECORD_NOT_IN_PROCESS);
					}
				}else{
					componentMasterResponseDataDTO.setStatus(StatusConstants.FAILURE);
					componentMasterResponseDataDTO.setMessage(StatusConstants.RECORD_NOT_EXIST);
				}
			}else{
				componentMasterResponseDataDTO.setStatus(StatusConstants.FAILURE);
				componentMasterResponseDataDTO.setMessage(StatusConstants.INPUT_MISS);
			}
			return componentMasterResponseDataDTO;
		}catch(ComponentMasterDataException componentMasterDataException){
			componentMasterResponseDataDTO.setStatus(StatusConstants.FAILURE);
			logger.error("Exception While retriving the Component master data "+componentMasterDataException.getMessage());
		}catch(Exception exception){
			componentMasterResponseDataDTO.setStatus(StatusConstants.FAILURE);
			logger.error("Exception While retriving the Component master data "+exception.getMessage());
		}
		return null;
	}

	@Override
	public String updateComponentMasterData(ComponentMasterDataDTO componentMasterDataDTO, String userName)	throws ComponentMasterDataException {
		
		String status = StatusConstants.FAILURE;
		try{
			if(null != componentMasterDataDTO){
				LISMaintainMasterDataComponent masterDataComponent = null;
				if(null != componentMasterDataDTO.getComponentId()){
					masterDataComponent = componentMasterDataDAO.getComponentDataById(componentMasterDataDTO.getComponentId());
					masterDataComponent.setComponentProductDrawNumber(componentMasterDataDTO.getComponentProductDrawNumber().trim());
					masterDataComponent.setComponentProductMeterial(componentMasterDataDTO.getComponentProductMeterial().trim());
					masterDataComponent.setComponentProductName(componentMasterDataDTO.getComponentProductName().trim());
					masterDataComponent.setComponentProductNotes(componentMasterDataDTO.getComponentProductNotes());
					masterDataComponent.setComponentProductNumber(componentMasterDataDTO.getComponentProductNumber().trim());
					masterDataComponent.setCustomerNameAddress(componentMasterDataDTO.getCustomerNameAddress().trim());
					masterDataComponent.setComponentProductManufacturerUnits(componentMasterDataDTO.getComponentProductManufacturerUnits().trim());
					masterDataComponent.setUpdatedBy(userName);
					masterDataComponent.setUpdatedTimestamp(new Date());
					masterDataComponent.setRecordInProcess(String.valueOf(StatusConstants.RECORD_IN_PROCESS_COMPLETED));
					componentMasterDataDAO.saveComponentMasterData(masterDataComponent);
					status = StatusConstants.SUCCESS;
					return status;
				}
			}
		}catch(ComponentMasterDataException componentMasterDataException){
			logger.error("Exception While saving the Component master data "+componentMasterDataException.getMessage());
		}catch (Exception exception) {
			logger.error("Exception While saving the Component master data "+exception.getMessage());
		}
		return status;
	}

	@Override
	public List<ComponentMasterDataDTO> getAllComponentMasterData(Integer SubscriberId) throws ComponentMasterDataException {
		try{
			List<ComponentMasterDataDTO> componentMasterDataDTOs = new ArrayList<ComponentMasterDataDTO>();
			List<LISMaintainMasterDataComponentResult> maintainMasterDataComponents = componentMasterDataDAO.getAllComponentMasterData(SubscriberId);
			if(null != maintainMasterDataComponents && maintainMasterDataComponents.size() > 0){
				for(LISMaintainMasterDataComponentResult masterDataComponent : maintainMasterDataComponents){
					ComponentMasterDataDTO componentMasterDataDTO = new ComponentMasterDataDTO();
					componentMasterDataDTO.setComponentId(Integer.valueOf(masterDataComponent.getCmdcsId()));
					componentMasterDataDTO.setComponentProductDrawNumber(masterDataComponent.getComponentProductDrawNumber());
					componentMasterDataDTO.setComponentProductManufacturerUnits(masterDataComponent.getComponentProductManufacturerUnits());
					componentMasterDataDTO.setComponentProductMeterial(masterDataComponent.getComponentProductMeterial());
					componentMasterDataDTO.setComponentProductName(masterDataComponent.getComponentProductName());
					componentMasterDataDTO.setComponentProductNotes(masterDataComponent.getComponentProductNotes());
					componentMasterDataDTO.setComponentProductNumber(masterDataComponent.getComponentProductNumber());
					componentMasterDataDTO.setCustomerNameAddress(masterDataComponent.getCustomerNameAddress());
					if(null != masterDataComponent.getSubscriberMaster()){
						componentMasterDataDTO.setSubscriberId(Integer.valueOf(masterDataComponent.getSubscriberMaster().getSubscriberId()));
						componentMasterDataDTO.setSubscriberName(masterDataComponent.getSubscriberMaster().getSubscriberName());
					}
					componentMasterDataDTOs.add(componentMasterDataDTO);
				}
			}
			return componentMasterDataDTOs;
		}catch(Exception exception){
			logger.error("Exception While Fetching the Component master data "+exception.getMessage());
		}
		return null;
	}

	@Override
	public String deleteComponent(Integer componentId) throws ComponentMasterDataException {
		try{
			return componentMasterDataDAO.deleteComponent(componentId);
		}catch(Exception exception){
			logger.error("Exception while deleting component "+exception.getMessage());
		}
		return StatusConstants.FAILURE;
	}
	
	@Override
	public CommonDTO validateComponentDrawNumber(String productDrawNumber) throws ComponentMasterDataException{
		logger.info("inside validateComponentDrawNumber method");
		CommonDTO commonDTO = new CommonDTO();
		try {
			LISMaintainMasterDataComponent lisMaintainMasterDataComponent = componentMasterDataDAO.getComponentDataByDrwNum(productDrawNumber.toLowerCase());
			if(null != lisMaintainMasterDataComponent){
				commonDTO.setStatus(StatusConstants.SUCCESS);
				commonDTO.setMessage(ComponentConstants.COMPONENT_DRAW_NUM_NOT_EXIST);
			}else{
				commonDTO.setStatus(StatusConstants.ERROR);
				commonDTO.setMessage(ComponentConstants.COMPONENT_DRAW_NUM_NOT_EXIST);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.info("Exception at validateComponentDrawNumber "+ exception.getMessage());
		}
		return commonDTO;
	}
}
