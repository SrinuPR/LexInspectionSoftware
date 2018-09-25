package com.deloitte.inspection.dao;

import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.model.LISLogin;

public interface LoginDAO {

	public LISLogin validateLoginCredentials(Integer userId) throws LoginException;
}
