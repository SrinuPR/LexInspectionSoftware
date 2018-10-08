/**
 * 
 */
package com.deloitte.inspection.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dto.SubscriberMasterDTO;
import com.deloitte.inspection.dto.UserTypeMasterDTO;
import com.deloitte.inspection.service.UserTypeMasterService;

/**
 * @author rnarne
 * This class will have the controller services for subscriber.
 */
@RestController
@RequestMapping(value = "/usertype")
public class UserTypeMasterController {

	private static final Logger logger = LogManager.getLogger(UserTypeMasterController.class);  
	
	@Autowired
	private UserTypeMasterService userTypeMasterService;
	
	/**
	 * @param userTypeMasterDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate", method = RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE, 
		consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<UserTypeMasterDTO> validateUserType
		(@RequestBody UserTypeMasterDTO userTypeMasterDTO){
		try{
			String outPutStr = userTypeMasterService.validateUserType(userTypeMasterDTO);
			if(null != outPutStr)
				return new ResponseEntity(outPutStr, HttpStatus.OK);
			else
				return new ResponseEntity(outPutStr, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating user type master " + exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @param userTypeMasterDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, 
		consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<SubscriberMasterDTO> createUserTypeMaster
				(@RequestBody UserTypeMasterDTO userTypeMasterDTO){
		UserTypeMasterDTO responseDTO = new UserTypeMasterDTO();
		try{
			responseDTO = userTypeMasterService.createUserTypeMaster(userTypeMasterDTO);
			if(null != responseDTO && responseDTO.getErrorMessage() == null)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While creating user type master "+exception.getMessage());
		}
		return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
