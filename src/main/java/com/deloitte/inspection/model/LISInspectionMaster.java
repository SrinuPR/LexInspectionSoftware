/**
 * 
 */
package com.deloitte.inspection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author rnarne
 *
 */
@Document(collection = "LIS_INMDC")
public class LISInspectionMaster {
		
	@Id
	private String _id;
	
	private String inspId;
	
	private String facilityName;
		
	private String compNum;
	
	private String compMaterial;
	
	private String compNotes;

	private Integer inspTypeId; 
	
	private Integer inspStageId;

	private String createdBy;
	
	private Date createdTimestamp;
	
	private String updatedBy;
	
	private Date updatedTimestamp;
	
	private String isActive;

	private Integer subscriberMasterId;//foreign key
	
	private String maintainMasterDataComponentId;//foreign key
	
	private String recordInProcess;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getInspId() {
		return inspId;
	}

	public void setInspId(String inspId) {
		this.inspId = inspId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getCompNum() {
		return compNum;
	}

	public void setCompNum(String compNum) {
		this.compNum = compNum;
	}

	public String getCompMaterial() {
		return compMaterial;
	}

	public void setCompMaterial(String compMaterial) {
		this.compMaterial = compMaterial;
	}

	public String getCompNotes() {
		return compNotes;
	}

	public void setCompNotes(String compNotes) {
		this.compNotes = compNotes;
	}

	public Integer getInspTypeId() {
		return inspTypeId;
	}

	public void setInspTypeId(Integer inspTypeId) {
		this.inspTypeId = inspTypeId;
	}

	public Integer getInspStageId() {
		return inspStageId;
	}

	public void setInspStageId(Integer inspStageId) {
		this.inspStageId = inspStageId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Integer getSubscriberMasterId() {
		return subscriberMasterId;
	}

	public void setSubscriberMasterId(Integer subscriberMasterId) {
		this.subscriberMasterId = subscriberMasterId;
	}

	public String getMaintainMasterDataComponent() {
		return maintainMasterDataComponentId;
	}

	public void setMaintainMasterDataComponent(String maintainMasterDataComponent) {
		this.maintainMasterDataComponentId = maintainMasterDataComponent;
	}

	public String getRecordInProcess() {
		return recordInProcess;
	}

	public void setRecordInProcess(String recordInProcess) {
		this.recordInProcess = recordInProcess;
	}

}
