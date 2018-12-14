package com.deloitte.inspection.service;

import javax.servlet.http.HttpSession;

import com.deloitte.inspection.dto.AccessMasterDTO;
import com.deloitte.inspection.dto.LoginDTO;

public interface AccessMasterService {

	public AccessMasterDTO saveAccess( AccessMasterDTO accessMasterDTO, HttpSession httpSession) throws Exception;
	
	public AccessMasterDTO getAccessMasterScreens (Integer subsId, Integer userTypeId) throws Exception;
}
