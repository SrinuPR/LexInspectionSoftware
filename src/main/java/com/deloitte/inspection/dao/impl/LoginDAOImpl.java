package com.deloitte.inspection.dao.impl;

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
import com.deloitte.inspection.dao.LoginDAO;
import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISUserMasterCreate;

@Repository
@Transactional
public class LoginDAOImpl implements LoginDAO{
	
	private static final Logger logger = LogManager.getLogger(LoginDAOImpl.class);  
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LISLogin validateLoginCredentials(String userId) throws LoginException {
		logger.info("Entered into validateLoginCredentials");	
		Query query = getSession().createQuery(" From LISLogin l where l.userMasterCreate.userId = :userId or l.adminId = :userId");
		query.setParameter("userId", userId);
		List<LISLogin> loginList = query.list();
		if(null != loginList && loginList.size() > 0){
			return loginList.get(0);
		}
		return null;
	}
	
	@Override
	public String changePassword(LISUserMasterCreate userMasterModel) throws LoginException {
		
		logger.info("Inside ChangePasswordDAOImpl");
		String status = new String();
		try {
			if (null != userMasterModel) {
				getSession().saveOrUpdate(userMasterModel);
			}
			status = StatusConstants.SUCCESS;
		}catch (HibernateException exception) {
			logger.info("Exception in updating password " + exception.getMessage());
			status = StatusConstants.FAILURE;
		}
		return status;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISUserMasterCreate validateUser(String userId) throws LoginException {
		logger.info("Inside LoginDAOImpl validateUser");
		Query query = getSession().createQuery("from LISUserMasterCreate where userId = :userId");
		query.setParameter("userId", userId);
		List<LISUserMasterCreate> userList = query.list();
		if (null != userList && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public void updateLoginPassword(String userId, String password) throws LoginException {
		Query query = getSession().createSQLQuery("UPDATE LIS_LOGIN SET PASSWORD = :password WHERE USER_ID = :userId or ADMIN_ID = :userId");
		query.setParameter("password", password);
		query.setParameter("userId",userId);
		query.executeUpdate();	
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public void logout(String userId) throws LoginException {
		logger.info("Inside LoginDAOImpl logout");
		Query query = getSession().createSQLQuery("UPDATE LIS_LOGIN SET IS_SESSION_ACTIVE = :inActive WHERE USER_ID = :userId or ADMIN_ID = :userId");
		query.setParameter("userId",userId);
		query.setParameter("inActive", StatusConstants.IN_ACTIVE);
		query.executeUpdate();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public void updateSessionActiveSwToN() throws LoginException {
		logger.info("Inside LoginDAOImpl updateSessionActiveSwToN");
		Query query = getSession().createSQLQuery("UPDATE LIS_LOGIN SET IS_SESSION_ACTIVE = :inActive WHERE IS_ACTIVE = :isActive");
		query.setParameter("inActive", StatusConstants.IN_ACTIVE);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.executeUpdate();
	}

	@Override
	public void updateLogin(LISLogin login) throws LoginException {
		logger.info("Inside LoginDAOImpl updateLogin");
		getSession().saveOrUpdate(login);
		
	}		

}
