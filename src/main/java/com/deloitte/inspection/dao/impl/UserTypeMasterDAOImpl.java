/**
 * 
 */
package com.deloitte.inspection.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dao.UserTypeMasterDAO;
import com.deloitte.inspection.dto.UserTypeMasterDTO;
import com.deloitte.inspection.exception.UserTypeMasterException;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserTypeMaster;

/**
 * @author rnarne
 *
 */
@Repository
@Transactional
public class UserTypeMasterDAOImpl implements UserTypeMasterDAO {

	
	private static final Logger logger = LogManager.getLogger(UserTypeMasterDAOImpl.class);  
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	SubscriberMasterDAO subscriberDAO;
    
    
    /* (non-Javadoc)
     * @see com.deloitte.inspection.dao.UserTypeMasterDAO#validateUserType(java.lang.Integer)
     */
	@Override
	public LISUserTypeMaster validateUserType(UserTypeMasterDTO userTypeMasDTO) throws UserTypeMasterException {
		logger.info("Entered into validateUserType");
		Criteria criteria = new Criteria();
		if(userTypeMasDTO.getUserTypeId() != null && userTypeMasDTO.getUserTypeName() != null 
				&& !userTypeMasDTO.getUserTypeName().equals("")) {
	        criteria.andOperator(Criteria.where("userTypeId").is(userTypeMasDTO.getUserTypeId()),
	        					 Criteria.where("userTypeName").is(userTypeMasDTO.getUserTypeName()));
	        Query query = new Query(criteria);
	        return mongoTemplate.findOne(query, LISUserTypeMaster.class, "LIS_UTMCS");
		} else if (userTypeMasDTO.getUserTypeId() != null) {
			criteria.andOperator(Criteria.where("userTypeId").is(userTypeMasDTO.getUserTypeId()));
			Query query = new Query(criteria);
			return mongoTemplate.findOne(query, LISUserTypeMaster.class, "LIS_UTMCS");
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.UserTypeMasterDAO#createUserTypeMaster(com.deloitte.inspection.dto.UserTypeMasterDTO)
	 */
	@Override
	public UserTypeMasterDTO createUserTypeMaster(UserTypeMasterDTO userTypeMasDTO) throws UserTypeMasterException {
		logger.info("Entered into createUserTypeMaster");	
		try {
			LISUserTypeMaster userTypeMasModel = new LISUserTypeMaster();
			userTypeMasModel.setCreatedBy(userTypeMasDTO.getCreatedBy());
			userTypeMasModel.setCreatedTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
			userTypeMasModel.setUserTypeId(userTypeMasDTO.getUserTypeId());
			userTypeMasModel.setUserTypeName(userTypeMasDTO.getUserTypeName());
			userTypeMasModel.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
			LISSubscriberMaster subMaster = subscriberDAO.getSubscriberById(userTypeMasDTO.getSubscriberId());
			userTypeMasModel.setSubscriber(subMaster);
			mongoTemplate.save(userTypeMasModel);
			return userTypeMasDTO;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		} 
		return null;
	}


	@Override
	public LISUserTypeMaster getByUserTypeId(Integer userTypeId) throws UserTypeMasterException {
		logger.info("Inside getByUserTypeId DAO");
		Query query = new Query();
		query.addCriteria(new Criteria("userTypeId").is(userTypeId));
		LISUserTypeMaster master = mongoTemplate.findOne(query, LISUserTypeMaster.class, "LIS_UTMCS");
		return master;
	}
}
