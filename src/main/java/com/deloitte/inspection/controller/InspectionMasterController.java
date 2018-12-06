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
import com.deloitte.inspection.dto.InspectionMasterDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.WorkJobOrderDTO;
import com.deloitte.inspection.exception.WorkJobOrderException;
import com.deloitte.inspection.response.dto.InspectionMasterResponseDataDTO;
import com.deloitte.inspection.response.dto.WorkJobOrderResponseDTO;
import com.deloitte.inspection.service.InspectionMasterService;
import com.deloitte.inspection.service.WorkJobOrderService;

@RestController
@RequestMapping(value = "/inspectionMaster")
public class InspectionMasterController {
	
	private static final Logger logger = LogManager.getLogger(InspectionMasterController.class);
	
	@Autowired
	private InspectionMasterService inspectionMasterService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validateStage/", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMasterResponseDataDTO> validateInspectionStage(@RequestBody InspectionMasterDTO inspectionMasterDTO){
		logger.info("Entered into validateInspectionStage");
		InspectionMasterResponseDataDTO inspectionResponseDTO = null;
		try {
			inspectionResponseDTO = inspectionMasterService.validateInspectionStage(inspectionMasterDTO);
			if (inspectionResponseDTO != null && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionResponseDTO.getStatus())) {
				return new ResponseEntity(inspectionResponseDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity(inspectionResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception exception) {
			logger.error("Exception while validating the InspectionStage : " + exception.getMessage());
		}
		return new ResponseEntity(inspectionResponseDTO, HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMasterResponseDataDTO> saveInspectionMaster(@RequestBody InspectionMasterDTO inspectionMasterDTO, HttpSession httpSession){
		logger.info("Entered into saveInspectionMaster");
		InspectionMasterResponseDataDTO inspectionMasterResponseDataDTO = null;
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			String userId = null;
			if(null != userDto){
				userName = userDto.getUserName();
				userId = userDto.getUserId();
			}else{
				userName = StatusConstants.DEFAULT_USER_NAME;
				userId = StatusConstants.DEFAULT_USER_ID;
			}
			inspectionMasterResponseDataDTO = inspectionMasterService.saveInspectionMaster(inspectionMasterDTO,userName,userId,StatusConstants.INSERT);
			if (null != inspectionMasterResponseDataDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionMasterResponseDataDTO.getStatus())) {
				return new ResponseEntity(inspectionMasterResponseDataDTO, HttpStatus.OK);
			}
		} catch (Exception exception) {
			logger.error("Exception while saving Inspection Master data : " + exception.getMessage());
			exception.printStackTrace();
		}
		return new ResponseEntity(inspectionMasterResponseDataDTO, HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMasterResponseDataDTO> getAllInspectionMasterData(HttpSession httpSession){
		logger.info("Entered into getAllInspectionMasterData");
		InspectionMasterResponseDataDTO inspectionResponseDTO = new InspectionMasterResponseDataDTO();
		try {
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			if (null != userDto) {
				userId = userDto.getUserId();
			} else {
				userId = StatusConstants.DEFAULT_USER_ID;
			}
			inspectionResponseDTO = inspectionMasterService.getInspectionMasterData(userId);
			if (inspectionResponseDTO != null && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionResponseDTO.getStatus())){
				return new ResponseEntity(inspectionResponseDTO, HttpStatus.OK);
			}
		} catch (Exception exception) {
			logger.error("Exception while fetching the Inspection Master data : " + exception.getMessage());
		}
		return new ResponseEntity(inspectionResponseDTO, HttpStatus.EXPECTATION_FAILED);
	}
	

}
