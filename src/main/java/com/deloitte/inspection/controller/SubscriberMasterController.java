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
import com.deloitte.inspection.service.SubscriberMasterService;

/**
 * @author rnarne
 * This class will have the controller services for subscriber.
 */
@RestController
@RequestMapping(value = "/subscriber")
public class SubscriberMasterController {

	
	private static final Logger logger = LogManager.getLogger(SubscriberMasterController.class);  
	
	@Autowired
	private SubscriberMasterService subMasterService;
	
	/**
	 * @param subMasterDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate", method = RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<SubscriberMasterDTO> validateSubscriber(@RequestBody SubscriberMasterDTO subMasterDTO){
		try{
			String outPutStr = subMasterService.validateSubscriber(subMasterDTO);
			if(null != outPutStr)
				return new ResponseEntity(outPutStr, HttpStatus.OK);
			else
				return new ResponseEntity(outPutStr, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating credentials " + exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @param subMasterDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<SubscriberMasterDTO> createSubscriber(@RequestBody SubscriberMasterDTO subMasterDTO){
		SubscriberMasterDTO responseDTO = new SubscriberMasterDTO();
		try{
			responseDTO = subMasterService.createSubscriber(subMasterDTO);
			if(null != responseDTO && responseDTO.getErrorMessage() == null)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating credentials "+exception.getMessage());
		}
		return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
