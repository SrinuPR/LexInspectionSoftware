package com.deloitte.inspection.controller;

import javax.servlet.http.HttpSession;

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
import com.deloitte.inspection.dto.AccessMasterDTO;
import com.deloitte.inspection.response.dto.AccessMasterResponseDTO;
import com.deloitte.inspection.service.AccessMasterService;

@RestController
@RequestMapping(value = "/accessControl")
public class AccessMasterController {
	
	private static final Logger logger = LogManager.getLogger(AccessMasterController.class);  
	
	@Autowired
	private AccessMasterService accessMasterService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AccessMasterResponseDTO> saveAccessMaster(@RequestBody  AccessMasterDTO accessMasterDTO){
		AccessMasterResponseDTO responseDTO = new AccessMasterResponseDTO();
		try{
			responseDTO = accessMasterService.saveAccess(accessMasterDTO);
			if(null != responseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(responseDTO.getStatus()))
				return new ResponseEntity(responseDTO, HttpStatus.OK);
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
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/userType/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AccessMasterResponseDTO> getUserTypeListforSubscriber(@PathVariable("subscriberId") Integer subscriberId){
		logger.info("Entered into getUserTypeListforSubscriber Controller");
		AccessMasterResponseDTO accessMasterResponseDTO = new AccessMasterResponseDTO();
		try{
			accessMasterResponseDTO = accessMasterService.getUserTypeListforSubscriber(subscriberId);
			if(null != accessMasterResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(accessMasterResponseDTO.getStatus())){
				return new ResponseEntity(accessMasterResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(accessMasterResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}	
		}catch(Exception exception){
			logger.error("Error while getUserTypeListforSubscriber data : "+exception.getMessage());
			return new ResponseEntity(accessMasterResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
