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

import com.deloitte.inspection.dao.LoginDAO;
import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.model.LISLogin;

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
		Query query = getSession().createQuery(" From LISLogin l where l.userMasterCreate.userId = :userId");
		query.setParameter("userId", userId);
		List<LISLogin> loginList = query.list();
		if(null != loginList && loginList.size() > 0){
			return loginList.get(0);
		}
		return null;
	}

}
