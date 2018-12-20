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
import com.deloitte.inspection.dao.InspectionStageMasterDAO;
import com.deloitte.inspection.dto.InspectionStageMasterDTO;
import com.deloitte.inspection.exception.InspectionStageMasterException;
import com.deloitte.inspection.model.LISInspectionStageMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;

/**
 * @author rnarne
 *
 */
@Repository
@Transactional
public class InspectionStageMasterDAOImpl implements InspectionStageMasterDAO{

private static final Logger logger = LogManager.getLogger(InspectionStageMasterDAOImpl.class);  
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    /* (non-Javadoc)
     * @see com.deloitte.inspection.dao.InspectionStageMasterDAO#getInspSatgeId(java.lang.Integer)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LISInspectionStageMaster getInspSatgeId(Integer inspStageId) throws InspectionStageMasterException {
		logger.info("Entered into getInspSatgeId");	
		Query query = getSession().createQuery(" From LISInspectionStageMaster ICMCS where ICMCS.inspStageId = :inspStageId");
		query.setParameter("inspStageId", inspStageId);
		List<LISInspectionStageMaster> inspStageList = query.list();
		if(null != inspStageList && inspStageList.size() > 0) {
			return inspStageList.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.InspectionStageMasterDAO#createInspectionStage(com.deloitte.inspection.dto.InspectionStageMasterDTO)
	 */
	@Override
	public InspectionStageMasterDTO createInspectionStage(InspectionStageMasterDTO inspTypeMasterDTO,String userId) throws InspectionStageMasterException {
		logger.info("Entered into createInspectionType");	
		try {
			LISInspectionStageMaster inspStageMaster = new LISInspectionStageMaster();
			inspStageMaster.setUserId(userId);
			inspStageMaster.setCreatedBy(inspTypeMasterDTO.getCreatedBy());
			inspStageMaster.setCreatedTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
			inspStageMaster.setInspStageId(inspTypeMasterDTO.getInspStageId());
			inspStageMaster.setInspStageName(inspTypeMasterDTO.getInspStageName());
			LISSubscriberMaster subMaster = new LISSubscriberMaster();
			subMaster.setSubscriberId(inspTypeMasterDTO.getSubscriberId());
			inspStageMaster.setSubscriberMaster(subMaster);
			inspStageMaster.setIsActive(StatusConstants.IS_ACTIVE);
			LISInspectionStageMaster value = (LISInspectionStageMaster)getSession().save(inspStageMaster);		
			if(value != null)
				return inspTypeMasterDTO;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		} 
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.InspectionStageMasterDAO#getAllInspTypeMasterData()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISInspectionStageMaster> getAllInspStageMasterData(Integer subscriberId) throws InspectionStageMasterException {
		logger.info("Entered into getAllInspStageMasterData");	
		Query query = getSession().createQuery(" From LISInspectionStageMaster ICMCS WHERE ICMCS.subscriberMaster.subscriberId = :subscriberId and ICMCS.isActive = :isActive ORDER BY ICMCS.createdTimestamp DESC");
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISInspectionStageMaster> list = query.list();
		return list;
	}
}
