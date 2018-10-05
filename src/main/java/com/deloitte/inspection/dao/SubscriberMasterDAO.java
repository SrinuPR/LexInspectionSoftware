package com.deloitte.inspection.dao;

import com.deloitte.inspection.dto.SubscriberMasterDTO;
import com.deloitte.inspection.exception.SubscriberMasterException;
import com.deloitte.inspection.model.LISSubscriberMaster;

public interface SubscriberMasterDAO {
	
	public LISSubscriberMaster validateSubscriber(Integer subscriberId) throws SubscriberMasterException;
	
	public SubscriberMasterDTO createSubscriber(SubscriberMasterDTO subMasterDTO) throws SubscriberMasterException;
	
	public LISSubscriberMaster getSubscriberById(Integer subscriberId) throws SubscriberMasterException;
}
