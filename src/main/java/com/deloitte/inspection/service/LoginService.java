package com.deloitte.inspection.service;

import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.exception.LoginException;

public interface LoginService {

	public LoginDTO validateLoginCredentials(LoginDTO loginDTO) throws LoginException;
}
