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
import com.deloitte.inspection.dao.AccessMasterDAO;
import com.deloitte.inspection.model.LISAccessMaster;
import com.deloitte.inspection.model.LISUserTypeMaster;

@Repository
@Transactional
public class AccessMasterDAOImpl implements AccessMasterDAO{
	
	private static final Logger logger = LogManager.getLogger(AccessMasterDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public void saveAccessMaster(LISAccessMaster lisAccessMaster) throws Exception {
		getSession().saveOrUpdate(lisAccessMaster);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
	public LISAccessMaster getAccessScreens(Integer subscriberId, Integer userTypId) throws Exception {
		logger.info("Entered into getAccessScreens");
		if (null != subscriberId && null != userTypId) {
			logger.info("Fetching data on basis of subdId and UserTpyeId");
			Query query = getSession().createQuery(" From LISAccessMaster LACM where LACM.subsId = :subsId AND LACM.userTypeId = :userTypeId");
			query.setInteger("subsId", subscriberId);
			query.setInteger("userTypeId", userTypId);
			List<LISAccessMaster> accessMasterScreenList = query.list();
			if(null != accessMasterScreenList && accessMasterScreenList.size() > 0) {
				return accessMasterScreenList.get(0);
			}
		} else if (null != subscriberId) {
			logger.info("Fetching data on basis of subdId");
			Query query = getSession().createQuery(" From LISAccessMaster LACM where LACM.subsId = :subsId");
			query.setInteger("subsId", subscriberId);
			List<LISAccessMaster> accessMasterScreenList = query.list();
			if(null != accessMasterScreenList && accessMasterScreenList.size() > 0) {
				return accessMasterScreenList.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LISUserTypeMaster> getUserTypeListforSubscriber(Integer subscriberId) throws Exception {
		logger.info("inside getUserTypeListforSubscriber DAO");
		Query query = getSession().createQuery(" From LISUserTypeMaster l where l.subscriberMaster.subscriberId = :subscriberId and l.isActive = :isActive order by l.userTypeName ASC");
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return query.list();
	}

	@Override
	public LISAccessMaster getAccessMaster(Integer accessMasterId) throws Exception {
		logger.info("Inside getAccessMaster DAO");
		Session session = getSession();
		LISAccessMaster accessMaster = (LISAccessMaster)session.get(LISAccessMaster.class,accessMasterId);
		return accessMaster;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public LISAccessMaster getAccessMasterByUserTypeId(Integer userTypeId) throws Exception {
		logger.info("inside getAccessMasterByUserTypeId DAO");
		Query query = getSession().createQuery(" From LISAccessMaster l where l.userTypeMaster.userTypeId = :userTypeId and l.isActive = :isActive");
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		@SuppressWarnings("unchecked")
		List<LISAccessMaster> master = query.list();
		if(null != master && master.size() > 0){
			return master.get(0);
		}
		return null;
	}
}
