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
import com.deloitte.inspection.constant.InspectionTypeMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.InspectionStageMasterDAO;
import com.deloitte.inspection.dto.InspectionStageMasterDTO;
import com.deloitte.inspection.exception.InspectionStageMasterException;
import com.deloitte.inspection.model.LISInspectionStageMaster;
import com.deloitte.inspection.service.InspectionStageMasterService;

/**
 * @author rnarne
 *
 */
@Service
public class InspectionStageMasterServiceImpl implements InspectionStageMasterService {

	private static final Logger logger = LogManager.getLogger(InspectionStageMasterServiceImpl.class);  

	@Autowired
	private InspectionStageMasterDAO inspStageMasterDAO;

	@Override
	public InspectionStageMasterDTO getInspStageId(Integer inspStageId) throws InspectionStageMasterException {

		logger.info("InspStageId : " + inspStageId);
		InspectionStageMasterDTO responseDTO = new InspectionStageMasterDTO();
		if(null != inspStageId && 0 != inspStageId) {
			LISInspectionStageMaster response = inspStageMasterDAO.getInspSatgeId(inspStageId);
			responseDTO.setStatus(StatusConstants.SUCCESS);
			if (null == response) {				
				responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_STAGE_ID_NOT_AVAILABLE);
				return responseDTO;
			} else {
				responseDTO.setInspStageId(inspStageId);
				responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_STAGE_ID_AVAILABLE);
				return responseDTO;
			}
		} else if(null != inspStageId && 0 == inspStageId) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_STAGE_ID_EMPTY);
			return responseDTO;
		}
		return responseDTO;
	}
	
	@Override
	public InspectionStageMasterDTO createInspectionStage(InspectionStageMasterDTO inspTypeMasterDTO)
			throws InspectionStageMasterException {

		InspectionStageMasterDTO responseDTO = new InspectionStageMasterDTO();
		logger.info("Inspection stage ID, name "+inspTypeMasterDTO.getInspStageId() + " , " + inspTypeMasterDTO.getInspStageName());
		if(null != inspTypeMasterDTO && null != inspTypeMasterDTO.getInspStageId() && null != inspTypeMasterDTO.getInspStageName()) {
			InspectionStageMasterDTO login = inspStageMasterDAO.createInspectionStage(inspTypeMasterDTO);
			responseDTO.setStatus(StatusConstants.SUCCESS);
			if (null == login) {
				responseDTO.setMessage(InspectionTypeMasterConstants.CREATE_INSPECTION_STAGE_FAILED);
			} else {
				responseDTO = inspTypeMasterDTO;
				responseDTO.setMessage(InspectionTypeMasterConstants.CREATE_INSPECTION_STAGE_SUCCESS);
			}
		} else if(null != inspTypeMasterDTO && null == inspTypeMasterDTO.getInspStageId()) {
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_STAGE_ID_EMPTY);
		} else if(null != inspTypeMasterDTO && null == inspTypeMasterDTO.getInspStageName()) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_STAGE_NAME_EMPTY);
		} 
		return responseDTO;
	}
	
	@Override
	public List<InspectionStageMasterDTO> getAllInspStageMasterData() throws InspectionStageMasterException {
		try{
			List<InspectionStageMasterDTO> inspTypeMasterList = new ArrayList<InspectionStageMasterDTO>();
			List<LISInspectionStageMaster> lisInspTypeMasterList = inspStageMasterDAO.getAllInspStageMasterData();
			if(null != lisInspTypeMasterList && lisInspTypeMasterList.size() > 0){
				for(LISInspectionStageMaster lisInspTypeMaster : lisInspTypeMasterList){
					InspectionStageMasterDTO inspTypeMasterDTO = new InspectionStageMasterDTO();
					inspTypeMasterDTO.setInspStageId(lisInspTypeMaster.getInspStageId());
					inspTypeMasterDTO.setInspStageName(lisInspTypeMaster.getInspStageName());
					inspTypeMasterDTO.setSubscriberId(lisInspTypeMaster.getSubscriberMaster().getSubscriberId());
					inspTypeMasterDTO.setSubscriberName(lisInspTypeMaster.getSubscriberMaster().getSubscriberName());
					inspTypeMasterList.add(inspTypeMasterDTO);
				}
			}
			return inspTypeMasterList;
		}catch(Exception exception){
			logger.error("Exception While Fetching the subscriber master data "+exception.getMessage());
		}
		return null;
	}

}