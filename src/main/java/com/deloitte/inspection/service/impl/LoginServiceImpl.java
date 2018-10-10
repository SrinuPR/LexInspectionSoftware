package com.deloitte.inspection.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.component.CryptoComponent;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.LoginDAO;
import com.deloitte.inspection.dao.impl.LoginDAOImpl;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.PasswordMaintenanceDTO;
import com.deloitte.inspection.email.EmailService;
import com.deloitte.inspection.exception.CryptoException;
import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.service.LoginService;
import com.deloitte.inspection.util.RandomPasswordGenerator;

@Service
public class LoginServiceImpl implements LoginService{
	
	private static final Logger logger = LogManager.getLogger(LoginDAOImpl.class);  

	@Autowired
	private LoginDAO loginDAO;
	
	@Autowired
	private CryptoComponent cryptoComponent;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public LoginDTO validateLoginCredentials(LoginDTO loginDTO, HttpSession httpSession) throws LoginException {
		LoginDTO responseDTO = new LoginDTO();
		logger.info("User name and password "+loginDTO.getUserId()+" , "+loginDTO.getPassword());
		if(null != loginDTO && null != loginDTO.getUserId() && null != loginDTO.getPassword()){
			System.out.println(loginDTO.getUserId()+" , "+loginDTO.getPassword());
			try{
				LISLogin login = loginDAO.validateLoginCredentials(loginDTO.getUserId());
				if(null == login || !cryptoComponent.decrypt(login.getPassword()).equals(loginDTO.getPassword())){
					responseDTO.setErrorMessage(StatusConstants.INCORRECT_CREDENTIALS);
				}else if(null != login.getUserMasterCreate() && login.getUserMasterCreate().getIsActive() != 'Y'){
					responseDTO.setErrorMessage(StatusConstants.IN_ACTIVE_LOGIN_USER);
				}else{
					if(null != login.getSubscriberMaster()){
						responseDTO.setSubscriberId(login.getSubscriberMaster().getSubscriberId());
						responseDTO.setSubscriberName(login.getSubscriberMaster().getSubscriberName());
					}
					if(null != login.getUserMasterCreate()){
						System.out.println(login.getUserMasterCreate().getUserId()+" , "+login.getPassword());
						responseDTO.setUserId(login.getUserMasterCreate().getUserId());
						responseDTO.setUserName(login.getUserMasterCreate().getUserName());
						if(null == login.getUserMasterCreate().getOldPassword1() && null == login.getUserMasterCreate().getOldPassword2() 
								&& null != login.getUserMasterCreate().getActivePassword()){
							responseDTO.setFirstTimeLogin(true);
						}
					}
					responseDTO.setStatus(StatusConstants.LOGIN_SUCCESS);
					httpSession.setAttribute("user", responseDTO);
				}
			}catch(CryptoException cryptoException){
				logger.error("Error while decrypting credentails "+cryptoException.getMessage());
			}
		}else if(null != loginDTO && null != loginDTO.getUserId() && null == loginDTO.getPassword()){
			responseDTO.setErrorMessage(StatusConstants.PASSWORD_EMPTY);
		}else if(null != loginDTO && null == loginDTO.getUserId() && null != loginDTO.getPassword()){
			responseDTO.setErrorMessage(StatusConstants.USER_ID_EMPTY);
		}
		return responseDTO;
	}

