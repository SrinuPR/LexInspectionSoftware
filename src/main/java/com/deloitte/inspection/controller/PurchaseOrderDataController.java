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

import com.deloitte.inspection.constant.PurchaseOrderConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.PurchaseOrderDataDTO;
import com.deloitte.inspection.response.dto.ComponetDrawNumberResponseDTO;
import com.deloitte.inspection.response.dto.PurchaseOrderResponseDataDTO;
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
	public @ResponseBody ResponseEntity<PurchaseOrderResponseDataDTO> savePurchaseOrderMasterData(@RequestBody PurchaseOrderDataDTO purchaseOrderDataDTO, HttpSession httpSession){
		
		logger.info("Entered into savePurchaseOrderMasterData");
		String status = StatusConstants.FAILURE;
		PurchaseOrderResponseDataDTO purchaseOrderResponseDataDTO = new PurchaseOrderResponseDataDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			String userId = null;
			if(null != userDto){
				userName = userDto.getUserName();
				userId = userDto.getUserId();
			}
			status = purchaseOrderDataService.savePurchaseOrderData(purchaseOrderDataDTO,userName, userId);
			if(null!=status && StatusConstants.SUCCESS.equalsIgnoreCase(status)) {
				List<PurchaseOrderDataDTO> purchaseOrderList = purchaseOrderDataService.getAllPurchaseOrders(userId);
				purchaseOrderResponseDataDTO.setResult(purchaseOrderList);
				purchaseOrderResponseDataDTO.setStatus(StatusConstants.SUCCESS);
				purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.CREATE_PURCHASE_ORDER_SUCCESS);
				return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.OK);
			}else {
				purchaseOrderResponseDataDTO.setStatus(StatusConstants.ERROR);
				purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.CREATE_PURCHASE_ORDER_FAILURE);
				return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Exception while saving the data : "+exception.getMessage());
			purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.CREATE_PURCHASE_ORDER_FAILURE);
			return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.METHOD_FAILURE);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PurchaseOrderResponseDataDTO> getAllPurchaseOrders(HttpSession httpSession){
		logger.info("Entered into getAllPurchaseOrders");
		PurchaseOrderResponseDataDTO purchaseOrderResponseDataDTO = new PurchaseOrderResponseDataDTO();
		List<PurchaseOrderDataDTO> purchaseOrderList = null;
		LoginDTO userDto=new LoginDTO();
		try{
			userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			if(null != userDto){
				userId = userDto.getUserId();
			}
			purchaseOrderList = purchaseOrderDataService.getAllPurchaseOrders(userId);
			purchaseOrderResponseDataDTO.setStatus(StatusConstants.SUCCESS);
			purchaseOrderResponseDataDTO.setResult(purchaseOrderList);
			purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.CUSTOMER_PO_EXIST);
			return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.OK);
		}catch(Exception exception){
			logger.error("Error while fetching the data : "+exception.getMessage());
		}
		purchaseOrderResponseDataDTO.setStatus(StatusConstants.ERROR);
		purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.CUSTOMER_PO_NOT_EXIST);
		return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.EXPECTATION_FAILED);
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
	@RequestMapping(value = "/validate/{customerPONumber}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<CommonDTO> validatePONumber(@PathVariable("customerPONumber") String customerPONumber){
		CommonDTO commonDTO = new CommonDTO();
		try{
			if(null != customerPONumber){
				commonDTO = purchaseOrderDataService.validatePONumber(customerPONumber);
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
		commonDTO.setMessage(PurchaseOrderConstants.SOMETHING_WENT_WRONG);
		return new ResponseEntity(commonDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<PurchaseOrderResponseDataDTO> updatePurchaseOrderData(@RequestBody PurchaseOrderDataDTO purchaseOrderDataDTO, HttpSession httpSession){
		logger.info("Entered into updateComponentMasterData");
		String status = StatusConstants.FAILURE;
		PurchaseOrderResponseDataDTO purchaseOrderResponseDataDTO = new PurchaseOrderResponseDataDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userName = null;
			String userId = null;
			if(null != userDto){
				userName = userDto.getUserName();
				userId = userDto.getUserId();
			}
			status = purchaseOrderDataService.updatePurchaseOrderData(purchaseOrderDataDTO, userName, userId);
			if(null!=status && StatusConstants.SUCCESS.equalsIgnoreCase(status)) {
				List<PurchaseOrderDataDTO> purchaseOrderList = purchaseOrderDataService.getAllPurchaseOrders(userId);
				purchaseOrderResponseDataDTO.setResult(purchaseOrderList);
				purchaseOrderResponseDataDTO.setStatus(StatusConstants.SUCCESS);
				purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.UPDATE_PURCHASE_ORDER_SUCCESS);
				return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.OK);
			}else {
				purchaseOrderResponseDataDTO.setStatus(StatusConstants.ERROR);
				purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.UPDATE_PURCHASE_ORDER_FAILURE);
				return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.EXPECTATION_FAILED);
			}
		}catch(Exception exception){
			logger.error("Error while updating the data : "+exception.getMessage());
			purchaseOrderResponseDataDTO.setStatus(StatusConstants.ERROR);
			purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.UPDATE_PURCHASE_ORDER_FAILURE);
			return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.METHOD_FAILURE);
		}
	}
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/delete/{customerPoId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<PurchaseOrderResponseDataDTO> deletePurchaseOrder(@PathVariable("customerPoId") String customerPoId){
		PurchaseOrderResponseDataDTO purchaseOrderResponseDataDTO = new PurchaseOrderResponseDataDTO();
		try{
			String status = purchaseOrderDataService.deletePurchaseOrder(customerPoId);
			if(null != status && StatusConstants.SUCCESS.equalsIgnoreCase(status)){
				purchaseOrderResponseDataDTO.setStatus(status);
				purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.DELETE_PURCHASE_ORDER_SUCCESS);
				return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.OK);
			}
			purchaseOrderResponseDataDTO.setStatus(StatusConstants.ERROR);
			purchaseOrderResponseDataDTO.setMessage(PurchaseOrderConstants.DELETE_PURCHASE_ORDER_FAILURE);
			return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			logger.error("Exception while sending the details "+exception.getMessage());
		}
		return new ResponseEntity(purchaseOrderResponseDataDTO,HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/drawNumbers", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ComponetDrawNumberResponseDTO> getAllComponentDrawingNumber(){
		ComponetDrawNumberResponseDTO componetDrawNumberResponseDTO = new ComponetDrawNumberResponseDTO();
		try{
			List<String> drawNumbers = purchaseOrderDataService.getAllComponentDrawingNumber();
			componetDrawNumberResponseDTO.setStatus(StatusConstants.SUCCESS);
			componetDrawNumberResponseDTO.setMessage(PurchaseOrderConstants.COMPONENT_DRAW_NUM_LIST);
			componetDrawNumberResponseDTO.setResult(drawNumbers);
			return new ResponseEntity(componetDrawNumberResponseDTO, HttpStatus.OK);
		}catch(Exception exception){
			exception.printStackTrace();
			logger.error("Exception While fetching the getAllComponentDrawingNumber " + exception.getMessage());
		}
		componetDrawNumberResponseDTO.setStatus(StatusConstants.ERROR);
		componetDrawNumberResponseDTO.setMessage(PurchaseOrderConstants.SOMETHING_WENT_WRONG);
		return new ResponseEntity(componetDrawNumberResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
