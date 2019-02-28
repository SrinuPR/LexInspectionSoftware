/**
 * 
 */
package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author rnarne
 *
 */
@Document(collection = "LIS_INMDC")
public class LISInspectionMaster implements Serializable{
		
	private static final long serialVersionUID = 382945870712132210L;
	
	@Id
	private Long id;

	@Field(value = "INSPECTION_ID")
	private Integer inspId;
	
	@Field(value = "COMPONENT_NAME")
	private String facilityName;
		
	@Field(value = "COMPONENT_NUM")
	private String compNum;
	
	@Field(value = "COMPONENT_MATERIAL")
	private String compMaterial;
	
	@Field(value = "COMPONENT_NOTES")
	private String compNotes;

	@Field(value = "INSPECTION_TYPE_ID")
	private Integer inspTypeId; 
	
	@Field(value = "INSPECTION_STAGE_ID")
	private Integer inspStageId;
	
	@Field(value = "CREATED_BY")
	private String createdBy;
	
	@Field(value = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Field(value = "UPDATED_BY")
	private String updatedBy;
	
	@Field(value = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@Field(value = "IS_ACTIVE")
	private char isActive;

	private LISSubscriberMaster subscriber;
	
	private LISMaintainMasterDataComponent component;
	
	@Field(value = "RECORD_IN_PROCESS")
	private char recordInProcess;

	/**
	 * @return the inspId
	 */
	public Integer getInspId() {
		return inspId;
	}

	/**
	 * @param inspId the inspId to set
	 */
	public void setInspId(Integer inspId) {
		this.inspId = inspId;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return the compNum
	 */
	public String getCompNum() {
		return compNum;
	}

	/**
	 * @param compNum the compNum to set
	 */
	public void setCompNum(String compNum) {
		this.compNum = compNum;
	}

	/**
	 * @return the compMaterial
	 */
	public String getCompMaterial() {
		return compMaterial;
	}

	/**
	 * @param compMaterial the compMaterial to set
	 */
	public void setCompMaterial(String compMaterial) {
		this.compMaterial = compMaterial;
	}

	/**
	 * @return the compNotes
	 */
	public String getCompNotes() {
		return compNotes;
	}

	/**
	 * @param compNotes the compNotes to set
	 */
	public void setCompNotes(String compNotes) {
		this.compNotes = compNotes;
	}

	/**
	 * @return the inspTypeId
	 */
	public Integer getInspTypeId() {
		return inspTypeId;
	}

	/**
	 * @param inspTypeId the inspTypeId to set
	 */
	public void setInspTypeId(Integer inspTypeId) {
		this.inspTypeId = inspTypeId;
	}

	/**
	 * @return the inspStageId
	 */
	public Integer getInspStageId() {
		return inspStageId;
	}

	/**
	 * @param inspStageId the inspStageId to set
	 */
	public void setInspStageId(Integer inspStageId) {
		this.inspStageId = inspStageId;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdTimestamp
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * @param createdTimestamp the createdTimestamp to set
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedTimestamp
	 */
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	/**
	 * @param updatedTimestamp the updatedTimestamp to set
	 */
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	/**
	 * @return the isActive
	 */
	public char getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public char getRecordInProcess() {
		return recordInProcess;
	}

	public void setRecordInProcess(char recordInProcess) {
		this.recordInProcess = recordInProcess;
	}

	public Long getId() {
		return id;
	}

	public LISSubscriberMaster getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(LISSubscriberMaster subscriber) {
		this.subscriber = subscriber;
	}

	public LISMaintainMasterDataComponent getComponent() {
		return component;
	}

	public void setComponent(LISMaintainMasterDataComponent component) {
		this.component = component;
	}
}
