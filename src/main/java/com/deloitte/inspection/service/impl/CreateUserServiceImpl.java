package com.deloitte.inspection.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.service.CreateUserService;

@Service
public class CreateUserServiceImpl implements CreateUserService {
	private static final Logger logger = LogManager.getLogger(CreateUserServiceImpl.class);  
	
	@Autowired
	 private CreateUserDAO createUserDAO ;
	
	
	@Override
	public CreateUserDTO fetchData() throws CreateUserException {
		CreateUserDTO resCreateUserDTO=new CreateUserDTO();
		try {
			resCreateUserDTO=createUserDAO.fetchData();
			
		}catch(CreateUserException cue) {
			cue.printStackTrace();
			throw cue;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			return resCreateUserDTO;
		}
		
	}

	@Override
	public String validateUserId(CreateUserDTO createuserDTO) {
		LISUserMasterCreate userMaster=new LISUserMasterCreate();
		try {
			userMaster=createUserDAO.validateUserId(createuserDTO.getUserId());
			if(null!=createuserDTO && !createuserDTO.getUserId().isEmpty()) {
				if(null!=userMaster && null!= userMaster.getUserId() ) {
					return StatusConstants.USER_EXISTS;
				}
				
			}
			
		}catch(CreateUserException cue) {
			cue.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return StatusConstants.EMPTY;
	}

	@Override
	public CreateUserDTO createUser(CreateUserDTO createuserDTO) {
		CreateUserDTO resCreateUserDTO=new CreateUserDTO();
		try {
			
			if(null!=createuserDTO && null!=createuserDTO.getUserTypeId()) {
				resCreateUserDTO=createUserDAO.createUser(createuserDTO);
				if(null!=resCreateUserDTO) {
					resCreateUserDTO.setStatus(StatusConstants.USER_CREATE_SUCCESS);
				}else {
					resCreateUserDTO.setErrorMessage(StatusConstants.USER_CREATE_FIALED);
				}
			}else if(null!=createuserDTO && null==createuserDTO.getSubscriberId()){
				resCreateUserDTO.setErrorMessage(StatusConstants.SUBSCRIBER_ID_EMPTY);
			}else if(null!=createuserDTO && null==createuserDTO.getUserTypeId()) {
				resCreateUserDTO.setErrorMessage(StatusConstants.USER_TYPE_ID_EMPTY);
			}
		} catch (CreateUserException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resCreateUserDTO;
	}

}