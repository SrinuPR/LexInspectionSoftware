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
import com.deloitte.inspection.dao.InspectionStageMasterDAO;
import com.deloitte.inspection.dto.InspectionStageMasterDTO;
import com.deloitte.inspection.exception.InspectionStageMasterException;
import com.deloitte.inspection.model.LISInspectionMaster;
import com.deloitte.inspection.model.LISInspectionStageMaster;
import com.deloitte.inspection.model.LISPartIdentification;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.mongodb.WriteResult;

/**
 * @author rnarne
 *
 */
@Repository
@Transactional
public class InspectionStageMasterDAOImpl implements InspectionStageMasterDAO {

	private static final Logger logger = LogManager.getLogger(InspectionStageMasterDAOImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deloitte.inspection.dao.InspectionStageMasterDAO#getInspSatgeId(java.lang
	 * .Integer)
	 */
	@Override
	public LISInspectionStageMaster getInspSatgeId(Integer inspStageId) throws InspectionStageMasterException {
		logger.info("Entered into getInspSatgeId");
		Query query = new Query();
		query.addCriteria(Criteria.where("inspStageId").in(inspStageId));
		List<LISInspectionStageMaster> inspStageList = mongoTemplate.find(query, LISInspectionStageMaster.class,
				"LIS_ISMCS");
		if (null != inspStageList && inspStageList.size() > 0) {
			return inspStageList.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deloitte.inspection.dao.InspectionStageMasterDAO#createInspectionStage(
	 * com.deloitte.inspection.dto.InspectionStageMasterDTO)
	 */
	@Override
	public InspectionStageMasterDTO createInspectionStage(InspectionStageMasterDTO inspTypeMasterDTO, String userId)
			throws InspectionStageMasterException {
		logger.info("Entered into createInspectionType");
		LISInspectionStageMaster inspStageMaster = new LISInspectionStageMaster();
		inspStageMaster.setUserId(userId);
		inspStageMaster.setCreatedBy(inspTypeMasterDTO.getCreatedBy());
		inspStageMaster.setCreatedTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
		inspStageMaster.setInspStageId(inspTypeMasterDTO.getInspStageId());
		inspStageMaster.setInspStageName(inspTypeMasterDTO.getInspStageName());
		LISSubscriberMaster subMaster = new LISSubscriberMaster();
		subMaster.setSubscriberId(inspTypeMasterDTO.getSubscriberId());
		mongoTemplate.save(subMaster, "LIS_SUMAS");
		inspStageMaster.setSubscriber(subMaster);
		inspStageMaster.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
		mongoTemplate.save(inspStageMaster, "LIS_ISMCS");
		if (inspStageMaster.getInspStageId() != null)
			return inspTypeMasterDTO;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deloitte.inspection.dao.InspectionStageMasterDAO#getAllInspTypeMasterData
	 * ()
	 */
	@Override
	public List<LISInspectionStageMaster> getAllInspStageMasterData(Integer subscriberId)
			throws InspectionStageMasterException {
		logger.info("Entered into getAllInspStageMasterData");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriber.subscriberId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		List<LISInspectionStageMaster> list = mongoTemplate
				.aggregate(aggregation, "LIS_ISMCS", LISInspectionStageMaster.class).getMappedResults();
		return list;
	}
}
