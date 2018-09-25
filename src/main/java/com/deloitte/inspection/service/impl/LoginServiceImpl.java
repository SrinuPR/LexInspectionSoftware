package com.deloitte.inspection.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.component.CryptoComponent;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.LoginDAO;
import com.deloitte.inspection.dao.impl.LoginDAOImpl;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.exception.CryptoException;
import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	private static final Logger logger = LogManager.getLogger(LoginDAOImpl.class);  

	@Autowired
	private LoginDAO loginDAO;
	
	@Autowired
	private CryptoComponent cryptoComponent;
	
	@Override
	public LoginDTO validateLoginCredentials(LoginDTO loginDTO) throws LoginException {
		LoginDTO responseDTO = new LoginDTO();
		logger.info("User name and password "+loginDTO.getUserId()+" , "+loginDTO.getPassword());
		if(null != loginDTO && null != loginDTO.getUserId() && null != loginDTO.getPassword()){
			System.out.println(loginDTO.getUserId()+" , "+loginDTO.getPassword());
			try{
				LISLogin login = loginDAO.validateLoginCredentials(loginDTO.getUserId());
				if(null == login || !cryptoComponent.decrypt(login.getPassword()).equals(loginDTO.getPassword())){
					responseDTO.setErrorMessage(StatusConstants.INCORRECT_CREDENTIALS);
				}else{
					System.out.println(login.getUserId()+" , "+login.getPassword());
					responseDTO.setStatus(StatusConstants.LOGIN_SUCCESS);
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

}
