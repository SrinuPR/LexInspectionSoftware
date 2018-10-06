/**
 * 
 */
package com.deloitte.inspection.dto;

/**
 * @author rnarne
 *
 */
public class UserTypeMasterDTO extends AbstractDTO{
		
		private Integer userTypeId;
		private Integer subscriberId;
		private String userTypeName;
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
		 * @return the userTypeId
		 */
		public Integer getUserTypeId() {
			return userTypeId;
		}
		/**
		 * @param userTypeId the userTypeId to set
		 */
		public void setUserTypeId(Integer userTypeId) {
			this.userTypeId = userTypeId;
		}
		/**
		 * @return the userTypeName
		 */
		public String getUserTypeName() {
			return userTypeName;
		}
		/**
		 * @param userTypeName the userTypeName to set
		 */
		public void setUserTypeName(String userTypeName) {
			this.userTypeName = userTypeName;
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
