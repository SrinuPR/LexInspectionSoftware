/**
 * 
 */
package com.deloitte.inspection.service;

import com.deloitte.inspection.dto.UserTypeMasterDTO;
import com.deloitte.inspection.exception.UserTypeMasterException;

/**
 * @author rnarne
 *
 */
public interface UserTypeMasterService {
	public String validateUserType(UserTypeMasterDTO userTypeMasterDTO) throws UserTypeMasterException;
	public UserTypeMasterDTO createUserTypeMaster(UserTypeMasterDTO userTypeMasterDTO) throws UserTypeMasterException;
}
