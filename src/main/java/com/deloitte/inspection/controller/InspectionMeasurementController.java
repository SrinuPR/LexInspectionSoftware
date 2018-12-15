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

import com.deloitte.inspection.constant.InspectionMeasurementConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.response.dto.InspectionMeasurementResponseDTO;
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
	public @ResponseBody ResponseEntity<InspectionMeasurementResponseDTO> getCompDrawNumList(@PathVariable Integer subscriberId){
		logger.info("Entered into getCompDrawNumList");
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		try{
			if(null != subscriberId){
				inspectionMeasurementResponseDTO = inspectionMeasurementService.getCompDrawNumList(subscriberId);
				if(null != inspectionMeasurementResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionMeasurementResponseDTO.getStatus())){
					return new ResponseEntity(inspectionMeasurementResponseDTO,HttpStatus.OK);
				}
			}
		}catch(Exception exception){
			logger.info("Exception At  getCompDrawNumList "+exception.getMessage());
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.UN_EXPECTED_EXCEPTION);
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.FAILURE);
			return new ResponseEntity(inspectionMeasurementResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(inspectionMeasurementResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
		
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/inspectionReport/{compDrawNum}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InspectionMeasurementResponseDTO> getInspectionReportList(@PathVariable String compDrawNum){
		logger.info("Entered into getWorkJobOrderList");
		InspectionMeasurementResponseDTO inspectionMeasurementResponseDTO = new InspectionMeasurementResponseDTO();
		try{
			if(null != compDrawNum){
				inspectionMeasurementResponseDTO = inspectionMeasurementService.getInspectionReportList(compDrawNum);
				if(null != inspectionMeasurementResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(inspectionMeasurementResponseDTO.getStatus())){
					return new ResponseEntity(inspectionMeasurementResponseDTO,HttpStatus.OK);
				}else{
					return new ResponseEntity(inspectionMeasurementResponseDTO,HttpStatus.EXPECTATION_FAILED);
				}
			}
		}catch(Exception exception){
			logger.info("Exception At  getWorkJobOrderList "+exception.getMessage());
			inspectionMeasurementResponseDTO.setMessage(InspectionMeasurementConstants.UN_EXPECTED_EXCEPTION);
			inspectionMeasurementResponseDTO.setStatus(StatusConstants.FAILURE);
			return new ResponseEntity(inspectionMeasurementResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(inspectionMeasurementResponseDTO,HttpStatus.PRECONDITION_FAILED);
	}
	/*
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
	}*/
}
