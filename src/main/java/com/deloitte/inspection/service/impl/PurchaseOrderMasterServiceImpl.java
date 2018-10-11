package com.deloitte.inspection.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.PurchaseOrderConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dao.PurchaseOrderDataDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dto.PurchaseOrderDataDTO;
import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.exception.PurchaseOrderMasterException;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
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
			if(null != purchaseOrderDataDTO.getComponentProductDrawNum()){
				try {
					LISMaintainMasterDataComponent masterDataComponent = componentMasterDataDAO.getComponentDataById(purchaseOrderDataDTO.getComponentId());
					purchaseOrderMaster.setComponentMasterData(masterDataComponent);
				} catch (ComponentMasterDataException e) {
					e.printStackTrace();
					logger.error("Error while getting the component");
				}
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
		
			purchaseOrderDataDAO.savePurchaseOrderData(purchaseOrderMaster);
			status = StatusConstants.SUCCESS;
		}
		return status;
	}

	@Override
	public List<PurchaseOrderDataDTO> getAllPurchaseOrders(String userId) throws PurchaseOrderMasterException, CreateUserException {
		LISUserMasterCreate userMaster=new LISUserMasterCreate();
		List<PurchaseOrderDataDTO> purchaseOrderDTOList =  new ArrayList<PurchaseOrderDataDTO>();
		List<LISPurchaseOrderMaster> purchaseOrderList = null;
		
		if( null!=userId)
			try {
				userMaster = createUserDAO.validateUserId(userId);
				
				purchaseOrderList = purchaseOrderDataDAO.getAllByUserId(userMaster.getUserId());
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
				//purchaseOrderDto.setPurchaseOrderList(purchaseOrderList);
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
	public String validatePONumber(PurchaseOrderDataDTO purchaseOrderDataDTO) {
		LISPurchaseOrderMaster purchaseOrder=new LISPurchaseOrderMaster();
		boolean poNumExists=false;
		try {
			if(null!=purchaseOrderDataDTO && null!=purchaseOrderDataDTO.getCustomerPONumber()) {
				purchaseOrder = purchaseOrderDataDAO.getByCustomerPONumber(purchaseOrderDataDTO.getCustomerPONumber());
				if(null!=purchaseOrder){
					poNumExists=true;
				}
			}
			if(poNumExists) {
				return PurchaseOrderConstants.CUSTOMER_PO_EXISTS;
			}else {
				return StatusConstants.SUCCESS;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return StatusConstants.EMPTY;
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
		LISUserMasterCreate userMaster=new LISUserMasterCreate();
		LISSubscriberMaster subscriberMaster=null;
		
		if(null!=purchaseOrderDataDTO && null!=purchaseOrderDataDTO.getCustomerPONumber()) {
			userMaster = createUserDAO.validateUserId(userId);
			purchaseMaster = purchaseOrderDataDAO.getByCustomerPONumber(purchaseOrderDataDTO.getCustomerPONumber());
			if(null != userMaster.getSubscriberMaster().getSubscriberId()){
					subscriberMaster = subscriberMasterDAO.getSubscriberById(userMaster.getSubscriberMaster().getSubscriberId());
			}
			try {
				purchaseMaster.setCustomerPODate(InspectionUtils.convertStringToDate(purchaseOrderDataDTO.getCustomerPODate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			purchaseMaster.setCustomerPONumber(purchaseOrderDataDTO.getCustomerPONumber());
			purchaseMaster.setCustomerPOQuantity(purchaseOrderDataDTO.getCustomerPOQuantity());
			purchaseMaster.setNotesPO(purchaseOrderDataDTO.getPoNotes());
			purchaseMaster.setSubscriberMaster(subscriberMaster);
			purchaseMaster.setUpdatedBy(userName);
			purchaseMaster.setUpdatedTimestamp(new Date());
			purchaseMaster.setUserMasterCreate(userMaster);
			purchaseOrderDataDAO.savePurchaseOrderData(purchaseMaster);
			status = StatusConstants.SUCCESS;
			return status;
		}
		return null;
	}

	@Override
	public String deletePurchaseOrder(String customerPoId)
			throws PurchaseOrderMasterException {
		try{
			return purchaseOrderDataDAO.deletePurchaseOrder(customerPoId);
		}catch(Exception exception){
			logger.error("Exception while deleting component "+exception.getMessage());
		}
		return StatusConstants.FAILURE;
	}

	
}
