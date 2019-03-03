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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.FacilitiesMasterDAO;
import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.exception.FacilityMasterException;
import com.deloitte.inspection.model.LISFacilityMaster;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISSubscriberMaster;

/**
 * @author rnarne
 *
 */
@Repository
@Transactional
public class FacilitiesMasterDAOImpl implements FacilitiesMasterDAO {

	private static final Logger logger = LogManager.getLogger(FacilitiesMasterDAOImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * @param facilityNum
	 * @return
	 * @throws FacilityMasterException
	 */
	@Override
	public LISFacilityMaster getFacilityNumber(String facilityNum) throws FacilityMasterException {
		logger.info("Entered into getFacilityNumber");
		Query query = new Query();
		query.addCriteria(Criteria.where("facilityNumber").in(facilityNum));
		List<LISFacilityMaster> facilitiesMasterList = mongoTemplate.find(query, LISFacilityMaster.class,"LIS_FMACS");
		if (null != facilitiesMasterList && facilitiesMasterList.size() > 0) {
			return facilitiesMasterList.get(0);
		}
		return null;
	}

	/**
	 * @param facilityMasterDTO
	 * @return
	 * @throws FacilityMasterException
	 */
	@Override
	public FacilityMasterDTO createFacility(FacilityMasterDTO facilityMasterDTO, String userName)
			throws FacilityMasterException {
		logger.info("Entered into createFacility");
		try {
			LISFacilityMaster facilityMaster = new LISFacilityMaster();
			facilityMaster.setCreatedBy(userName);
			facilityMaster.setCreatedTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
			facilityMaster.setFacilityNumber(facilityMasterDTO.getFacilityNumber());
			facilityMaster.setFacilityName(facilityMasterDTO.getFacilityName());
			facilityMaster.setUserId(userName);
			LISSubscriberMaster subMaster = new LISSubscriberMaster();
			subMaster.setSubscriberId(facilityMasterDTO.getSubscriberId());
			mongoTemplate.save(subMaster,"LIS_SUMAS");
			facilityMaster.setSubscriber(subMaster);
			facilityMaster.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
			mongoTemplate.save(facilityMaster,"LIS_FMACS");
			if (facilityMaster.getFacilityId() != null)
				return facilityMasterDTO;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		return null;
	}

	/**
	 * @return
	 * @throws FacilityMasterException
	 */
	@Override
	public List<LISFacilityMaster> getFacilitiesMasterData(Integer subscriberId) throws FacilityMasterException {
		logger.info("Entered into getFacilitiesMasterData");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		List<LISFacilityMaster> list = mongoTemplate
				.aggregate(aggregation, "LIS_FMACS", LISFacilityMaster.class).getMappedResults();
		return list;
	}

	@Override
	public List<LISFacilityMaster> getFacilityDetailsBySubscriberID(Integer subscriberId)
			throws FacilityMasterException {
		logger.info("Entered into getFacilityDetailsBySubscriberID DAO");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		return mongoTemplate.aggregate(aggregation, "LIS_FMACS", LISFacilityMaster.class).getMappedResults();
	}
}
