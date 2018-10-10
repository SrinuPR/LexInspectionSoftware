package com.deloitte.inspection.dto;

import java.util.Date;
import java.util.List;

import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseOrderDataDTO {
	
	private Integer customerPoId;
	private Integer subscriberId;
	private String subscriberName;
	private String customerPONumber;
	private String customerPODate;
	private Integer customerPOQuantity;
	private String poNotes;
	private String errorMessage;
	private String status; 
	private Integer componentId;
	private List<LISPurchaseOrderMaster> purchaseOrderList;
	private String userId;
	
	
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
	public List<LISPurchaseOrderMaster> getPurchaseOrderList() {
		return purchaseOrderList;
	}
	public void setPurchaseOrderList(List<LISPurchaseOrderMaster> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
