package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.ShiftMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ShiftMasterDAO;
import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ShiftMasterDTO;
import com.deloitte.inspection.exception.ShiftMasterException;
import com.deloitte.inspection.model.LISShiftMaster;
import com.deloitte.inspection.response.dto.ShiftMasterResponseDTO;
import com.deloitte.inspection.service.ShiftMasterService;



@Service
public class ShiftMasterServiceImpl implements ShiftMasterService{
	
	private static final Logger logger = LogManager.getLogger(ShiftMasterServiceImpl.class); 
	
	@Autowired
	private ShiftMasterDAO ShiftMasterDAO;
	
	 
	@Override
	public CommonDTO validateShiftId(String shiftId) {
		LISShiftMaster shiftMasater = new LISShiftMaster();
		CommonDTO commonDTO=new CommonDTO();
		try {
			shiftMasater = ShiftMasterDAO.getShiftId(shiftId);
			if (!shiftId.isEmpty()) {
				if (null != shiftMasater && null != shiftMasater.getShiftId()) {
					commonDTO.setMessage(ShiftMasterConstants.SHIFT_ID_EXISTS);
					commonDTO.setStatus(StatusConstants.SUCCESS);
					return commonDTO;
				}
			}

		} catch (ShiftMasterException cue) {
			cue.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		commonDTO.setMessage(ShiftMasterConstants.SHIFT_DOESNT_ID_EXISTS);
		commonDTO.setStatus(StatusConstants.FAILURE);
		return commonDTO;

	}
	@Override
	public ShiftMasterResponseDTO createShiftMaster(ShiftMasterDTO shiftMasterDTO, String userId) throws ShiftMasterException {
		
		ShiftMasterResponseDTO resShiftMasterDTO = new ShiftMasterResponseDTO();
		Integer subscriberId = null;
			try {
			if(null!=shiftMasterDTO && null!=shiftMasterDTO.getSubscriberId()) {
				subscriberId = shiftMasterDTO.getSubscriberId();
				if( null!=shiftMasterDTO.getShiftId()) {
					shiftMasterDTO.setCreatedTimestamp(new Date());
					shiftMasterDTO= ShiftMasterDAO.createShiftMaster(shiftMasterDTO,userId);
					if(null!=shiftMasterDTO) {
						resShiftMasterDTO.setMessage(ShiftMasterConstants.SHIFT_SAVE_SUCCESS);
					}else {
						resShiftMasterDTO.setMessage(ShiftMasterConstants.SHIFT_SAVE_FAILURE);
					}
				}else if(null!=shiftMasterDTO && null==shiftMasterDTO.getShiftId()){
					resShiftMasterDTO.setMessage(ShiftMasterConstants.SHIFT_ID_REQUIRED);
				}	
				List<ShiftMasterDTO> shiftMasterDTOList=new ArrayList<ShiftMasterDTO>();
				List<LISShiftMaster> shiftMasterList=ShiftMasterDAO.findBySubscriberId(subscriberId);
				for(LISShiftMaster tempMaster:shiftMasterList) {
					ShiftMasterDTO masterDTO=new ShiftMasterDTO();
					masterDTO.setShiftId(tempMaster.getShiftId());
					masterDTO.setShiftName(tempMaster.getShiftName());
					masterDTO.setSubscriberId(tempMaster.getSubscriberMaster().getSubscriberId());
					shiftMasterDTOList.add(masterDTO);
				}
				resShiftMasterDTO.setResult(shiftMasterDTOList);
			}
			
		} catch (ShiftMasterException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resShiftMasterDTO;
		
	 }
	@Override
	public ShiftMasterResponseDTO getAllShifts(Integer subscriberId) throws ShiftMasterException {
		ShiftMasterResponseDTO shiftMasterResponseDTO=new ShiftMasterResponseDTO();
		List<ShiftMasterDTO> shiftMasterDTOList=new ArrayList<ShiftMasterDTO>();
		List<LISShiftMaster> shiftMasterList=ShiftMasterDAO.findBySubscriberId(subscriberId);
		if(null!=shiftMasterList && shiftMasterList.size()>0) {
			for(LISShiftMaster tempMaster:shiftMasterList) {
				ShiftMasterDTO masterDTO=new ShiftMasterDTO();
				masterDTO.setShiftId(tempMaster.getShiftId());
				masterDTO.setShiftName(tempMaster.getShiftName());
				masterDTO.setSubscriberId(tempMaster.getSubscriberMaster().getSubscriberId());
				shiftMasterDTOList.add(masterDTO);
			}
		}
		shiftMasterResponseDTO.setResult(shiftMasterDTOList);
		shiftMasterResponseDTO.setStatus(StatusConstants.SUCCESS);
		shiftMasterResponseDTO.setMessage(ShiftMasterConstants.FETCH_SHIFT_LIST_SUCCESS);
		return shiftMasterResponseDTO;
	}
	
	@Override
	public ShiftMasterResponseDTO deleteShift(String shiftId,String userId) throws ShiftMasterException {
		ShiftMasterResponseDTO shiftMasterResponseDTO = new ShiftMasterResponseDTO();
		try{
			String deleteStatus=ShiftMasterDAO.deleteByShiftId(shiftId,userId) ;
			if(StatusConstants.SUCCESS.equalsIgnoreCase(deleteStatus)){
				shiftMasterResponseDTO.setMessage(ShiftMasterConstants.SHIFT_DELETE_SUCCESS);
			}else{
				shiftMasterResponseDTO.setMessage(ShiftMasterConstants.SHIFT_DELETE_FAILURE);
			}
		}catch(Exception exception){
			shiftMasterResponseDTO.setStatus(StatusConstants.ERROR);
			shiftMasterResponseDTO.setMessage(ShiftMasterConstants.SHIFT_DELETE_FAILURE);
			logger.error("Error while deleting the shift master "+exception.getMessage());
		}
		return shiftMasterResponseDTO;
	}
	@Override
	public ShiftMasterResponseDTO updateShiftMaster(ShiftMasterDTO shiftMasterDTO) throws ShiftMasterException {
		
		ShiftMasterResponseDTO resShiftMasterDTO = new ShiftMasterResponseDTO();
		try {
			if(null!=shiftMasterDTO && null!=shiftMasterDTO.getSubscriberId()) {
				Integer subscriberId = shiftMasterDTO.getSubscriberId();
				if(null!=shiftMasterDTO.getShiftId()) {
					shiftMasterDTO.setUpdatedTimestamp(new Date());
					shiftMasterDTO= ShiftMasterDAO.updateShiftMaster(shiftMasterDTO);
					if(null!=shiftMasterDTO) {
						resShiftMasterDTO.setMessage(ShiftMasterConstants.SHIFT_SAVE_SUCCESS);
					}else {
						resShiftMasterDTO.setMessage(ShiftMasterConstants.SHIFT_SAVE_FAILURE);
					}
				}else if(null!=shiftMasterDTO && null==shiftMasterDTO.getShiftId()){
					resShiftMasterDTO.setMessage(ShiftMasterConstants.SHIFT_ID_REQUIRED);
				}
				List<ShiftMasterDTO> shiftMasterDTOList=new ArrayList<ShiftMasterDTO>();
				List<LISShiftMaster> shiftMasterList=ShiftMasterDAO.findBySubscriberId(subscriberId);
				for(LISShiftMaster tempMaster:shiftMasterList) {
					ShiftMasterDTO masterDTO=new ShiftMasterDTO();
					masterDTO.setShiftId(tempMaster.getShiftId());
					masterDTO.setShiftName(tempMaster.getShiftName());
					masterDTO.setSubscriberId(tempMaster.getSubscriberMaster().getSubscriberId());
					shiftMasterDTOList.add(masterDTO);
				}
				resShiftMasterDTO.setResult(shiftMasterDTOList);
			}
			
		} catch (ShiftMasterException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resShiftMasterDTO;
		
	 }
	
}
