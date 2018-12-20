package com.deloitte.inspection.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class LogInMap {

	private static LogInMap logInMap = null;
	public Map<String, HttpSession>  logins = null;
	
	private LogInMap() {
		logins = new HashMap<String, HttpSession>();
	}
	
	
	public static synchronized LogInMap getInstance() {
		if(logInMap == null) {
			logInMap = new LogInMap();
		}
		return logInMap;
	}
}
