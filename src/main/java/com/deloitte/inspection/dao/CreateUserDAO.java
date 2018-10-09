package com.deloitte.inspection.dao;

import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserMasterCreate;

public interface CreateUserDAO {

	public CreateUserDTO createUser (CreateUserDTO createuserDTO) throws CreateUserException;
	
	public CreateUserDTO fetchData () throws CreateUserException;
	
	public LISUserMasterCreate validateUserId(String userId) throws CreateUserException;
	
	public LISUserMasterCreate getByUserName(String userName) throws CreateUserException;
	
	}

