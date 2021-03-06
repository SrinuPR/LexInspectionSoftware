package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISUserTypeMaster;

public interface CreateUserDAO {

	public String createUser (CreateUserDTO createuserDTO) throws CreateUserException;
	
	public LISUserMasterCreate validateUserId(String userId) throws CreateUserException;

	public List<LISUserTypeMaster> getUserTypeBySubscriberId(Integer subscriberId) throws CreateUserException;

	public LISLogin validateAdminId(String adminId) throws CreateUserException;

	public boolean deleteAdmin(String adminId) throws CreateUserException;

	public void saveAdmin(LISLogin login) throws CreateUserException;
	
	/*public LISUserMasterCreate getByUserID(String userId) throws CreateUserException;
	*/
}

