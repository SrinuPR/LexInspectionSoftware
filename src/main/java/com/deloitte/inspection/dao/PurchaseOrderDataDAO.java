package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.dto.PurchaseOrderDataDTO;
import com.deloitte.inspection.exception.PurchaseOrderMasterException;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;

public interface PurchaseOrderDataDAO {

	public void savePurchaseOrderData(LISPurchaseOrderMaster purchaseOrderMaster) throws PurchaseOrderMasterException;

	public List<LISPurchaseOrderMaster> getAllByUserId(String userId) throws PurchaseOrderMasterException;

	public String validatePOQuantity(PurchaseOrderDataDTO PurchaseOrderDataDTO, String userName) throws PurchaseOrderMasterException;

	public String deletePurchaseOrder(String customerPONumber) throws PurchaseOrderMasterException;
	
	public LISPurchaseOrderMaster getByCustomerPONumber(String customerPoId) throws PurchaseOrderMasterException;

}
