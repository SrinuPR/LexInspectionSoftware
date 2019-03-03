/**
 * 
 */
package com.deloitte.inspection.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author 
 * This class will be used to map User Type Master table data
 */

@Document(collection = "LIS_DATATABLE_USERTYPEID")
public class LISStaticUserType implements Serializable {

	
	private static final long serialVersionUID = 5112717685665555841L;

	@Id	
	// @Field(value = "USER_TYPE_ID")
	private String userTypeId;
	
	// @Field(value = "USER_TYPE_NAME")
	private String userTypeName;
	
	// @Field(value = "ACTIVE_SW")
	private String activeSw;
	
	// @Field(value = "EFF_START_DT")
	private Timestamp effStartDt;
	
	// @Field(value = "EFF_END_DT")
	private Timestamp effEndDt;

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getActiveSw() {
		return activeSw;
	}

	public void setActiveSw(String activeSw) {
		this.activeSw = activeSw;
	}

	public Timestamp getEffStartDt() {
		return effStartDt;
	}

	public void setEffStartDt(Timestamp effStartDt) {
		this.effStartDt = effStartDt;
	}

	public Timestamp getEffEndDt() {
		return effEndDt;
	}

	public void setEffEndDt(Timestamp effEndDt) {
		this.effEndDt = effEndDt;
	}	
}
