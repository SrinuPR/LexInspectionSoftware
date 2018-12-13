package com.deloitte.inspection.dao.impl;

import java.util.List;

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
}
