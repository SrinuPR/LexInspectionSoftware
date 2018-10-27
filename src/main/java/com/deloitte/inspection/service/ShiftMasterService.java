package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ShiftMasterDTO;
import com.deloitte.inspection.exception.ShiftMasterException;
import com.deloitte.inspection.response.dto.ShiftMasterResponseDTO;


public interface ShiftMasterService {

	public CommonDTO validateShiftId (String shiftId) throws ShiftMasterException;

	public ShiftMasterResponseDTO createShiftMaster (ShiftMasterDTO shiftMasterDTO) throws ShiftMasterException ;

	public ShiftMasterResponseDTO getAllShifts (int subscriberId) throws ShiftMasterException ;
	
	public ShiftMasterResponseDTO deleteShift (String shiftId,String userId) throws ShiftMasterException ;
	
	public ShiftMasterResponseDTO updateShiftMaster (ShiftMasterDTO shiftMasterDTO) throws ShiftMasterException ;

	}
