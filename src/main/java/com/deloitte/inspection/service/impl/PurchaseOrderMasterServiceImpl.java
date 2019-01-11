package com.deloitte.inspection.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.PurchaseOrderConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dao.InspectionMeasurementDAO;
import com.deloitte.inspection.dao.PurchaseOrderDataDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dao.WorkJobOrderDAO;
import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.PurchaseOrderDataDTO;
import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.exception.PurchaseOrderMasterException;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISInspectionMeasurements;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;
import com.deloitte.inspection.service.PurchaseOrderMasterService;
import com.deloitte.inspection.util.InspectionUtils;

@Service
public class PurchaseOrderMasterServiceImpl implements PurchaseOrderMasterService{

	private static final Logger logger = LogManager.getLogger(PurchaseOrderMasterServiceImpl.class);
	
	@Autowired
	private PurchaseOrderDataDAO purchaseOrderDataDAO;
	
	@Autowired
	private CreateUserDAO createUserDAO;
	
	@Autowired
	private SubscriberMasterDAO subscriberMasterDAO;
	
	@Autowired
	private ComponentMasterDataDAO componentMasterDataDAO;
	
	@Autowired
	private WorkJobOrderDAO workJobOrderDAO;
	
	@Autowired
	private InspectionMeasurementDAO inspectionMeasurementDAO;

