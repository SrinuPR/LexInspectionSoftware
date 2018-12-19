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
import com.deloitte.inspection.dao.InspectionMeasurementDAO;
import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISInspectionMeasurements;
import com.deloitte.inspection.model.LISInspectionReportMaster;

@Repository
@Transactional
public class InspectionMeasurementDAOImpl implements InspectionMeasurementDAO{

	private static final Logger logger = LogManager.getLogger(InspectionMeasurementDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionReportMaster> getCompDrawNumList(Integer subscriberId)
			throws InspectionMeasurementException {
		logger.info("getCompDrawNumList DAO");
		Query query = getSession().createQuery("FROM LISInspectionReportMaster i where i.subscriberId = :subscriberId and i.isActive = :isActive ORDER BY i.createdTimestamp DESC ");
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return query.list();
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionMeasurements> getProducedQuantityListByBatchNumber(String manufacturingBatchNumber)
			throws InspectionMeasurementException {
		logger.info("getProducedQuantityListByBatchNumber DAO");
		Query query = getSession().createQuery("FROM LISInspectionMeasurements i where lower(i.manufacturingBatchNumber) = :manufacturingBatchNumber and i.isActive = :isActive ORDER BY i.createdTimestamp DESC ");
		query.setParameter("manufacturingBatchNumber", manufacturingBatchNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return query.list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionMeasurements> getProducedQuantityListByLotchNumber(String lotNumber)
			throws InspectionMeasurementException {
		logger.info("getProducedQuantityListByLotchNumber DAO");
		Query query = getSession().createQuery("FROM LISInspectionMeasurements i where lower(i.lotNumber) = :lotNumber and i.isActive = :isActive ORDER BY i.createdTimestamp DESC ");
		query.setParameter("lotNumber", lotNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return query.list();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionMeasurements> getProducedQuantityListByWJandPO(String workJobOrderNumber,
			String customerPONumber) throws InspectionMeasurementException {
		logger.info("getProducedQuantityListByWJandPO DAO");
		Query query = getSession().createQuery("FROM LISInspectionMeasurements i where lower(i.workJobOrderNumber) = :workJobOrderNumber and lower(i.customerPONumber) = :customerPONumber and i.isActive = :isActive ORDER BY i.createdTimestamp DESC ");
		query.setParameter("workJobOrderNumber", workJobOrderNumber);
		query.setParameter("customerPONumber", customerPONumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return query.list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISInspectionReportMaster getWorkJobOrderByReportNum(Integer reportNumber)
			throws InspectionMeasurementException {
		logger.info("getProducedQuantityListByWJandPO DAO");
		Query query = getSession().createQuery("FROM LISInspectionReportMaster i where i.inspectionReportNumber = :reportNumber and i.isActive = :isActive ORDER BY i.createdTimestamp DESC ");
		query.setParameter("reportNumber", reportNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISInspectionReportMaster> list = query.list();
		if(null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionMeasurements> getAllMeasurementList(String userId) throws InspectionMeasurementException {
		logger.info("Entered into getAllMeasurementList");	
		Query query = getSession().createQuery(" From LISInspectionMeasurements l where l.userId = :userId and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("userId", userId);
		return query.list();
	}

}
