package com.deloitte.inspection.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISStaticUserType;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;

@Repository
@Transactional
public class CreateUserDAOImpl implements CreateUserDAO{
private static final Logger logger = LogManager.getLogger(CreateUserDAOImpl.class);  
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private SubscriberMasterDAO subscriberMasterDAO;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public CreateUserDTO createUser(CreateUserDTO createuserDTO) throws CreateUserException {
		logger.info("Entered into createUser");	
		try {
			LISSubscriberMaster subscriberMaster=new LISSubscriberMaster();
			subscriberMaster = subscriberMasterDAO.validateSubscriber(createuserDTO.getSubscriberId());
			LISUserMasterCreate userMaster=new LISUserMasterCreate();
			userMaster.setActivePassword(createuserDTO.getPassword());
			userMaster.setCreatedBy(createuserDTO.getUserName());
			userMaster.setCreatedTimestamp(new Date());
			userMaster.setOldPassword1(null);
			userMaster.setOldPassword2(null);
			userMaster.setSubscriberMaster(subscriberMaster);
			userMaster.setUserId(createuserDTO.getUserId());
			userMaster.setUserName(createuserDTO.getUserName());
			userMaster.setUserTypeId(createuserDTO.getUserTypeId());
			LISLogin lisLogin=new LISLogin();
			lisLogin.setCreatedBy(createuserDTO.getUserName());
			lisLogin.setCreatedTimestamp(new Date());
			lisLogin.setPassword(createuserDTO.getPassword());
			lisLogin.setUserMasterCreate(userMaster);
			lisLogin.setSubscriberMaster(subscriberMaster);
			
			getSession().save(userMaster);
			
				return createuserDTO;
		} catch (HibernateException | SubscriberMasterException ex) {
			//if(trans != null)
				//trans.rollback();
		} 
		return null;
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public CreateUserDTO fetchData() throws CreateUserException {
		logger.info("Entered into fetchData");	
		CreateUserDTO createuserDTO=new CreateUserDTO();
		Map<Integer,String> subscriberMap=new HashMap<Integer,String>();
		Map<Integer,String> userTypeMap=new HashMap<Integer,String>();
		Query query = getSession().createQuery(" From LISSubscriberMaster SUMAS ");
		List<LISSubscriberMaster> subscriberList = query.list();
		if(null != subscriberList && subscriberList.size() > 0) {
			for(LISSubscriberMaster subscriber:subscriberList) {
				subscriberMap.put(subscriber.getSubscriberId(), subscriber.getSubscriberName());
			}
		}
		
		query = getSession().createQuery(" From LISStaticUserType USERTYP WHERE ACTIVE_SW='Y' ");
		List<LISStaticUserType> usertypes = query.list();
		if(null != usertypes && usertypes.size() > 0) {
			for(LISStaticUserType userType:usertypes) {
				userTypeMap.put(userType.getUserTypeId(), userType.getUserTypeName());
			}
		}
		
		createuserDTO.setSubscriberMap(subscriberMap);
		createuserDTO.setUserTypeMap(userTypeMap);
		
		return createuserDTO;
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public LISUserMasterCreate validateUserId(String userId) throws CreateUserException {
		logger.info("Entered into validateUserId");	
		LISUserMasterCreate userMasterCreate=null;
		Query query = getSession().createQuery(" From LISUserMasterCreate UMACS where UMACS.userId = :userId");
		query.setParameter("userId", userId);
		List<LISUserMasterCreate> userMaster = query.list();
		
		if(null != userMaster && userMaster.size() > 0) {
			userMasterCreate= userMaster.get(0);
		}
		return userMasterCreate;
	}
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LISUserMasterCreate getByUserID(String userId) {
		logger.info("Entered into getByUserName");
		LISUserMasterCreate userMaster = null;
		Query query = getSession().createQuery(" From LISSubscriberMaster SUMAS where SUMAS.userName = :userName");
		query.setParameter("userName", userId);
		List<LISUserMasterCreate> userMasterlist = query.list();
		if (null != userMasterlist && userMasterlist.size() > 0) {
			userMaster = userMasterlist.get(0);
		}
		return userMaster;
	}
}
