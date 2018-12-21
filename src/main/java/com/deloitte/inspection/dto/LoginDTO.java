package com.deloitte.inspection.dto;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.deloitte.inspection.config.LogInMap;
import com.deloitte.inspection.constant.StatusConstants;

public class LoginDTO /*implements HttpSessionBindingListener*/ {
	
	private String userId;
	private String password;
	private String userName;
	private Integer subscriberId;
	private String subscriberName;
	private String status;
	private String errorMessage;
	private boolean firstTimeLogin;
	private char isAdmin;
	private String screenList;
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getSubscriberName() {
		return subscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	public boolean isFirstTimeLogin() {
		return firstTimeLogin;
	}
	public void setFirstTimeLogin(boolean firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}
	public char getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(char isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getScreenList() {
		return screenList;
	}
	public void setScreenList(String screenList) {
		this.screenList = screenList;
	}
	

	/*@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession oldSession = (LogInMap.getInstance().logins).get(this.getUserId());
		if(oldSession != null){
			int  val = oldSession.getMaxInactiveInterval();
			if(val >= StatusConstants.MAX_INACTIVE_INTERVAL){
				(LogInMap.getInstance().logins).remove(this.getUserId());
				oldSession = null;
			}
		}
		if (oldSession != null) {
			alreadyLoggedIn = true;
		}else{
			(LogInMap.getInstance().logins).put(this.getUserId(), event.getSession());
		}		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		if(!"user".equalsIgnoreCase(event.getName())){
			(LogInMap.getInstance().logins).remove(this.getUserId());	
		}
	}*/
	
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
	public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
		this.alreadyLoggedIn = alreadyLoggedIn;
	}
	
}
