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
import com.deloitte.inspection.dao.InspectionLineItemMasterDAO;
import com.deloitte.inspection.dto.InspectionLineItemDTO;
import com.deloitte.inspection.exception.InspectionLineItemMasterException;
import com.deloitte.inspection.model.LISInspectionLineItemMaster;
import com.deloitte.inspection.model.LISInspectionMaster;

@Repository
@Transactional
public class InspectionLineItemMasterDAOImpl implements InspectionLineItemMasterDAO{
	
	private static final Logger logger = LogManager.getLogger(InspectionLineItemMasterDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public void saveInspectionLineItem(List<LISInspectionLineItemMaster> lineItemsList) throws InspectionLineItemMasterException {
		logger.info("Inside saveInspectionLineItem DAO");
		Session session = getSession();
		for(LISInspectionLineItemMaster list:lineItemsList){
			session.saveOrUpdate(list);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LISInspectionMaster> getComponentProductDrawNumbers(Integer subscriberId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getComponentProductDrawNumbers DAO");	
		Query query = getSession().createQuery(" From LISInspectionMaster l where l.subscriberMaster.subscriberId = :subscriberId and isActive = :isActive ORDER BY l.componentMasterData.componentProductDrawNumber ASC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("subscriberId", subscriberId);
		List<LISInspectionMaster> list = query.list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionLineItemMaster> getAllInspectionLineItems(Integer subscriberId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getAllInspectionLineItems DAO");	
		Query query = getSession().createQuery(" From LISInspectionLineItemMaster l where l.subscriberId = :subscriberId and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("subscriberId", subscriberId);
		List<LISInspectionLineItemMaster> list = query.list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISInspectionLineItemMaster validateMeasurementName(InspectionLineItemDTO inspectionLineItem)
			throws InspectionLineItemMasterException {
		logger.info("Entered into validateMeasurementName DAO");	
		Query query = getSession().createQuery(" From LISInspectionLineItemMaster l where lower(l.componentProductDrawNumber) = :componentProductDrawNumber and lower(l.measurmentName) = :measurmentName and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("measurmentName", inspectionLineItem.getMeasurementName().toLowerCase());
		query.setParameter("componentProductDrawNumber", inspectionLineItem.getComponentProductDrawNumber().toLowerCase());
		List<LISInspectionLineItemMaster> list = query.list();
		if(null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LISInspectionLineItemMaster getInspectionItem(Integer inspectionLineItemId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into validateMeasurementName DAO");	
		Query query = getSession().createQuery(" From LISInspectionLineItemMaster l where l.InspectionLineItemId = :inspectionLineItemId and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("inspectionLineItemId", inspectionLineItemId);
		List<LISInspectionLineItemMaster> list = query.list();
		if(null != list && list.size() > 0)
			return list.get(0);
		return null;
	}
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<LISInspectionLineItemMaster>  getComponentProductDrawNumbers(String compDraNum) throws InspectionLineItemMasterException {
		logger.info("Entered into getComponentProductDrawNumbers DAO");	
		Query query = getSession().createQuery(" From LISInspectionLineItemMaster l where lower(l.componentProductDrawNumber) = :compDraNum and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("compDraNum", compDraNum);
		List<LISInspectionLineItemMaster> list = query.list();
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LISInspectionLineItemMaster> getAllInspectionLineItemByUserID(String userId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getAllInspectionLineItems DAO");	
		Query query = getSession().createQuery(" From LISInspectionLineItemMaster l where l.userID = :userId and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("userId", userId);
		return query.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LISInspectionLineItemMaster> getAllInspectionLineItemsByDrawNum(String compDrawNum)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getAllInspectionLineItems DAO");	
		Query query = getSession().createQuery(" From LISInspectionLineItemMaster l where lower(l.componentProductDrawNumber) = :compDrawNum and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("compDrawNum", compDrawNum);
		return query.list();
	}
}
