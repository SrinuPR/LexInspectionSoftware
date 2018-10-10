package com.deloitte.inspection.dao.impl;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.PurchaseOrderDataDAO;
import com.deloitte.inspection.dto.PurchaseOrderDataDTO;
import com.deloitte.inspection.exception.PurchaseOrderMasterException;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;

@Repository
@Transactional
public class PurchaseOrderDataDAOImpl implements PurchaseOrderDataDAO{
	
	private static final Logger logger = LogManager.getLogger(PurchaseOrderDataDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @PersistenceContext
    private EntityManager em;

	@Override
	public void savePurchaseOrderData(LISPurchaseOrderMaster purchaseOrderMaster)
			throws PurchaseOrderMasterException {
		logger.info("Entered into savePurchaseOrderData DAO");	
		getSession().saveOrUpdate(purchaseOrderMaster);		
	}

	@Override
	public List<LISPurchaseOrderMaster> getAllByUserId(String userId)
			throws PurchaseOrderMasterException {
		logger.info("Entered into getAllByUserId");	
		Query query = getSession().createQuery("SELECT i FROM LISPurchaseOrderMaster i where i.userMasterCreate.userId=:userId"	);
		query.setParameter("userId", userId);
		List<LISPurchaseOrderMaster> LISPurchaseOrderMaster = query.list();
		if(null != LISPurchaseOrderMaster && LISPurchaseOrderMaster.size() > 0){
			return LISPurchaseOrderMaster;
		}
		return null;
	}


	@Override
	public String validatePOQuantity(PurchaseOrderDataDTO PurchaseOrderDataDTO, String userName)
			throws PurchaseOrderMasterException {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public String deletePurchaseOrder(String customerPoId) throws PurchaseOrderMasterException {
		String status = StatusConstants.FAILURE;
		Query query = getSession().createSQLQuery("DELETE FROM LIS_CPMCS WHERE CUSTOMER_PO_ID = :customerPoId");
		query.setParameter("customerPoId", customerPoId);
		int result = query.executeUpdate();
		if(result > 0){
			status = StatusConstants.SUCCESS;
		}
		return status;	
	}

	@Override
	public LISPurchaseOrderMaster getByCustomerPONumber(String customerPONumber) throws PurchaseOrderMasterException {
		logger.info("Entered into validatePoNumber");	
		Query query = getSession().createQuery(" From LISPurchaseOrderMaster CPMCS where CPMCS.customerPONumber = :customerPONumber");
		query.setParameter("customerPONumber", customerPONumber);
		List<LISPurchaseOrderMaster> LISPurchaseOrderMaster = query.list();
		if(null != LISPurchaseOrderMaster && LISPurchaseOrderMaster.size() > 0){
			return LISPurchaseOrderMaster.get(0);
		}
		return null;
	}
	
}
