package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.MasterListOfScreensForSubscriberDAO;
import com.deloitte.inspection.exception.MasterListOfScreensForSubscriberException;
import com.deloitte.inspection.model.LISMasterListOfScreensForSubscriber;

@Repository
@Transactional
public class MasterListOfScreensForSubscriberDAOImpl implements MasterListOfScreensForSubscriberDAO{

	private static final Logger logger = LogManager.getLogger(MasterListOfScreensForSubscriberDAOImpl.class);
	
	@Autowired
    private MongoTemplate mongoTemplate;

	@Override
	public void deleteScreenForSubscriber(Integer subscriberId, String screenId) throws MasterListOfScreensForSubscriberException {
		logger.info("Inside deleteScreenForSubscriber DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
				Aggregation.match(Criteria.where("screenNumber").is(screenId.toLowerCase())));
		List<LISMasterListOfScreensForSubscriber> list = mongoTemplate.aggregate(aggregation, "LIS_MLOSS", LISMasterListOfScreensForSubscriber.class).getMappedResults();
		for (LISMasterListOfScreensForSubscriber item : list) {
			mongoTemplate.remove(item);
		}
	}

	@Override
	public void insertScreensForSubscribers(List<LISMasterListOfScreensForSubscriber> insertList)
			throws MasterListOfScreensForSubscriberException {
		logger.info("Inside insertScreensForSubscribers DAO");
		for(LISMasterListOfScreensForSubscriber masterScreen : insertList){
			mongoTemplate.save(masterScreen);
		}
	}

	@Override
	public List<LISMasterListOfScreensForSubscriber> getScreensforSubscriber(Integer subscriberId)
			throws MasterListOfScreensForSubscriberException {
		logger.info("Inside getScreensforSubscriber DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		return mongoTemplate.aggregate(aggregation, "LIS_MLOSS", LISMasterListOfScreensForSubscriber.class).getMappedResults();
	}
}
