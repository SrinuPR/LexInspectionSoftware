package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ShiftMasterDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dto.ShiftMasterDTO;
import com.deloitte.inspection.exception.ShiftMasterException;
import com.deloitte.inspection.model.LISShiftMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;


@Repository
@Transactional
public class ShiftMasterDAOImpl implements ShiftMasterDAO {
private static final Logger logger = LogManager.getLogger(ShiftMasterDAOImpl.class);  
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Autowired
	private SubscriberMasterDAO subcriberDAO;

	
	@Override
	public ShiftMasterDTO createShiftMaster(ShiftMasterDTO shiftMasterDTO, String userId) throws ShiftMasterException {
		logger.info("Entered into createShiftMaster");	
		try {
			LISShiftMaster shiftMaster = new LISShiftMaster();
			shiftMaster.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
			shiftMaster.setShiftId(shiftMasterDTO.getShiftId());
			shiftMaster.setUserId(userId);
			shiftMaster.setShiftName(shiftMasterDTO.getShiftName());
			LISSubscriberMaster subMaster = subcriberDAO.getSubscriberById(shiftMasterDTO.getSubscriberId());
			shiftMaster.setSubscriber(subMaster);
			shiftMaster.setCreatedBy(shiftMasterDTO.getCreatedBy());
			shiftMaster.setCreatedTimestamp(shiftMasterDTO.getCreatedTimestamp());			
			mongoTemplate.save(shiftMaster);
			return shiftMasterDTO;
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}

	@Override
	public LISShiftMaster getShiftId(String shiftId) throws ShiftMasterException {
		logger.info("Entered into getShiftId");	
		Query query = new Query();
		query.addCriteria(new Criteria("shiftId").is(shiftId).and("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
		return mongoTemplate.findOne(query, LISShiftMaster.class, "LIS_SHMCS");
	}

	@Override
	public List<LISShiftMaster> findBySubscriberId(Integer subscriberId) throws ShiftMasterException {
		logger.info("Entered into getAllShifts");	
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
						Aggregation.sort(Sort.Direction.DESC, "createdTimestamp"));
		return mongoTemplate.aggregate(aggregation, "LIS_SHMCS", LISShiftMaster.class).getMappedResults();
	}

	@Override
	public String deleteByShiftId(String shiftId,String userId) throws ShiftMasterException {
		logger.info("Entered into deleteByShiftId");
		String status = StatusConstants.FAILURE;
		Query query = new Query();
		query.addCriteria(new Criteria("shiftId").is(shiftId));
		LISShiftMaster deleted = mongoTemplate.findAndRemove(query, LISShiftMaster.class);
		if(deleted != null){
			status = StatusConstants.SUCCESS;
		}
		return status;	
	}

	@Override
	public ShiftMasterDTO updateShiftMaster(ShiftMasterDTO createShiftMasterDTO) throws ShiftMasterException {
		logger.info("Entered into updateShiftMaster");	
		try {
			LISShiftMaster shiftMaster = this.getShiftId(createShiftMasterDTO.getShiftId());
			shiftMaster.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
			shiftMaster.setShiftId(createShiftMasterDTO.getShiftId());
			shiftMaster.setShiftName(createShiftMasterDTO.getShiftName());
			LISSubscriberMaster subMaster = subcriberDAO.getSubscriberById(createShiftMasterDTO.getSubscriberId());
			shiftMaster.setSubscriber(subMaster);
			shiftMaster.setUpdatedBy(createShiftMasterDTO.getUpdatedBy());
			shiftMaster.setUpdatedTimestamp(createShiftMasterDTO.getUpdatedTimestamp());
			
			if(null == shiftMaster.getCreatedBy()) {
				shiftMaster.setCreatedBy(createShiftMasterDTO.getUpdatedBy());
				shiftMaster.setCreatedTimestamp(createShiftMasterDTO.getUpdatedTimestamp());	
			}
			
			mongoTemplate.save(shiftMaster);			
			return createShiftMasterDTO;
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}

	@Override
	public List<LISShiftMaster> getAllShiftsBySubscriberId(Integer subscriberId) throws ShiftMasterException {
		logger.info("Entered into getAllShifts");	
		return findBySubscriberId(subscriberId);
	}
	
}

