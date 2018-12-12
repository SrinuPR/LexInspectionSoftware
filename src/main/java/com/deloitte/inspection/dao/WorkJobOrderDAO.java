package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.exception.WorkJobOrderException;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;

public interface WorkJobOrderDAO {

	public String deleteWorkJobOrder(Integer workJobOrderId) throws WorkJobOrderException;

	public LISWorkJobOrderMaster getWorkJobOrderByNumber(String workJobOrderNumber, String lotNumber, String batchNumber) throws WorkJobOrderException;

	public void saveWorkJobOrderData(LISWorkJobOrderMaster workJobOrderMaster) throws WorkJobOrderException;

	public List<LISWorkJobOrderMaster> WorkJobOrderList(String userId) throws WorkJobOrderException;

	public LISWorkJobOrderMaster validateWorkJobOrderNumber(String workJobOrderNumber, String customerPONumber) throws WorkJobOrderException;

	public LISWorkJobOrderMaster validateLotNumber(String workJobOrderNumber, String lotNumber) throws WorkJobOrderException;

	public LISWorkJobOrderMaster validateManufacturerBatchNumber(String manufacturerBatchNumber, String lotNumber) throws WorkJobOrderException;

	public LISPurchaseOrderMaster getCustomerPOQuantity(String componentProdDrawNum, String customerPONumber) throws WorkJobOrderException;

	public List<LISWorkJobOrderMaster> getAllWorkJobOrderListByNumber(String workJobOrderNumber, String customerPONumber) throws WorkJobOrderException;

	public LISWorkJobOrderMaster getWorkJobOrderBy4(String componentProductDrawNumber, String customerPONumber, String lotNumber,
			String workJobOrderNumber) throws WorkJobOrderException;

	public List<LISWorkJobOrderMaster> getAllWorkJobOrderListByLotNumber(String LotNumber) throws WorkJobOrderException;

	public LISWorkJobOrderMaster getWorkJobOrderById(Integer wjOrderId) throws WorkJobOrderException;

	public LISWorkJobOrderMaster getWorkOrderByBatchNumber(String manufacturingBatchNumber) throws WorkJobOrderException;
	
	public List<LISWorkJobOrderMaster> getComponentDataFromWJOBySubscriberId(Integer subscriberId) throws WorkJobOrderException;

	public List<LISWorkJobOrderMaster> getWorkJobOrderByCompDrawNum(String compProdDrawNum) throws WorkJobOrderException;

}
