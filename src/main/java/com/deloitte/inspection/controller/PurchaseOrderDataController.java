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
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.PurchaseOrderDataDTO;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.service.PurchaseOrderMasterService;

@RestController
@RequestMapping(value = "/purchaseOrder")
public class PurchaseOrderDataController {

	private static final Logger logger = LogManager.getLogger(PurchaseOrderDataController.class);  
	
	@Autowired
	private PurchaseOrderMasterService purchaseOrderDataService;
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PurchaseOrderDataDTO> savePurchaseOrderMasterData(@RequestBody PurchaseOrderDataDTO purchaseOrderDataDTO, HttpSession httpSession){
		logger.info("Entered into saveComponentMasterData");
		String status = StatusConstants.FAILURE;
		String response=null;
		PurchaseOrderDataDTO purchaseOrderDto=new PurchaseOrderDataDTO();
		try{
			
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			if(null != userDto)
				userName = userDto.getUserName();
			status = purchaseOrderDataService.savePurchaseOrderData(purchaseOrderDataDTO,userName);
			if(null!=status && StatusConstants.SUCCESS.equalsIgnoreCase(status)) {
				purchaseOrderDto.setStatus(StatusConstants.CREATE_PURCHASE_ORDER_SUCCESS);
			}else {
				purchaseOrderDto.setErrorMessage(StatusConstants.CREATE_PURCHASE_ORDER_FAILURE);
			}
			return new ResponseEntity(purchaseOrderDto,StatusConstants.SUCCESS.equalsIgnoreCase(status)? HttpStatus.OK:HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			logger.error("Exception while saving the data : "+exception.getMessage());
			purchaseOrderDto.setErrorMessage(StatusConstants.CREATE_PURCHASE_ORDER_FAILURE);
			return new ResponseEntity(purchaseOrderDto,HttpStatus.METHOD_FAILURE);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<PurchaseOrderDataDTO>> getAllPurchaseOrders(HttpSession httpSession){
		logger.info("Entered into displayComponentMasterData");
		List<PurchaseOrderDataDTO> purchaseOrderList = null;
		LoginDTO userDto=new LoginDTO();
		try{
			//userDto = (LoginDTO)httpSession.getAttribute("userId");
			//to do
		//	userDto.setUserId("Srinu123");
		//	if(null != userDto && null!= userDto.getUserId()) {
			purchaseOrderList = purchaseOrderDataService.getAllPurchaseOrders("Srinu123");
			if(null!=purchaseOrderList)
			return new ResponseEntity(purchaseOrderList,HttpStatus.OK);
			//}
			
		}catch(Exception exception){
			logger.error("Error while fetching the data : "+exception.getMessage());
		}
		return new ResponseEntity(purchaseOrderList,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate-date", method = RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<String> validatePODate(@RequestBody PurchaseOrderDataDTO purchaseOrderDataDTO){
		try{
			String response = purchaseOrderDataService.validatePODate(purchaseOrderDataDTO);
			if(null != response)
				return new ResponseEntity(response, HttpStatus.OK);
			else
				return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating PO date " + exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate-order", method = RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<LoginDTO> validatePONumber(@RequestBody PurchaseOrderDataDTO purchaseOrderDataDTO){
		try{
			String response = purchaseOrderDataService.validatePONumber(purchaseOrderDataDTO);
			if(null != response)
				return new ResponseEntity(response, HttpStatus.OK);
			else
				return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating PO date " + exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PurchaseOrderDataDTO> updatePurchaseOrderData(@RequestBody PurchaseOrderDataDTO purchaseOrderDataDTO, HttpSession httpSession){
		logger.info("Entered into updateComponentMasterData");
		String status = StatusConstants.FAILURE;
		PurchaseOrderDataDTO purchaseOrderDto=new PurchaseOrderDataDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			if(null != userDto)
				userName = userDto.getUserName();
			status = purchaseOrderDataService.updatePurchaseOrderData(purchaseOrderDataDTO, userName);
			if(null!=status && StatusConstants.SUCCESS.equalsIgnoreCase(status)) {
				purchaseOrderDto.setStatus(StatusConstants.CREATE_PURCHASE_ORDER_SUCCESS);
			}else {
				purchaseOrderDto.setErrorMessage(StatusConstants.CREATE_PURCHASE_ORDER_FAILURE);
			}
			
			return new ResponseEntity(purchaseOrderDto,StatusConstants.SUCCESS.equalsIgnoreCase(status)? HttpStatus.OK:HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			logger.error("Error while updating the data : "+exception.getMessage());
			purchaseOrderDto.setErrorMessage(StatusConstants.CREATE_PURCHASE_ORDER_FAILURE);
			return new ResponseEntity(purchaseOrderDto,HttpStatus.METHOD_FAILURE);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/delete/{customerPoId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> deletePurchaseOrder(@PathVariable("customerPoId") String customerPoId){
		try{
			String status = purchaseOrderDataService.deletePurchaseOrder(customerPoId);
			return new ResponseEntity(status,HttpStatus.OK);
		}catch(Exception exception){
			logger.error("Exception while sending the details "+exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.FAILURE,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/validate-quantity", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PurchaseOrderDataDTO> validatePOQuantity(@RequestBody PurchaseOrderDataDTO purchaseOrderDataDTO){
		try{
			String response = purchaseOrderDataService.validatePOQuantity(purchaseOrderDataDTO);
			if(null != response)
				return new ResponseEntity(response, HttpStatus.OK);
			else
				return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While validating PO date " + exception.getMessage());
		}
		return new ResponseEntity(StatusConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
