package com.deloitte.inspection.dto;

public class ComponentMasterDataDTO {
	
	private Integer subscriberId;
	private String subscriberName;
	private Integer componentId;
	private String componentProductDrawNumber;
	private String customerNameAddress;
	private String componentProductName;
	private String componentProductNumber;
	private String componentProductMeterial;
	private String componentProductManufacturerUnits;
	private String componentProductNotes;
	
	public Integer getComponentId() {
		return componentId;
	}
	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
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
	public String getComponentProductDrawNumber() {
		return componentProductDrawNumber;
	}
	public void setComponentProductDrawNumber(String componentProductDrawNumber) {
		this.componentProductDrawNumber = componentProductDrawNumber;
	}
	public String getCustomerNameAddress() {
		return customerNameAddress;
	}
	public void setCustomerNameAddress(String customerNameAddress) {
		this.customerNameAddress = customerNameAddress;
	}
	public String getComponentProductName() {
		return componentProductName;
	}
	public void setComponentProductName(String componentProductName) {
		this.componentProductName = componentProductName;
	}
	public String getComponentProductNumber() {
		return componentProductNumber;
	}
	public void setComponentProductNumber(String componentProductNumber) {
		this.componentProductNumber = componentProductNumber;
	}
	public String getComponentProductMeterial() {
		return componentProductMeterial;
	}
	public void setComponentProductMeterial(String componentProductMeterial) {
		this.componentProductMeterial = componentProductMeterial;
	}
	public String getComponentProductManufacturerUnits() {
		return componentProductManufacturerUnits;
	}
	public void setComponentProductManufacturerUnits(String componentProductManufacturerUnits) {
		this.componentProductManufacturerUnits = componentProductManufacturerUnits;
	}
	public String getComponentProductNotes() {
		return componentProductNotes;
	}
	public void setComponentProductNotes(String componentProductNotes) {
		this.componentProductNotes = componentProductNotes;
	}

}
