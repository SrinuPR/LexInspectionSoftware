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
import com.deloitte.inspection.constant.WorkJobOrderConstants;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.WorkJobOrderDTO;
import com.deloitte.inspection.exception.WorkJobOrderException;
import com.deloitte.inspection.response.dto.WorkJobOrderResponseDTO;
import com.deloitte.inspection.service.WorkJobOrderService;

@RestController
@RequestMapping(value = "/workjoborder")
public class WorkJobOrderController {
	
	private static final Logger logger = LogManager.getLogger(WorkJobOrderController.class);
	
	@Autowired
	private WorkJobOrderService workJobOrderService;

	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> saveWorkJobOrderData(@RequestBody WorkJobOrderDTO workJobOrderDTO, HttpSession httpSession){
		logger.info("Entered into saveComponentMasterData");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
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
			workJobOrderResponseDTO = workJobOrderService.saveWorkJobOrderData(workJobOrderDTO,userName,userId,StatusConstants.INSERT);
			if(null != workJobOrderResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				workJobOrderResponseDTO.setResults(workJobOrderService.WorkJobOrderList(subscriberId));
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while saving the Work/Job Order data into DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while saving the Work/Job Order data into DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> updateWorkJobOrderData(@RequestBody WorkJobOrderDTO workJobOrderDTO, HttpSession httpSession){
		logger.info("Entered into updateWorkJobOrderData");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
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
			workJobOrderResponseDTO = workJobOrderService.saveWorkJobOrderData(workJobOrderDTO,userName,userId, StatusConstants.UPDATE);
			if(null != workJobOrderResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				workJobOrderResponseDTO.setResults(workJobOrderService.WorkJobOrderList(subscriberId));
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while updating the Work/Job Order data into DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while updating the Work/Job Order data into DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> getAllWorkJobOrderData(HttpSession httpSession){
		logger.info("Entered into getAllWorkJobOrderData");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			Integer subscriberId = null;
			if(null != userDto){
				subscriberId = userDto.getSubscriberId();
			}
			workJobOrderResponseDTO = workJobOrderService.getAllWorkJobOrderData(subscriberId);
			if(null != workJobOrderResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while fetching the Work/Job Order data from DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while fetching the Work/Job Order data from DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/delete/{workJobOrderId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> deleteComponent(@PathVariable("workJobOrderId") Integer workJobOrderId){
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			String status = workJobOrderService.deleteWorkJobOrder(workJobOrderId);
			if(StatusConstants.SUCCESS.equalsIgnoreCase(status)){
				workJobOrderResponseDTO.setStatus(status);
				workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_DELETE_SUCCESS);
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				workJobOrderResponseDTO.setStatus(StatusConstants.ERROR);
				workJobOrderResponseDTO.setMessage(WorkJobOrderConstants.WORK_JOB_ORDER_DELETE_FAILED);
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
			
		}catch(Exception exception){
			logger.error("Exception while deleting the Work/Job Order"+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate/workJobOrderNumber", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> validateWorkJobOrderNumber(@RequestBody WorkJobOrderDTO workJobOrderDTO){
		logger.info("Entered into validateWorkJobOrderNumber");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			workJobOrderResponseDTO = workJobOrderService.validateWorkJobOrderNumber(workJobOrderDTO);
			if(null != workJobOrderResponseDTO && !StatusConstants.ERROR.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while validating the Work/Job Order Number in DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while validating the Work/Job Order Number in DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate/lotNumber", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> validateLotNumber(@RequestBody WorkJobOrderDTO workJobOrderDTO){
		logger.info("Entered into validatelotNumber");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			workJobOrderResponseDTO = workJobOrderService.validateLotNumber(workJobOrderDTO);
			if(null != workJobOrderResponseDTO && !StatusConstants.ERROR.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while validating the Lot Number in DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while validating the Lot Number in DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate/lotsize", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> validateLotSize(@RequestBody WorkJobOrderDTO workJobOrderDTO){
		logger.info("Entered into validatelotNumber");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			workJobOrderResponseDTO = workJobOrderService.validateLotSize(workJobOrderDTO);
			if(null != workJobOrderResponseDTO && !StatusConstants.ERROR.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while validating the Lot Number in DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while validating the Lot Number in DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate/ManfBatchNumber", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> validateManufacturerBatchNumber(@RequestBody WorkJobOrderDTO workJobOrderDTO){
		logger.info("Entered into validateManufacturerBatchNumber ");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			workJobOrderResponseDTO = workJobOrderService.validateManufacturerBatchNumber(workJobOrderDTO);
			if(null != workJobOrderResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while validating the Manufacturer Batch Number in DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while validating the Manufacturer Batch Number in DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate/ManfBatchSize", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> validateManufacturerBatchSize(@RequestBody WorkJobOrderDTO workJobOrderDTO){
		logger.info("Entered into validateManufacturerBatchNumber ");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			workJobOrderResponseDTO = workJobOrderService.validateManufacturerBatchSize(workJobOrderDTO);
			if(null != workJobOrderResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while validating the Manufacturer Batch Size in DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while validating the Manufacturer Batch Size in DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/componentData/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> getComponentData(@PathVariable("subscriberId") Integer subscriberId){
		logger.info("Entered into getComponentData ");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			workJobOrderResponseDTO = workJobOrderService.getComponentData(subscriberId);
			if(null != workJobOrderResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while getting component data from DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while getting component data from DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/customerpo/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> getCustomerPOData(@PathVariable("subscriberId") Integer subscriberId){
		logger.info("Entered into getCustomerPOData ");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try{
			workJobOrderResponseDTO = workJobOrderService.getCustomerPOData(subscriberId);
			if(null != workJobOrderResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(WorkJobOrderException workJobOrderException){
			logger.error("Exception while getting customer PO data from DB 1 : "+workJobOrderException.getMessage());
		}catch(Exception exception){
			logger.error("Exception while getting customer PO data from DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO,HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/compProdData/{subscriberId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> getWJOComponentData(@PathVariable("subscriberId") Integer subscriberId){
		logger.info("Entered into getComponentData ");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try {
			workJobOrderResponseDTO = workJobOrderService.getComponentDataFromWO(subscriberId);
			if(null != workJobOrderResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO, HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (WorkJobOrderException workJobOrderException){
			logger.error("Exception while getting component data from DB 1 : "+workJobOrderException.getMessage());
		} catch (Exception exception){
			logger.error("Exception while getting component data from DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO, HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/wjList/{compProdDrawNum}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<WorkJobOrderResponseDTO> getWJODataByCompProdDrawNum(@PathVariable("compProdDrawNum") String compProdDrawNum){
		logger.info("Entered into getWJODataByCompProdDrawNum ");
		WorkJobOrderResponseDTO workJobOrderResponseDTO = new WorkJobOrderResponseDTO();
		try {
			workJobOrderResponseDTO = workJobOrderService.getWJODataByCompProdDrawNum(compProdDrawNum);
			if(null != workJobOrderResponseDTO && StatusConstants.SUCCESS.equalsIgnoreCase(workJobOrderResponseDTO.getStatus())){
				return new ResponseEntity(workJobOrderResponseDTO, HttpStatus.OK);
			}else{
				return new ResponseEntity(workJobOrderResponseDTO, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (WorkJobOrderException workJobOrderException){
			logger.error("Exception while getting getWJODataByCompProdDrawNum from DB 1 : "+workJobOrderException.getMessage());
		} catch (Exception exception){
			logger.error("Exception while getting getWJODataByCompProdDrawNum from DB 2 : "+exception.getMessage());
		}
		return new ResponseEntity(workJobOrderResponseDTO, HttpStatus.EXPECTATION_FAILED);
	}
	
}
