package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.LoginDAO;
import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.mapper.LISLoginResult;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISUserMasterCreate;

@Repository
@Transactional
public class LoginDAOImpl implements LoginDAO{
	
	private static final Logger logger = LogManager.getLogger(LoginDAOImpl.class);  
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public LISLoginResult validateLoginCredentials(String userId) throws LoginException {
		logger.info("Entered into validateLoginCredentials" + userId);	
		LookupOperation lookupOperation = LookupOperation.newLookup().from("LIS_SUMAS").localField("subscriberMasterId")
				.foreignField("subscriberId").as("subscriberMaster");
		LookupOperation lookupOperation1 = LookupOperation.newLookup().from("LIS_UMACS").localField("userMasterCreateId")
				.foreignField("userId").as("userMasterCreate");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(new Criteria().orOperator(Criteria.where("userMasterCreateId").is(userId),Criteria.where("adminId").is(userId))),
				lookupOperation,
				lookupOperation1);
		List<LISLoginResult> loginList = mongoTemplate.aggregate(aggregation, "LIS_LOGIN", LISLoginResult.class)
				.getMappedResults();
		if(null != loginList && loginList.size() > 0){
			return loginList.get(0);
		}
		return null;
	}
	
	@Override
	public String changePassword(LISUserMasterCreate userMasterModel) throws LoginException {
		logger.info("Inside ChangePasswordDAOImpl");
		String status = new String();
		if (null != userMasterModel) {
			mongoTemplate.save(userMasterModel);
		}
		if (userMasterModel.getUserId() != null) {
			status = StatusConstants.SUCCESS;
		} else {
			status = StatusConstants.FAILURE;
		}
		return status;
	}

	@Override
	public LISUserMasterCreate validateUser(String userId) throws LoginException {
		logger.info("Inside LoginDAOImpl validateUser");
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").in(userId));
		List<LISUserMasterCreate> userList = mongoTemplate.find(query, LISUserMasterCreate.class, "LIS_UMACS");
		if (null != userList && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public void updateLoginPassword(String userId, String password) throws LoginException {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(new Criteria().orOperator(Criteria.where("user.userId").is(userId),Criteria.where("adminId").is(userId)))
				);
		LISLogin lisLogin = mongoTemplate.aggregate(aggregation, "LIS_LOGIN", LISLogin.class).getUniqueMappedResult();
		lisLogin.setPassword(password);
		mongoTemplate.save(lisLogin);
	}

	@Override
	public void logout(String userId) throws LoginException {
		logger.info("Inside LoginDAOImpl logout");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(new Criteria().orOperator(Criteria.where("user.userId").is(userId),Criteria.where("adminId").is(userId)))
				);
		List<LISLogin> list = mongoTemplate.aggregate(aggregation, "LIS_LOGIN", LISLogin.class).getMappedResults();
		for (LISLogin lisLogin: list) {
			lisLogin.setIsSessionActive(String.valueOf(StatusConstants.IN_ACTIVE));
			mongoTemplate.save(lisLogin);
		}
	}

	@Override
	public void updateSessionActiveSwToN() throws LoginException {
		logger.info("Inside LoginDAOImpl updateSessionActiveSwToN");
		Query query = new Query();
		query.addCriteria(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
		List<LISLogin> lisLoginList = mongoTemplate.find(query, LISLogin.class,"LIS_LOGIN");
		for(LISLogin lisLogin : lisLoginList){
			lisLogin.setIsSessionActive(String.valueOf(StatusConstants.IN_ACTIVE));
			mongoTemplate.save(lisLogin);
		}	
	}

	@Override
	public void updateLogin(LISLogin login) throws LoginException {
		logger.info("Inside LoginDAOImpl updateLogin");
		mongoTemplate.save(login);
	}	
}