	@Override
	public String savePurchaseOrderData(PurchaseOrderDataDTO purchaseOrderDataDTO, String userName, String userId)
			throws PurchaseOrderMasterException, CreateUserException, SubscriberMasterException {
		String status = StatusConstants.FAILURE;
		if(null!=purchaseOrderDataDTO) {
			logger.info("savePurchaseOrderData for Subscriber Id "+purchaseOrderDataDTO.getSubscriberId());
			LISPurchaseOrderMaster purchaseOrderMaster=new LISPurchaseOrderMaster();
			LISUserMasterCreate userMaster=new LISUserMasterCreate();
			LISSubscriberMaster subscriberMaster=null;
			if(null != purchaseOrderDataDTO.getSubscriberId()){
				subscriberMaster = subscriberMasterDAO.getSubscriberById(purchaseOrderDataDTO.getSubscriberId());
				purchaseOrderMaster.setSubscriberMaster(subscriberMaster);
			}
			try {
				LISMaintainMasterDataComponent masterDataComponent = componentMasterDataDAO.getComponentDataById(purchaseOrderDataDTO.getComponentId());
				purchaseOrderMaster.setComponentMasterData(masterDataComponent);
			} catch (ComponentMasterDataException e) {
				e.printStackTrace();
				logger.error("Error while getting the component");
			}
			userMaster = createUserDAO.validateUserId(userId);
			purchaseOrderMaster.setUserMasterCreate(userMaster);
			purchaseOrderMaster.setCreatedBy(userName);
			purchaseOrderMaster.setCreatedTimestamp(new Date());
			try {
				purchaseOrderMaster.setCustomerPODate(InspectionUtils.convertStringToDate(purchaseOrderDataDTO.getCustomerPODate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			purchaseOrderMaster.setCustomerPONumber(purchaseOrderDataDTO.getCustomerPONumber());
			purchaseOrderMaster.setCustomerPOQuantity(purchaseOrderDataDTO.getCustomerPOQuantity());
			purchaseOrderMaster.setNotesPO(purchaseOrderDataDTO.getPoNotes());
			purchaseOrderMaster.setSubscriberMaster(subscriberMaster);
			purchaseOrderMaster.setIsActive(StatusConstants.IS_ACTIVE);
			purchaseOrderDataDAO.savePurchaseOrderData(purchaseOrderMaster);
			status = StatusConstants.SUCCESS;
		}
		return status;
	}

	@Override
	public List<PurchaseOrderDataDTO> getAllPurchaseOrders(Integer subscriberId) throws PurchaseOrderMasterException, CreateUserException {
		logger.info("getAllPurchaseOrders "+ subscriberId);
		List<PurchaseOrderDataDTO> purchaseOrderDTOList =  new ArrayList<PurchaseOrderDataDTO>();
		List<LISPurchaseOrderMaster> purchaseOrderList = null;
		if( null!= subscriberId)
			try {			
				purchaseOrderList = purchaseOrderDataDAO.getAllBySubscriberId(subscriberId);
				if(null != purchaseOrderList && purchaseOrderList.size() > 0){
					for(LISPurchaseOrderMaster purchaseOrder:purchaseOrderList) {
						PurchaseOrderDataDTO purchaseOrderDto=new PurchaseOrderDataDTO();
						if(null != purchaseOrder.getComponentMasterData()){
							purchaseOrderDto.setComponentProductDrawNum(purchaseOrder.getComponentMasterData().getComponentProductDrawNumber());
							purchaseOrderDto.setComponentId(purchaseOrder.getComponentMasterData().getCmdcsId());
						}
						purchaseOrderDto.setCustomerPODate(InspectionUtils.convertDateToString(purchaseOrder.getCustomerPODate()));
						purchaseOrderDto.setCustomerPONumber(purchaseOrder.getCustomerPONumber());
						purchaseOrderDto.setCustomerPOQuantity(purchaseOrder.getCustomerPOQuantity());
						purchaseOrderDto.setPoNotes(purchaseOrder.getNotesPO());
						purchaseOrderDto.setSubscriberId(purchaseOrder.getSubscriberMaster().getSubscriberId());
						purchaseOrderDto.setSubscriberName(purchaseOrder.getSubscriberMaster().getSubscriberName());
						purchaseOrderDto.setCustomerPoId(purchaseOrder.getCustomerPoId());
						purchaseOrderDTOList.add(purchaseOrderDto);
					}
				}
				return purchaseOrderDTOList;
			}catch(ParseException e) {
				e.printStackTrace();
			}catch(PurchaseOrderMasterException pue) {
				pue.printStackTrace();
			}
		
		return null;
	}

	@Override
	public String validatePODate(PurchaseOrderDataDTO PurchaseOrderDataDTO) {
		logger.info(" Entered into validatePODate ");
		boolean isValid=false;
		try {
			if(null!=PurchaseOrderDataDTO.getCustomerPODate()) {
				isValid=InspectionUtils.isValidDate(PurchaseOrderDataDTO.getCustomerPODate());
				if(!isValid)
				return PurchaseOrderConstants.INVALID_DATE;	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return StatusConstants.SUCCESS;
	}
	
	@Override
	public CommonDTO validatePONumber(String custDrawNumber) {
		logger.info("Inside validatePONumber method");
		CommonDTO commonDTO = new CommonDTO();
		boolean poNumExists=false;
		try {
			LISPurchaseOrderMaster purchaseOrder = purchaseOrderDataDAO.getByCustomerPONumber(custDrawNumber.trim().toLowerCase());
			if(null!=purchaseOrder){
				poNumExists=true;
			}
			if(poNumExists) {
				commonDTO.setStatus(StatusConstants.ERROR);
				commonDTO.setMessage(PurchaseOrderConstants.CUSTOMER_PO_EXISTS);
			}else {
				commonDTO.setStatus(StatusConstants.SUCCESS);
				commonDTO.setMessage(PurchaseOrderConstants.CUSTOMER_PO_NOT_EXIST);
			}
		}catch(Exception exception) {
			exception.printStackTrace();
			logger.info("validatePONumber Exception "+exception.getMessage());
		}
		return commonDTO;
	}
	
	@Override
	public String validatePOQuantity(PurchaseOrderDataDTO purchaseOrderDataDTO)
			throws PurchaseOrderMasterException {
		try {
			
			if(null!=purchaseOrderDataDTO && null!=purchaseOrderDataDTO.getCustomerPONumber()) {
				LISPurchaseOrderMaster purchaseOrder = purchaseOrderDataDAO.getByCustomerPONumber(purchaseOrderDataDTO.getCustomerPONumber());
				if(null != purchaseOrder)
					return StatusConstants.SUCCESS;	
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return StatusConstants.FAILURE;
	}

	@Override
	public String updatePurchaseOrderData(PurchaseOrderDataDTO purchaseOrderDataDTO, String userName, String userId)
			throws PurchaseOrderMasterException, SubscriberMasterException, CreateUserException {
		String status=StatusConstants.FAILURE;
		LISPurchaseOrderMaster purchaseMaster=new LISPurchaseOrderMaster();
	
		if(null!=purchaseOrderDataDTO && null!=purchaseOrderDataDTO.getCustomerPONumber()) {
			purchaseMaster = purchaseOrderDataDAO.getByCustomerPONumber(purchaseOrderDataDTO.getCustomerPONumber().trim().toLowerCase());
			if(null != purchaseMaster){
				try {
					purchaseMaster.setCustomerPODate(InspectionUtils.convertStringToDate(purchaseOrderDataDTO.getCustomerPODate()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				boolean valid = quantityChangeValidation(purchaseOrderDataDTO.getCustomerPONumber(),purchaseOrderDataDTO.getSubscriberId(),purchaseOrderDataDTO.getCustomerPOQuantity());
				if(valid){
					purchaseMaster.setCustomerPONumber(purchaseOrderDataDTO.getCustomerPONumber().trim());
					purchaseMaster.setCustomerPOQuantity(purchaseOrderDataDTO.getCustomerPOQuantity());
					purchaseMaster.setNotesPO(purchaseOrderDataDTO.getPoNotes());
					purchaseMaster.setUpdatedBy(userName);
					purchaseMaster.setUpdatedTimestamp(new Date());
					purchaseOrderDataDAO.savePurchaseOrderData(purchaseMaster);
					status = StatusConstants.SUCCESS;
				}
				return status;
			}
		}
		return null;
	}

	private boolean quantityChangeValidation(String customerPONumber, Integer subscriberId, Integer customerPOQuantity) {
		boolean valid = false;
		try{
			List<LISWorkJobOrderMaster> wjList = workJobOrderDAO.getWJOListByPONumAndSubId(customerPONumber.toLowerCase(),subscriberId);
			if(null != wjList && wjList.size() > 0){
				Set<String> wjoNum = new HashSet<String>();
				for(LISWorkJobOrderMaster wjMaster : wjList){
					wjoNum.add(wjMaster.getWorkJobOrderNumber());
				}
				if(null != wjoNum && wjoNum.size() > 0){
					List<LISInspectionMeasurements> inspectionMeasurements = inspectionMeasurementDAO.getProducedQuantityListByWJandSubId(wjoNum, subscriberId);
					if(null != inspectionMeasurements && inspectionMeasurements.size() > 0){
						int producedQuantity = 0;
						for(LISInspectionMeasurements row:inspectionMeasurements){
							producedQuantity = producedQuantity + row.getProducedQuantity();
						}
						if((customerPOQuantity - producedQuantity) >= 0)
							valid = true;
					}
				}
			}
		}catch(Exception exception){
			logger.error("Error in change validation logic "+exception.getMessage());
			exception.printStackTrace();
		}
		return valid;
	}

	@Override
	public String deletePurchaseOrder(Integer customerPoId)
			throws PurchaseOrderMasterException {
		try{
			return purchaseOrderDataDAO.deletePurchaseOrder(customerPoId);
		}catch(Exception exception){
			logger.error("Exception while deleting component "+exception.getMessage());
		}
		return StatusConstants.FAILURE;
	}

	@Override
	public List<String> getAllComponentDrawingNumber() throws PurchaseOrderMasterException {
		List<LISMaintainMasterDataComponent> masterDataComponent;
		try {
			masterDataComponent = componentMasterDataDAO.getAllComponentDrawingNumber();
			if(null != masterDataComponent && masterDataComponent.size() > 0){
				List<String> drawNum = new ArrayList<String>();
				for(LISMaintainMasterDataComponent masterDataComponent2 : masterDataComponent){
					drawNum.add(masterDataComponent2.getComponentProductDrawNumber());
				}
			}
		} catch (ComponentMasterDataException componentMasterDataException) {
			componentMasterDataException.printStackTrace();
			logger.info("Error atComponet Product draw number list "+ componentMasterDataException.getMessage());
		}
		return null;
	}

	
}
