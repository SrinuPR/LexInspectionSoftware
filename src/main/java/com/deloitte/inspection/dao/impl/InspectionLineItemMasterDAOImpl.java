package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.DatabaseSequenceDAO;
import com.deloitte.inspection.dao.InspectionLineItemMasterDAO;
import com.deloitte.inspection.dto.InspectionLineItemDTO;
import com.deloitte.inspection.exception.DatabaseSequenceException;
import com.deloitte.inspection.exception.InspectionLineItemMasterException;
import com.deloitte.inspection.mapper.LISInspectionMasterResult;
import com.deloitte.inspection.model.DatabaseSequence;
import com.deloitte.inspection.model.LISInspectionLineItemMaster;
import com.deloitte.inspection.model.LISInspectionMaster;

@Repository
@Transactional
public class InspectionLineItemMasterDAOImpl implements InspectionLineItemMasterDAO {

	private static final Logger logger = LogManager.getLogger(InspectionLineItemMasterDAOImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	DatabaseSequenceDAO databaseSequence;

	@Override
	public void saveInspectionLineItem(List<LISInspectionLineItemMaster> lineItemsList)
			throws InspectionLineItemMasterException {
		logger.info("Inside saveInspectionLineItem DAO");
		for (LISInspectionLineItemMaster master : lineItemsList) {
			try {
				if (master.getInspectionLineItemId() == null)
					master.setInspectionLineItemId(String.valueOf(databaseSequence.getNextSequenceId("LIS_ILIMC")));
			} catch (DatabaseSequenceException e) {
				e.printStackTrace();
			}
			mongoTemplate.save(master,"LIS_ILIMC");
		}
	}

	@Override
	public List<LISInspectionMasterResult> getComponentProductDrawNumbers(Integer subscriberId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getComponentProductDrawNumbers DAO");
		LookupOperation lookupOperation = LookupOperation.newLookup().from("LIS_SUMAS").localField("subscriberMasterId")
				.foreignField("subscriberId").as("subscriberMaster");
		LookupOperation masterLookup = LookupOperation.newLookup().from("LIS_CMDCS").localField("maintainMasterDataComponentId")
				.foreignField("componentProductDrawNumber").as("maintainMasterDataComponent");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriberMasterId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
				lookupOperation,
				masterLookup,
				Aggregation.sort(Direction.ASC, "maintainMasterDataComponentId"));

		List<LISInspectionMasterResult> list = mongoTemplate
				.aggregate(aggregation, "LIS_INMDC", LISInspectionMasterResult.class).getMappedResults();
		return list;
	}

	@Override
	public List<LISInspectionLineItemMaster> getAllInspectionLineItems(Integer subscriberId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getAllInspectionLineItems DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		List<LISInspectionLineItemMaster> list = mongoTemplate
				.aggregate(aggregation, "LIS_ILIMC", LISInspectionLineItemMaster.class)
				.getMappedResults();
		return list;
	}

	@Override
	public LISInspectionLineItemMaster validateMeasurementName(InspectionLineItemDTO inspectionLineItem)
			throws InspectionLineItemMasterException {
		logger.info("Entered into validateMeasurementName DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("componentProductDrawNumber")
				.is(inspectionLineItem.getComponentProductDrawNumber())
				.andOperator(Criteria.where("measurmentName").is(inspectionLineItem.getMeasurementName()),
						Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		List<LISInspectionLineItemMaster> list = mongoTemplate.find(query, LISInspectionLineItemMaster.class,"LIS_ILIMC");
		if (null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public LISInspectionLineItemMaster getInspectionItem(Integer inspectionLineItemId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into validateMeasurementName DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("inspectionLineItemId").is(inspectionLineItemId.toString())
				.andOperator(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		List<LISInspectionLineItemMaster> list = mongoTemplate.find(query, LISInspectionLineItemMaster.class,"LIS_ILIMC");
		if (null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	public List<LISInspectionLineItemMaster> getComponentProductDrawNumbers(String compDraNum)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getComponentProductDrawNumbers DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("componentProductDrawNumber").is(compDraNum)
				.andOperator(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		List<LISInspectionLineItemMaster> list = mongoTemplate.find(query, LISInspectionLineItemMaster.class,"LIS_ILIMC");
		return list;
	}

	@Override
	public List<LISInspectionLineItemMaster> getAllInspectionLineItemByUserID(String userId)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getAllInspectionLineItems DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("userID").is(userId)
				.andOperator(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		return mongoTemplate.find(query, LISInspectionLineItemMaster.class,"LIS_ILIMC");
	}

	@Override
	public List<LISInspectionLineItemMaster> getAllInspectionLineItemsByDrawNum(String compDrawNum)
			throws InspectionLineItemMasterException {
		logger.info("Entered into getAllInspectionLineItems DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("componentProductDrawNumber").is(compDrawNum)
				.andOperator(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		return mongoTemplate.find(query, LISInspectionLineItemMaster.class,"LIS_ILIMC");
	}
}
