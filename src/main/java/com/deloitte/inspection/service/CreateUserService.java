package com.deloitte.inspection.service;

import com.deloitte.inspection.dto.CreateUserDTO;
import com.deloitte.inspection.exception.CreateUserException;

public interface CreateUserService {
	
	public CreateUserDTO createUser (CreateUserDTO createuserDTO) throws CreateUserException ;
	
	public CreateUserDTO fetchData () throws CreateUserException ;
	
	public String validateUserId(CreateUserDTO createuserDTO) throws CreateUserException ;

}
