package com.deloitte.inspection.dto;

public class InspectionMasterDTO {
	
	private Integer subscriberId;
	private String subscriberName;
	private Integer inspectionMasterId;
	private String componentProductDrawNumber;
	private String componentProductName;
	private String componentProductNumber;
	private String componentProductMaterial;
	private String componentProductNotes;
	private Integer inspectionType;
	private Integer inspectionStage;
	private String inspectionTypeName;
	private String inspectionStageName;
	private String createdBy;
	private String updatedBy;
	private Integer userId;
	
		
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Integer getInspectionMasterId() {
		return inspectionMasterId;
	}
	public void setInspectionMasterId(Integer inspectionMasterId) {
		this.inspectionMasterId = inspectionMasterId;
	}
	public String getComponentProductDrawNumber() {
		return componentProductDrawNumber;
	}
	public void setComponentProductDrawNumber(String componentProductDrawNumber) {
		this.componentProductDrawNumber = componentProductDrawNumber;
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
	public String getComponentProductMaterial() {
		return componentProductMaterial;
	}
	public void setComponentProductMaterial(String componentProductMaterial) {
		this.componentProductMaterial = componentProductMaterial;
	}
	public String getComponentProductNotes() {
		return componentProductNotes;
	}
	public void setComponentProductNotes(String componentProductNotes) {
		this.componentProductNotes = componentProductNotes;
	}
	public Integer getInspectionType() {
		return inspectionType;
	}
	public void setInspectionType(Integer inspectionType) {
		this.inspectionType = inspectionType;
	}
	public Integer getInspectionStage() {
		return inspectionStage;
	}
	public void setInspectionStage(Integer inspectionStage) {
		this.inspectionStage = inspectionStage;
	}
	public String getInspectionTypeName() {
		return inspectionTypeName;
	}
	public void setInspectionTypeName(String inspectionTypeName) {
		this.inspectionTypeName = inspectionTypeName;
	}
	public String getInspectionStageName() {
		return inspectionStageName;
	}
	public void setInspectionStageName(String inspectionStageName) {
		this.inspectionStageName = inspectionStageName;
	}

}
