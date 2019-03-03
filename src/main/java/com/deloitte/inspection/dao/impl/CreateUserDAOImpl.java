package com.deloitte.inspection.dao.impl;

import java.util.Date;
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

import com.deloitte.inspection.component.ApplicationProperties;
import com.deloitte.inspection.component.CryptoComponent;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.exception.CryptoException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISUserTypeMaster;
import com.mongodb.client.result.DeleteResult;

@Repository
@Transactional
public class CreateUserDAOImpl implements CreateUserDAO {
	private static final Logger logger = LogManager.getLogger(CreateUserDAOImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	CryptoComponent cryptoComponent;

	@Autowired
	private ApplicationProperties applicationProperties;

	public String createUser(CreateUserDTO createuserDTO) throws CreateUserException {
		logger.info("Entered into createUser");
		try {
			Aggregation aggregation = Aggregation.newAggregation(
					Aggregation
							.match(Criteria.where("subscriber.subscriberId").is(createuserDTO.getSubscriberId())),
					Aggregation.match(Criteria.where("isActive").in(String.valueOf(StatusConstants.IS_ACTIVE))));
			List<LISUserMasterCreate> userMasterList = mongoTemplate
					.aggregate(aggregation, "LIS_UMACS", LISUserMasterCreate.class).getMappedResults();
			if (null != userMasterList && userMasterList.size() >= applicationProperties.EACH_SUBSCRIBER_USER_COUNT) {
				return StatusConstants.FAILURE;
			}
			Query query = new Query();
			query.addCriteria(Criteria.where("subscriberId").in(createuserDTO.getSubscriberId())
					.andOperator(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
			LISSubscriberMaster subscriberMaster = mongoTemplate.findOne(query, LISSubscriberMaster.class);
			LISUserMasterCreate userMaster = new LISUserMasterCreate();
			userMaster.setActivePassword(cryptoComponent.encrypt(createuserDTO.getPassword()));
			userMaster.setCreatedBy(createuserDTO.getCreatedBy());
			userMaster.setCreatedTimestamp(new Date());
			userMaster.setOldPassword1(null);
			userMaster.setOldPassword2(null);
			userMaster.setSubscriber(subscriberMaster);
			userMaster.setUserId(createuserDTO.getUserId());
			userMaster.setUserName(createuserDTO.getUserName());
			userMaster.setUserTypeId(createuserDTO.getUserTypeId());
			userMaster.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
			mongoTemplate.save(userMaster,"LIS_UMACS");
			LISLogin lisLogin = new LISLogin();
			lisLogin.setCreatedBy(createuserDTO.getCreatedBy());
			lisLogin.setCreatedTimestamp(new Date());
			lisLogin.setPassword(cryptoComponent.encrypt(createuserDTO.getPassword()));
			lisLogin.setUser(userMaster);
			lisLogin.setSubscriber(subscriberMaster);
			lisLogin.setAdminId(createuserDTO.getAdminId());
			lisLogin.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
			mongoTemplate.save(lisLogin,"LIS_LOGIN");
			mongoTemplate.save(userMaster,"LIS_UMACS");
			return StatusConstants.SUCCESS;
		} catch (CryptoException ex) {
			ex.printStackTrace();
		}
		return StatusConstants.FAILURE;
	}

	public LISUserMasterCreate validateUserId(String userId) throws CreateUserException {
		logger.info("Entered into validateUserId");
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").in(userId)
				.andOperator(Criteria.where("isActive").is(StatusConstants.IS_ACTIVE)));
		List<LISUserMasterCreate> userMaster = mongoTemplate.find(query, LISUserMasterCreate.class,"LIS_UMACS");
		if (null != userMaster && userMaster.size() > 0) {
			return userMaster.get(0);
		}
		return null;
	}

	@Override
	public List<LISUserTypeMaster> getUserTypeBySubscriberId(Integer subscriberId) throws CreateUserException {
		logger.info("Entered into getUserTypeBySubscriberId ");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").in(subscriberId)),
				Aggregation.match(Criteria.where("isActive").in(StatusConstants.IS_ACTIVE)),
				Aggregation.sort(Direction.ASC, "userTypeName"));
		return mongoTemplate.aggregate(aggregation, "LIS_UTMCS", LISUserTypeMaster.class).getMappedResults();
	}

	@Override
	public LISLogin validateAdminId(String adminId) throws CreateUserException {
		logger.info("Entered into validateLoginCredentials");
		Query query = new Query();
		query.addCriteria(Criteria.where("adminId").in(adminId.toLowerCase()));
		List<LISLogin> loginList = mongoTemplate.find(query, LISLogin.class,"LIS_LOGIN");
		if (null != loginList && loginList.size() > 0) {
			return loginList.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteAdmin(String adminId) throws CreateUserException {
		Query query = new Query();
		query.addCriteria(Criteria.where("ADMIN_ID").in(adminId.toLowerCase()));
		DeleteResult result = mongoTemplate.remove(query, LISLogin.class,"LIS_LOGIN");
		if (result.wasAcknowledged())
			return true;
		return false;
	}

	@Override
	public void saveAdmin(LISLogin login) throws CreateUserException {
		mongoTemplate.save(login,"LIS_LOGIN");

	}
}
