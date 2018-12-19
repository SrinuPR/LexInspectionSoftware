package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.InspectionReportMasterDAO;
import com.deloitte.inspection.exception.InspectionReportMasterException;
import com.deloitte.inspection.model.LISInspectionReportMaster;

@Repository
@Transactional
public class InspectionReportMasterDAOImpl implements InspectionReportMasterDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(InspectionReportMasterDAOImpl.class);

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
	@Override
	public void saveReport(LISInspectionReportMaster inspRptMatser) throws InspectionReportMasterException {
		Session session = getSession();
		session.save(inspRptMatser);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LISInspectionReportMaster validateInspReportNumber(Integer inspReportNum)
			throws InspectionReportMasterException {
		Query query = getSession().createQuery(" From LISInspectionReportMaster rpt where rpt.inspReportNumber = :inspReportNum and rpt.isActive = :isActive ORDER BY rpt.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("inspReportNum", inspReportNum);
		List<LISInspectionReportMaster> list = query.list();
		if(null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionReportMaster> getInspectionReportList(String compDrawNum)
			throws InspectionReportMasterException {
		logger.info("getInspectionReportList DAO");
		Query query = getSession().createQuery("FROM LISInspectionReportMaster i where i.compProdDrawNum = :compDrawNum and i.isActive = :isActive ORDER BY i.createdTimestamp DESC ");
		query.setParameter("compDrawNum", compDrawNum);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return query.list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionReportMaster> getAllInspectionReportList(String userId)
			throws InspectionReportMasterException {
		logger.info("Entered into getAllInspectionReportList DAO");	
		Query query = getSession().createQuery(" From LISInspectionReportMaster l where l.userID = :userId and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("userId", userId);
		return query.list();
	}
}
