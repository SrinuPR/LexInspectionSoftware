/**
 * 
 */
package com.deloitte.inspection.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author 
 * This class will be used to map User Type Master table data
 */

@Entity
@Table(name = "LIS_DATATABLE_USERTYPEID")
public class LISStaticUserType implements Serializable {

	
	private static final long serialVersionUID = 5112717685665555841L;

	@Id
	/*@GeneratedValue(strategy = GenerationType.AUTO)*/
	@Column(name = "USER_TYPE_ID")
	private Integer userTypeId;
	
	@Column(name = "USER_TYPE_NAME")
	private String userTypeName;
	
	@Column(name = "ACTIVE_SW")
	private String activeSw;
	
	@Column(name = "EFF_START_DT")
	private Timestamp effStartDt;
	
	@Column(name = "EFF_END_DT")
	private Timestamp effEndDt;

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
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
