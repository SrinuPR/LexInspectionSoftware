/**
 * 
 */
package com.deloitte.inspection.dao.impl;

import java.math.BigInteger;
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
import com.deloitte.inspection.dto.SubscriberMasterDTO;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISSubscriberMaster;

/**
 * @author rnarne
 *
 */
@Repository
@Transactional
public class SubscriberMasterDAOImpl implements SubscriberMasterDAO{
	
	private static final Logger logger = LogManager.getLogger(SubscriberMasterDAOImpl.class);  
	
	@Autowired
	MongoTemplate mongoTemplate;
    
    /* (non-Javadoc)
     * @see com.deloitte.inspection.dao.SubscriberMasterDAO#validateSubscriber(java.lang.Integer)
     */
	@Override
	public LISSubscriberMaster validateSubscriber(Integer subscriberId) throws SubscriberMasterException {
		logger.info("Entered into validateSubscriber");	
		return getSubscriberById(subscriberId);
	}

	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.SubscriberMasterDAO#createSubscriber(com.deloitte.inspection.dto.SubscriberMasterDTO)
	 */
	@Override
	public SubscriberMasterDTO createSubscriber(SubscriberMasterDTO subMasterDTO) throws SubscriberMasterException {
		logger.info("Entered into createSubscriber");	
		try {
			LISSubscriberMaster subMasterModel = new LISSubscriberMaster();
			subMasterModel.setCreatedBy(subMasterDTO.getCreatedBy());
			subMasterModel.setCreatedTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
			subMasterModel.setSubscriberId(subMasterDTO.getSubscriberId());
			subMasterModel.setSubscriberName(subMasterDTO.getSubscriberName());
			subMasterModel.setSubscriberAddress(subMasterDTO.getSubscriberAddress());
			subMasterModel.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
			mongoTemplate.save(subMasterModel);
			return subMasterDTO;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		} 
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.SubscriberMasterDAO#getSubscriberById(java.lang.Integer)
	 */
	@Override
	public LISSubscriberMaster getSubscriberById(Integer subscriberId) throws SubscriberMasterException {
		logger.info("Entered into validateLoginCredentials " + subscriberId);	
		Query query = new Query();
		query.addCriteria(new Criteria("subscriberId").is(subscriberId));
		return mongoTemplate.findOne(query, LISSubscriberMaster.class, "LIS_SUMAS");
	}
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.SubscriberMasterDAO#getAllSubscriberMasterData()
	 */
	@Override
	public List<LISSubscriberMaster> getAllSubscriberMasterData() throws SubscriberMasterException {
		logger.info("Entered into getAllSubscriberMasterData");
		Query query = new Query();
		query.with(new Sort(Sort.Direction.ASC, "subscriberId"));
		return mongoTemplate.find(query, LISSubscriberMaster.class);
	}
	
	@Override
	public List<LISSubscriberMaster> getSubscriberData(String userId) throws SubscriberMasterException {
		logger.info("Entered into getSubscriberData");
		Query query = new Query();
		query.addCriteria(new Criteria("createdBy").is(userId));
		query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
		return mongoTemplate.find(query, LISSubscriberMaster.class);
	}
}
