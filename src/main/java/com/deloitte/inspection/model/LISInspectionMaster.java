/**
 * 
 */
package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author rnarne
 *
 */
@Entity
@Table(name = "LIS_INMDC")
public class LISInspectionMaster implements Serializable{
		
	private static final long serialVersionUID = 382945870712132210L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INSPECTION_ID", length = 10)
	private String inspId;
	
	@Column(name = "COMPONENT_NAME" , length = 150)
	private String facilityName;
		
	@Column(name = "COMPONENT_NUM")
	private String compNum;
	
	@Column(name = "COMPONENT_MATERIAL")
	private String compMaterial;
	
	@Column(name = "COMPONENT_NOTES")
	private String compNotes;

	@Column(name = "INSPECTION_TYPE_ID")
	private Integer inspTypeId; 
	
	@Column(name = "INSPECTION_STAGE_ID")
	private Integer inspStageId;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@Column(name = "IS_ACTIVE")
	private char isActive;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="SUBSCRIBER_ID")
    private LISSubscriberMaster subscriberMaster;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name="COMP_PROD_DRAW_NUM")
    private LISMaintainMasterDataComponent componentMasterData;

	/**
	 * @return the inspId
	 */
	public String getInspId() {
		return inspId;
	}

	/**
	 * @param inspId the inspId to set
	 */
	public void setInspId(String inspId) {
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

	/**
	 * @return the subscriberMaster
	 */
	public LISSubscriberMaster getSubscriberMaster() {
		return subscriberMaster;
	}

	/**
	 * @param subscriberMaster the subscriberMaster to set
	 */
	public void setSubscriberMaster(LISSubscriberMaster subscriberMaster) {
		this.subscriberMaster = subscriberMaster;
	}

	/**
	 * @return the componentMasterData
	 */
	public LISMaintainMasterDataComponent getComponentMasterData() {
		return componentMasterData;
	}

	/**
	 * @param componentMasterData the componentMasterData to set
	 */
	public void setComponentMasterData(LISMaintainMasterDataComponent componentMasterData) {
		this.componentMasterData = componentMasterData;
	}
}
