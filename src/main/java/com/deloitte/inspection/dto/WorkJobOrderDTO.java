package com.deloitte.inspection.dto;

public class WorkJobOrderDTO {
	
	private Integer wjOrderId;
	private String workJobOrderNumber;
	private String workJobOrderDate;
	private String lotNumber;
	private Integer lotSize;
	private String lotSizeUnits;
	private String manufacturingBatchNumber;
	private Integer manufacturingBatchSize;
	private String manufacturingBatchUnits;
	private String workOrderJobNotes;
	private Integer subscriberId;
	private String SubscriberName;
	private String componentProductDrawNumber;
	private String customerPONumber;
	
	public Integer getWjOrderId() {
		return wjOrderId;
	}
	public void setWjOrderId(Integer wjOrderId) {
		this.wjOrderId = wjOrderId;
	}
	public String getWorkJobOrderNumber() {
		return workJobOrderNumber;
	}
	public void setWorkJobOrderNumber(String workJobOrderNumber) {
		this.workJobOrderNumber = workJobOrderNumber;
	}
	public String getWorkJobOrderDate() {
		return workJobOrderDate;
	}
	public void setWorkJobOrderDate(String workJobOrderDate) {
		this.workJobOrderDate = workJobOrderDate;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public Integer getLotSize() {
		return lotSize;
	}
	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}
	public String getLotSizeUnits() {
		return lotSizeUnits;
	}
	public void setLotSizeUnits(String lotSizeUnits) {
		this.lotSizeUnits = lotSizeUnits;
	}
	public String getManufacturingBatchNumber() {
		return manufacturingBatchNumber;
	}
	public void setManufacturingBatchNumber(String manufacturingBatchNumber) {
		this.manufacturingBatchNumber = manufacturingBatchNumber;
	}
	public Integer getManufacturingBatchSize() {
		return manufacturingBatchSize;
	}
	public void setManufacturingBatchSize(Integer manufacturingBatchSize) {
		this.manufacturingBatchSize = manufacturingBatchSize;
	}
	public String getManufacturingBatchUnits() {
		return manufacturingBatchUnits;
	}
	public void setManufacturingBatchUnits(String manufacturingBatchUnits) {
		this.manufacturingBatchUnits = manufacturingBatchUnits;
	}
	public String getWorkOrderJobNotes() {
		return workOrderJobNotes;
	}
	public void setWorkOrderJobNotes(String workOrderJobNotes) {
		this.workOrderJobNotes = workOrderJobNotes;
	}
	public Integer getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getSubscriberName() {
		return SubscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		SubscriberName = subscriberName;
	}
	public String getComponentProductDrawNumber() {
		return componentProductDrawNumber;
	}
	public void setComponentProductDrawNumber(String componentProductDrawNumber) {
		this.componentProductDrawNumber = componentProductDrawNumber;
	}
	public String getCustomerPONumber() {
		return customerPONumber;
	}
	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}
}
