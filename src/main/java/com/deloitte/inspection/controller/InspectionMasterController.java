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

import com.deloitte.inspection.constant.InspectionMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dto.InspectionMasterDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.response.dto.ComponentMasterResponseDataDTO;
import com.deloitte.inspection.response.dto.InspectionMasterResponseDataDTO;
import com.deloitte.inspection.service.InspectionMasterService;

@RestController
@RequestMapping(value = "/inspectionMaster")
public class InspectionMasterController {

	private static final Logger logger = LogManager.getLogger(InspectionMasterController.class);

	@Autowired
	private InspectionMasterService inspectionMasterService;

	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validateStage/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMasterResponseDataDTO> validateInspectionStage(
			@RequestBody InspectionMasterDTO inspectionMasterDTO) {
		logger.info("Entered into validateInspectionStage");
		InspectionMasterResponseDataDTO inspectionResponseDTO = null;
		try {
			inspectionResponseDTO = inspectionMasterService.validateInspectionStage(inspectionMasterDTO);
			if (inspectionResponseDTO != null
					&& StatusConstants.SUCCESS.equalsIgnoreCase(inspectionResponseDTO.getStatus())) {
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
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMasterResponseDataDTO> saveInspectionMaster(
			@RequestBody InspectionMasterDTO inspectionMasterDTO, HttpSession httpSession) {
		logger.info("Entered into saveInspectionMaster");
		InspectionMasterResponseDataDTO inspectionMasterResponseDataDTO = null;
		try {
			inspectionMasterDTO.setCreatedBy(this.getUserDetails(httpSession).getUserName());
			inspectionMasterResponseDataDTO = inspectionMasterService.saveInspectionMaster(inspectionMasterDTO);
			if (null != inspectionMasterResponseDataDTO
					&& StatusConstants.SUCCESS.equalsIgnoreCase(inspectionMasterResponseDataDTO.getStatus())) {
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
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMasterResponseDataDTO> updateInspectionMaster(
			@RequestBody InspectionMasterDTO inspectionMasterDTO, HttpSession httpSession) {
		logger.info("Entered into updateInspectionMaster");
		InspectionMasterResponseDataDTO inspectionMasterResponseDataDTO = null;
		try {
			inspectionMasterDTO.setUpdatedBy(this.getUserDetails(httpSession).getUserName());
			inspectionMasterResponseDataDTO = inspectionMasterService.updateInspectionMaster(inspectionMasterDTO);
			if (null != inspectionMasterResponseDataDTO
					&& StatusConstants.SUCCESS.equalsIgnoreCase(inspectionMasterResponseDataDTO.getStatus())) {
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
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMasterResponseDataDTO> getAllInspectionMasterData(
			HttpSession httpSession) {
		logger.info("Entered into getAllInspectionMasterData");
		InspectionMasterResponseDataDTO inspectionResponseDTO = new InspectionMasterResponseDataDTO();
		try {
			inspectionResponseDTO = inspectionMasterService
					.getInspectionMasterData(this.getUserDetails(httpSession).getUserId());
			if (inspectionResponseDTO != null
					&& StatusConstants.SUCCESS.equalsIgnoreCase(inspectionResponseDTO.getStatus())) {
				return new ResponseEntity(inspectionResponseDTO, HttpStatus.OK);
			}
		} catch (Exception exception) {
			logger.error("Exception while fetching the Inspection Master data : " + exception.getMessage());
		}
		return new ResponseEntity(inspectionResponseDTO, HttpStatus.EXPECTATION_FAILED);
	}

	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMasterResponseDataDTO> deleteInspectionMaster(
			@RequestBody InspectionMasterDTO inspectionMasterDTO, HttpSession httpSession) {
		InspectionMasterResponseDataDTO inspectionResponseDTO = new InspectionMasterResponseDataDTO();
		try {
			String status = inspectionMasterService.deleteInspectionMaster(inspectionMasterDTO.getInspectionMasterId());
			if (StatusConstants.SUCCESS.equalsIgnoreCase(status)) {
				inspectionResponseDTO.setStatus(status);
				inspectionResponseDTO.setMessage(InspectionMasterConstants.INSPECTION_MASTER_DELETE_SUCCESS);
				return new ResponseEntity(inspectionResponseDTO, HttpStatus.OK);
			} else {
				inspectionResponseDTO.setStatus(StatusConstants.ERROR);
				inspectionResponseDTO.setMessage(InspectionMasterConstants.INSPECTION_MASTER_DELETE_FAILED);
				return new ResponseEntity(inspectionResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}

		} catch (Exception exception) {
			logger.error("Exception while deleting the Inspection Master " + exception.getMessage());
		}
		return new ResponseEntity(inspectionResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private LoginDTO getUserDetails(HttpSession session) {
		LoginDTO userDto = null;
		try {
			userDto = (LoginDTO) session.getAttribute("user");
			if (userDto == null) {
				userDto = new LoginDTO();
				userDto.setUserId(StatusConstants.DEFAULT_USER_ID);
				userDto.setUserName(StatusConstants.DEFAULT_USER_NAME);
			}
		} catch (Exception exception) {
			logger.error("Exception while fetching User details from HttpSession : " + exception.getMessage());
		}
		return userDto;
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/inspData/{compProdDrawNum}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMasterResponseDataDTO> getInspectionTypesByCompProdDrawNum(@PathVariable("compProdDrawNum") String compProdDrawNum) {
		logger.info("Entered into getInspectionTypesByCompProdDrawNum ");
		InspectionMasterResponseDataDTO inspectionResponseDTO = null;
		try {
			inspectionResponseDTO = inspectionMasterService.getInspectionTypesByCompProdDrawNum(compProdDrawNum);
			if (inspectionResponseDTO != null
					&& StatusConstants.SUCCESS.equalsIgnoreCase(inspectionResponseDTO.getStatus())) {
				return new ResponseEntity(inspectionResponseDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity(inspectionResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception exception) {
			logger.error("Exception while validating getInspectionTypesByCompProdDrawNum : " + exception.getMessage());
		}
		return new ResponseEntity(inspectionResponseDTO, HttpStatus.EXPECTATION_FAILED);
	}

	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/componentDrawNum/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ComponentMasterResponseDataDTO> getCompDrawNumList(@PathVariable("subscriberId") Integer subscriberId){
		logger.info("Entered into getCompDrawNumList");
		ComponentMasterResponseDataDTO componentDrawNumbers = new ComponentMasterResponseDataDTO();
		try{
			if(null != subscriberId){
				componentDrawNumbers = inspectionMasterService.getCompDrawNumsBySubscriberId(subscriberId);
				if(null != componentDrawNumbers && StatusConstants.SUCCESS.equalsIgnoreCase(componentDrawNumbers.getStatus()))
					return new ResponseEntity(componentDrawNumbers,HttpStatus.OK);
				else
					return new ResponseEntity(componentDrawNumbers,HttpStatus.EXPECTATION_FAILED);
			}else{
				return new ResponseEntity(componentDrawNumbers,HttpStatus.PRECONDITION_FAILED);
			}
		}catch(Exception exception){
			logger.info("Exception At  getCompDrawNumList "+exception.getMessage());
			return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
