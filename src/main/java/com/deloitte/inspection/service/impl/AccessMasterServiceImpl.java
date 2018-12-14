package com.deloitte.inspection.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.component.CryptoComponent;
import com.deloitte.inspection.constant.FacilityMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.AccessMasterDAO;
import com.deloitte.inspection.dao.LoginDAO;
import com.deloitte.inspection.dao.impl.LoginDAOImpl;
import com.deloitte.inspection.dto.AccessMasterDTO;
import com.deloitte.inspection.dto.LoginDTO;
import com.deloitte.inspection.dto.PasswordMaintenanceDTO;
import com.deloitte.inspection.email.EmailService;
import com.deloitte.inspection.exception.CryptoException;
import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.model.LISAccessMaster;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISUserMasterCreate;
import com.deloitte.inspection.service.AccessMasterService;
import com.deloitte.inspection.service.LoginService;
import com.deloitte.inspection.util.RandomPasswordGenerator;

@Service
public class AccessMasterServiceImpl implements AccessMasterService{
	
	private static final Logger logger = LogManager.getLogger(LoginDAOImpl.class);

	@Autowired 
	AccessMasterDAO accessMasterDao;
	
	@Override
	public AccessMasterDTO saveAccess(AccessMasterDTO accessMasterDTO, HttpSession httpSession) throws Exception {
		// TODO Auto-generated method stub
		
		AccessMasterDTO responseDTO = new AccessMasterDTO();
					
		if (null != accessMasterDTO && null != accessMasterDTO.getSubscriberId()) {
			logger.info("Saving data for Access Master");
			try {
				accessMasterDao.saveAccessMaster(accessMasterDTO);
				accessMasterDTO.setStatus(StatusConstants.SUCCESS);
				} catch(Exception exception) {
					accessMasterDTO.setStatus(StatusConstants.FAILURE);
					logger.error("Exception While validating credentials "+exception.getMessage());
				}
			}else {
				accessMasterDTO.setStatus(StatusConstants.FAILURE);
				logger.error("Exception While saving access master as reques is null");
			}		
		return accessMasterDTO; 
	
	}

	@Override
	public AccessMasterDTO getAccessMasterScreens(Integer subsId, Integer userTypeId) throws Exception {

		logger.info("inside getAccessMasterScreens with subsId :"+ subsId + " and userTypeId :" + userTypeId);
		AccessMasterDTO responseDTO = new AccessMasterDTO();
		LISAccessMaster responseLACS = new LISAccessMaster(); 
		if (null != subsId || null != userTypeId) {
			
			responseLACS = accessMasterDao.getAccessScreens(subsId, userTypeId);
			responseDTO.setStatus(StatusConstants.SUCCESS);
			if (null == responseLACS) {				
				responseDTO.setMessage(StatusConstants.ACCESS_MASTER_DATA_NOT_AVAILABLE);
				return responseDTO;
			} else {
				responseDTO.setMessage(StatusConstants.ACCESS_MASTER_DATA_AVAILABLE);
				return responseDTO;
			}
		} else {
			responseDTO.setStatus(StatusConstants.FAILURE);
		}
		return null;
	}
}
