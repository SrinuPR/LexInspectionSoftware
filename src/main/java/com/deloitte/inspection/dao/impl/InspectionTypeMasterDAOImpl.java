/**
 * 
 */
package com.deloitte.inspection.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.InspectionTypeMasterDAO;
import com.deloitte.inspection.dto.InspectionTypeMasterDTO;
import com.deloitte.inspection.exception.InspectionTypeMasterException;
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
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    /* (non-Javadoc)
     * @see com.deloitte.inspection.dao.InspectionTypeMasterDAO#getInspTypeId(java.lang.Integer)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LISInspectionTypeMaster getInspTypeId(Integer inspTypeId) throws InspectionTypeMasterException {
		logger.info("Entered into getInspTypeId");	
		Query query = getSession().createQuery(" From LISInspectionTypeMaster ITMCS where ITMCS.inspTypeId = :inspTypeId");
		query.setParameter("inspTypeId", inspTypeId);
		List<LISInspectionTypeMaster> inspTypeList = query.list();
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
		try {
			LISInspectionTypeMaster inspTypeMaster = new LISInspectionTypeMaster();
			inspTypeMaster.setCreatedBy(inspTypeMasterDTO.getCreatedBy());
			inspTypeMaster.setUserId(userId);
			inspTypeMaster.setCreatedTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
			inspTypeMaster.setInspTypeId(inspTypeMasterDTO.getInspTypeId());
			inspTypeMaster.setInspTypeName(inspTypeMasterDTO.getInspTypeName());
			LISSubscriberMaster subMaster = new LISSubscriberMaster();
			subMaster.setSubscriberId(inspTypeMasterDTO.getSubscriberId());
			inspTypeMaster.setSubscriberMaster(subMaster);
			inspTypeMaster.setIsActive(StatusConstants.IS_ACTIVE);
			Integer value = (Integer) getSession().save(inspTypeMaster);
			if(value != null)
				return inspTypeMasterDTO;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		} 
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.InspectionTypeMasterDAO#getAllInspTypeMasterData()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionTypeMaster> getAllInspTypeMasterData(Integer subscriberId) throws InspectionTypeMasterException {
		logger.info("Entered into getAllInspTypeMasterData");	
		Query query = getSession().createQuery(" From LISInspectionTypeMaster ITMCS WHERE ITMCS.subscriberMaster.subscriberId = :subscriberId  and ITMCS.isActive = :isActive ORDER BY ITMCS.createdTimestamp DESC");
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISInspectionTypeMaster> list = query.list();
		return list;
	}
}
