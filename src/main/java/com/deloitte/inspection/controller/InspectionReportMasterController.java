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

import com.deloitte.inspection.constant.InspectionReportMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dto.InspectionReportMasterDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.response.dto.InspectionLineItemResponseDTO;
import com.deloitte.inspection.response.dto.InspectionReportMasterResponseDTO;
import com.deloitte.inspection.service.InspectionReportMasterService;

@RestController
@RequestMapping(value = "/inspectionreportmaster")
public class InspectionReportMasterController {

	

	private static final Logger logger = LogManager.getLogger(InspectionReportMasterController.class);  
	
	@Autowired
	private InspectionReportMasterService inspRptMasterService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/{inspreportnumber}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionLineItemResponseDTO> validateInspReportNum(@PathVariable("inspreportnumber") Integer inspReportNum){
		logger.info("Inside validateInspReportNum");
		InspectionReportMasterResponseDTO inspRptMasResDto = new InspectionReportMasterResponseDTO();
		try{
			inspRptMasResDto = inspRptMasterService.validateInspReportNumber(inspReportNum);
			if(null != inspRptMasResDto && StatusConstants.SUCCESS.equalsIgnoreCase(inspRptMasResDto.getStatus())){
				return new ResponseEntity(inspRptMasResDto, HttpStatus.OK);
			}else{
				return new ResponseEntity(inspRptMasResDto, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception Occurred validateInspReportNum "+exception.getMessage());
			inspRptMasResDto.setStatus(StatusConstants.ERROR);
			inspRptMasResDto.setMessage(InspectionReportMasterConstants.UN_EXPECTED_EXCEPTION);
			return new ResponseEntity(inspRptMasResDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/reportsave", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionReportMasterResponseDTO> saveBlankReport(HttpSession httpSession,@RequestBody InspectionReportMasterDTO inspRptMasterDto){
		logger.info("Inside saveBlankReport");
		InspectionReportMasterResponseDTO inspRptMasResDto = new InspectionReportMasterResponseDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			String userName = null;
			if(null != userDto){
				userId = userDto.getUserId();
				userName = userDto.getUserName();
			}else{
				userId = StatusConstants.DEFAULT_USER_ID;
				userName = StatusConstants.DEFAULT_USER_NAME;
			}
			inspRptMasResDto = inspRptMasterService.saveBlankReport(inspRptMasterDto,userId,userName);
			if(null != inspRptMasResDto && StatusConstants.SUCCESS.equalsIgnoreCase(inspRptMasResDto.getStatus())){
				return new ResponseEntity(inspRptMasResDto, HttpStatus.OK);
			}else{
				return new ResponseEntity(inspRptMasResDto, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception Occurred reportsave "+exception.getMessage());
			inspRptMasResDto.setStatus(StatusConstants.ERROR);
			inspRptMasResDto.setMessage(InspectionReportMasterConstants.UN_EXPECTED_EXCEPTION);
			return new ResponseEntity(inspRptMasResDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
