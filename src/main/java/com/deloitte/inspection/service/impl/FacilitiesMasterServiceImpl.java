/**
 * 
 */
package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deloitte.inspection.constant.FacilityMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.FacilitiesMasterDAO;
import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.exception.FacilityMasterException;
import com.deloitte.inspection.model.LISFacilityMaster;
import com.deloitte.inspection.service.FacilitiesMasterService;

/**
 * @author rnarne
 *
 */
@Service
public class FacilitiesMasterServiceImpl implements FacilitiesMasterService {

	private static final Logger logger = LogManager.getLogger(FacilitiesMasterServiceImpl.class);  

	@Autowired
	private FacilitiesMasterDAO facilityMasterDAO;

	@Override
	public FacilityMasterDTO getFacilityNumber(String facilityNum) throws FacilityMasterException {
		logger.info("facilityNum : " + facilityNum);
		FacilityMasterDTO responseDTO = new FacilityMasterDTO();
		if(null != facilityNum && !facilityNum.isEmpty()) {
			LISFacilityMaster response = facilityMasterDAO.getFacilityNumber(facilityNum);
			responseDTO.setStatus(StatusConstants.SUCCESS);
			if (null == response) {				
				responseDTO.setMessage(FacilityMasterConstants.FACILITY_NUMBER_NOT_AVAILABLE);
				return responseDTO;
			} else {
				responseDTO.setMessage(FacilityMasterConstants.FACILITY_NUMBER_AVAILABLE);
				return responseDTO;
			}
		} else if(null == facilityNum || (null != facilityNum && facilityNum.isEmpty())) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(FacilityMasterConstants.FACILITY_NUMBER_EMPTY);
			return responseDTO;
		}
		return responseDTO;
	}
	
	@Override
	public FacilityMasterDTO createFacilities(FacilityMasterDTO facilityMasterDTO)
			throws FacilityMasterException {

		FacilityMasterDTO responseDTO = new FacilityMasterDTO();
		logger.info("Facility Number, name :"+facilityMasterDTO.getFacilityNumber() + " , " + facilityMasterDTO.getFacilityName());
		if(null != facilityMasterDTO && null != facilityMasterDTO.getFacilityNumber() && null != facilityMasterDTO.getFacilityName()) {
			FacilityMasterDTO login = facilityMasterDAO.createFacility(facilityMasterDTO);
			responseDTO.setStatus(StatusConstants.SUCCESS);
			if (null == login) {
				responseDTO.setMessage(FacilityMasterConstants.CREATE_FACILITY_FAILED);
			} else {
				responseDTO = facilityMasterDTO;
				responseDTO.setMessage(FacilityMasterConstants.CREATE_FACILITY_SUCCESS);
			}
		} else if(null != facilityMasterDTO && null == facilityMasterDTO.getFacilityNumber()) {
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(FacilityMasterConstants.FACILITY_NUMBER_EMPTY);
		} else if(null != facilityMasterDTO && null == facilityMasterDTO.getFacilityName()) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(FacilityMasterConstants.FACILITY_NAME_EMPTY);
		} 
		return responseDTO;
	}
	
	@Override
	public List<FacilityMasterDTO> getFacilitiesMasterData() throws FacilityMasterException {
		try{
			List<FacilityMasterDTO> facilityMasterList = new ArrayList<FacilityMasterDTO>();
			List<LISFacilityMaster> facilityMasterModelList = facilityMasterDAO.getFacilitiesMasterData();
			if(null != facilityMasterModelList && facilityMasterModelList.size() > 0){
				for(LISFacilityMaster facilityMaster : facilityMasterModelList){
					FacilityMasterDTO facilityMasterDTO = new FacilityMasterDTO();
					facilityMasterDTO.setFacilityNumber(facilityMaster.getFacilityNumber());
					facilityMasterDTO.setFacilityName(facilityMaster.getFacilityName());
					facilityMasterDTO.setSubscriberId(facilityMaster.getSubscriberMaster().getSubscriberId());
					facilityMasterDTO.setSubscriberName(facilityMaster.getSubscriberMaster().getSubscriberName());
					facilityMasterList.add(facilityMasterDTO);
				}
			}
			return facilityMasterList;
		}catch(Exception exception){
			logger.error("Exception While Fetching the Facility master data "+exception.getMessage());
		}
		return null;
	}
}
