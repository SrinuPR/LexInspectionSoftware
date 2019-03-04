package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.mapper.LISUserTypeMasterResult;
import com.deloitte.inspection.model.LISAccessMaster;

public interface AccessMasterDAO {

	public void saveAccessMaster(LISAccessMaster accessMaster) throws Exception;
	
	public LISAccessMaster getAccessScreens (Integer subsId, Integer userTypeId) throws Exception;

	public List<LISUserTypeMasterResult> getUserTypeListforSubscriber(Integer subscriberId) throws Exception;

	public LISAccessMaster getAccessMaster(Integer accessMasterId) throws Exception;

	public LISAccessMaster getAccessMasterByUserTypeId(Integer userTypeId) throws Exception;
}
