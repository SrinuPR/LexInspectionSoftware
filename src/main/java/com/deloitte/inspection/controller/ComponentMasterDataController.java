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
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.response.dto.ComponentMasterResponseDataDTO;
import com.deloitte.inspection.service.ComponentMasterDataService;

@RestController
@RequestMapping(value = "/component")
public class ComponentMasterDataController {

	private static final Logger logger = LogManager.getLogger(ComponentMasterDataController.class);  
	
	@Autowired
	private ComponentMasterDataService componentMasterDataService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ComponentMasterResponseDataDTO> saveComponentMasterData(@RequestBody ComponentMasterDataDTO componentMasterDataDTO, HttpSession httpSession){
		logger.info("Entered into saveComponentMasterData");
		String status = StatusConstants.FAILURE;
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			String userId = null;
			if(null != userDto){
				userName = userDto.getUserName();
				userId = userDto.getUserId();
			}else{
				userName = "Srinu";
				userId = "Srinu123";
			}
			ComponentMasterResponseDataDTO componentMasterResponseDataDTO = new ComponentMasterResponseDataDTO();
			status = componentMasterDataService.saveComponentMasterData(componentMasterDataDTO,userName,userId);
			List<ComponentMasterDataDTO> componentMasterData = null;
			if(StatusConstants.SUCCESS.equalsIgnoreCase(status)){
				componentMasterData  = componentMasterDataService.getAllComponentMasterData(userId);
				componentMasterResponseDataDTO.setResult(componentMasterData);
				componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_UPDATE_SUCCESS);
				componentMasterResponseDataDTO.setStatus(StatusConstants.SUCCESS);
				return new ResponseEntity(componentMasterResponseDataDTO,HttpStatus.OK);
			}else{
				componentMasterResponseDataDTO.setStatus(StatusConstants.ERROR);
				componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_SAVE_FAILED);
				return new ResponseEntity(componentMasterResponseDataDTO,HttpStatus.EXPECTATION_FAILED);
			}	
		}catch(Exception exception){
			logger.error("Error while saving the data : "+exception.getMessage());
			return new ResponseEntity(status,HttpStatus.METHOD_FAILURE);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@RequestMapping(value = "/edit/{componentId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ComponentMasterDataDTO> editComponentMasterData(@PathVariable("componentId") Integer componentId){
		logger.info("Entered into saveComponentMasterData");
		ComponentMasterDataDTO componentMasterDataDTO = null;
		try{
			componentMasterDataDTO = componentMasterDataService.getComponentDataById(componentId);
			if(null != componentMasterDataDTO)
				return new ResponseEntity(componentMasterDataDTO,HttpStatus.OK);
		}catch(Exception exception){
			logger.error("Error while saving the data : "+exception.getMessage());
			return new ResponseEntity(componentMasterDataDTO,HttpStatus.METHOD_FAILURE);
		}
		return new ResponseEntity(componentMasterDataDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ComponentMasterResponseDataDTO> updateComponentMasterData(@RequestBody ComponentMasterDataDTO componentMasterDataDTO, HttpSession httpSession){
		logger.info("Entered into updateComponentMasterData");
		String status = StatusConstants.FAILURE;
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			String userId = null;
			if(null != userDto){
				userName = userDto.getUserName();
				userId = userDto.getUserId();
			}else{
				userName = "Srinu";
				userId = "Srinu123";
			}
			ComponentMasterResponseDataDTO componentMasterResponseDataDTO = new ComponentMasterResponseDataDTO();
			status = componentMasterDataService.updateComponentMasterData(componentMasterDataDTO, userName);		
			List<ComponentMasterDataDTO> componentMasterData = null;
			if(StatusConstants.SUCCESS.equalsIgnoreCase(status)){
				componentMasterData  = componentMasterDataService.getAllComponentMasterData(userId);
				componentMasterResponseDataDTO.setResult(componentMasterData);
				componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_UPDATE_SUCCESS);
				componentMasterResponseDataDTO.setStatus(StatusConstants.SUCCESS);
				return new ResponseEntity(componentMasterResponseDataDTO,HttpStatus.OK);
			}else{
				componentMasterResponseDataDTO.setStatus(StatusConstants.ERROR);
				componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_UPDATE_FAILED);
				return new ResponseEntity(componentMasterResponseDataDTO,HttpStatus.EXPECTATION_FAILED);
			}	
		}catch(Exception exception){
			logger.error("Error while updating the data : "+exception.getMessage());
			return new ResponseEntity(status,HttpStatus.METHOD_FAILURE);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ComponentMasterResponseDataDTO> displayComponentMasterData(HttpSession httpSession){
		logger.info("Entered into displayComponentMasterData");
		ComponentMasterResponseDataDTO componentMasterResponseDataDTO = new ComponentMasterResponseDataDTO();
		List<ComponentMasterDataDTO> componentMasterDataDTOs = null;
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			if(null != userDto){
				userId = userDto.getUserId();
			}else{
				userId = "Srinu123";
			}
			componentMasterDataDTOs = componentMasterDataService.getAllComponentMasterData(userId);
			componentMasterResponseDataDTO.setResult(componentMasterDataDTOs);
			componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_LIST_SUCCESS);
			componentMasterResponseDataDTO.setStatus(StatusConstants.SUCCESS);
			return new ResponseEntity(componentMasterResponseDataDTO,HttpStatus.OK);
		}catch(Exception exception){
			logger.error("Error while fetching the data : "+exception.getMessage());
		}
		componentMasterResponseDataDTO.setStatus(StatusConstants.ERROR);
		componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_LIST_FAILED);
		return new ResponseEntity(componentMasterResponseDataDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<LoginDTO> createComponent(HttpSession httpSession){
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			return new ResponseEntity(userDto,HttpStatus.OK);
		}catch(Exception exception){
			logger.error("Exception while sending the details "+exception.getMessage());
		}
		return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/delete/{componentId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ComponentMasterResponseDataDTO> deleteComponent(@PathVariable("componentId") Integer componentId){
		ComponentMasterResponseDataDTO componentMasterResponseDataDTO = new ComponentMasterResponseDataDTO();
		try{
			String status = componentMasterDataService.deleteComponent(componentId);
			if(StatusConstants.SUCCESS.equalsIgnoreCase(status)){
				componentMasterResponseDataDTO.setStatus(status);
				componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_DELETE_SUCCESS);
				return new ResponseEntity(componentMasterResponseDataDTO,HttpStatus.OK);
			}else{
				componentMasterResponseDataDTO.setStatus(StatusConstants.ERROR);
				componentMasterResponseDataDTO.setMessage(ComponentConstants.COMPONENT_DELETE_FAILED);
				return new ResponseEntity(componentMasterResponseDataDTO,HttpStatus.EXPECTATION_FAILED);
			}
			
		}catch(Exception exception){
			logger.error("Exception while sending the details "+exception.getMessage());
		}
		return new ResponseEntity(componentMasterResponseDataDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate/{customerDrawNumber}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<CommonDTO> validateComponentDrawNumber(@PathVariable("customerDrawNumber") String customerDrawNumber){
		CommonDTO commonDTO = new CommonDTO();
		try{
			if(null != customerDrawNumber){
				commonDTO = componentMasterDataService.validateComponentDrawNumber(customerDrawNumber.trim().toLowerCase());
				if(StatusConstants.SUCCESS.equalsIgnoreCase(commonDTO.getStatus())){
					return new ResponseEntity(commonDTO, HttpStatus.OK);
				}else{
					return new ResponseEntity(commonDTO, HttpStatus.EXPECTATION_FAILED);
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception while validating PO Number " + exception.getMessage());
		}
		commonDTO.setStatus(StatusConstants.ERROR);
		commonDTO.setMessage(ComponentConstants.SOMETHING_WENT_WRONG);
		return new ResponseEntity(commonDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
