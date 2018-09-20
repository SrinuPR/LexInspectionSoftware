package com.deloitte.inspection.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {
	
	@RequestMapping(value = "/test")
	public String display(){
		return "Welcome";
	}

}
