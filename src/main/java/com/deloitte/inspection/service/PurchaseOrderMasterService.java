package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.PurchaseOrderDataDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.exception.PurchaseOrderMasterException;
import com.deloitte.inspection.exception.SubscriberMasterException;

public interface PurchaseOrderMasterService {

	public String savePurchaseOrderData(PurchaseOrderDataDTO PurchaseOrderDataDTO, String userName, String userId) throws PurchaseOrderMasterException, CreateUserException, SubscriberMasterException ;

	public List<PurchaseOrderDataDTO> getAllPurchaseOrders(String userName) throws PurchaseOrderMasterException,CreateUserException;

	public String validatePODate(PurchaseOrderDataDTO PurchaseOrderDataDTO) ;
	
	public String validatePONumber(PurchaseOrderDataDTO PurchaseOrderDataDTO) ;

	public String validatePOQuantity(PurchaseOrderDataDTO PurchaseOrderDataDTO) throws PurchaseOrderMasterException;

	public String updatePurchaseOrderData(PurchaseOrderDataDTO PurchaseOrderDataDTO, String userName, String userId) throws PurchaseOrderMasterException,SubscriberMasterException,CreateUserException;

	public String deletePurchaseOrder(String customerPoId) throws PurchaseOrderMasterException; 
	

}
