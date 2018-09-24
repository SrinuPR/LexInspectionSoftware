package com.deloitte.inspection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.inspection.service.LoginService;

@RestController
@RequestMapping(value = "/user")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> display(){
		String status= loginService.validateCredentails();
		return new ResponseEntity(status, HttpStatus.OK);
	}

}
