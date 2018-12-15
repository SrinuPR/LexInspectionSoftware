package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.AccessMasterDAO;
import com.deloitte.inspection.dao.MasterListOfScreensForSubscriberDAO;
import com.deloitte.inspection.dao.SubscriberMasterDAO;
import com.deloitte.inspection.dao.UserTypeMasterDAO;
import com.deloitte.inspection.dao.impl.LoginDAOImpl;
import com.deloitte.inspection.dto.AccessMasterDTO;
import com.deloitte.inspection.model.LISAccessMaster;
import com.deloitte.inspection.model.LISMasterListOfScreensForSubscriber;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserTypeMaster;
import com.deloitte.inspection.response.dto.AccessMasterResponseDTO;
import com.deloitte.inspection.service.AccessMasterService;

@Service
public class AccessMasterServiceImpl implements AccessMasterService{
	
	private static final Logger logger = LogManager.getLogger(LoginDAOImpl.class);

	@Autowired 
	private AccessMasterDAO accessMasterDao;
	
	@Autowired
	private SubscriberMasterDAO subscriberMasterDAO;
	
	@Autowired
	private UserTypeMasterDAO userTypeMasterDAO;
	
	@Autowired
	private MasterListOfScreensForSubscriberDAO masterListOfScreensForSubscriberDAO;
	
	@Override
	public AccessMasterResponseDTO saveAccess(AccessMasterDTO accessMasterDTO) throws Exception {
		logger.info("Saving data for Access Master");
		AccessMasterResponseDTO responseDTO = new AccessMasterResponseDTO();
		if (null != accessMasterDTO && null != accessMasterDTO.getSubscriberId()) {
			try {
				LISSubscriberMaster subscriberMaster = subscriberMasterDAO.getSubscriberById(accessMasterDTO.getSubscriberId());
				LISUserTypeMaster userTypeMaster = userTypeMasterDAO.getByUserTypeId(accessMasterDTO.getUserTypeId());
				if(null != accessMasterDTO.getAccessMasterId()){
					LISAccessMaster accessMaster = accessMasterDao.getAccessMaster(accessMasterDTO.getAccessMasterId());
					accessMaster.setScreenNumber(accessMasterDTO.getScreenNumbers());
					accessMaster.setSubscriberMaster(subscriberMaster);
					accessMaster.setUserTypeMaster(userTypeMaster);
					accessMaster.setUpdatedBy(accessMasterDTO.getUserId());
					accessMaster.setUpdatedTimeStamp(new Date());
					accessMasterDao.saveAccessMaster(accessMaster);
				}else{
					LISAccessMaster accessMaster = new LISAccessMaster();
					accessMaster.setCreatedBy(accessMasterDTO.getUserId());
					accessMaster.setIsActive(StatusConstants.IS_ACTIVE);
					accessMaster.setScreenNumber(accessMasterDTO.getScreenNumbers());
					accessMaster.setSubscriberMaster(subscriberMaster);
					accessMaster.setUserTypeMaster(userTypeMaster);
					accessMaster.setCreatedTimestamp(new Date());
					accessMasterDao.saveAccessMaster(accessMaster);
				}
				responseDTO.setStatus(StatusConstants.SUCCESS);
				responseDTO.setMessage(StatusConstants.ACCESS_RECORD_SAVED);
			}catch(Exception exception) {
				responseDTO.setStatus(StatusConstants.FAILURE);
				responseDTO.setMessage(StatusConstants.ACCESS_RECORD_NOT_SAVED);
				logger.error("Exception While validating credentials "+exception.getMessage());
			}
		}else{
			logger.error("Exception While saving access master as reques is null");
		}		
		return responseDTO; 
	
	}

	@Override
	public AccessMasterDTO getAccessMasterScreens(Integer subsId, Integer userTypeId) throws Exception {
		logger.info("inside getAccessMasterScreens with subsId :"+ subsId + " and userTypeId :" + userTypeId);
		AccessMasterDTO responseDTO = new AccessMasterDTO();
		LISAccessMaster responseLACS = new LISAccessMaster(); 
		if (null != subsId || null != userTypeId) {
			responseLACS = accessMasterDao.getAccessScreens(subsId, userTypeId);
			if (null == responseLACS) {				
				return responseDTO;
			} else {
				return responseDTO;
			}
		}
		return null;
	}

	@Override
	public AccessMasterResponseDTO getUserTypeListforSubscriber(Integer subscriberId) throws Exception {
		logger.info("Inside getUserTypeListforSubscriber service");
		AccessMasterResponseDTO accessMasterResponseDTO = new AccessMasterResponseDTO();
		try{
			List<LISUserTypeMaster> userTypeMasters = accessMasterDao.getUserTypeListforSubscriber(subscriberId);
			if(null != userTypeMasters && userTypeMasters.size() > 0){
				List<AccessMasterDTO> accessMasterDTOs = new ArrayList<AccessMasterDTO>();
				for(LISUserTypeMaster usertype : userTypeMasters){
					AccessMasterDTO accessMasterDTO = new AccessMasterDTO();
					accessMasterDTO.setSubscriberId(usertype.getSubscriberMaster().getSubscriberId());
					accessMasterDTO.setUserTypeId(usertype.getUserTypeId());
					accessMasterDTO.setUserTypeName(usertype.getUserTypeName());
					if(null != usertype.getLisAccessMaster() && null != usertype.getLisAccessMaster().getScreenNumber()){
						accessMasterDTO.setScreenNumbers(usertype.getLisAccessMaster().getScreenNumber());
					}
					accessMasterDTOs.add(accessMasterDTO);
				}
				accessMasterResponseDTO.setResult(accessMasterDTOs);
			}
			List<LISMasterListOfScreensForSubscriber> masterListOfScreensForSubscribers = masterListOfScreensForSubscriberDAO.getScreensforSubscriber(subscriberId);
			String screens = "";
			for(LISMasterListOfScreensForSubscriber list:masterListOfScreensForSubscribers){
					screens = screens+list.getScreenNumber() +",";
			}
			if(null != screens && !"".equalsIgnoreCase(screens))
				screens = screens.replaceAll(",$", "");
			accessMasterResponseDTO.setSubscriberScreens(screens);
			accessMasterResponseDTO.setStatus(StatusConstants.SUCCESS);
			accessMasterResponseDTO.setMessage(StatusConstants.ACCESS_MASTER_DATA_AVAILABLE);
		}catch(Exception exception){
			accessMasterResponseDTO.setStatus(StatusConstants.FAILURE);
			accessMasterResponseDTO.setMessage(StatusConstants.ACCESS_MASTER_DATA_NOT_AVAILABLE);
			logger.error(" Exception getUserTypeListforSubscriber "+exception.getMessage());
		}
		return accessMasterResponseDTO;
	}
}
