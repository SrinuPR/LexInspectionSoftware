package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.dto.ShiftMasterDTO;
import com.deloitte.inspection.exception.ShiftMasterException;
import com.deloitte.inspection.mapper.LISShiftMasterResult;
import com.deloitte.inspection.model.LISShiftMaster;

public interface ShiftMasterDAO {

	public ShiftMasterDTO createShiftMaster (ShiftMasterDTO createShiftMasterDTO, String userId) throws ShiftMasterException;
	
	public LISShiftMaster getShiftId (String shiftId) throws ShiftMasterException;
	
	public List<LISShiftMasterResult> findBySubscriberId (Integer subscriberId) throws ShiftMasterException;
	
	public String deleteByShiftId (String shiftId,String userId) throws ShiftMasterException;
	
	public ShiftMasterDTO updateShiftMaster (ShiftMasterDTO createShiftMasterDTO) throws ShiftMasterException;

	public List<LISShiftMasterResult> getAllShiftsBySubscriberId(Integer subscriberId) throws ShiftMasterException;
}
