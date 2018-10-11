package com.deloitte.inspection.dto;


public class PurchaseOrderDataDTO {
	
	private Integer customerPoId;
	private String componentProductDrawNum;
	private Integer subscriberId;
	private String subscriberName;
	private String customerPONumber;
	private String customerPODate;
	private Integer customerPOQuantity;
	private String poNotes;
	private Integer componentId;
	
	public Integer getCustomerPoId() {
		return customerPoId;
	}
	public void setCustomerPoId(Integer customerPoId) {
		this.customerPoId = customerPoId;
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
	public String getCustomerPONumber() {
		return customerPONumber;
	}
	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}
	
	public Integer getCustomerPOQuantity() {
		return customerPOQuantity;
	}
	public void setCustomerPOQuantity(Integer customerPOQuantity) {
		this.customerPOQuantity = customerPOQuantity;
	}
	public String getPoNotes() {
		return poNotes;
	}
	public void setPoNotes(String poNotes) {
		this.poNotes = poNotes;
	}
	public String getCustomerPODate() {
		return customerPODate;
	}
	public void setCustomerPODate(String customerPODate) {
		this.customerPODate = customerPODate;
	}
	
	public Integer getComponentId() {
		return componentId;
	}
	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}
	public String getComponentProductDrawNum() {
		return componentProductDrawNum;
	}
	public void setComponentProductDrawNum(String componentProductDrawNum) {
		this.componentProductDrawNum = componentProductDrawNum;
	}	
}
