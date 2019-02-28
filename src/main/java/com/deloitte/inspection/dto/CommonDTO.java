package com.deloitte.inspection.dto;

public class CommonDTO {

	private String status;
	private String message;
	private LoginDTO userInfo;
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LoginDTO getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(LoginDTO userInfo) {
		this.userInfo = userInfo;
	}
	
}
