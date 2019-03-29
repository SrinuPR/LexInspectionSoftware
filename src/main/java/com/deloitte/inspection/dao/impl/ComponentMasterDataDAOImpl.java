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
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.DatabaseSequenceDAO;
import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.exception.DatabaseSequenceException;
import com.deloitte.inspection.mapper.LISMaintainMasterDataComponentResult;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.mongodb.client.result.DeleteResult;

@Repository
@Transactional
public class ComponentMasterDataDAOImpl implements ComponentMasterDataDAO {

	private static final Logger logger = LogManager.getLogger(ComponentMasterDataDAOImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	DatabaseSequenceDAO databaseSequence;

	@Override
	public void saveComponentMasterData(LISMaintainMasterDataComponent masterDataComponent)
			throws ComponentMasterDataException {
		logger.info("Entered into saveComponentMasterData DAO");
		try {
			if (masterDataComponent.getCmdcsId() == null)
				masterDataComponent.setCmdcsId(String.valueOf(databaseSequence.getNextSequenceId("LIS_CMDCS")));
		} catch (DatabaseSequenceException e) {
			e.printStackTrace();
		}
		mongoTemplate.save(masterDataComponent,"LIS_CMDCS");
	}

	@Override
	public LISMaintainMasterDataComponent getComponentDataById(Integer componentId)
			throws ComponentMasterDataException {
		logger.info("Entered into validateLoginCredentials");
		Query query = new Query();
		query.addCriteria(Criteria.where("cmdcsId").in(String.valueOf(componentId)));
		List<LISMaintainMasterDataComponent> maintainMasterDataComponents = mongoTemplate.find(query,
				LISMaintainMasterDataComponent.class,"LIS_CMDCS");
		if (null != maintainMasterDataComponents && maintainMasterDataComponents.size() > 0) {
			return maintainMasterDataComponents.get(0);
		}
		return null;
	}

	@Override
	public List<LISMaintainMasterDataComponentResult> getAllComponentMasterData(Integer subscriberId)
			throws ComponentMasterDataException {
		logger.info("Entered into validateLoginCredentials");
		LookupOperation lookupOperation = LookupOperation.newLookup().from("LIS_SUMAS").localField("subscriberMasterId")
				.foreignField("subscriberId").as("subscriberMaster");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriberMasterId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").in(String.valueOf(StatusConstants.IS_ACTIVE))),
				Aggregation.sort(Direction.ASC, "componentProductDrawNumber"),
				lookupOperation);
		List<LISMaintainMasterDataComponentResult> list = mongoTemplate
				.aggregate(aggregation, "LIS_CMDCS", LISMaintainMasterDataComponentResult.class)
				.getMappedResults();
		return list;
	}

	@Override
	public List<LISMaintainMasterDataComponent> getAllComponentMasterDataByUserID(String userId)
			throws ComponentMasterDataException {
		logger.info("Entered into getAllComponentMasterDataByUserID");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("user.userId").in(userId)),
				Aggregation.match(Criteria.where("isActive").in(String.valueOf(StatusConstants.IS_ACTIVE))),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		List<LISMaintainMasterDataComponent> list = mongoTemplate
				.aggregate(aggregation, "LIS_CMDCS", LISMaintainMasterDataComponent.class)
				.getMappedResults();
		return list;
	}

	@Override
	public String deleteComponent(Integer componentId) throws ComponentMasterDataException {
		String status = StatusConstants.FAILURE;
		Query query = new Query();
		query.addCriteria(Criteria.where("cmdcsId").in(String.valueOf(componentId)));
		DeleteResult result = mongoTemplate.remove(query, LISMaintainMasterDataComponent.class,"LIS_CMDCS");
		if (result.wasAcknowledged()) {
			status = StatusConstants.SUCCESS;
		}
		return status;
	}

	@Override
	public List<LISMaintainMasterDataComponent> getAllBySubscriberId(Integer subscriberId)
			throws ComponentMasterDataException {
		logger.info("Entered into getAllBySubscriberId");
		Aggregation aggregation = Aggregation
				.newAggregation(Aggregation.match(Criteria.where("subscriberMasterId").in(subscriberId)));
		List<LISMaintainMasterDataComponent> maintainMasterDataComponents = mongoTemplate
				.aggregate(aggregation, "LIS_CMDCS", LISMaintainMasterDataComponent.class)
				.getMappedResults();
		if (null != maintainMasterDataComponents && maintainMasterDataComponents.size() > 0) {
			return maintainMasterDataComponents;
		}
		return null;
	}

	@Override
	public LISMaintainMasterDataComponent getComponentDataByDrwNum(String productDrawNumber)
			throws ComponentMasterDataException {
		logger.info("Entered into getComponentDataByDrwNum");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("componentProductDrawNumber").in(productDrawNumber)
				.andOperator(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		List<LISMaintainMasterDataComponent> list = mongoTemplate.find(query, LISMaintainMasterDataComponent.class,"LIS_CMDCS");
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<LISMaintainMasterDataComponent> getAllComponentDrawingNumber() throws ComponentMasterDataException {
		logger.info("Entered into getComponentDataByDrwNum");
		Query query = new Query().with(new Sort(Direction.ASC, "componentProductDrawNumber"));
		query.addCriteria(Criteria.where("isActive").in(String.valueOf(StatusConstants.IS_ACTIVE)));
		List<LISMaintainMasterDataComponent> list = mongoTemplate.find(query, LISMaintainMasterDataComponent.class,"LIS_CMDCS");
		return list;
	}

	@Override
	public List<LISMaintainMasterDataComponent> getComponentData(Integer subscriberId)
			throws ComponentMasterDataException {
		logger.info("Entered into getComponentData DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriberMasterId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
				Aggregation.sort(Direction.ASC, "componentProductDrawNumber"));
		List<LISMaintainMasterDataComponent> list = mongoTemplate
				.aggregate(aggregation, "LIS_CMDCS", LISMaintainMasterDataComponent.class)
				.getMappedResults();
		return list;
	}

}
