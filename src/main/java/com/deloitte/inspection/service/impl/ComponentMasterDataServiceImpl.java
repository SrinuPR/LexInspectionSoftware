package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.service.ComponentMasterDataService;

@Service
public class ComponentMasterDataServiceImpl implements ComponentMasterDataService{

	private static final Logger logger = LogManager.getLogger(ComponentMasterDataServiceImpl.class);
	
	@Autowired
	private ComponentMasterDataDAO componentMasterDataDAO;
	
	@Autowired
	private SubscriberMasterDAO subscriberMasterDAO;

	@Override
	public String saveComponentMasterData(ComponentMasterDataDTO componentMasterDataDTO, String userName) throws ComponentMasterDataException {
		String status = StatusConstants.FAILURE;
		try{
			if(null != componentMasterDataDTO){
				logger.info(" Subscriber Id "+componentMasterDataDTO.getSubscriberId());
				LISMaintainMasterDataComponent masterDataComponent =  new LISMaintainMasterDataComponent();
				if(null != componentMasterDataDTO.getSubscriberId()){
					LISSubscriberMaster subscriberMaster = subscriberMasterDAO.getSubscriberById(componentMasterDataDTO.getSubscriberId());
					masterDataComponent.setSubscriberMaster(subscriberMaster);
				}
				if(!isProductDrawNumberExist(componentMasterDataDTO.getComponentProductDrawNumber())){
					masterDataComponent.setComponentProductDrawNumber(componentMasterDataDTO.getComponentProductDrawNumber());
					masterDataComponent.setComponentProductMeterial(componentMasterDataDTO.getComponentProductMeterial());
					masterDataComponent.setComponentProductName(componentMasterDataDTO.getComponentProductName());
					masterDataComponent.setComponentProductNotes(componentMasterDataDTO.getComponentProductNotes());
					masterDataComponent.setComponentProductNumber(componentMasterDataDTO.getComponentProductNumber());
					masterDataComponent.setCustomerNameAddress(componentMasterDataDTO.getCustomerNameAddress());
					masterDataComponent.setComponentProductManufacturerUnits(componentMasterDataDTO.getComponentProductManufacturerUnits());
					masterDataComponent.setCreatedBy("Hai");
					masterDataComponent.setCreatedTimestamp(new Date());
					componentMasterDataDAO.saveComponentMasterData(masterDataComponent);
					status = StatusConstants.SUCCESS;
				}else
					status = StatusConstants.DUPLICATE;
			}
		}catch(ComponentMasterDataException componentMasterDataException){
			logger.error("Exception While saving the Component master data "+componentMasterDataException.getMessage());
		}catch (Exception exception) {
			logger.error("Exception While saving the Component master data "+exception.getMessage());
		}
		return status;
	}

