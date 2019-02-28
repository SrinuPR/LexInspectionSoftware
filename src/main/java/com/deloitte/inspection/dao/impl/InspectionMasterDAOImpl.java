package com.deloitte.inspection.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.InspectionMasterDAO;
import com.deloitte.inspection.dto.InspectionMasterDTO;
import com.deloitte.inspection.model.LISInspectionMaster;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.mongodb.client.result.DeleteResult;

@Repository
@Transactional
public class InspectionMasterDAOImpl implements InspectionMasterDAO {
	private static final Logger logger = LogManager.getLogger(WorkJobOrderDAOImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public LISInspectionMaster getInspectionStage(InspectionMasterDTO inspectionDTO) {
		logger.info("Inside getInspectionStage DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("componentMasterData.componentProductDrawNumber")
						.is(inspectionDTO.getComponentProductDrawNumber())),
				Aggregation.match(Criteria.where("inspTypeId").in(inspectionDTO.getInspectionType())),
				Aggregation.match(Criteria.where("inspStageId").in(inspectionDTO.getInspectionStage())),
				Aggregation.match(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		List<LISInspectionMaster> list = mongoTemplate
				.aggregate(aggregation, "LIS_INMDC", LISInspectionMaster.class).getMappedResults();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveInspectionMaster(LISInspectionMaster inspectionMaster) {
		logger.info("InspectionMasterDAOImpl - Saving Inspection Master");
		mongoTemplate.save(inspectionMaster,"LIS_INMDC");
	}

	@Override
	public List<LISInspectionMaster> getInspectionMasterList(Integer subscriberId) {
		logger.info("Entered into getInspectionMasterList DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		return mongoTemplate.aggregate(aggregation, "LIS_INMDC", LISInspectionMaster.class)
				.getMappedResults();
	}

	@Override
	public LISInspectionMaster getInspectionMasterById(Integer inspectionMasterId) {
		logger.info("Inside getWorkJobOrderById DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("inspId").in(inspectionMasterId)
				.andOperator(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		List<LISInspectionMaster> list = mongoTemplate.find(query, LISInspectionMaster.class,"LIS_INMDC");
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public LISInspectionMaster getInspectionStageOtherThanCurrent(InspectionMasterDTO inspectionDTO) {
		logger.info("Inside getInspectionStage DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("component.componentProductDrawNumber")
						.is(inspectionDTO.getComponentProductDrawNumber())),
				Aggregation.match(Criteria.where("inspTypeId").in(inspectionDTO.getInspectionType())),
				Aggregation.match(Criteria.where("inspStageId").in(inspectionDTO.getInspectionStage())),
				Aggregation.match(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		List<LISInspectionMaster> list = mongoTemplate
				.aggregate(aggregation, "LIS_INMDC", LISInspectionMaster.class).getMappedResults();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public String deleteInspectionMaster(Integer inspectionMasterId) {
		logger.info("inside deleteInspectionMaster DAO method");
		String status = StatusConstants.FAILURE;
		Query query = new Query();
		query.addCriteria(Criteria.where("inspId").in(inspectionMasterId));
		DeleteResult result = mongoTemplate.remove(query, "LIS_INMDC");
		if (result.wasAcknowledged()) {
			status = StatusConstants.SUCCESS;
		}
		return status;
	}

	@Override
	public List<LISInspectionMaster> getInspectionTypesByCompProdDrawNum(String compProdDrawNum) {
		logger.info("Inside getInspectionTypesByCompProdDrawNum DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("component.componentProductDrawNumber").is(compProdDrawNum)),
				Aggregation.match(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		return mongoTemplate.aggregate(aggregation, "LIS_INMDC", LISInspectionMaster.class)
				.getMappedResults();
	}

	@Override
	public List<LISMaintainMasterDataComponent> getCompDrawNumsBySubscriberId(Integer subscriberId) {
		logger.info("getCompDrawNumsBySubscriberId DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.project("component.componentProductDrawNumber"));
		AggregationResults<String> output = mongoTemplate.aggregate(aggregation, "LIS_INMDC", String.class);
		Set<String> drawNums = new HashSet<String>();
		if (output != null) {
			for (String result : output) {
				drawNums.add(result);
			}
		}
		Aggregation aggregation1 = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("componentProductDrawNumber").in(drawNums)),
				Aggregation.match(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		return mongoTemplate
				.aggregate(aggregation1, "LIS_CMDCS", LISMaintainMasterDataComponent.class)
				.getMappedResults();

	}
}
