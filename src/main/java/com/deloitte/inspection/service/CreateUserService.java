package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.exception.CreateUserException;

public interface CreateUserService {
	
	public CreateUserDTO createUser (CreateUserDTO createuserDTO) throws CreateUserException ;
	
	public String validateUserId(String userId) throws CreateUserException ;

	public List<com.deloitte.inspection.dto.UserTypeMasterDTO> getUserTypeBySubscriberId(Integer subscriberId) throws CreateUserException;

	public CreateUserDTO deleteAdmin(String adminId) throws CreateUserException;

	public CreateUserDTO saveAdmin(CreateUserDTO createUserDTO) throws CreateUserException;

	public CreateUserDTO validateAdminId(String adminId) throws CreateUserException;

}