	@Override
	public String forgotPassword(PasswordMaintenanceDTO passwordMaintenanceDTO) throws LoginException {
		
		logger.info("Inside forgotPassword of ForgotPasswordServiceImpl for " + passwordMaintenanceDTO.getUserId());
		String response = new String();
		Boolean emailSent = false;
		LISUserMasterCreate userMasterModel = new LISUserMasterCreate();
		if (null != passwordMaintenanceDTO && null != passwordMaintenanceDTO.getUserId()) {
			userMasterModel = loginDAO.validateUser(passwordMaintenanceDTO.getUserId());
		}
		if (null == userMasterModel) {
			response = StatusConstants.INVALID_USER;
			return response;
		} else {
			RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
			String randomPassword = new String();
			try {
				randomPassword = randomPasswordGenerator.generateRandomPassword(StatusConstants.RANDOM_PASSWORD_LENGTH).substring(0, 20);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			}	
			logger.info("Random Password generated for" + passwordMaintenanceDTO.getUserId());
						
			if(null != passwordMaintenanceDTO && null != passwordMaintenanceDTO.getEmailId() 
					&& null != passwordMaintenanceDTO.getUserId()) {
				logger.info("Sending mail to " + passwordMaintenanceDTO.getEmailId());
				String messageBody = new String (StatusConstants.FORGOT_PASSWORD_MAIL_BODY + randomPassword);
				String subject = new String ("New Password for " + passwordMaintenanceDTO.getUserId());
				try {
					emailService.sendEmail(passwordMaintenanceDTO.getEmailId(), messageBody, subject);
					logger.info("Email Sent Successfully");
					emailSent = true;
				} catch (Exception ex) {
					logger.info("Email sending Failed");
					response = StatusConstants.EMAIL_SENT_FAILED;
				}
				if (emailSent) {
					passwordMaintenanceDTO.setNewPassword(randomPassword);
					response = updatePassword(passwordMaintenanceDTO,userMasterModel);
				}
			}else {
				logger.error("Mail cannot be send as emailId is null");
			}
		}
		return response;
	}

	@Override
	public String changePassword(PasswordMaintenanceDTO passwordMaintenanceDTO) throws LoginException {

		logger.info("Inside changePassword of ChangePasswordServiceImpl for ");
		String response = new String();
		boolean correctPass = false;
		LISUserMasterCreate userMasterModel = new LISUserMasterCreate();
		if (null != passwordMaintenanceDTO && null != passwordMaintenanceDTO.getUserId()
				&& null != passwordMaintenanceDTO.getActivePassword()) {
			userMasterModel = loginDAO.validateUser(passwordMaintenanceDTO.getUserId());
			try {
				correctPass = cryptoComponent.decrypt(userMasterModel.getActivePassword())
						.equals(passwordMaintenanceDTO.getActivePassword());
			} catch (CryptoException e) {
				logger.error(e.getMessage());
			}
		}
		if (null == userMasterModel || !correctPass) {
			response = StatusConstants.INVALID_USER;
			return response;
		} else {
			if (null != passwordMaintenanceDTO.getNewPassword()) {
				response = updatePassword(passwordMaintenanceDTO, userMasterModel);
			}
			return response;
		}

	}
	
	private String updatePassword(PasswordMaintenanceDTO passwordMaintenanceDTO, LISUserMasterCreate userMasterModel)
			throws LoginException {

		String response = new String();
		String status = new String();
		try {
			userMasterModel.setOldPassword2(userMasterModel.getOldPassword1());
			userMasterModel.setOldPassword1(userMasterModel.getActivePassword());
			userMasterModel.setActivePassword(cryptoComponent.encrypt(passwordMaintenanceDTO.getNewPassword()));
		} catch (CryptoException cryptoException) {
			logger.error("Error while encrypting Password " + cryptoException.getMessage());
			response = StatusConstants.PASSWORD_CHANGED_FAIL;
		}
		status = loginDAO.changePassword(userMasterModel);
		if(status.equalsIgnoreCase(StatusConstants.SUCCESS)){
			try{
				String password = cryptoComponent.encrypt(passwordMaintenanceDTO.getNewPassword());
				loginDAO.updateLoginPassword(userMasterModel.getUserId(), password);
			}catch(LoginException |CryptoException loginException){
				logger.error("Exception While updating the password in login table "+loginException.getMessage());
			}
		}
		if (status.equals(StatusConstants.SUCCESS)) {
			response = StatusConstants.PASSWORD_CHANGED_SUCCESS;
		} else {
			response = StatusConstants.PASSWORD_CHANGED_FAIL;
		}
		return response;
	}

}
