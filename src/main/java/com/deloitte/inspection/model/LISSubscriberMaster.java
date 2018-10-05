/**
 * 
 */
package com.deloitte.inspection.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author rnarne
 * This class will be used to map Subscriber Master table data
 */

@Entity
@Table(name = "LIS_SUMAS")
public class LISSubscriberMaster implements Serializable {
	
	private static final long serialVersionUID = 5112717685665555841L;

	@Id
	/*@GeneratedValue(strategy = GenerationType.AUTO)*/
	@Column(name = "SUBSCRIBER_ID")
	private Integer subscriberId;
	
	@Column(name = "SUBSCRIBER_NAME")
	private String subscriberName;
	
	@Column(name = "SUBSCRIBER_ADDRESS")
	private String subscriberAddress;
	
	@Column(name = "CREATED_TIMESTAMP")
	private Date createdTimestamp;
	
	@Column(name = "UPDATED_TIMESTAMP")
	private Date updatedTimestamp;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
		
	@OneToMany(mappedBy="subscriberMaster", cascade = CascadeType.ALL)
	private Set<LISUserTypeMaster> userTypeMaster;
	
	/**
	 * @return the subscriberId
	 */
	public Integer getSubscriberId() {
		return subscriberId;
	}

	/**
	 * @param subscriberId the subscriberId to set
	 */
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	/**
	 * @return the subscriberName
	 */
	public String getSubscriberName() {
		return subscriberName;
	}

	/**
	 * @param subscriberName the subscriberName to set
	 */
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	/**
	 * @return the subscriberAddress
	 */
	public String getSubscriberAddress() {
		return subscriberAddress;
	}

	/**
	 * @param subscriberAddress the subscriberAddress to set
	 */
	public void setSubscriberAddress(String subscriberAddress) {
		this.subscriberAddress = subscriberAddress;
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
	 * @return the userTypeMaster
	 */
	public Set<LISUserTypeMaster> getUserTypeMaster() {
		return userTypeMaster;
	}

	/**
	 * @param userTypeMaster the userTypeMaster to set
	 */
	public void setUserTypeMaster(Set<LISUserTypeMaster> userTypeMaster) {
		this.userTypeMaster = userTypeMaster;
	}
	
}
