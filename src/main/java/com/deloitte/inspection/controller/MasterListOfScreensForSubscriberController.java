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
import com.deloitte.inspection.dto.MasterListOfScreensForSubscriberDTO;
import com.deloitte.inspection.response.dto.MasterListOfScreensForSubscriberResponseDTO;
import com.deloitte.inspection.service.MasterListOfScreensForSubscriber;

@RestController
@RequestMapping(value = "/mloss")
public class MasterListOfScreensForSubscriberController {
	
	private static final Logger logger = LogManager.getLogger(MasterListOfScreensForSubscriberController.class);
	
	@Autowired
	private MasterListOfScreensForSubscriber masterListOfScreensForSubscriber;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/saveData", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MasterListOfScreensForSubscriberResponseDTO> saveScreensData(@RequestBody List<MasterListOfScreensForSubscriberDTO> masterListOfScreensForSubscriberDTO){
		logger.info("Entered into saveScreensData Controller");
		MasterListOfScreensForSubscriberResponseDTO masterListOfScreensForSubscriberResponseDTO = new MasterListOfScreensForSubscriberResponseDTO();
		try{
			masterListOfScreensForSubscriberResponseDTO = masterListOfScreensForSubscriber.saveScreensData(masterListOfScreensForSubscriberDTO);
			if(null != masterListOfScreensForSubscriberResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(masterListOfScreensForSubscriberResponseDTO.getStatus())){
				return new ResponseEntity(masterListOfScreensForSubscriberResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(masterListOfScreensForSubscriberResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}	
		}catch(Exception exception){
			logger.error("Error while saveScreensData data : "+exception.getMessage());
			return new ResponseEntity(masterListOfScreensForSubscriberResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/screensList/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MasterListOfScreensForSubscriberResponseDTO> getScreensforSubscriber(@PathVariable("subscriberId") Integer subscriberId){
		logger.info("Entered into getScreensforSubscriber Controller");
		MasterListOfScreensForSubscriberResponseDTO masterListOfScreensForSubscriberResponseDTO = new MasterListOfScreensForSubscriberResponseDTO();
		try{
			masterListOfScreensForSubscriberResponseDTO = masterListOfScreensForSubscriber.getScreensforSubscriber(subscriberId);
			if(null != masterListOfScreensForSubscriberResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(masterListOfScreensForSubscriberResponseDTO.getStatus())){
				return new ResponseEntity(masterListOfScreensForSubscriberResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(masterListOfScreensForSubscriberResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}	
		}catch(Exception exception){
			logger.error("Error while getScreensforSubscriber data : "+exception.getMessage());
			return new ResponseEntity(masterListOfScreensForSubscriberResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
