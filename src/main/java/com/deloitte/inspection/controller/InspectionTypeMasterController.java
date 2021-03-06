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
import com.deloitte.inspection.dto.InspectionTypeMasterDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.service.InspectionTypeMasterService;

/**
 * @author rnarne
 * This class will have the controller services for subscriber.
 */
@RestController
@RequestMapping(value = "/inspectiontype")
public class InspectionTypeMasterController {

	
	private static final Logger logger = LogManager.getLogger(InspectionTypeMasterController.class);  
	
	@Autowired
	private InspectionTypeMasterService inspTypeMasterService;
	
	/**
	 * @param inspTypeMasDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/{inspTypeId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionTypeMasterDTO> validateInspType(@PathVariable("inspTypeId") Integer inspTypeId){
		InspectionTypeMasterDTO responseDTO = new InspectionTypeMasterDTO();
		try{
			responseDTO = inspTypeMasterService.getInspTypeId(inspTypeId);
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
	 * @param inspTypeMasDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionTypeMasterDTO> createInspectionType(@RequestBody InspectionTypeMasterDTO inspTypeMasDTO, HttpSession httpSession){
		InspectionTypeMasterDTO responseDTO = new InspectionTypeMasterDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			if(null != userDto){
				userId = userDto.getUserId();
			}else{
				userId = StatusConstants.DEFAULT_USER_NAME;
			}
			responseDTO = inspTypeMasterService.createInspectionType(inspTypeMasDTO,userId);
			if(null != responseDTO)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception in createInspectionType "+exception.getMessage());
		}
		return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @return ResponseEntity
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionTypeMasterDTO> inspTypeMasterDTOList(HttpSession httpSession){
		logger.info("Entered into inspTypeMasterDTOList");
		InspectionTypeMasterDTO responseDTO = new InspectionTypeMasterDTO();
		List<InspectionTypeMasterDTO> inspTypeMasDTOList = null;
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			Integer subscriberId = null;
			if(null != userDto){
				subscriberId = userDto.getSubscriberId();
			}
			inspTypeMasDTOList = inspTypeMasterService.getAllInspTypeMasterData(subscriberId);
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setInspTypeMasterList(inspTypeMasDTOList);
			return new ResponseEntity(responseDTO, HttpStatus.OK);
		}catch(Exception exception){
			logger.error("Error while fetching the data : "+exception.getMessage());
		}
		return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
