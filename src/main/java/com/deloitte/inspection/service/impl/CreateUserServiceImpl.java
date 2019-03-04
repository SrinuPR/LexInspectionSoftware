package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.component.ApplicationProperties;
import com.deloitte.inspection.component.CryptoComponent;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.constant.SubscriberConstants;
import com.deloitte.inspection.constant.UserTypeMasterConstants;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.dto.UserTypeMasterDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISUserTypeMaster;
import com.deloitte.inspection.service.CreateUserService;

@Service
public class CreateUserServiceImpl implements CreateUserService {
	
	private static final Logger logger = LogManager.getLogger(CreateUserServiceImpl.class);  
	
	@Autowired
	private CreateUserDAO createUserDAO ;
	
	@Autowired
	private CryptoComponent cryptoComponent;

	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Override
	public String validateUserId(String userId) {
		LISUserMasterCreate userMaster=new LISUserMasterCreate();
		try {
			userMaster=createUserDAO.validateUserId(userId);
			if(null != userMaster)
				return StatusConstants.ERROR;
			else
				return StatusConstants.SUCCESS;
		}catch(CreateUserException cue) {
			cue.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return StatusConstants.EMPTY;
	}

	@Override
	public CreateUserDTO createUser(CreateUserDTO createuserDTO) {
		String respose = null;
		CreateUserDTO resCreateUserDTO = new CreateUserDTO();
		try {
			if(null!=createuserDTO && null!=createuserDTO.getUserTypeId()) {
				respose = createUserDAO.createUser(createuserDTO);
				if(null!=respose && StatusConstants.SUCCESS.equalsIgnoreCase(respose)) {
					resCreateUserDTO.setStatus(StatusConstants.USER_CREATE_SUCCESS);
				}else{
					resCreateUserDTO.setErrorMessage(SubscriberConstants.EACH_SUBSCRIBER_USER_COUNT_MESSAGE+" "+applicationProperties.EACH_SUBSCRIBER_USER_COUNT);
				}
			}else if(null!=createuserDTO && null==createuserDTO.getSubscriberId()){
				resCreateUserDTO.setErrorMessage(SubscriberConstants.SUBSCRIBER_ID_EMPTY);
			}else if(null!=createuserDTO && null==createuserDTO.getUserTypeId()) {
				resCreateUserDTO.setErrorMessage(UserTypeMasterConstants.USER_TYPE_ID_EMPTY);
			}
		} catch (CreateUserException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resCreateUserDTO;
	}

	@Override
	public List<UserTypeMasterDTO> getUserTypeBySubscriberId(Integer subscriberId) throws CreateUserException {
		List<UserTypeMasterDTO> userTypeMasterDTOs = new ArrayList<UserTypeMasterDTO>();
		try{
			List<LISUserTypeMaster> userTypeMasters = createUserDAO.getUserTypeBySubscriberId(subscriberId);
			if(null != userTypeMasters && userTypeMasters.size() > 0){
				for(LISUserTypeMaster lisUserTypeMaster : userTypeMasters){
					UserTypeMasterDTO userTypeMasterDTO = new UserTypeMasterDTO();
					userTypeMasterDTO.setUserTypeId(Integer.valueOf(lisUserTypeMaster.getUserTypeId()));
					userTypeMasterDTO.setUserTypeName(lisUserTypeMaster.getUserTypeName());
					userTypeMasterDTOs.add(userTypeMasterDTO);
				}
			}
		}catch(Exception exception){
			logger.error("Exception occured in "+exception.getMessage());
		}
		return userTypeMasterDTOs;
	}

	@Override
	public CreateUserDTO deleteAdmin(String adminId) throws CreateUserException {
		logger.info("inside deleteAdmin service");
		CreateUserDTO adminDTO = new CreateUserDTO();
		try{
			boolean deleted = createUserDAO.deleteAdmin(adminId);
			if(deleted){
				adminDTO.setStatus(StatusConstants.SUCCESS);
				adminDTO.setErrorMessage(StatusConstants.ADMIN_DELETE_SUCCESS);
			}else{
				adminDTO.setErrorMessage(StatusConstants.ADMIN_DELETE_FAILURE);
				adminDTO.setStatus(StatusConstants.FAILURE);
			}
		}catch(Exception exception){
			adminDTO.setErrorMessage(StatusConstants.ERROR);
			adminDTO.setStatus(StatusConstants.ERROR);
			logger.error("exception deleteAdmin service"+exception.getMessage());
		}
		return adminDTO;
	}
	@Override
	public CreateUserDTO saveAdmin(CreateUserDTO createUserDTO) throws CreateUserException {
		logger.info("inside saveAdmin service");
		CreateUserDTO adminDTO = new CreateUserDTO();
		try{
			LISLogin login = new LISLogin();
			login.setAdminId(createUserDTO.getAdminId());
			login.setPassword(cryptoComponent.encrypt(createUserDTO.getPassword()));
			login.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
			login.setCreatedBy(createUserDTO.getAdminId());
			login.setCreatedTimestamp(new Date());
			createUserDAO.saveAdmin(login);
			adminDTO.setErrorMessage(StatusConstants.ADMIN_SAVE_SUCCESS);
			adminDTO.setStatus(StatusConstants.SUCCESS);
		}catch(Exception exception){
			adminDTO.setErrorMessage(StatusConstants.ADMIN_SAVE_FAILURE);
			adminDTO.setStatus(StatusConstants.FAILURE);
			logger.error("exception saveAdmin service"+exception.getMessage());
		}
		return adminDTO;
	}

	@Override
	public CreateUserDTO validateAdminId(String adminId) throws CreateUserException {
		logger.info("inside validateAdminId service");
		CreateUserDTO createUserDTO = new CreateUserDTO();
		try{
			LISLogin login = createUserDAO.validateAdminId(adminId);
			if(null != login){
				createUserDTO.setErrorMessage(StatusConstants.VALIDATE_ADMIN_FAILURE);
				createUserDTO.setStatus(StatusConstants.FAILURE);
			}else{
				createUserDTO.setErrorMessage(StatusConstants.VALIDATE_ADMIN_SUCCESS);
				createUserDTO.setStatus(StatusConstants.SUCCESS);
			}
		}catch(Exception exception){
			createUserDTO.setErrorMessage(StatusConstants.ERROR);
			createUserDTO.setStatus(StatusConstants.ERROR);
			logger.error("exception validateAdminId service"+exception.getMessage());
		}
		return createUserDTO;
	}
}