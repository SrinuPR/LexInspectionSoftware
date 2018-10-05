/**
 * 
 */
package com.deloitte.inspection.service;

import com.deloitte.inspection.dto.SubscriberMasterDTO;
import com.deloitte.inspection.exception.SubscriberMasterException;

/**
 * @author rnarne
 *
 */
public interface SubscriberMasterService {
	public String validateSubscriber(SubscriberMasterDTO subMasterDTO) throws SubscriberMasterException;
	public SubscriberMasterDTO createSubscriber(SubscriberMasterDTO subMasterDTO) throws SubscriberMasterException;
}
