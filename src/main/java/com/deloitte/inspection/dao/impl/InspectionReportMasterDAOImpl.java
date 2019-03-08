package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.DatabaseSequenceDAO;
import com.deloitte.inspection.dao.InspectionReportMasterDAO;
import com.deloitte.inspection.exception.DatabaseSequenceException;
import com.deloitte.inspection.exception.InspectionReportMasterException;
import com.deloitte.inspection.model.DatabaseSequence;
import com.deloitte.inspection.model.LISInspectionReportMaster;

@Repository
@Transactional
public class InspectionReportMasterDAOImpl implements InspectionReportMasterDAO {

	private static final Logger logger = LogManager.getLogger(InspectionReportMasterDAOImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	DatabaseSequenceDAO databaseSequence;

	@Override
	public void saveReport(LISInspectionReportMaster inspRptMatser) throws InspectionReportMasterException {
		try {
			if (inspRptMatser.getInspRptMasterId() == null)
				inspRptMatser.setInspRptMasterId(String.valueOf(databaseSequence.getNextSequenceId("LIS_IRMCS")));
		} catch (DatabaseSequenceException e) {
			e.printStackTrace();
		}
		mongoTemplate.save(inspRptMatser, "LIS_IRMCS");
	}

	@Override
	public LISInspectionReportMaster validateInspReportNumber(Integer inspReportNum)
			throws InspectionReportMasterException {
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("inspReportNumber").in(inspReportNum)
				.andOperator(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		List<LISInspectionReportMaster> list = mongoTemplate.find(query, LISInspectionReportMaster.class, "LIS_IRMCS");
		if (null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<LISInspectionReportMaster> getInspectionReportList(String compDrawNum)
			throws InspectionReportMasterException {
		logger.info("getInspectionReportList DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("compProdDrawNum").in(compDrawNum)
				.andOperator(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		return mongoTemplate.find(query, LISInspectionReportMaster.class, "LIS_IRMCS");
	}

	@Override
	public List<LISInspectionReportMaster> getAllInspectionReportList(String userId)
			throws InspectionReportMasterException {
		logger.info("Entered into getAllInspectionReportList DAO");
		Query query = new Query().with(new Sort(Direction.DESC, "createdTimestamp"));
		query.addCriteria(Criteria.where("userID").in(userId)
				.andOperator(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))));
		return mongoTemplate.find(query, LISInspectionReportMaster.class, "LIS_IRMCS");
	}
}
