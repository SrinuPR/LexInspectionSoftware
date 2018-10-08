/**
 * 
 */
package com.deloitte.inspection.service;

import java.util.List;
import com.deloitte.inspection.dto.SubscriberMasterDTO;
import com.deloitte.inspection.exception.SubscriberMasterException;

/**
 * @author rnarne
 *
 */
public interface SubscriberMasterService {
	public String validateSubscriber(Integer subscriberId) throws SubscriberMasterException;
	public SubscriberMasterDTO createSubscriber(SubscriberMasterDTO subMasterDTO) throws SubscriberMasterException;
	public List<SubscriberMasterDTO> getAllSubscriberMasterData() throws SubscriberMasterException;
}
