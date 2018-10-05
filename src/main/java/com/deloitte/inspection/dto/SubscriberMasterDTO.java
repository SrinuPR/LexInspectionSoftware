/**
 * 
 */
package com.deloitte.inspection.dto;

/**
 * @author rnarne
 *
 */
public class SubscriberMasterDTO extends AbstractDTO{
	
	private Integer subscriberId;
	private String subscriberName;
	private String subscriberAddress;
	private String errorMessage;
	private String status;
	
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
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}	
}
