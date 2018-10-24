package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.constant.SubscriberConstants;
import com.deloitte.inspection.constant.UserTypeMasterConstants;
import com.deloitte.inspection.dao.CreateUserDAO;
import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.dto.UserTypeMasterDTO;
import com.deloitte.inspection.exception.CreateUserException;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.model.LISUserTypeMaster;
import com.deloitte.inspection.service.CreateUserService;

@Service
public class CreateUserServiceImpl implements CreateUserService {
	
	private static final Logger logger = LogManager.getLogger(CreateUserServiceImpl.class);  
	
	@Autowired
	 private CreateUserDAO createUserDAO ;

	@Override
	public String validateUserId(String userId) {
		LISUserMasterCreate userMaster=new LISUserMasterCreate();
		try {
			userMaster=createUserDAO.validateUserId(userId.toLowerCase());
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
					userTypeMasterDTO.setUserTypeId(lisUserTypeMaster.getUserTypeId());
					userTypeMasterDTO.setUserTypeName(lisUserTypeMaster.getUserTypeName());
					userTypeMasterDTOs.add(userTypeMasterDTO);
				}
			}
		}catch(Exception exception){
			logger.error("Exception occured in "+exception.getMessage());
		}
		return userTypeMasterDTOs;
	}

}