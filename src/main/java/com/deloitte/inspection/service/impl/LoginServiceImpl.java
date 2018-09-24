package com.deloitte.inspection.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.dao.LoginDAO;
import com.deloitte.inspection.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDAO loginDAO;
	
	@Override
	public String validateCredentails() {
		return loginDAO.validateCredentails();
	}

}
