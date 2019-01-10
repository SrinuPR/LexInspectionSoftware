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
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.PasswordMaintenanceDTO;
import com.deloitte.inspection.service.LoginService;

@RestController
@RequestMapping(value = "/user")
public class LoginController {
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);  
	
	@Autowired
	private LoginService loginService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<LoginDTO> validateLoginCredentials(@RequestBody LoginDTO loginDTO, HttpSession httpSession){
		LoginDTO responseDTO = new LoginDTO();
		try{
			/*httpSession.setMaxInactiveInterval(StatusConstants.MAX_INACTIVE_INTERVAL);*/
			responseDTO = loginService.validateLoginCredentials(loginDTO,httpSession);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/logout/{userId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<LoginDTO> logout(HttpSession httpSession,@PathVariable("userId") String userId) {
		LoginDTO loginDto = (LoginDTO)httpSession.getAttribute("user");
		try {
			if(null == loginDto){
				loginDto = new LoginDTO();
				loginDto.setStatus(StatusConstants.SUCCESS);
			}else{
				loginDto.setStatus(StatusConstants.SUCCESS);
			}
			loginService.logout(userId);
			httpSession.invalidate();
			return new ResponseEntity(loginDto, HttpStatus.OK);
		}catch (Exception exception) {
			logger.error("Exception While logout the user "+exception.getMessage());
			return new ResponseEntity(loginDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/forgot/password", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> forgotPassword(@RequestBody PasswordMaintenanceDTO passwordMaintenanceDTO) {
		String status = null;
		try {
			status = loginService.forgotPassword(passwordMaintenanceDTO);
			if(StatusConstants.PASSWORD_CHANGED_SUCCESS.equalsIgnoreCase(status))
				return new ResponseEntity(status, HttpStatus.OK);
			else
				return new ResponseEntity(status, HttpStatus.EXPECTATION_FAILED);
		}catch (Exception exception) {
			logger.error("Exception While performing forgot password "+exception.getMessage());
		}
		return new ResponseEntity(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/change/password", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> changePassword(@RequestBody PasswordMaintenanceDTO passwordMaintenanceDTO) {
		String status = null;
		try {
			status = loginService.changePassword(passwordMaintenanceDTO);
			if(StatusConstants.PASSWORD_CHANGED_SUCCESS.equalsIgnoreCase(status))
				return new ResponseEntity(status, HttpStatus.OK);
			else
				return new ResponseEntity(status, HttpStatus.EXPECTATION_FAILED);
		}catch (Exception exception) {
			logger.error("Exception While user changing the password "+exception.getMessage());
		}
		return new ResponseEntity(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate/session", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<LoginDTO> validate(HttpSession httpSession) {
		try {
			LoginDTO ut = (LoginDTO)httpSession.getAttribute("user");
			return new ResponseEntity(ut, ut==null?HttpStatus.UNAUTHORIZED:HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/active/{userId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<LoginDTO> resetLogin(@PathVariable("userId") String userId) {
		LoginDTO loginDTO = new LoginDTO();
		try {
			if(null != userId){
				String status = loginService.resetLogin(userId);
				if(null != status && StatusConstants.SUCCESS.equalsIgnoreCase(status)){
					loginDTO.setStatus(StatusConstants.SUCCESS);
					return new ResponseEntity(loginDTO,HttpStatus.OK);
				}else{
					loginDTO.setErrorMessage(status);
					return new ResponseEntity(loginDTO,HttpStatus.EXPECTATION_FAILED);
				}
			}else{
				loginDTO.setErrorMessage(StatusConstants.FAILURE);
				return new ResponseEntity(loginDTO,HttpStatus.PRECONDITION_FAILED);
			}
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
