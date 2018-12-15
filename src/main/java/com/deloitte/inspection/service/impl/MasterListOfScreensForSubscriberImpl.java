package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.MasterListOfScreensForSubscriberConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.MasterListOfScreensForSubscriberDAO;
import com.deloitte.inspection.dto.MasterListOfScreensForSubscriberDTO;
import com.deloitte.inspection.exception.MasterListOfScreensForSubscriberException;
import com.deloitte.inspection.model.LISMasterListOfScreensForSubscriber;
import com.deloitte.inspection.response.dto.MasterListOfScreensForSubscriberResponseDTO;
import com.deloitte.inspection.service.MasterListOfScreensForSubscriber;

@Service
public class MasterListOfScreensForSubscriberImpl implements MasterListOfScreensForSubscriber{

	private static final Logger logger = LogManager.getLogger(MasterListOfScreensForSubscriberImpl.class);
	
	@Autowired
	private MasterListOfScreensForSubscriberDAO masterListOfScreensForSubscriberDAO;
	
	@Override
	public MasterListOfScreensForSubscriberResponseDTO saveScreensData(List<MasterListOfScreensForSubscriberDTO> masterListOfScreensForSubscriberDTO)
			throws MasterListOfScreensForSubscriberException {
		logger.info("Entered into saveScreensData service");
		MasterListOfScreensForSubscriberResponseDTO masterListOfScreensForSubscriberResponseDTO = new MasterListOfScreensForSubscriberResponseDTO();
		try{
			List<LISMasterListOfScreensForSubscriber> insertList = new ArrayList<LISMasterListOfScreensForSubscriber>();
			for(MasterListOfScreensForSubscriberDTO masterDTO : masterListOfScreensForSubscriberDTO){
				if(null != masterDTO.getMasterListId() && !masterDTO.isSelected()){
					deleteRow(masterDTO);
				}else if(null == masterDTO.getMasterListId() && masterDTO.isSelected()){
					LISMasterListOfScreensForSubscriber insertRow = new LISMasterListOfScreensForSubscriber();
					insertRow = updateDetailsToModel(insertRow,masterDTO);
					insertList.add(insertRow);
				}
			}
			masterListOfScreensForSubscriberDAO.insertScreensForSubscribers(insertList);
			masterListOfScreensForSubscriberResponseDTO.setStatus(StatusConstants.SUCCESS);
			masterListOfScreensForSubscriberResponseDTO.setMessage(MasterListOfScreensForSubscriberConstants.SCREEN_LIST_SAVE_SUCCESS);
		}catch(Exception exception){
			masterListOfScreensForSubscriberResponseDTO.setStatus(StatusConstants.FAILURE);
			masterListOfScreensForSubscriberResponseDTO.setMessage(MasterListOfScreensForSubscriberConstants.SCREEN_LIST_SAVE_FAILED);
			logger.error("Exception while saveScreensData" +exception.getMessage());
		}
		return masterListOfScreensForSubscriberResponseDTO;
	}
	
	private void deleteRow(MasterListOfScreensForSubscriberDTO masterDTO) throws MasterListOfScreensForSubscriberException{
		try {
			masterListOfScreensForSubscriberDAO.deleteScreenForSubscriber(masterDTO.getSubscriberId(), masterDTO.getScreenNumber());
		} catch (Exception exception) {
			logger.error("Exception while saveScreensData" +exception.getMessage());
			exception.printStackTrace();
		}
	}
	
	private LISMasterListOfScreensForSubscriber updateDetailsToModel(LISMasterListOfScreensForSubscriber row,
			MasterListOfScreensForSubscriberDTO masterDTO) {
		row.setCreatedBy(masterDTO.getUserId());
		row.setCreatedTimestamp(new Date());
		row.setIsActive(StatusConstants.IS_ACTIVE);
		row.setScreenName(masterDTO.getScreenName());
		row.setScreenNumber(masterDTO.getScreenNumber());
		row.setSubscriberId(masterDTO.getSubscriberId());
		return row;
	}

	@Override
	public MasterListOfScreensForSubscriberResponseDTO getScreensforSubscriber(Integer subscriberId)
			throws MasterListOfScreensForSubscriberException {
		MasterListOfScreensForSubscriberResponseDTO masterListOfScreensForSubscriberResponseDTO = new MasterListOfScreensForSubscriberResponseDTO();
		logger.info("Entered into getScreensforSubscriber service");
		try{
			List<LISMasterListOfScreensForSubscriber> masterList = masterListOfScreensForSubscriberDAO.getScreensforSubscriber(subscriberId);
			if(null != masterList && masterList.size() > 0){
				List<MasterListOfScreensForSubscriberDTO> results = new ArrayList<MasterListOfScreensForSubscriberDTO>();
				for(LISMasterListOfScreensForSubscriber screenRow:masterList){
					MasterListOfScreensForSubscriberDTO screenData = new MasterListOfScreensForSubscriberDTO();
					screenData.setMasterListId(screenRow.getMasterListId());
					screenData.setScreenName(screenRow.getScreenName());
					screenData.setScreenNumber(screenRow.getScreenNumber());
					screenData.setSubscriberId(screenRow.getSubscriberId());
					results.add(screenData);
				}
				masterListOfScreensForSubscriberResponseDTO.setResults(results);
			}
			masterListOfScreensForSubscriberResponseDTO.setStatus(StatusConstants.SUCCESS);
			masterListOfScreensForSubscriberResponseDTO.setMessage(MasterListOfScreensForSubscriberConstants.SUBSCRIBER_SCREEN_LIST_SUCCESS);
		}catch(Exception exception){
			masterListOfScreensForSubscriberResponseDTO.setStatus(StatusConstants.FAILURE);
			masterListOfScreensForSubscriberResponseDTO.setMessage(MasterListOfScreensForSubscriberConstants.SUBSCRIBER_SCREEN_LIST_FAILED);
			logger.error("Exception while getScreensforSubscriber" +exception.getMessage());
		}
		return masterListOfScreensForSubscriberResponseDTO;
	}

}
