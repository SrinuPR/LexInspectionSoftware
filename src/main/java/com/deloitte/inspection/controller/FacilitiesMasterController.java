/**
 * 
 */
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
import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.response.dto.FacilityMasterResponseDataDTO;
import com.deloitte.inspection.service.FacilitiesMasterService;

/**
 * @author rnarne
 *This class will have the services for facilities.
 */
@RestController
@RequestMapping(value = "/facilities")
public class FacilitiesMasterController {
	
	private static final Logger logger = LogManager.getLogger(FacilitiesMasterController.class);  
	
	@Autowired
	private FacilitiesMasterService facilitiesMasterService;
	
	/**
	 * @param facilityMasDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/{facilityNumber}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<FacilityMasterDTO> validateFacilityNumber(@PathVariable("facilityNumber") String facilityNumber){
		FacilityMasterResponseDataDTO responseDTO = new FacilityMasterResponseDataDTO();
		try{
			responseDTO = facilitiesMasterService.getFacilityNumber(facilityNumber);
			if(null != responseDTO)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			responseDTO.setStatus(StatusConstants.ERROR);
			exception.printStackTrace();
			logger.error("Exception While validateFacilityNumber " + exception.getMessage());
		}
		return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @param facilityMasDTO
	 * @return
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<FacilityMasterDTO> createFacilities(@RequestBody FacilityMasterDTO facilityMasDTO, HttpSession httpSession){
		FacilityMasterResponseDataDTO responseDTO = new FacilityMasterResponseDataDTO();
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			String userId = null;
			if(null != userDto){
				userId = userDto.getUserId();
			}else{
				userId = StatusConstants.DEFAULT_USER_NAME;
			}
			responseDTO = facilitiesMasterService.createFacilities(facilityMasDTO,userId,facilityMasDTO.getSubscriberId());
			if(null != responseDTO)
				return new ResponseEntity(responseDTO, HttpStatus.OK);
			else
				return new ResponseEntity(responseDTO, HttpStatus.EXPECTATION_FAILED);
		}catch(Exception exception){
			responseDTO.setStatus(StatusConstants.ERROR);
			exception.printStackTrace();
			logger.error("Exception in createFacilities "+exception.getMessage());
		}
		return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @return ResponseEntity
	 */
	@CrossOrigin
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<FacilityMasterResponseDataDTO> facilitiesMasterList(HttpSession httpSession){
		logger.info("Entered into facilitiesMasterList");
		FacilityMasterResponseDataDTO repsonseDto = new FacilityMasterResponseDataDTO();
		List<FacilityMasterDTO> facilityMasDTOList = null;
		try{
			LoginDTO userDto = (LoginDTO)httpSession.getAttribute("user");
			Integer subscriberId = null;
			if(null != userDto){
				subscriberId = userDto.getSubscriberId();
			}
			facilityMasDTOList = facilitiesMasterService.getFacilitiesMasterData(subscriberId);
			repsonseDto.setResult(facilityMasDTOList);
			repsonseDto.setStatus(StatusConstants.SUCCESS);
			repsonseDto.setMessage(StatusConstants.SUCCESS);
			return new ResponseEntity(repsonseDto, HttpStatus.OK);
		}catch(Exception exception){
			repsonseDto.setStatus(StatusConstants.ERROR);
			logger.error("Error while fetching the data : "+exception.getMessage());
		}
		return new ResponseEntity(repsonseDto,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
