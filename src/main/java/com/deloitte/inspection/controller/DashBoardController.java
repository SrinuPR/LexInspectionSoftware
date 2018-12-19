package com.deloitte.inspection.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.response.dto.DashBoardResponseDTO;
import com.deloitte.inspection.service.DashBoardService;

@RestController
@RequestMapping(value = "/dashboard")
public class DashBoardController {
	
	private static final Logger logger = LogManager.getLogger(DashBoardController.class);
	
	@Autowired
	private DashBoardService dashBoardService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@RequestMapping(value = "/userData/{userId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<DashBoardResponseDTO> getDashboardData(@PathVariable("userId") String userId){
		logger.info("Entered into getDashboardData ");
		DashBoardResponseDTO responseDTO = null;
		try{
			responseDTO = dashBoardService.getDashboardData(userId);
			if(null != responseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(responseDTO.getStatus()))
				return new ResponseEntity(responseDTO,HttpStatus.OK);
		}catch(Exception exception){
			logger.error("Error while fetching the data : "+exception.getMessage());
			return new ResponseEntity(responseDTO,HttpStatus.METHOD_FAILURE);
		}
		return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
