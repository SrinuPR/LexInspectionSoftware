/**
 * 
 */
package com.deloitte.inspection.controller;

import java.util.List;

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
import com.deloitte.inspection.dto.InspectionStageMasterDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.service.InspectionStageMasterService;

/**
 * @author rnarne
 *
 */
@RestController
@RequestMapping(value = "/inspectionstage")
public class InspectionStageMasterController {
	
	private static final Logger logger = LogManager.getLogger(InspectionStageMasterController.class);  
	
	@Autowired
	private InspectionStageMasterService inspStageMasterService;
	
	/**
	 * @param inspStageMasDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/{inspStageId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionStageMasterDTO> validateInspStageId(@PathVariable("inspStageId") Integer inspStageId){
		InspectionStageMasterDTO responseDTO = new InspectionStageMasterDTO();
		try{
			responseDTO = inspStageMasterService.getInspStageId(inspStageId);
			if(null != responseDTO)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validateSubscriber " + exception.getMessage());
		}
		return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @param inspStageMasDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionStageMasterDTO> createInspectionStage(@RequestBody InspectionStageMasterDTO inspStageMasDTO, HttpSession httpSession){
		InspectionStageMasterDTO responseDTO = new InspectionStageMasterDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			if(null != userDto){
				userId = userDto.getUserId();
			}else{
				userId = StatusConstants.DEFAULT_USER_NAME;
			}
			responseDTO = inspStageMasterService.createInspectionStage(inspStageMasDTO,userId);
			if(null != responseDTO)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating credentials "+exception.getMessage());
		}
		return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @return ResponseEntity
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<InspectionStageMasterDTO>> inspStageMasterList(HttpSession httpSession){
		logger.info("Entered into inspStageMasterList");
		InspectionStageMasterDTO responseDTO = new InspectionStageMasterDTO();
		List<InspectionStageMasterDTO> inspStageMasDTOList = null;
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			Integer subscriberId = null;
			if(null != userDto){
				subscriberId = userDto.getSubscriberId();
			}
			inspStageMasDTOList = inspStageMasterService.getAllInspStageMasterData(subscriberId);
			if(null != inspStageMasDTOList && inspStageMasDTOList.size() > 0) {
				responseDTO.setStatus(StatusConstants.SUCCESS);
				responseDTO.setInspStageMasterList(inspStageMasDTOList);
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			} else {
				responseDTO.setStatus(StatusConstants.SUCCESS);
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Error while fetching the data : "+exception.getMessage());
		}
		return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
