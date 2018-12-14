package com.deloitte.inspection.controller;

import javax.servlet.http.HttpSession;

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
import com.deloitte.inspection.dto.AccessMasterDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.PasswordMaintenanceDTO;
import com.deloitte.inspection.service.AccessMasterService;
import com.deloitte.inspection.service.LoginService;

@RestController
@RequestMapping(value = "/accessControl")
public class AccessMasterController {
	
	private static final Logger logger = LogManager.getLogger(AccessMasterController.class);  
	
	@Autowired
	private AccessMasterService accessMasterService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AccessMasterDTO> saveAccessMaster(@RequestBody  AccessMasterDTO accessMasterDTO, HttpSession httpSession){
		AccessMasterDTO responseDTO = new AccessMasterDTO();
		try{
			responseDTO = accessMasterService.saveAccess(accessMasterDTO, httpSession);
			if(null != responseDTO)
				return new ResponseEntity(responseDTO.getStatus(), HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO.getStatus(), HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception while saving data in Access Master "+exception.getMessage());
		}
		return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getData", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AccessMasterDTO> getAccessMaster(@RequestBody  AccessMasterDTO accessMasterDTO, HttpSession httpSession){
		AccessMasterDTO responseDTO = new AccessMasterDTO();
		try{
			if (null != accessMasterDTO && (null != accessMasterDTO.getSubscriberId() || null != accessMasterDTO.getUserTypeId())) {
				
				responseDTO = accessMasterService.getAccessMasterScreens(accessMasterDTO.getSubscriberId(), accessMasterDTO.getUserTypeId());
			}
			if(null != responseDTO)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception while saving data in Access Master "+exception.getMessage());
		}
		return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