	@Override
	public ComponentMasterDataDTO getComponentDataById(Integer componentId)	throws ComponentMasterDataException {
		
		logger.info("component Id "+componentId);
		try{
			ComponentMasterDataDTO  componentMasterDataDTO = new ComponentMasterDataDTO();
			if(null != componentId){
				LISMaintainMasterDataComponent masterDataComponent  = componentMasterDataDAO.getComponentDataById(componentId);
				if(null != masterDataComponent){
					componentMasterDataDTO.setComponentId(masterDataComponent.getCmdcsId());
					componentMasterDataDTO.setComponentProductDrawNumber(masterDataComponent.getComponentProductDrawNumber());
					componentMasterDataDTO.setComponentProductManufacturerUnits(masterDataComponent.getComponentProductManufacturerUnits());
					componentMasterDataDTO.setComponentProductMeterial(masterDataComponent.getComponentProductMeterial());
					componentMasterDataDTO.setComponentProductName(masterDataComponent.getComponentProductName());
					componentMasterDataDTO.setComponentProductNotes(masterDataComponent.getComponentProductNotes());
					componentMasterDataDTO.setComponentProductNumber(masterDataComponent.getComponentProductNumber());
					componentMasterDataDTO.setCustomerNameAddress(masterDataComponent.getCustomerNameAddress());
					if(null != masterDataComponent.getSubscriberMaster()){
						componentMasterDataDTO.setSubscriberId(masterDataComponent.getSubscriberMaster().getSubscriberId());
						componentMasterDataDTO.setSubscriberName(masterDataComponent.getSubscriberMaster().getSubscriberName());
					}
				}
			}
			return componentMasterDataDTO;
		}catch(ComponentMasterDataException componentMasterDataException){
			logger.error("Exception While retriving the Component master data "+componentMasterDataException.getMessage());
		}catch(Exception exception){
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
				if(null != componentMasterDataDTO.getComponentId() && 0 != componentMasterDataDTO.getComponentId()){
						if(!isProductDrawNumberExist(componentMasterDataDTO.getComponentProductDrawNumber())){
						masterDataComponent = componentMasterDataDAO.getComponentDataById(componentMasterDataDTO.getComponentId());
						masterDataComponent.setComponentProductDrawNumber(componentMasterDataDTO.getComponentProductDrawNumber());
						masterDataComponent.setComponentProductMeterial(componentMasterDataDTO.getComponentProductMeterial());
						masterDataComponent.setComponentProductName(componentMasterDataDTO.getComponentProductName());
						masterDataComponent.setComponentProductNotes(componentMasterDataDTO.getComponentProductNotes());
						masterDataComponent.setComponentProductNumber(componentMasterDataDTO.getComponentProductNumber());
						masterDataComponent.setCustomerNameAddress(componentMasterDataDTO.getCustomerNameAddress());
						masterDataComponent.setComponentProductManufacturerUnits(componentMasterDataDTO.getComponentProductManufacturerUnits());
						masterDataComponent.setUpdatedBy("Update");
						masterDataComponent.setUpdatedTimestamp(new Date());
						componentMasterDataDAO.saveComponentMasterData(masterDataComponent);
						status = StatusConstants.SUCCESS;
						return status;
					}
				}else
					status = StatusConstants.DUPLICATE;
			}
		}catch(ComponentMasterDataException componentMasterDataException){
			logger.error("Exception While saving the Component master data "+componentMasterDataException.getMessage());
		}catch (Exception exception) {
			logger.error("Exception While saving the Component master data "+exception.getMessage());
		}
		return status;
	}

	@Override
	public List<ComponentMasterDataDTO> getAllComponentMasterData() throws ComponentMasterDataException {
		try{
			List<ComponentMasterDataDTO> componentMasterDataDTOs = new ArrayList<ComponentMasterDataDTO>();
			List<LISMaintainMasterDataComponent> maintainMasterDataComponents = componentMasterDataDAO.getAllComponentMasterData();
			if(null != maintainMasterDataComponents && maintainMasterDataComponents.size() > 0){
				for(LISMaintainMasterDataComponent masterDataComponent : maintainMasterDataComponents){
					ComponentMasterDataDTO componentMasterDataDTO = new ComponentMasterDataDTO();
					componentMasterDataDTO.setComponentId(masterDataComponent.getCmdcsId());
					componentMasterDataDTO.setComponentProductDrawNumber(masterDataComponent.getComponentProductDrawNumber());
					componentMasterDataDTO.setComponentProductManufacturerUnits(masterDataComponent.getComponentProductManufacturerUnits());
					componentMasterDataDTO.setComponentProductMeterial(masterDataComponent.getComponentProductMeterial());
					componentMasterDataDTO.setComponentProductName(masterDataComponent.getComponentProductName());
					componentMasterDataDTO.setComponentProductNotes(masterDataComponent.getComponentProductNotes());
					componentMasterDataDTO.setComponentProductNumber(masterDataComponent.getComponentProductNumber());
					componentMasterDataDTO.setCustomerNameAddress(masterDataComponent.getCustomerNameAddress());
					if(null != masterDataComponent.getSubscriberMaster()){
						componentMasterDataDTO.setSubscriberId(masterDataComponent.getSubscriberMaster().getSubscriberId());
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
	
	private boolean isProductDrawNumberExist(String productDrawNumber){
		try {
			LISMaintainMasterDataComponent lisMaintainMasterDataComponent = componentMasterDataDAO.getComponentDataByDrwNum(productDrawNumber.toLowerCase());
			if(null != lisMaintainMasterDataComponent){
				return true;
			}
		} catch (ComponentMasterDataException e) {
			e.printStackTrace();
		}
		return false;
	}
}
