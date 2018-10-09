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
import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.dto.SubscriberMasterDTO;
import com.deloitte.inspection.service.CreateUserService;


@RestController
@RequestMapping(value = "/createUser")
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
	@RequestMapping(value = "/fetchData", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<CreateUserDTO> fetchData(){
		try{
			CreateUserDTO response = createUserService.fetchData();
			if(null != response)
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
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<CreateUserDTO> CreateUser(@RequestBody CreateUserDTO createUserDTO){
		try{
			CreateUserDTO response = createUserService.createUser(createUserDTO);
			if(null != response)
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
	@RequestMapping(value = "/validate", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<CreateUserDTO> validateUserId(@RequestBody CreateUserDTO createUserDTO){
		try{
			String response = createUserService.validateUserId(createUserDTO);
			if(null != response)
				return new ResponseEntity(response, HttpStatus.OK);
			else
				return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating credentials " + exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
}

	
}
