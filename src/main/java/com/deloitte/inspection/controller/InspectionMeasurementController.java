package com.deloitte.inspection.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.deloitte.inspection.dto.InspectionTypeMasterDTO;
import com.deloitte.inspection.response.dto.WorkJobOrderResponseDTO;
import com.deloitte.inspection.service.InspectionMeasurementService;

@RestController
@RequestMapping(value = "/inspectionMeasurement")
public class InspectionMeasurementController {
	
	private static final Logger logger = LogManager.getLogger(InspectionMeasurementController.class);
	
	@Autowired
	private InspectionMeasurementService inspectionMeasurementService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/componentDrawNum/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<String>> getCompDrawNumList(@PathVariable Integer subscriberId){
		logger.info("Entered into getCompDrawNumList");
		List<String> componentDrawNumbers = new ArrayList<String>();
		try{
			if(null != subscriberId){
				componentDrawNumbers = inspectionMeasurementService.getCompDrawNumList(subscriberId);
				return new ResponseEntity(componentDrawNumbers,HttpStatus.OK);
			}else{
				return new ResponseEntity(null,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.info("Exception At  getCompDrawNumList "+exception.getMessage());
			return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/worJobkOrder/{compDrawNum}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> getWorkJobOrderList(@PathVariable String compDrawNum){
		logger.info("Entered into getCompDrawNumList");
		WorkJobOrderResponseDTO workOrderJobList = new WorkJobOrderResponseDTO();
		try{
			if(null != compDrawNum){
				workOrderJobList = inspectionMeasurementService.getWorkJobOrderList(compDrawNum);
				if(null != workOrderJobList && StatusConstants.SUCCESS.equalsIgnoreCase(workOrderJobList.getStatus())){
					return new ResponseEntity(workOrderJobList,HttpStatus.OK);
				}else{
					return new ResponseEntity(workOrderJobList,HttpStatus.EXPECTATION_FAILED);
				}
			}
		}catch(Exception exception){
			logger.info("Exception At  getCompDrawNumList "+exception.getMessage());
			return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(null,HttpStatus.PRECONDITION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/insptypes/{compDrawNum}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<InspectionTypeMasterDTO>> getInspectionTypesList(@PathVariable("compDrawNum") String compDrawNum){
		logger.info("Entered into getInspectionTypesList ");
		WorkJobOrderResponseDTO workOrderJobList = new WorkJobOrderResponseDTO();
		try{
			if(null != compDrawNum){
				//workOrderJobList = inspectionMeasurementService.getInspectionTypesList(compDrawNum);
				if(null != workOrderJobList && StatusConstants.SUCCESS.equalsIgnoreCase(workOrderJobList.getStatus())){
					return new ResponseEntity(workOrderJobList,HttpStatus.OK);
				}else{
					return new ResponseEntity(workOrderJobList,HttpStatus.EXPECTATION_FAILED);
				}
			}
		}catch(Exception exception){
			logger.info("Exception At  getCompDrawNumList "+exception.getMessage());
			return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(null,HttpStatus.PRECONDITION_FAILED);
	}
}
