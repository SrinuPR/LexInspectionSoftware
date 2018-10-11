/**
 * 
 */
package com.deloitte.inspection.controller;

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
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<SubscriberMasterDTO> validateSubscriber(@PathVariable("subscriberId") Integer subscriberId){
		SubscriberMasterDTO responseDTO = new SubscriberMasterDTO();
		try{
			responseDTO = subMasterService.validateSubscriber(subscriberId);
			if(null != responseDTO)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
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
			if(null != responseDTO)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating credentials "+exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @return ResponseEntity
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<SubscriberMasterDTO>> subscriberMasterDTOList(){
		logger.info("Entered into displayComponentMasterData");
		SubscriberMasterDTO responseDTO = new SubscriberMasterDTO();
		List<SubscriberMasterDTO> subscriberMasterDTOList = null;
		try{
			responseDTO.setStatus(StatusConstants.SUCCESS);
			subscriberMasterDTOList = subMasterService.getAllSubscriberMasterData();
			if(null != subscriberMasterDTOList && subscriberMasterDTOList.size() > 0) {
				responseDTO.setSubMasterList(subscriberMasterDTOList);
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Error while fetching the data : "+exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<SubscriberMasterDTO> getSubscribers(@PathVariable("userId") String userId){
		SubscriberMasterDTO responseDTO = new SubscriberMasterDTO();
		List<SubscriberMasterDTO> subscriberMasterDTOList = null;
		try{
			subscriberMasterDTOList = subMasterService.getSubscriber(userId);
			if(null != subscriberMasterDTOList && subscriberMasterDTOList.size() > 0) {
				responseDTO.setSubMasterList(subscriberMasterDTOList);
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating credentials " + exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
