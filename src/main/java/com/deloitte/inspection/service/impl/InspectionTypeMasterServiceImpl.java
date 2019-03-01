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
import com.deloitte.inspection.dao.InspectionTypeMasterDAO;
import com.deloitte.inspection.dto.InspectionTypeMasterDTO;
import com.deloitte.inspection.exception.InspectionTypeMasterException;
import com.deloitte.inspection.model.LISInspectionTypeMaster;
import com.deloitte.inspection.service.InspectionTypeMasterService;

/**
 * @author rnarne
 *
 */
@Service
public class InspectionTypeMasterServiceImpl implements InspectionTypeMasterService{
	private static final Logger logger = LogManager.getLogger(SubscriberMasterServiceImpl.class);  

	@Autowired
	private InspectionTypeMasterDAO inspTypeMasterDAO;

	@Override
	public InspectionTypeMasterDTO getInspTypeId(Integer inspTypeId) throws InspectionTypeMasterException {

		logger.info("Subscriber ID: " + inspTypeId);
		InspectionTypeMasterDTO responseDTO = new InspectionTypeMasterDTO();
		if(null != inspTypeId && 0 != inspTypeId) {
			LISInspectionTypeMaster response = inspTypeMasterDAO.getInspTypeId(inspTypeId);
			responseDTO.setStatus(StatusConstants.SUCCESS);
			if (null == response) {				
				responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_TYPE_ID_NOT_AVAILABLE);
				return responseDTO;
			} else {
				responseDTO.setInspTypeId(inspTypeId);
				responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_TYPE_ID_AVAILABLE);
				return responseDTO;
			}
		} else if(null != inspTypeId && 0 == inspTypeId) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_TYPE_ID_EMPTY);
			return responseDTO;
		}
		return responseDTO;
	
	}
	
	@Override
	public InspectionTypeMasterDTO createInspectionType(InspectionTypeMasterDTO inspTypeMasterDTO,String userId)
			throws InspectionTypeMasterException{

		InspectionTypeMasterDTO responseDTO = new InspectionTypeMasterDTO();
		logger.info("Inspection ID, name "+inspTypeMasterDTO.getInspTypeId()+" , "+inspTypeMasterDTO.getInspTypeName());
		if(null != inspTypeMasterDTO && null != inspTypeMasterDTO.getInspTypeId() && null != inspTypeMasterDTO.getInspTypeName()) {
			InspectionTypeMasterDTO login = inspTypeMasterDAO.createInspectionType(inspTypeMasterDTO,userId);
			if (null == login) {
				responseDTO.setStatus(StatusConstants.SUCCESS);
				responseDTO.setMessage(InspectionTypeMasterConstants.CREATE_INSPECTION_TYPE_FAILED);
			} else {
				responseDTO = inspTypeMasterDTO;
				responseDTO.setStatus(StatusConstants.SUCCESS);
				responseDTO.setMessage(InspectionTypeMasterConstants.CREATE_INSPECTION_TYPE_SUCCESS);
			}
		} else if(null != inspTypeMasterDTO && null == inspTypeMasterDTO.getInspTypeId()) {
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_TYPE_ID_EMPTY);
		} else if(null != inspTypeMasterDTO && null == inspTypeMasterDTO.getInspTypeName()) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(InspectionTypeMasterConstants.INSPECTION_TYPE_NAME_EMPTY);
		} 
		return responseDTO;
	
	}
	
	@Override
	public List<InspectionTypeMasterDTO> getAllInspTypeMasterData(Integer subscriberId) throws InspectionTypeMasterException{
		try{
			List<InspectionTypeMasterDTO> inspTypeMasterList = new ArrayList<InspectionTypeMasterDTO>();
			List<LISInspectionTypeMaster> lisInspTypeMasterList = inspTypeMasterDAO.getAllInspTypeMasterData(subscriberId);
			if(null != lisInspTypeMasterList && lisInspTypeMasterList.size() > 0){
				for(LISInspectionTypeMaster lisInspTypeMaster : lisInspTypeMasterList){
					InspectionTypeMasterDTO inspTypeMasterDTO = new InspectionTypeMasterDTO();
					inspTypeMasterDTO.setInspTypeId(lisInspTypeMaster.getInspTypeId());
					inspTypeMasterDTO.setInspTypeName(lisInspTypeMaster.getInspTypeName());
					inspTypeMasterDTO.setSubscriberId(lisInspTypeMaster.getSubscriber().getSubscriberId());
					inspTypeMasterDTO.setSubscriberName(lisInspTypeMaster.getSubscriber().getSubscriberName());
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
