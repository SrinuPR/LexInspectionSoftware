/**
 * 
 */
package com.deloitte.inspection.dao;

import com.deloitte.inspection.dto.UserTypeMasterDTO;
import com.deloitte.inspection.exception.UserTypeMasterException;
import com.deloitte.inspection.model.LISUserTypeMaster;

/**
 * @author rnarne
 *
 */
public interface UserTypeMasterDAO {
	public LISUserTypeMaster validateUserType(UserTypeMasterDTO userTypeMasDTO) throws UserTypeMasterException;
	public UserTypeMasterDTO createUserTypeMaster(UserTypeMasterDTO userTypeMasDTO) throws UserTypeMasterException;
}
