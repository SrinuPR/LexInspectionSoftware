/**
 * 
 */
package com.deloitte.inspection.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.constant.UserTypeMasterConstants;
import com.deloitte.inspection.dao.UserTypeMasterDAO;
import com.deloitte.inspection.dto.UserTypeMasterDTO;
import com.deloitte.inspection.exception.UserTypeMasterException;
import com.deloitte.inspection.model.LISUserTypeMaster;
import com.deloitte.inspection.service.UserTypeMasterService;

/**
 * @author rnarne
 *
 */
@Service
public class UserTypeMasterServiceImpl implements UserTypeMasterService{

	private static final Logger logger = LogManager.getLogger(UserTypeMasterServiceImpl.class);  

	@Autowired
	private UserTypeMasterDAO userTypeMasDAO;
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.service.UserTypeMasterService#validateUserType(com.deloitte.inspection.dto.UserTypeMasterDTO)
	 */
	@Override
	public UserTypeMasterDTO validateUserType(UserTypeMasterDTO userTypeMasDTO) throws UserTypeMasterException {
		logger.info("Subscriber ID: " + userTypeMasDTO.getUserTypeId());
		UserTypeMasterDTO responseDTO = new UserTypeMasterDTO();
		if(null != userTypeMasDTO && null != userTypeMasDTO.getUserTypeId()) {
			LISUserTypeMaster login = userTypeMasDAO.validateUserType(userTypeMasDTO);
			if(userTypeMasDTO.getUserTypeName() != null && !userTypeMasDTO.getUserTypeName().equals("")) {
				responseDTO.setStatus(StatusConstants.SUCCESS);
				if (null == login) {
					responseDTO.setMessage(UserTypeMasterConstants.USER_TYPE_NAME_NOT_AVAILABLE);
					return responseDTO;
				} else {
					responseDTO.setMessage(UserTypeMasterConstants.USER_TYPE_NAME_AVAILABLE);
					return responseDTO;
				}
			} else  {
				responseDTO.setStatus(StatusConstants.SUCCESS);
				if (null == login) {
					responseDTO.setMessage(UserTypeMasterConstants.USER_TYPE_NOT_AVAILABLE);
					return responseDTO;
				} else {
					responseDTO.setMessage(UserTypeMasterConstants.USER_TYPE_AVAILABLE);
					return responseDTO;
				}
			} 
		} else if(null != userTypeMasDTO && null == userTypeMasDTO.getUserTypeId()) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(UserTypeMasterConstants.USER_TYPE_ID_EMPTY);
			return responseDTO;
		}
		return responseDTO;
	}	
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.service.UserTypeMasterService#createUserTypeMaster(com.deloitte.inspection.dto.UserTypeMasterDTO)
	 */
	@Override
	public UserTypeMasterDTO createUserTypeMaster(UserTypeMasterDTO userTypeMasDTO) 
			throws UserTypeMasterException {
		UserTypeMasterDTO responseDTO = new UserTypeMasterDTO();
		logger.info("Subscriber ID, name and Address "+userTypeMasDTO.getUserTypeId()+" , "+userTypeMasDTO.getUserTypeName());
		if(null != userTypeMasDTO && null != userTypeMasDTO.getUserTypeId() && null != userTypeMasDTO.getUserTypeName()) {
			UserTypeMasterDTO login = userTypeMasDAO.createUserTypeMaster(userTypeMasDTO);
			if (null == login) {
				responseDTO.setStatus(StatusConstants.SUCCESS);
				responseDTO.setMessage(UserTypeMasterConstants.CREATE_USER_TYPE_FAILED);
			} else {
				responseDTO.setStatus(StatusConstants.SUCCESS);
				responseDTO = userTypeMasDTO;
				responseDTO.setStatus(UserTypeMasterConstants.CREATE_USER_TYPE_SUCCESS);
			}
		} else if(null != userTypeMasDTO && null == userTypeMasDTO.getUserTypeId()) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(UserTypeMasterConstants.USER_TYPE_ID_EMPTY);
		} else if(null != userTypeMasDTO && null == userTypeMasDTO.getUserTypeName()) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(UserTypeMasterConstants.USER_TYPE_NAME_EMPTY);
		}
		return responseDTO;
	}
}
