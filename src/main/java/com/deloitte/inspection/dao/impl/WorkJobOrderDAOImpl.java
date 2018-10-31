package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.WorkJobOrderDAO;
import com.deloitte.inspection.exception.WorkJobOrderException;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;

@Repository
@Transactional
public class WorkJobOrderDAOImpl implements WorkJobOrderDAO{

private static final Logger logger = LogManager.getLogger(WorkJobOrderDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public String deleteWorkJobOrder(Integer workJobOrderId) throws WorkJobOrderException {
		logger.info("inside deleteWorkJobOrder DAO method");
		String status = StatusConstants.FAILURE;
		Query query = getSession().createSQLQuery("UPDATE LIS_WOMCS SET IS_ACTIVE = :inactive WHERE WOMCS_ID = :workJobOrderId ");
		query.setParameter("workJobOrderId", workJobOrderId);
		query.setParameter("inactive", StatusConstants.IN_ACTIVE);
		int result = query.executeUpdate();
		if(result > 0){
			status = StatusConstants.SUCCESS;
		}
		return status;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISWorkJobOrderMaster getWorkJobOrderByNumber(String workJobOrderNumber,String lotNumber,String batchNumber) throws WorkJobOrderException {
		logger.info("Inside getWorkJobOrderByNumber DAO");
		Query query = getSession().createQuery(" From LISWorkJobOrderMaster l where lower(l.workJobOrderNumber) = :workJobOrderNumber "
				+ " and lower(l.lotNumber) = :lotNumber "
				+ " and lower(l.manufacturingBatchNumber) = :batchNumber "
				+ " and l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("workJobOrderNumber", workJobOrderNumber);
		query.setParameter("lotNumber", lotNumber);
		query.setParameter("batchNumber", batchNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISWorkJobOrderMaster> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveWorkJobOrderData(LISWorkJobOrderMaster workJobOrderMaster) throws WorkJobOrderException {
		logger.info("Entered into saveWorkJobOrderData DAO");	
		getSession().saveOrUpdate(workJobOrderMaster);			
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISWorkJobOrderMaster> WorkJobOrderList(String userId) throws WorkJobOrderException {
		logger.info("Entered into WorkJobOrderList DAO");	
		Query query = getSession().createQuery(" From LISWorkJobOrderMaster l where l.userMasterCreate.userId = :userId and l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("userId", userId);
		List<LISWorkJobOrderMaster> list = query.list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISWorkJobOrderMaster validateWorkJobOrderNumber(String workJobOrderNumber, String customerPONumber)	throws WorkJobOrderException {
		logger.info("Inside validateWorkJobOrderNumber DAO");
		Query query = getSession().createQuery(" From LISWorkJobOrderMaster l where lower(l.workJobOrderNumber) = :workJobOrderNumber and lower(l.purchaseOrderMaster.customerPONumber) = :customerPONumber  and l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("workJobOrderNumber", workJobOrderNumber);
		query.setParameter("customerPONumber",customerPONumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISWorkJobOrderMaster> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISWorkJobOrderMaster validateLotNumber(String workJobOrderNumber, String lotNumber)	throws WorkJobOrderException {
		logger.info("Inside validatelotNumber DAO");
		StringBuffer hqlQuery = new StringBuffer("From LISWorkJobOrderMaster l where l.isActive = :isActive ");
		if(null != workJobOrderNumber)
			hqlQuery.append("and lower(l.workJobOrderNumber) = :workJobOrderNumber ");
		hqlQuery.append("and lower(l.lotNumber) = :lotNumber ORDER BY l.createdTimestamp DESC");
		Query query = getSession().createQuery(hqlQuery.toString());
		if(null != workJobOrderNumber)
			query.setParameter("workJobOrderNumber", workJobOrderNumber);
		query.setParameter("lotNumber",lotNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISWorkJobOrderMaster> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISWorkJobOrderMaster validateManufacturerBatchNumber(String manufacturerBatchNumber, String lotNumber) throws WorkJobOrderException {
		logger.info("Inside validateManufacturerBatchNumber DAO");
		Query query = getSession().createQuery(" From LISWorkJobOrderMaster l where lower(l.manufacturingBatchNumber) = :manufacturerBatchNumber and lower(l.lotNumber) = :lotNumber  and l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("manufacturerBatchNumber", manufacturerBatchNumber);
		query.setParameter("lotNumber",lotNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISWorkJobOrderMaster> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISPurchaseOrderMaster getCustomerPOQuantity(String componentProdDrawNum, String customerPONumber)
			throws WorkJobOrderException {
		logger.info("Inside getCustomerPOQuantity DAO");
		Query query = getSession().createQuery(" From LISPurchaseOrderMaster l where lower(l.customerPONumber) = :customerPONumber and lower(l.componentMasterData.componentProductDrawNumber) = :componentProdDrawNum  and l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("componentProdDrawNum", componentProdDrawNum);
		query.setParameter("customerPONumber",customerPONumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISPurchaseOrderMaster> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISWorkJobOrderMaster> getAllWorkJobOrderListByNumber(String workJobOrderNumber) throws WorkJobOrderException {
		logger.info("Inside getAllWorkJobOrderListByNumber DAO");
		Query query = getSession().createQuery(" From LISWorkJobOrderMaster l where lower(l.workJobOrderNumber) = :workJobOrderNumber and l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("workJobOrderNumber", workJobOrderNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return query.list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISWorkJobOrderMaster getWorkJobOrderBy4(String componentProductDrawNumber, String customerPONumber,
			String lotNumber, String workJobOrderNumber) throws WorkJobOrderException {
		logger.info("Inside getWorkJobOrderBy4 DAO");
		Query query = getSession().createQuery(" From LISWorkJobOrderMaster l where lower(l.purchaseOrderMaster.customerPONumber) = :customerPONumber and "
				+ "lower(l.componentMasterData.componentProductDrawNumber) = :componentProductDrawNumber and "
				+ "lower(l.lotNumber) = :lotNumber and "
				+ "lower(l.workJobOrderNumber) = :workJobOrderNumber and "
				+ "l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("componentProductDrawNumber", componentProductDrawNumber);
		query.setParameter("customerPONumber",customerPONumber);
		query.setParameter("workJobOrderNumber",workJobOrderNumber);
		query.setParameter("lotNumber",lotNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISWorkJobOrderMaster> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISWorkJobOrderMaster> getAllWorkJobOrderListByLotNumber(String lotNumber)	throws WorkJobOrderException {
		logger.info("Inside getAllWorkJobOrderListByLotNumber DAO");
		Query query = getSession().createQuery(" From LISWorkJobOrderMaster l where lower(l.lotNumber) = :lotNumber and l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("lotNumber", lotNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return query.list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISWorkJobOrderMaster getWorkJobOrderById(Integer wjOrderId) throws WorkJobOrderException {
		logger.info("Inside getWorkJobOrderById DAO");
		Query query = getSession().createQuery(" From LISWorkJobOrderMaster l where l.wjOrderId = :wjOrderId and l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("wjOrderId", wjOrderId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISWorkJobOrderMaster> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
}
