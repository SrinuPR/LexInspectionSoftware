package com.deloitte.inspection.dao.impl;

import java.util.ArrayList;
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

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISStaticUserType;
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

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

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
			userMaster.setIsActive(StatusConstants.IS_ACTIVE);
			LISLogin lisLogin=new LISLogin();
			lisLogin.setCreatedBy(createuserDTO.getUserName());
			lisLogin.setCreatedTimestamp(new Date());
			lisLogin.setPassword(createuserDTO.getPassword());
			lisLogin.setUserMasterCreate(userMaster);
			lisLogin.setSubscriberMaster(subscriberMaster);
			lisLogin.setIsActive(StatusConstants.IS_ACTIVE);
			getSession().save(userMaster);
			
				return createuserDTO;
		} catch (HibernateException | SubscriberMasterException ex) {
			//if(trans != null)
				//trans.rollback();
		} 
		return null;
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

	@Override
	public List<CreateUserDTO> fetchData() throws CreateUserException {
		logger.info("Entered into fetchData");	
		List<CreateUserDTO> userList=new ArrayList<CreateUserDTO>();
		Query query = getSession().createQuery(" From LISUserTypeMaster USERTYP WHERE ACTIVE_SW='Y' ");
		List<LISUserTypeMaster> usersList = query.list();
		if(null != usersList && usersList.size() > 0) {
			for(LISUserTypeMaster user:usersList) {
				CreateUserDTO createuserDTO=new CreateUserDTO();
				createuserDTO.setUserTypeId(user.getUserTypeId());
				createuserDTO.setUserName(user.getUserTypeName());
				createuserDTO.setActiveSw(user.getIsActive());
				userList.add(createuserDTO);
			}
		}
		
		return userList;
	}
    
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	public LISUserMasterCreate getByUserID(String userId) {
		logger.info("Entered into getByUserName");
		LISUserMasterCreate userMaster = null;
		Query query = getSession().createQuery(" From LISUserMasterCreate SUMAS where SUMAS.userId = :userId");
		query.setParameter("userName", userId);
		List<LISUserMasterCreate> userMasterlist = query.list();
		if (null != userMasterlist && userMasterlist.size() > 0) {
			userMaster = userMasterlist.get(0);
		}
		return userMaster;
	}*/
}
