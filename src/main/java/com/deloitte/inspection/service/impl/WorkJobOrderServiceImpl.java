package com.deloitte.inspection.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.constant.WorkJobOrderConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dao.PurchaseOrderDataDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dao.WorkJobOrderDAO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.WorkJobOrderDTO;
import com.deloitte.inspection.exception.WorkJobOrderException;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;
import com.deloitte.inspection.response.dto.WorkJobOrderResponseDTO;
import com.deloitte.inspection.service.WorkJobOrderService;
import com.deloitte.inspection.util.InspectionUtils;

@Service
public class WorkJobOrderServiceImpl implements WorkJobOrderService{

	private static final Logger logger = LogManager.getLogger(WorkJobOrderServiceImpl.class);

	@Autowired
	private WorkJobOrderDAO workJobOrderDAO;
	
	@Autowired
	private SubscriberMasterDAO subscriberMasterDAO;
	
	@Autowired
	private CreateUserDAO createUserDAO;
	
	@Autowired
	private ComponentMasterDataDAO componentMasterDataDAO;
	
	@Autowired
	private PurchaseOrderDataDAO purchaseOrderDataDAO;
	
	@Override
	public WorkJobOrderResponseDTO saveWorkJobOrderData(WorkJobOrderDTO workJobOrderDTO, String userName,String userId, String action) throws WorkJobOrderException {
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			if(null != workJobOrderDTO){
				if(null != workJobOrderDTO.getWorkOrderJobNotes()){
					if(!isWorkRecordExist(workJobOrderDTO.getWorkJobOrderNumber(),workJobOrderDTO.getLotNumber(),workJobOrderDTO.getManufacturingBatchNumber())){
						logger.info(" Subscriber Id "+workJobOrderDTO.getSubscriberId());
						LISWorkJobOrderMaster workJobOrderMaster =null;
						if(StatusConstants.INSERT.equalsIgnoreCase(action)){
							workJobOrderMaster =  new LISWorkJobOrderMaster();
							workJobOrderMaster.setCreatedTimestamp(new Date());
							workJobOrderMaster.setCreatedBy(userName);
						}else{
							workJobOrderMaster =  new LISWorkJobOrderMaster();
							workJobOrderMaster.setUpdatedBy(userName);
							workJobOrderMaster.setUpdatedTimestamp(new Date());
						}
						workJobOrderMaster = setForeignKeys(workJobOrderDTO,workJobOrderMaster,userId);
						workJobOrderMaster.setLotSize(workJobOrderDTO.getLotSize());
						workJobOrderMaster.setLotNumber(workJobOrderDTO.getLotNumber());
						workJobOrderMaster.setLotSizeUnits(workJobOrderDTO.getLotSizeUnits());
						workJobOrderMaster.setManufacturingBatchNumber(workJobOrderDTO.getManufacturingBatchNumber());
						workJobOrderMaster.setManufacturingBatchSize(workJobOrderDTO.getManufacturingBatchSize());
						workJobOrderMaster.setManufacturingBatchUnits(workJobOrderDTO.getManufacturingBatchUnits());
						workJobOrderMaster.setWorkJobOrderNumber(workJobOrderDTO.getWorkJobOrderNumber());
						workJobOrderMaster.setWorkOrderJobNotes(workJobOrderDTO.getWorkOrderJobNotes());
						workJobOrderMaster.setIsActive(StatusConstants.IS_ACTIVE);
						workJobOrderMaster.setWorkJobOrderDate(InspectionUtils.convertStringToDate(workJobOrderDTO.getWorkJobOrderDate()));
						workJobOrderDAO.saveWorkJobOrderData(workJobOrderMaster);
						workJobOrderResponseDTO.setStatus(StatusConstants.SUCCESS);
						workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_SAVE_SUCCESS);
					}else{
						workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
						workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_NUMBER_EXIST);
					}
				}
			}
		}catch(ParseException parseException){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_DATE_ISSUE);	
		}catch(Exception exception){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_SAVE_FAILED);	
		}
		return workJobOrderResponseDTO;
	}
	
	@Override
	public String deleteWorkJobOrder(Integer workJobOrderId) throws WorkJobOrderException {
		try{
			return workJobOrderDAO.deleteWorkJobOrder(workJobOrderId);
		}catch(Exception exception){
			logger.error("Exception while deleting Work-Job Order "+exception.getMessage());
		}
		return StatusConstants.FAILURE;
	}
	
	private boolean isWorkRecordExist(String workJobOrderNumber, String lotNumber, String batchNumber){
		logger.error("The Work/Job Order Number "+workJobOrderNumber);
		boolean isExist = false;
		try{
			LISWorkJobOrderMaster workJobOrderMaster = workJobOrderDAO.getWorkJobOrderByNumber(workJobOrderNumber.toLowerCase(),lotNumber.toLowerCase(),batchNumber.toLowerCase());
			if(null != workJobOrderMaster){
				isExist = true;
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Error while checking the Work/Job Order Number "+workJobOrderException.getMessage());
		}
		return isExist;
	}
	
	private LISWorkJobOrderMaster setForeignKeys(WorkJobOrderDTO workJobOrderDTO, LISWorkJobOrderMaster workJobOrderMaster, String userId) throws Exception{
		if(null != workJobOrderDTO.getSubscriberId()){
			LISSubscriberMaster subscriberMaster = subscriberMasterDAO.getSubscriberById(workJobOrderDTO.getSubscriberId());
			workJobOrderMaster.setSubscriberMaster(subscriberMaster);
		}
		if(null != workJobOrderDTO.getSubscriberId()){
			LISUserMasterCreate userMasterCreate = createUserDAO.validateUserId(userId);
			workJobOrderMaster.setUserMasterCreate(userMasterCreate);
		}
		if(null != workJobOrderDTO.getComponentProductDrawNumber()){
			LISMaintainMasterDataComponent maintainMasterDataComponent = componentMasterDataDAO.getComponentDataByDrwNum(workJobOrderDTO.getComponentProductDrawNumber());
			workJobOrderMaster.setComponentMasterData(maintainMasterDataComponent);
		}
		if(null != workJobOrderDTO.getCustomerPONumber()){
			LISPurchaseOrderMaster purchaseOrderMaster = purchaseOrderDataDAO.getByCustomerPONumber(workJobOrderDTO.getCustomerPONumber());
			workJobOrderMaster.setPurchaseOrderMaster(purchaseOrderMaster);
		}
		return workJobOrderMaster;
	}

	@Override
	public WorkJobOrderResponseDTO getAllWorkJobOrderData(String userId) throws WorkJobOrderException {
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			List<WorkJobOrderDTO> workJobOrderDTOs = WorkJobOrderList(userId);
			workJobOrderResponseDTO.setResults(workJobOrderDTOs);
			workJobOrderResponseDTO.setStatus(StatusConstants.SUCCESS);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_LIST_SUCCESS);
		}catch(Exception exception){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_LIST_FALIED);
			logger.error("Error while getting the ");
		}
		return workJobOrderResponseDTO;
	}
	
	@Override
	public List<WorkJobOrderDTO> WorkJobOrderList(String userId) throws WorkJobOrderException{
		logger.info("Entered into WorkJobOrderList");
		List<WorkJobOrderDTO> workJobOrderDTOs = new ArrayList<WorkJobOrderDTO>();
		try{
			List<LISWorkJobOrderMaster> workJobOrderMasters = workJobOrderDAO.WorkJobOrderList(userId);
			workJobOrderDTOs =  transferModelToDTO(workJobOrderMasters);
		}catch(Exception exception){
			logger.error("Error while fetching the data "+exception.getMessage());
		}
		return workJobOrderDTOs;
	}
	
	private List<WorkJobOrderDTO> transferModelToDTO(List<LISWorkJobOrderMaster> workJobOrderMasters) throws ParseException{
		List<WorkJobOrderDTO> workJobOrderDTOs = new ArrayList<WorkJobOrderDTO>();
		if(null != workJobOrderMasters && workJobOrderMasters.size() > 0){
			for(LISWorkJobOrderMaster workJobOrderMaster : workJobOrderMasters){
				WorkJobOrderDTO workJobOrderDTO = new WorkJobOrderDTO();
				workJobOrderDTO.setSubscriberId(workJobOrderMaster.getSubscriberMaster().getSubscriberId());
				workJobOrderDTO.setSubscriberName(workJobOrderMaster.getSubscriberMaster().getSubscriberName());
				workJobOrderDTO.setCustomerPONumber(workJobOrderMaster.getPurchaseOrderMaster().getCustomerPONumber());
				workJobOrderDTO.setComponentProductDrawNumber(workJobOrderMaster.getComponentMasterData().getComponentProductDrawNumber());
				workJobOrderDTO.setLotNumber(workJobOrderMaster.getLotNumber());
				workJobOrderDTO.setLotSize(workJobOrderMaster.getLotSize());
				workJobOrderDTO.setLotSizeUnits(workJobOrderMaster.getLotSizeUnits());
				workJobOrderDTO.setWorkJobOrderNumber(workJobOrderMaster.getWorkJobOrderNumber());
				workJobOrderDTO.setManufacturingBatchNumber(workJobOrderMaster.getManufacturingBatchNumber());
				workJobOrderDTO.setManufacturingBatchSize(workJobOrderMaster.getManufacturingBatchSize());
				workJobOrderDTO.setManufacturingBatchUnits(workJobOrderMaster.getManufacturingBatchUnits());
				workJobOrderDTO.setWjOrderId(workJobOrderMaster.getWjOrderId());
				workJobOrderDTO.setWorkJobOrderDate(InspectionUtils.convertDateToString(workJobOrderMaster.getWorkJobOrderDate()));
				workJobOrderDTOs.add(workJobOrderDTO);
			}
		}
		return workJobOrderDTOs;
	}

	@Override
	public WorkJobOrderResponseDTO validateWorkJobOrderNumber(WorkJobOrderDTO workJobOrderDTO)	throws WorkJobOrderException {
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			if(null != workJobOrderDTO && null != workJobOrderDTO.getWorkJobOrderNumber() && null != workJobOrderDTO.getCustomerPONumber()){
				LISWorkJobOrderMaster workJobOrderMaster = workJobOrderDAO.validateWorkJobOrderNumber(workJobOrderDTO.getWorkJobOrderNumber().toLowerCase(),workJobOrderDTO.getCustomerPONumber().toLowerCase());
				if(null != workJobOrderMaster){
					workJobOrderResponseDTO.setStatus(StatusConstants.WARNING);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_VALIDATION_WARNING);
				}else{
					workJobOrderResponseDTO.setStatus(StatusConstants.SUCCESS);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_VALIDATION_SUCCESS);
				}
			}
		}catch(Exception exception){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.UN_EXPECTED_EXCEPTION);
			logger.error("While validating the validateWorkJobOrderNumber "+exception.getMessage());
		}
		return workJobOrderResponseDTO;
	}

	@Override
	public WorkJobOrderResponseDTO validateLotNumber(WorkJobOrderDTO workJobOrderDTO) throws WorkJobOrderException {
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			if(null != workJobOrderDTO && null != workJobOrderDTO.getWorkJobOrderNumber() && null != workJobOrderDTO.getLotNumber()){
				LISWorkJobOrderMaster workJobOrderMaster = workJobOrderDAO.validateLotNumber(workJobOrderDTO.getWorkJobOrderNumber().toLowerCase(),workJobOrderDTO.getLotNumber().toLowerCase());
				if(null != workJobOrderMaster){
					workJobOrderResponseDTO.setLotSize(workJobOrderMaster.getLotSize());
					workJobOrderResponseDTO.setStatus(StatusConstants.WARNING);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.LOT_NUMBER_VALIDATION_WARNING);
				}else{
					workJobOrderMaster = workJobOrderDAO.validateLotNumber(null,workJobOrderDTO.getLotNumber().toLowerCase());
					if(null != workJobOrderMaster){
						workJobOrderResponseDTO.setLotSize(workJobOrderMaster.getLotSize());
					}
					workJobOrderResponseDTO.setStatus(StatusConstants.SUCCESS);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.LOT_NUMBER_VALIDATION_SUCCESS);
				}
			}
		}catch(Exception exception){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.UN_EXPECTED_EXCEPTION);
			logger.error("While validating the validatelotNumber "+exception.getMessage());
		}
		return workJobOrderResponseDTO;
	}

	@Override
	public WorkJobOrderResponseDTO validateManufacturerBatchNumber(WorkJobOrderDTO workJobOrderDTO)	throws WorkJobOrderException {
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			if(null != workJobOrderDTO && null != workJobOrderDTO.getManufacturingBatchNumber() && null != workJobOrderDTO.getLotNumber()){
				LISWorkJobOrderMaster workJobOrderMaster = workJobOrderDAO.validateManufacturerBatchNumber(workJobOrderDTO.getManufacturingBatchNumber().toLowerCase(),workJobOrderDTO.getLotNumber().toLowerCase());
				if(null != workJobOrderMaster){
					workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.MANUFACTURER_BATCH_NUMBER_VALIDATION_FAILED);
				}else{
					workJobOrderResponseDTO.setStatus(StatusConstants.SUCCESS);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.MANUFACTURER_BATCH_NUMBER_VALIDATION_SUCCESS);
				}
			}
		}catch(Exception exception){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.UN_EXPECTED_EXCEPTION);
			logger.error("While validating the validateManufacturerBatchNumber "+exception.getMessage());
		}
		return workJobOrderResponseDTO;
	}

	@Override
	public WorkJobOrderResponseDTO validateLotSize(WorkJobOrderDTO workJobOrderDTO) throws WorkJobOrderException {
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			if(null != workJobOrderDTO && null != workJobOrderDTO.getComponentProductDrawNumber() && null != workJobOrderDTO.getCustomerPONumber()){
				LISPurchaseOrderMaster lisPurchaseOrderMaster = workJobOrderDAO.getCustomerPOQuantity(workJobOrderDTO.getComponentProductDrawNumber().toLowerCase(),workJobOrderDTO.getCustomerPONumber().toLowerCase());
				int customerPOQuantity = 0;
				int workOrderLotSize = 0;
				if(null != lisPurchaseOrderMaster){
					customerPOQuantity = lisPurchaseOrderMaster.getCustomerPOQuantity() != null ? lisPurchaseOrderMaster.getCustomerPOQuantity() :0;
				}
				List<LISWorkJobOrderMaster> workJobOrderMasters = workJobOrderDAO.getAllWorkJobOrderListByNumber(workJobOrderDTO.getWorkJobOrderNumber().toLowerCase());
				if(null != workJobOrderMasters && workJobOrderMasters.size() > 0){
					for(LISWorkJobOrderMaster workJobOrderMaster : workJobOrderMasters){
						workOrderLotSize = workOrderLotSize + (workJobOrderMaster.getLotSize() != null ? workJobOrderMaster.getLotSize() : 0);
					}
				}
				int finalLotSize = customerPOQuantity - workOrderLotSize;
				if(finalLotSize >= 0){
					workJobOrderResponseDTO.setStatus(StatusConstants.SUCCESS);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.LOT_SIZE_VALIDATION_SUCCESS);
				}else{
					workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.LOT_SIZE_VALIDATION_FAILED);
				}
			}
		}catch(Exception exception){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.UN_EXPECTED_EXCEPTION);
			logger.error("While validating the validatelotSize "+exception.getMessage());
		}
		return workJobOrderResponseDTO;
	}
	
	@Override
	public WorkJobOrderResponseDTO validateManufacturerBatchSize(WorkJobOrderDTO workJobOrderDTO) throws WorkJobOrderException {
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			if(null != workJobOrderDTO && null != workJobOrderDTO.getComponentProductDrawNumber() && null != workJobOrderDTO.getCustomerPONumber()
					&& null != workJobOrderDTO.getLotNumber() && null != workJobOrderDTO.getWorkJobOrderNumber()){
				LISWorkJobOrderMaster workJobOrderMaster = workJobOrderDAO.getWorkJobOrderBy4(workJobOrderDTO.getComponentProductDrawNumber().toLowerCase(),
																							  workJobOrderDTO.getCustomerPONumber().toLowerCase(),
																							  workJobOrderDTO.getLotNumber().toLowerCase(),
																							  workJobOrderDTO.getWorkJobOrderNumber().toLowerCase());
				int lotSize = 0;
				if(null != workJobOrderMaster){
					lotSize = workJobOrderMaster.getLotSize() != null?workJobOrderMaster.getLotSize():0;
				}
				int manufacturingBatchSize = 0;
				List<LISWorkJobOrderMaster> workJobOrderMasters = workJobOrderDAO.getAllWorkJobOrderListByLotNumber(workJobOrderDTO.getLotNumber().toLowerCase());
				if(null != workJobOrderMasters && workJobOrderMasters.size() > 0){
					for(LISWorkJobOrderMaster workJobOrderMaster1 : workJobOrderMasters){
						manufacturingBatchSize = manufacturingBatchSize + (workJobOrderMaster1.getManufacturingBatchSize() != null ? workJobOrderMaster1.getManufacturingBatchSize() : 0);
					}
				}
				int finalResult = (lotSize - manufacturingBatchSize) - (workJobOrderDTO.getManufacturingBatchSize()!=null?workJobOrderDTO.getManufacturingBatchSize():0);
				if(finalResult >= 0){
					workJobOrderResponseDTO.setStatus(StatusConstants.SUCCESS);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.MANUFACTURER_BATCH_SIZE_VALIDATION_SUCCESS);
				}else{
					workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
					workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.MANUFACTURER_BATCH_SIZE_VALIDATION_FAILED);
				}
			}
		}catch(Exception exception){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.UN_EXPECTED_EXCEPTION);
			logger.error("While validating the validatelotManufacturerBatchSize "+exception.getMessage());
		}
		return workJobOrderResponseDTO;
	}

	@Override
	public WorkJobOrderResponseDTO getComponentData(Integer subscriberId) throws WorkJobOrderException {
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			List<LISMaintainMasterDataComponent> lisMaintainMasterDataComponents = componentMasterDataDAO.getComponentData(subscriberId);
			List<ComponentMasterDataDTO> componentData = new ArrayList<ComponentMasterDataDTO>();
			if(null != lisMaintainMasterDataComponents && lisMaintainMasterDataComponents.size() > 0){
				for(LISMaintainMasterDataComponent masterDataComponent : lisMaintainMasterDataComponents){
					ComponentMasterDataDTO component = new ComponentMasterDataDTO();
					component.setComponentProductDrawNumber(masterDataComponent.getComponentProductDrawNumber());
					component.setComponentProductManufacturerUnits(masterDataComponent.getComponentProductManufacturerUnits());
					componentData.add(component);
				}
			}
			workJobOrderResponseDTO.setStatus(StatusConstants.SUCCESS);
			workJobOrderResponseDTO.setComponentData(componentData);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.MANUFACTURER_BATCH_NUMBER_VALIDATION_SUCCESS);
		}catch(Exception exception){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.UN_EXPECTED_EXCEPTION);
			logger.error("While validating the validatelotManufacturerBatchSize "+exception.getMessage());
		}
		return workJobOrderResponseDTO;
	}

	@Override
	public WorkJobOrderResponseDTO getCustomerPOData(Integer subscriberId) throws WorkJobOrderException {
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			List<LISPurchaseOrderMaster> lisPurchaseOrderMasters = purchaseOrderDataDAO.getCustomerPOData(subscriberId);
			Set<String> customerPONumber = new TreeSet<String>();
			if(null != lisPurchaseOrderMasters && lisPurchaseOrderMasters.size() > 0){
				for(LISPurchaseOrderMaster purchaseOrderMaster : lisPurchaseOrderMasters){
					customerPONumber.add(purchaseOrderMaster.getCustomerPONumber());
				}
			}
			workJobOrderResponseDTO.setStatus(StatusConstants.SUCCESS);
			workJobOrderResponseDTO.setCustomerPONumberList(customerPONumber);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.MANUFACTURER_BATCH_NUMBER_VALIDATION_SUCCESS);
		}catch(Exception exception){
			workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
			workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.UN_EXPECTED_EXCEPTION);
			logger.error("While validating the validatelotManufacturerBatchSize "+exception.getMessage());
		}
		return workJobOrderResponseDTO;
	}
	
}
