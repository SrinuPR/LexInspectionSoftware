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
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.dto.LoginDTO;
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
	public @ResponseBody ResponseEntity<String> saveComponentMasterData(@RequestBody ComponentMasterDataDTO componentMasterDataDTO, HttpSession httpSession){
		logger.info("Entered into saveComponentMasterData");
		String status = StatusConstants.FAILURE;
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			if(null != userDto)
				userName = userDto.getUserName();
			status = componentMasterDataService.saveComponentMasterData(componentMasterDataDTO,userName);
			return new ResponseEntity(status,StatusConstants.SUCCESS.equalsIgnoreCase(status)? HttpStatus.OK:HttpStatus.GONE);
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
	public @ResponseBody ResponseEntity<String> updateComponentMasterData(@RequestBody ComponentMasterDataDTO componentMasterDataDTO, HttpSession httpSession){
		logger.info("Entered into updateComponentMasterData");
		String status = StatusConstants.FAILURE;
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			if(null != userDto)
				userName = userDto.getUserName();
			status = componentMasterDataService.updateComponentMasterData(componentMasterDataDTO, userName);
			return new ResponseEntity(status,StatusConstants.SUCCESS.equalsIgnoreCase(status)? HttpStatus.OK:HttpStatus.GONE);
		}catch(Exception exception){
			logger.error("Error while updating the data : "+exception.getMessage());
			return new ResponseEntity(status,HttpStatus.METHOD_FAILURE);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<ComponentMasterDataDTO>> displayComponentMasterData(){
		logger.info("Entered into displayComponentMasterData");
		List<ComponentMasterDataDTO> componentMasterDataDTOs = null;
		try{
			componentMasterDataDTOs = componentMasterDataService.getAllComponentMasterData();
			return new ResponseEntity(componentMasterDataDTOs,HttpStatus.OK);
		}catch(Exception exception){
			logger.error("Error while fetching the data : "+exception.getMessage());
		}
		return new ResponseEntity(componentMasterDataDTOs,HttpStatus.INTERNAL_SERVER_ERROR);
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
	public @ResponseBody ResponseEntity<String> deleteComponent(@PathVariable("componentId") Integer componentId){
		try{
			String status = componentMasterDataService.deleteComponent(componentId);
			return new ResponseEntity(status,HttpStatus.OK);
		}catch(Exception exception){
			logger.error("Exception while sending the details "+exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.FAILURE,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
