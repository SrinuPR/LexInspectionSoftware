/**
 * 
 */
package com.deloitte.inspection.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.component.CryptoComponent;
import com.deloitte.inspection.constant.StatusConstants;
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
	
	@Autowired
	private CryptoComponent cryptoComponent;
	
	/* (non-Javadoc)
	 * @see com.deloitte.inspection.service.SubscriberMasterService#validateSubscriber(com.deloitte.inspection.dto.SubscriberMasterDTO)
	 */
	@Override
	public String validateSubscriber(SubscriberMasterDTO subMasterDTO) throws SubscriberMasterException {
		logger.info("Subscriber ID: " + subMasterDTO.getSubscriberId());
		if(null != subMasterDTO && null != subMasterDTO.getSubscriberId()) {
			LISSubscriberMaster login = subMasterDAO.validateSubscriber(subMasterDTO.getSubscriberId());
			if (null == login) {
				return StatusConstants.SUBSCRIBER_NOT_AVAILABLE;
			} else {
				return StatusConstants.SUBSCRIBER_AVAILABLE;
			}
		} else if(null != subMasterDTO && null == subMasterDTO.getSubscriberId()) { 
			return StatusConstants.SUBSCRIBER_ID_EMPTY;
		}
		return "";
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
				responseDTO.setErrorMessage(StatusConstants.CREATE_SUBSCRIBER_FAILED);
			} else {
				responseDTO = subMasterDTO;
				responseDTO.setStatus(StatusConstants.CREATE_SUBSCRIBER_SUCCESS);
			}
		} else if(null != subMasterDTO && null == subMasterDTO.getSubscriberId()) { 
			responseDTO.setErrorMessage(StatusConstants.SUBSCRIBER_ID_EMPTY);
		} else if(null != subMasterDTO && null == subMasterDTO.getSubscriberName()) { 
			responseDTO.setErrorMessage(StatusConstants.USER_ID_EMPTY);
		} else if(null != subMasterDTO && null == subMasterDTO.getSubscriberAddress()) { 
			responseDTO.setErrorMessage(StatusConstants.USER_ID_EMPTY);
		}
		return responseDTO;
	}
}
