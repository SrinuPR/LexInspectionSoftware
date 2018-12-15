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
import com.deloitte.inspection.dao.MasterListOfScreensForSubscriberDAO;
import com.deloitte.inspection.exception.MasterListOfScreensForSubscriberException;
import com.deloitte.inspection.model.LISMasterListOfScreensForSubscriber;

@Repository
@Transactional
public class MasterListOfScreensForSubscriberDAOImpl implements MasterListOfScreensForSubscriberDAO{

	private static final Logger logger = LogManager.getLogger(MasterListOfScreensForSubscriberDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public void deleteScreenForSubscriber(Integer subscriberId, String screenId) throws MasterListOfScreensForSubscriberException {
		logger.info("Inside deleteScreenForSubscriber DAO");
		Query query = getSession().createSQLQuery("DELETE FROM LIS_MLOSS WHERE IS_ACTIVE = :isActive AND SUBSCRIBER_ID = :subscriberId AND lower(SCREEN_NUMBER) = :screenNumber");
		query.setParameter("screenNumber", screenId.toLowerCase());
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.executeUpdate();
	}

	@Override
	public void insertScreensForSubscribers(List<LISMasterListOfScreensForSubscriber> insertList)
			throws MasterListOfScreensForSubscriberException {
		logger.info("Inside insertScreensForSubscribers DAO");
		Session session = getSession();
		for(LISMasterListOfScreensForSubscriber masterScreen : insertList){
			session.saveOrUpdate(masterScreen);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISMasterListOfScreensForSubscriber> getScreensforSubscriber(Integer subscriberId)
			throws MasterListOfScreensForSubscriberException {
		logger.info("Inside getScreensforSubscriber DAO");
		Query query = getSession().createQuery("FROM LISMasterListOfScreensForSubscriber l where l.isActive = :isActive and l.subscriberId = :subscriberId ");
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return query.list();
	}
}
