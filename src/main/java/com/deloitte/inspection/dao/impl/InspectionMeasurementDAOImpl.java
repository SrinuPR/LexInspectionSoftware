package com.deloitte.inspection.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.InspectionMeasurementDAO;
import com.deloitte.inspection.exception.InspectionMeasurementException;
import com.deloitte.inspection.model.LISInspectionMaster;
import com.deloitte.inspection.model.LISInspectionMeasurements;
import com.deloitte.inspection.model.LISInspectionReportMaster;
import com.deloitte.inspection.model.LISPartIdentification;

@Repository
@Transactional
public class InspectionMeasurementDAOImpl implements InspectionMeasurementDAO{

	private static final Logger logger = LogManager.getLogger(InspectionMeasurementDAOImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;	

	@Override
	public List<LISInspectionReportMaster> getCompDrawNumList(Integer subscriberId)
			throws InspectionMeasurementException {
		logger.info("getCompDrawNumList DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("subscriberId").in(subscriberId)
				.andOperator(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		return mongoTemplate.find(query, LISInspectionReportMaster.class,"LIS_IRMCS");
		
	}

	@Override
	public List<LISInspectionMeasurements> getProducedQuantityListByBatchNumber(String manufacturingBatchNumber)
			throws InspectionMeasurementException {
		logger.info("getProducedQuantityListByBatchNumber DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("manufacturingBatchNumber").in(manufacturingBatchNumber.toLowerCase())
				.andOperator(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		return mongoTemplate.find(query, LISInspectionMeasurements.class,"LIS_IMDES");
	}

	@Override
	public List<LISInspectionMeasurements> getProducedQuantityListByLotchNumber(String lotNumber)
			throws InspectionMeasurementException {
		logger.info("getProducedQuantityListByLotchNumber DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("lotNumber").in(lotNumber.toLowerCase())
				.andOperator(Criteria.where("isActive").in(StatusConstants.IS_ACTIVE)));
		return mongoTemplate.find(query, LISInspectionMeasurements.class,"LIS_IMDES");
	}
	
	@Override
	public List<LISInspectionMeasurements> getProducedQuantityListByWJandPO(String workJobOrderNumber,
			String customerPONumber) throws InspectionMeasurementException {
		logger.info("getProducedQuantityListByWJandPO DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("workJobOrderNumber").in(workJobOrderNumber.toLowerCase())
				.andOperator(Criteria.where("customerPONumber").in(customerPONumber.toLowerCase()))
				.andOperator(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		return mongoTemplate.find(query, LISInspectionMeasurements.class,"LIS_IMDES");
	}

	@Override
	public LISInspectionReportMaster getWorkJobOrderByReportNum(Integer reportNumber)
			throws InspectionMeasurementException {
		logger.info("getProducedQuantityListByWJandPO DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("inspectionReportNumber").in(reportNumber)
				.andOperator(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		List<LISInspectionReportMaster> list = mongoTemplate.find(query, LISInspectionReportMaster.class,"LIS_IRMCS");
		if(null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<LISInspectionMeasurements> getAllMeasurementList(String userId) throws InspectionMeasurementException {
		logger.info("Entered into getAllMeasurementList");	
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("userId").in(userId)
				.andOperator(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		return mongoTemplate.find(query, LISInspectionMeasurements.class,"LIS_IMDES");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionMeasurements> validatePartIdentification(String partIdententificationId,Integer subscriberId)
			throws InspectionMeasurementException {
		logger.info("Entered into validatePartIdentification");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("subscriberId").in(subscriberId)
				.andOperator(Criteria.where("partIdentificationNumber").in(partIdententificationId.toLowerCase()))
				.andOperator(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		return mongoTemplate.find(query, LISInspectionMeasurements.class,"LIS_IMDES");
	}

	@Override
	public void saveMeasurementsToDataBase(LISInspectionMeasurements inspectionMeasurements)
			throws InspectionMeasurementException {
		logger.info("Entered into saveMeasurementsToDataBase ");
		mongoTemplate.save(inspectionMeasurements,"LIS_IMDES");
	}

	@Override
	public List<LISInspectionMeasurements> getProducedQuantityListByWJandSubId(Set<String> wjoNum, Integer subscriberId)
			throws InspectionMeasurementException {
		logger.info("Entered into getProducedQuantityListByWJandSubId");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("subscriberId").in(subscriberId)
				.andOperator(Criteria.where("workJobOrderNumber").in(wjoNum))
				.andOperator(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		return mongoTemplate.find(query, LISInspectionMeasurements.class,"LIS_IMDES");
	}

	@Override
	public LISPartIdentification getMeasurementRecord(Integer partVerifId) throws InspectionMeasurementException {
		logger.info("Entered into getMeasurementRecord DAO");
		Query query = new Query();
		query.addCriteria(Criteria.where("partVerifId").in(partVerifId));
		LISPartIdentification lisPartIdentification =  mongoTemplate.findOne(query, LISPartIdentification.class,"LIS_PIFIM");
		return lisPartIdentification;
	}

	@Override
	public void saveMeasurementRecord(LISPartIdentification partIdentification) throws InspectionMeasurementException {
		logger.info("Entered into saveMeasurementRecord DAO");
		mongoTemplate.save(partIdentification,"LIS_PIFIM");
	}

	@Override
	public LISInspectionMeasurements getRecordById(Integer inspectionMeasurementId)
			throws InspectionMeasurementException {
		logger.info("Entered into getRecordById DAO");
		Query query = new Query();
		query.addCriteria(Criteria.where("inspectionMeasurementId").in(inspectionMeasurementId));
		LISInspectionMeasurements lisInspectionMeasurements =  mongoTemplate.findOne(query, LISInspectionMeasurements.class,"LIS_IMDES");
		return lisInspectionMeasurements;
	}

}
