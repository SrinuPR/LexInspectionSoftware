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

import com.deloitte.inspection.constant.InspectionLineItemConstants;
import com.deloitte.inspection.constant.InspectionReportMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dto.InspectionLineItemDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.response.dto.InspectionLineItemResponseDTO;
import com.deloitte.inspection.service.InspectionLineItemMasterService;

@RestController
@RequestMapping(value = "/insplineitem")
public class InspectionLineItemController {
	
	private static final Logger logger = LogManager.getLogger(InspectionLineItemController.class);
	
	@Autowired
	private InspectionLineItemMasterService inspectionLineItemMasterService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/componentproductdrawNum/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionLineItemResponseDTO> getComponentProductDrawNumbers(@PathVariable("subscriberId")Integer subscriberId){
		logger.info("Inside getComponentProductDrawNumbers ");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			inspectionLineItemResponseDTO = inspectionLineItemMasterService.getComponentProductDrawNumbers(subscriberId);
			if(null != inspectionLineItemResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionLineItemResponseDTO.getStatus())){
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.OK);
			}else{
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception Occurred getComponentProductDrawNumbers "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
			return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/all/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionLineItemResponseDTO> getAllInspectionLineItems(@PathVariable("subscriberId") Integer subscriberId){
		logger.info("Inside getAllInspectionLineItems");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			inspectionLineItemResponseDTO = inspectionLineItemMasterService.getAllInspectionLineItems(subscriberId);
			if(null != inspectionLineItemResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionLineItemResponseDTO.getStatus())){
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.OK);
			}else{
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception Occurred getAllInspectionLineItems "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
			return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/measuresave", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionLineItemResponseDTO> measureItemSave(HttpSession httpSession,@RequestBody List<InspectionLineItemDTO> inspectionLineItems){
		logger.info("Inside measureItemSave");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			String userName = null;
			Integer subscriberId = null;
			if(null != userDto){
				userId = userDto.getUserId();
				userName = userDto.getUserName();
			}else{
				userId = StatusConstants.DEFAULT_USER_ID;
				userName = StatusConstants.DEFAULT_USER_NAME;
			}
			inspectionLineItemResponseDTO = inspectionLineItemMasterService.measureItemSave(inspectionLineItems,userId, userName,subscriberId);
			if(null != inspectionLineItemResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionLineItemResponseDTO.getStatus())){
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.OK);
			}else{
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception Occurred measureItemSave "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
			return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/reportsave", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionLineItemResponseDTO> reportSave(HttpSession httpSession,@RequestBody List<InspectionLineItemDTO> inspectionLineItems){
		logger.info("Inside reportSave");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			String userName = null;
			Integer subscriberId = null;
			if(null != userDto){
				userId = userDto.getUserId();
				userName = userDto.getUserName();
				subscriberId = userDto.getSubscriberId();
			}else{
				userId = StatusConstants.DEFAULT_USER_ID;
				userName = StatusConstants.DEFAULT_USER_NAME;
			}
			inspectionLineItemResponseDTO = inspectionLineItemMasterService.reportSave(inspectionLineItems,userId,userName,subscriberId);
			if(null != inspectionLineItemResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionLineItemResponseDTO.getStatus())){
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.OK);
			}else{
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception Occurred reportsave "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
			return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/validate/measurementname", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionLineItemResponseDTO> validateMeasurementName(@RequestBody InspectionLineItemDTO inspectionLineItem){
		logger.info("Inside measurementname");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			inspectionLineItemResponseDTO = inspectionLineItemMasterService.validateMeasurementName(inspectionLineItem);
			if(null != inspectionLineItemResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionLineItemResponseDTO.getStatus())){
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.OK);
			}else{
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception Occurred measurementname "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
			return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionLineItemResponseDTO> updateInspectionData(@RequestBody List<InspectionLineItemDTO> inspectionLineItem,HttpSession httpSession){
		logger.info("Inside reportSave");
		InspectionLineItemResponseDTO inspectionLineItemResponseDTO = new InspectionLineItemResponseDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			String userId = null;
			Integer subscriberId = null;
			if(null != userDto){
				userName = userDto.getUserName();
				userId = userDto.getUserId();
				subscriberId = userDto.getSubscriberId();
			}else{
				userName = StatusConstants.DEFAULT_USER_NAME;
				userId = StatusConstants.DEFAULT_USER_ID;
			}
			inspectionLineItemResponseDTO = inspectionLineItemMasterService.updateInspectionData(inspectionLineItem,userName,userId,subscriberId);
			if(null != inspectionLineItemResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionLineItemResponseDTO.getStatus())){
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.OK);
			}else{
				return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception Occurred reportsave "+exception.getMessage());
			inspectionLineItemResponseDTO.setStatus(StatusConstants.ERROR);
			inspectionLineItemResponseDTO.setMessage(InspectionLineItemConstants.UN_EXPECTED_EXCEPTION);
			return new ResponseEntity(inspectionLineItemResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/report/{compdrawnum}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionLineItemResponseDTO> validateInspReportNum(@PathVariable("compdrawnum") String compdrawnum){
		logger.info("Inside compdrawnum");
		InspectionLineItemResponseDTO inspLineItemResDto = new InspectionLineItemResponseDTO();
		try{
			inspLineItemResDto = inspectionLineItemMasterService.getLineItemByCompDraNum(compdrawnum.toLowerCase());
			if(null != inspLineItemResDto && StatusConstants.SUCCESS.equalsIgnoreCase(inspLineItemResDto.getStatus())){
				return new ResponseEntity(inspLineItemResDto, HttpStatus.OK);
			}else{
				return new ResponseEntity(inspLineItemResDto, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception Occurred validateInspReportNum "+exception.getMessage());
			inspLineItemResDto.setStatus(StatusConstants.ERROR);
			inspLineItemResDto.setMessage(InspectionReportMasterConstants.UN_EXPECTED_EXCEPTION);
			return new ResponseEntity(inspLineItemResDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
