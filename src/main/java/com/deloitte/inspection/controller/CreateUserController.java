package com.deloitte.inspection.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.dto.UserTypeMasterDTO;
import com.deloitte.inspection.service.CreateUserService;


@RestController
@RequestMapping(value = "/user")
public class CreateUserController {
	
	private static final Logger logger = LogManager.getLogger(CreateUserController.class);
	
	@Autowired
	private CreateUserService createUserService;
	/**
	 * @param subMasterDTO
	 * @return
	 */
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<CreateUserDTO> CreateUser(@RequestBody CreateUserDTO createUserDTO){
		try{
			CreateUserDTO response = createUserService.createUser(createUserDTO);
			if(null != response && StatusConstants.SUCCESS.equalsIgnoreCase(response.getStatus()))
				return new ResponseEntity(response, HttpStatus.OK);
			else
				return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating credentials " + exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate/{userId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<CreateUserDTO> validateUserId(@PathVariable("userId") String userId){
		CreateUserDTO createUserDTO = new CreateUserDTO();
		try{
			if(null != userId){
				String response = createUserService.validateUserId(userId);
				if(null != response && response.equalsIgnoreCase(StatusConstants.ERROR)){
					createUserDTO.setStatus(response);
					createUserDTO.setErrorMessage(StatusConstants.USER_CREATE_FIALED);
					return new ResponseEntity(createUserDTO, HttpStatus.EXPECTATION_FAILED);
				}else{
					createUserDTO.setStatus(response);
					return new ResponseEntity(createUserDTO, HttpStatus.OK);
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating userId " + exception.getMessage());
		}
		return new ResponseEntity(createUserDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/types/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<UserTypeMasterDTO>> getUserTypeBySubscriberId(@PathVariable("subscriberId") Integer subscriberId){
		List<UserTypeMasterDTO>  UserTypeMasterDTO = new ArrayList<UserTypeMasterDTO>();
		try{
			UserTypeMasterDTO = createUserService.getUserTypeBySubscriberId(subscriberId);
			return new ResponseEntity(UserTypeMasterDTO, HttpStatus.OK);
				
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating credentials " + exception.getMessage());
			return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
