package com.deloitte.inspection.service;

import javax.servlet.http.HttpSession;

import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.PasswordMaintenanceDTO;
import com.deloitte.inspection.exception.LoginException;

public interface LoginService {

	public LoginDTO validateLoginCredentials(LoginDTO loginDTO, HttpSession httpSession) throws LoginException;

	public String forgotPassword(PasswordMaintenanceDTO passwordMaintenanceDTO) throws LoginException;

	public String changePassword(PasswordMaintenanceDTO passwordMaintenanceDTO) throws LoginException;

	public void logout(String userId) throws LoginException;

	public String resetLogin(String userId) throws LoginException;

	public String validateUserId(String userId) throws LoginException;
}
