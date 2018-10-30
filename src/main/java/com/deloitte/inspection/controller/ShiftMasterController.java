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

import com.deloitte.inspection.constant.ComponentConstants;
import com.deloitte.inspection.constant.ShiftMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.ShiftMasterDTO;
import com.deloitte.inspection.response.dto.ShiftMasterResponseDTO;
import com.deloitte.inspection.service.ShiftMasterService;

@RestController
@RequestMapping(value = "/createShiftMaster")
public class ShiftMasterController {
	
	private static final Logger logger = LogManager.getLogger(CreateUserController.class);
	
	@Autowired
	private ShiftMasterService shiftMasterService;
	
	
	/**
	 * @param createShiftMasterDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate/{shiftId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<CommonDTO> validateShiftId(@PathVariable("shiftId") String shiftId){
		CommonDTO commonDTO = new CommonDTO();
		try{
			commonDTO = shiftMasterService.validateShiftId(shiftId);
			if(StatusConstants.SUCCESS.equalsIgnoreCase(commonDTO.getStatus())){
				return new ResponseEntity(commonDTO, HttpStatus.OK);
			}else{
				return new ResponseEntity(commonDTO, HttpStatus.EXPECTATION_FAILED);
			}
			
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating Shift ID " + exception.getMessage());
		}
		commonDTO.setStatus(StatusConstants.ERROR);
		commonDTO.setMessage(ShiftMasterConstants.SOMETHING_WENT_WRONG);
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/createShift", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ShiftMasterResponseDTO> createShiftMaster(@RequestBody ShiftMasterDTO createShiftMasterDTO,HttpSession httpSession){

		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			if(null != userDto){
				userId = userDto.getUserId();
			}else{
				userId = StatusConstants.DEFAULT_USER_ID;
			}
			createShiftMasterDTO.setCreatedBy(userId);
			ShiftMasterResponseDTO response = shiftMasterService.createShiftMaster(createShiftMasterDTO);
			if(null != response && response.getMessage().equals(ShiftMasterConstants.SHIFT_SAVE_SUCCESS)) {
				response.setStatus(StatusConstants.SUCCESS);
				return new ResponseEntity(response, HttpStatus.OK);
			}else {
				response.setStatus(StatusConstants.FAILURE);
				return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While creating shift Master " + exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}

@CrossOrigin
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping(value = "/all", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody ResponseEntity<ShiftMasterResponseDTO> getShiftMasterData(@RequestBody ShiftMasterDTO shiftMasterDTO){
	logger.info("Entered into getShiftMasterData");
	ShiftMasterResponseDTO ShiftMasterResponseDTO = new ShiftMasterResponseDTO();
	List<ShiftMasterDTO> ShiftMasterDTOs = null;
	try{
		
		ShiftMasterResponseDTO = shiftMasterService.getAllShifts(shiftMasterDTO.getSubscriberId());
		if(null!=ShiftMasterResponseDTO.getResult() && ShiftMasterConstants.FETCH_SHIFT_LIST_SUCCESS.equals(ShiftMasterResponseDTO.getMessage())){
			ShiftMasterResponseDTO.setStatus(StatusConstants.SUCCESS);
			return new ResponseEntity(ShiftMasterResponseDTO,HttpStatus.OK);	
		}
		
	}catch(Exception exception){
		logger.error("Error while fetching the data : "+exception.getMessage());
	}
	ShiftMasterResponseDTO.setStatus(StatusConstants.ERROR);
	return new ResponseEntity(ShiftMasterResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
}

@CrossOrigin
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping(value = "/delete/{shiftId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody ResponseEntity<ShiftMasterResponseDTO> deleteShift(@PathVariable("shiftId") String shiftId,HttpSession httpSession){
	ShiftMasterDTO ShiftMasterDTO = new ShiftMasterDTO();
	ShiftMasterResponseDTO shiftMasterResponseDTO=new ShiftMasterResponseDTO();
	shiftMasterResponseDTO.setStatus(StatusConstants.ERROR);
	try{
		LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
		String userId = null;
		if(null != userDto){
			userId = userDto.getUserId();
		}else{
			userId = StatusConstants.DEFAULT_USER_ID;
		}
		shiftMasterResponseDTO.setStatus(StatusConstants.FAILURE);
		shiftMasterResponseDTO = shiftMasterService.deleteShift(shiftId,userId);
		if(ShiftMasterConstants.SHIFT_DELETE_SUCCESS.equalsIgnoreCase(shiftMasterResponseDTO.getMessage())){
			shiftMasterResponseDTO.setStatus(StatusConstants.SUCCESS);
			return new ResponseEntity(shiftMasterResponseDTO,HttpStatus.OK);
		}else{
			return new ResponseEntity(shiftMasterResponseDTO,HttpStatus.EXPECTATION_FAILED);
		}
		
	}catch(Exception exception){
		logger.error("Exception while deleting the shift id "+exception.getMessage());
		
	}
	
	return new ResponseEntity(shiftMasterResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
}


@CrossOrigin
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping(value = "/updateShift", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody ResponseEntity<ShiftMasterResponseDTO> updateShiftMaster(@RequestBody ShiftMasterDTO updateShiftMasterDTO,HttpSession httpSession){

	try{
		LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
		String userId = null;
		if(null != userDto){
			userId = userDto.getUserId();
		}else{
			userId = StatusConstants.DEFAULT_USER_ID;
		}
		updateShiftMasterDTO.setUpdatedBy(userId);
		ShiftMasterResponseDTO response = shiftMasterService.updateShiftMaster(updateShiftMasterDTO);
		if(null != response && response.getMessage().equals(ShiftMasterConstants.SHIFT_SAVE_SUCCESS)) {
			response.setStatus(StatusConstants.SUCCESS);
			return new ResponseEntity(response, HttpStatus.OK);
		}else {
			response.setStatus(StatusConstants.FAILURE);
			return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
		}
	}catch(Exception exception){
		exception.printStackTrace();
		logger.error("Exception while updating shift Master " + exception.getMessage());
	}
	return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
}
}