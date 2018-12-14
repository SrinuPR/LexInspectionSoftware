package com.deloitte.inspection.service;

import com.deloitte.inspection.dto.AccessMasterDTO;
import com.deloitte.inspection.response.dto.AccessMasterResponseDTO;

public interface AccessMasterService {

	public AccessMasterResponseDTO saveAccess( AccessMasterDTO accessMasterDTO) throws Exception;
	
	public AccessMasterDTO getAccessMasterScreens (Integer subsId, Integer userTypeId) throws Exception;

	public AccessMasterResponseDTO getUserTypeListforSubscriber(Integer subscriberId) throws Exception;
}
