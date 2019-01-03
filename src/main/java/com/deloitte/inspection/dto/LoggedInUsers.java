package com.deloitte.inspection.dto;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.deloitte.inspection.config.LogInMap;

public class LoggedInUsers implements HttpSessionBindingListener{

	private String userId;
	private String password;
	private boolean alreadyLoggedIn = false;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession oldSession = (LogInMap.getInstance().logins).get(this.getUserId());
		if (oldSession != null) {
			alreadyLoggedIn = true;
		}else{
			(LogInMap.getInstance().logins).put(this.getUserId(), event.getSession());
		}		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		(LogInMap.getInstance().logins).remove(this.getUserId());	
	}
	
	@Override
	public boolean equals(Object other) {
		boolean isEqual = false;
		if(other instanceof LoginDTO){
			if(getUserId() != null){
				isEqual = getUserId().equals(((LoginDTO) other).getUserId());
			}else{
				isEqual = (other == this);
			}
		}
		return isEqual;
	}
	
	@Override
	public int hashCode() {
		int hashValue;
		if(getUserId() != null){
			hashValue = (getUserId().hashCode());
		}else{
			hashValue = super.hashCode();
		}
		return hashValue; 
	}
	
	public boolean isAlreadyLoggedIn() {
		return alreadyLoggedIn;
	}
}
