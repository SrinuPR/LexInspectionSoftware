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
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    /* (non-Javadoc)
     * @see com.deloitte.inspection.dao.SubscriberMasterDAO#validateSubscriber(java.lang.Integer)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LISSubscriberMaster validateSubscriber(Integer subscriberId) throws SubscriberMasterException {
		logger.info("Entered into validateSubscriber");	
		Query query = getSession().createQuery(" From LISSubscriberMaster SUMAS where SUMAS.subscriberId = :subscriberId");
		query.setParameter("subscriberId", subscriberId);
		List<LISSubscriberMaster> subscriberList = query.list();
		if(null != subscriberList && subscriberList.size() > 0) {
			return subscriberList.get(0);
		}
		return null;
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
			Integer value = (Integer) getSession().save(subMasterModel);
			if(value != null)
				return subMasterDTO;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		} 
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.SubscriberMasterDAO#getSubscriberById(java.lang.Integer)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISSubscriberMaster getSubscriberById(Integer subscriberId) throws SubscriberMasterException {
		logger.info("Entered into validateLoginCredentials");	
		Query query = getSession().createQuery(" From LISSubscriberMaster l where l.subscriberId = :subscriberId");
		query.setParameter("subscriberId", subscriberId);
		List<LISSubscriberMaster> subscriberMasterlist = query.list();
		if(null != subscriberMasterlist && subscriberMasterlist.size() > 0)
			return subscriberMasterlist.get(0);
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.SubscriberMasterDAO#getAllSubscriberMasterData()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISSubscriberMaster> getAllSubscriberMasterData() throws SubscriberMasterException {
		logger.info("Entered into getAllSubscriberMasterData");	
		Query query = getSession().createQuery(" From LISSubscriberMaster SUMAS ORDER BY SUMAS.createdTimestamp DESC");
		List<LISSubscriberMaster> list = query.list();
		return list;
	}
}
