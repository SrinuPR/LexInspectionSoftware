package com.deloitte.inspection.dao.impl;

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

import com.deloitte.inspection.component.ApplicationProperties;
import com.deloitte.inspection.component.CryptoComponent;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.exception.CryptoException;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISUserTypeMaster;

@Repository
@Transactional
public class CreateUserDAOImpl implements CreateUserDAO{
private static final Logger logger = LogManager.getLogger(CreateUserDAOImpl.class);  
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private SubscriberMasterDAO subscriberMasterDAO;
	
	@Autowired
	private CryptoComponent cryptoComponent;
	
	@Autowired
	private ApplicationProperties applicationProperties;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String createUser(CreateUserDTO createuserDTO) throws CreateUserException {
		logger.info("Entered into createUser");	
		try {
			Query query = getSession().createQuery(" From LISUserMasterCreate UMACS where UMACS.subscriberMaster.subscriberId = :subscriberId and UMACS.isActive = :isActive ");
			query.setParameter("subscriberId", createuserDTO.getSubscriberId());
			query.setParameter("isActive", StatusConstants.IS_ACTIVE);
			List<LISUserMasterCreate> userMasterList = query.list();
			if(null != userMasterList && userMasterList.size() >= applicationProperties.EACH_SUBSCRIBER_USER_COUNT){
				return StatusConstants.FAILURE;
			}
			LISSubscriberMaster subscriberMaster=new LISSubscriberMaster();
			subscriberMaster = subscriberMasterDAO.validateSubscriber(createuserDTO.getSubscriberId());
			LISUserMasterCreate userMaster=new LISUserMasterCreate();
			userMaster.setActivePassword(cryptoComponent.encrypt(createuserDTO.getPassword()));
			userMaster.setCreatedBy(createuserDTO.getUserName());
			userMaster.setCreatedTimestamp(new Date());
			userMaster.setOldPassword1(null);
			userMaster.setOldPassword2(null);
			userMaster.setSubscriberMaster(subscriberMaster);
			userMaster.setUserId(createuserDTO.getUserId());
			userMaster.setUserName(createuserDTO.getCreatedBy());
			userMaster.setUserTypeId(createuserDTO.getUserTypeId());
			userMaster.setIsActive(StatusConstants.IS_ACTIVE);
			LISLogin lisLogin=new LISLogin();
			lisLogin.setCreatedBy(createuserDTO.getCreatedBy());
			lisLogin.setCreatedTimestamp(new Date());
			lisLogin.setPassword(cryptoComponent.encrypt(createuserDTO.getPassword()));
			lisLogin.setUserMasterCreate(userMaster);
			lisLogin.setSubscriberMaster(subscriberMaster);
			lisLogin.setIsActive(StatusConstants.IS_ACTIVE);
			userMaster.setLoginDetails(lisLogin);
			userMaster.setSubscriberMaster(subscriberMaster);
			getSession().save(userMaster);
			
			return StatusConstants.SUCCESS;
		} catch (HibernateException | SubscriberMasterException | CryptoException ex) {
			ex.printStackTrace();
		} 
		return StatusConstants.FAILURE;
	}

   

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public LISUserMasterCreate validateUserId(String userId) throws CreateUserException {
		logger.info("Entered into validateUserId");	
		Query query = getSession().createQuery(" From LISUserMasterCreate UMACS where lower(UMACS.userId) = :userId and UMACS.isActive = :isActive ");
		query.setParameter("userId", userId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISUserMasterCreate> userMaster = query.list();
		if(null != userMaster && userMaster.size() > 0) {
			return userMaster.get(0);
		}
		return null;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISUserTypeMaster> getUserTypeBySubscriberId(Integer subscriberId) throws CreateUserException {
		logger.info("Entered into getUserTypeBySubscriberId ");	
		Query query = getSession().createQuery(" From LISUserTypeMaster l where l.subscriberMaster.subscriberId = :subscriberId and l.isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		return  query.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LISLogin validateAdminId(String adminId) throws CreateUserException {
		logger.info("Entered into validateLoginCredentials");	
		Query query = getSession().createQuery(" From LISLogin l where lower(l.adminId) = :adminId");
		query.setParameter("adminId", adminId);
		List<LISLogin> loginList = query.list();
		if(null != loginList && loginList.size() > 0){
			return loginList.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public boolean deleteAdmin(String adminId) throws CreateUserException {
		Query query = getSession().createSQLQuery("UPDATE LIS_LOGIN SET IS_ACTIVE = :inactive WHERE lower(ADMIN_ID) = :adminId ");
		query.setParameter("adminId", adminId);
		query.setParameter("inactive", StatusConstants.IN_ACTIVE);
		int result = query.executeUpdate();
		if(result > 0)
			return true;
		return false;
	}

	@Override
	public void saveAdmin(LISLogin login) throws CreateUserException {
		getSession().save(login);
		
	}
}
