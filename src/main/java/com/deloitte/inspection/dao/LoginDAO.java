package com.deloitte.inspection.dao;

import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISUserMasterCreate;

public interface LoginDAO {

	public LISLogin validateLoginCredentials(String userId) throws LoginException;

	public String changePassword(LISUserMasterCreate userMasterModel) throws LoginException;

	public LISUserMasterCreate validateUser(String userId) throws LoginException;

	public void updateLoginPassword(String userId, String password) throws LoginException;
}
