package com.deloitte.inspection.dao.impl;

import java.util.List;
import java.util.Set;

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
import com.deloitte.inspection.model.LISPartIdentification;

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionMeasurements> validatePartIdentification(String partIdententificationId,Integer subscriberId)
			throws InspectionMeasurementException {
		logger.info("Entered into validatePartIdentification");	
		Query query = getSession().createQuery(" From LISInspectionMeasurements l where l.subscriberId = :subscriberId and lower(l.partIdentificationNumber) = :partIdententificationId and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("partIdententificationId", partIdententificationId);
		return query.list();
	}

	@Override
	public void saveMeasurementsToDataBase(LISInspectionMeasurements inspectionMeasurements)
			throws InspectionMeasurementException {
		logger.info("Entered into saveMeasurementsToDataBase ");
		 getSession().saveOrUpdate(inspectionMeasurements);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionMeasurements> getProducedQuantityListByWJandSubId(Set<String> wjoNum, Integer subscriberId)
			throws InspectionMeasurementException {
		logger.info("Entered into getProducedQuantityListByWJandSubId");	
		Query query = getSession().createQuery(" From LISInspectionMeasurements l where l.subscriberId = :subscriberId and lower(l.workJobOrderNumber) in (:wjoNum) and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("subscriberId", subscriberId);
		query.setParameterList("wjoNum", wjoNum);
		return query.list();
	}

	@Override
	public LISPartIdentification getMeasurementRecord(Integer partVerifId) throws InspectionMeasurementException {
		logger.info("Entered into getMeasurementRecord DAO");
		LISPartIdentification lisPartIdentification =  (LISPartIdentification) getSession().get(LISPartIdentification.class, partVerifId);
		return lisPartIdentification;
	}

	@Override
	public void saveMeasurementRecord(LISPartIdentification partIdentification) throws InspectionMeasurementException {
		logger.info("Entered into saveMeasurementRecord DAO");
		getSession().saveOrUpdate(partIdentification);
	}

	@Override
	public LISInspectionMeasurements getRecordById(Integer inspectionMeasurementId)
			throws InspectionMeasurementException {
		logger.info("Entered into getRecordById DAO");
		LISInspectionMeasurements lisInspectionMeasurements =  (LISInspectionMeasurements) getSession().get(LISInspectionMeasurements.class, inspectionMeasurementId);
		return lisInspectionMeasurements;
	}

}
