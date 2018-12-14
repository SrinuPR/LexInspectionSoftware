package com.deloitte.inspection.dao;

import com.deloitte.inspection.dto.AccessMasterDTO;
import com.deloitte.inspection.model.LISAccessMaster;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public interface AccessMasterDAO {

	public AccessMasterDTO saveAccessMaster(AccessMasterDTO accessMasterDTO) throws Exception;
	
	public LISAccessMaster getAccessScreens (Integer subsId, Integer userTypeId) throws Exception;
}
