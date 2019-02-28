package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.AccessMasterDAO;
import com.deloitte.inspection.model.LISAccessMaster;
import com.deloitte.inspection.model.LISUserTypeMaster;

@Repository
@Transactional
public class AccessMasterDAOImpl implements AccessMasterDAO {

	private static final Logger logger = LogManager.getLogger(AccessMasterDAOImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void saveAccessMaster(LISAccessMaster lisAccessMaster) throws Exception {
		mongoTemplate.save(lisAccessMaster,"LIS_ACMDS");
	}

	@Override
	public LISAccessMaster getAccessScreens(Integer subscriberId, Integer userTypId) throws Exception {
		logger.info("Entered into getAccessScreens");
		if (null != subscriberId && null != userTypId) {
			logger.info("Fetching data on basis of subdId and UserTpyeId");
			Aggregation aggregation = Aggregation.newAggregation(
					Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
					Aggregation.match(Criteria.where("userType.userTypeId").is(userTypId)));
			List<LISAccessMaster> accessMasterScreenList = mongoTemplate
					.aggregate(aggregation, "LIS_ACMDS", LISAccessMaster.class).getMappedResults();
			if (null != accessMasterScreenList && accessMasterScreenList.size() > 0) {
				return accessMasterScreenList.get(0);
			}
		} else if (null != subscriberId) {
			logger.info("Fetching data on basis of subdId");
			Aggregation aggregation1 = Aggregation.newAggregation(
					Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)));
			List<LISAccessMaster> accessMasterScreenList = mongoTemplate
					.aggregate(aggregation1, "LIS_ACMDS", LISAccessMaster.class).getMappedResults();
			if (null != accessMasterScreenList && accessMasterScreenList.size() > 0) {
				return accessMasterScreenList.get(0);
			}
		}
		return null;
	}

	@Override
	public List<LISUserTypeMaster> getUserTypeListforSubscriber(Integer subscriberId) throws Exception {
		logger.info("inside getUserTypeListforSubscriber DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)),
				Aggregation.sort(Direction.ASC, "userTypeName"));
		return mongoTemplate.aggregate(aggregation, "LIS_UTMCS", LISUserTypeMaster.class).getMappedResults();
	}

	@Override
	public LISAccessMaster getAccessMaster(Integer accessMasterId) throws Exception {
		logger.info("Inside getAccessMaster DAO");
		Query query = new Query();
		query.addCriteria(Criteria.where("accessMasterId").is(accessMasterId));
		LISAccessMaster accessMaster = mongoTemplate.findOne(query, LISAccessMaster.class,"LIS_ACMDS");
		return accessMaster;
	}

	@Override
	public LISAccessMaster getAccessMasterByUserTypeId(Integer userTypeId) throws Exception {
		logger.info("inside getAccessMasterByUserTypeId DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("userType.userTypeId").is(userTypeId)),
				Aggregation.match(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		List<LISAccessMaster> master = mongoTemplate.aggregate(aggregation, "LIS_ACMDS", LISAccessMaster.class)
				.getMappedResults();
		if (null != master && master.size() > 0) {
			return master.get(0);
		}
		return null;
	}
}
