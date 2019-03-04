package com.deloitte.inspection.dao;

import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.mapper.LISLoginResult;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISUserMasterCreate;

public interface LoginDAO {

	public LISLoginResult validateLoginCredentials(String userId) throws LoginException;

	public String changePassword(LISUserMasterCreate userMasterModel) throws LoginException;

	public LISUserMasterCreate validateUser(String userId) throws LoginException;

	public void updateLoginPassword(String userId, String password) throws LoginException;

	public void logout(String userId) throws LoginException;

	public void updateSessionActiveSwToN() throws LoginException;

	public void updateLogin(LISLogin login) throws LoginException;
}
