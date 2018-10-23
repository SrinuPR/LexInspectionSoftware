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
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.constant.SubscriberConstants;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dto.SubscriberMasterDTO;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.service.SubscriberMasterService;

/**
 * @author rnarne
 *
 */
@Service
public class SubscriberMasterServiceImpl implements SubscriberMasterService{
	private static final Logger logger = LogManager.getLogger(SubscriberMasterServiceImpl.class);  

	@Autowired
	private SubscriberMasterDAO subMasterDAO;
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.service.SubscriberMasterService#validateSubscriber(com.deloitte.inspection.dto.SubscriberMasterDTO)
	 */
	@Override
	public SubscriberMasterDTO validateSubscriber(Integer subscriberId) throws SubscriberMasterException {
		logger.info("Subscriber ID: " + subscriberId);
		SubscriberMasterDTO responseDTO = new SubscriberMasterDTO();
		if(null != subscriberId && 0 != subscriberId) {
			LISSubscriberMaster login = subMasterDAO.validateSubscriber(subscriberId);
			responseDTO.setStatus(StatusConstants.SUCCESS);
			if (null == login) {				
				responseDTO.setMessage(SubscriberConstants.SUBSCRIBER_NOT_AVAILABLE);
				return responseDTO;
			} else {
				responseDTO.setMessage(SubscriberConstants.SUBSCRIBER_AVAILABLE);
				return responseDTO;
			}
		} else if(null != subscriberId && 0 == subscriberId) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(SubscriberConstants.SUBSCRIBER_ID_EMPTY);
			return responseDTO;
		}
		return responseDTO;
	}	
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.service.SubscriberMasterService#createSubscriber(com.deloitte.inspection.dto.SubscriberMasterDTO)
	 */
	@Override
	public SubscriberMasterDTO createSubscriber(SubscriberMasterDTO subMasterDTO) throws SubscriberMasterException {
		SubscriberMasterDTO responseDTO = new SubscriberMasterDTO();
		logger.info("Subscriber ID, name and Address "+subMasterDTO.getSubscriberId()+" , "+subMasterDTO.getSubscriberName() 
					+ " , " + subMasterDTO.getSubscriberAddress());
		if(null != subMasterDTO && null != subMasterDTO.getSubscriberId() && null != subMasterDTO.getSubscriberName() 
				&& null != subMasterDTO.getSubscriberAddress()) {
			SubscriberMasterDTO login = subMasterDAO.createSubscriber(subMasterDTO);
			if (null == login) {
				responseDTO.setStatus(StatusConstants.SUCCESS);
				responseDTO.setMessage(SubscriberConstants.CREATE_SUBSCRIBER_FAILED);
			} else {
				responseDTO = subMasterDTO;
				responseDTO.setStatus(StatusConstants.SUCCESS);
				responseDTO.setMessage(SubscriberConstants.CREATE_SUBSCRIBER_SUCCESS);
			}
		} else if(null != subMasterDTO && null == subMasterDTO.getSubscriberId()) {
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(SubscriberConstants.SUBSCRIBER_ID_EMPTY);
		} else if(null != subMasterDTO && null == subMasterDTO.getSubscriberName()) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(SubscriberConstants.SUBSCRIBER_NAME_EMPTY);
		} else if(null != subMasterDTO && null == subMasterDTO.getSubscriberAddress()) { 
			responseDTO.setStatus(StatusConstants.SUCCESS);
			responseDTO.setMessage(SubscriberConstants.SUBSCRIBER_ADDRESS_EMPTY);
		}
		return responseDTO;
	}
	
	@Override
	public List<SubscriberMasterDTO> getAllSubscriberMasterData() throws SubscriberMasterException {
		try{
			List<SubscriberMasterDTO> subscriberMasterDTOList = new ArrayList<SubscriberMasterDTO>();
			List<LISSubscriberMaster> lisSubscriberMasterList = subMasterDAO.getAllSubscriberMasterData();
			if(null != lisSubscriberMasterList && lisSubscriberMasterList.size() > 0){
				for(LISSubscriberMaster lisSubscriberMaster : lisSubscriberMasterList){
					SubscriberMasterDTO subscriberMasterDTO = new SubscriberMasterDTO();
					subscriberMasterDTO.setSubscriberId(lisSubscriberMaster.getSubscriberId());
					subscriberMasterDTO.setSubscriberName(lisSubscriberMaster.getSubscriberName());
					subscriberMasterDTOList.add(subscriberMasterDTO);
				}
			}
			return subscriberMasterDTOList;
		}catch(Exception exception){
			logger.error("Exception While Fetching the subscriber master data "+exception.getMessage());
		}
		return null;
	}
	@Override
	public List<SubscriberMasterDTO> getSubscriber(String userId) throws SubscriberMasterException {

		try{
			List<SubscriberMasterDTO> subscriberMasterDTOList = new ArrayList<SubscriberMasterDTO>();
			List<LISSubscriberMaster> lisSubscriberMasterList = subMasterDAO.getSubscriberData(userId);
			if(null != lisSubscriberMasterList && lisSubscriberMasterList.size() > 0){
				for(LISSubscriberMaster lisSubscriberMaster : lisSubscriberMasterList){
					SubscriberMasterDTO subscriberMasterDTO = new SubscriberMasterDTO();
					subscriberMasterDTO.setSubscriberId(lisSubscriberMaster.getSubscriberId());
					subscriberMasterDTO.setSubscriberName(lisSubscriberMaster.getSubscriberName());
					subscriberMasterDTOList.add(subscriberMasterDTO);
				}
			}
			return subscriberMasterDTOList;
		}catch(Exception exception){
			logger.error("Exception While Fetching the subscriber master data "+exception.getMessage());
		}
		return null;
	
	}
	
}
