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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.InspectionTypeMasterDAO;
import com.deloitte.inspection.dto.InspectionTypeMasterDTO;
import com.deloitte.inspection.exception.InspectionTypeMasterException;
import com.deloitte.inspection.mapper.LISInspectionTypeMasterResult;
import com.deloitte.inspection.model.LISInspectionTypeMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;

/**
 * @author rnarne
 *
 */
@Repository
@Transactional
public class InspectionTypeMasterDAOImpl implements InspectionTypeMasterDAO{
private static final Logger logger = LogManager.getLogger(InspectionTypeMasterDAOImpl.class);  
	
	@Autowired
	MongoTemplate mongoTemplate;   
    /* (non-Javadoc)
     * @see com.deloitte.inspection.dao.InspectionTypeMasterDAO#getInspTypeId(java.lang.Integer)
     */
	@Override
	public LISInspectionTypeMaster getInspTypeId(Integer inspTypeId) throws InspectionTypeMasterException {
		logger.info("Entered into getInspTypeId");	
		Query query = new Query();
		query.addCriteria(Criteria.where("inspTypeId").in(inspTypeId));
		List<LISInspectionTypeMaster> inspTypeList = mongoTemplate.find(query, LISInspectionTypeMaster.class,"LIS_ITMCS");
		if(null != inspTypeList && inspTypeList.size() > 0) {
			return inspTypeList.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.InspectionTypeMasterDAO#createInspectionType(com.deloitte.inspection.dto.InspectionTypeMasterDTO)
	 */
	@Override
	public InspectionTypeMasterDTO createInspectionType(InspectionTypeMasterDTO inspTypeMasterDTO, String userId) throws InspectionTypeMasterException {
		logger.info("Entered into createInspectionType");
		LISInspectionTypeMaster inspTypeMaster = new LISInspectionTypeMaster();
		inspTypeMaster.setCreatedBy(inspTypeMasterDTO.getCreatedBy());
		inspTypeMaster.setUserId(userId);
		inspTypeMaster.setCreatedTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
		inspTypeMaster.setInspTypeId(inspTypeMasterDTO.getInspTypeId());
		inspTypeMaster.setInspTypeName(inspTypeMasterDTO.getInspTypeName());
		LISSubscriberMaster subMaster = new LISSubscriberMaster();
		subMaster.setSubscriberId(String.valueOf(inspTypeMasterDTO.getSubscriberId()));
		mongoTemplate.save(subMaster);
		inspTypeMaster.setSubscriberMasterId(subMaster.getSubscriberId());
		inspTypeMaster.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
		mongoTemplate.save(inspTypeMaster);
		if (inspTypeMaster.getInspTypeId() != null)
			return inspTypeMasterDTO;
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.InspectionTypeMasterDAO#getAllInspTypeMasterData()
	 */
	@Override
	public List<LISInspectionTypeMasterResult> getAllInspTypeMasterData(Integer subscriberId) throws InspectionTypeMasterException {
		logger.info("Entered into getAllInspTypeMasterData");
		LookupOperation lookupOperation = LookupOperation.newLookup().from("LIS_SUMAS").localField("subscriberMasterId")
				.foreignField("subscriberId").as("subscriberMaster");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriberMasterId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
				Aggregation.sort(Direction.DESC, "createdTimestamp"));
		List<LISInspectionTypeMasterResult> list = mongoTemplate.aggregate(aggregation, "LIS_ITMCS", LISInspectionTypeMasterResult.class)
				.getMappedResults();
		return list;
	}
}
