package com.deloitte.inspection.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.dao.LoginDAO;

@Repository
@Transactional
public class LoginDAOImpl implements LoginDAO{
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public String validateCredentails() {
		Session s = getSession();
		if(s != null){
			return "Success";
		}
		return "fail";
	}
    
    

}
