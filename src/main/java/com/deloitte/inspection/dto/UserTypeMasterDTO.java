/**
 * 
 */
package com.deloitte.inspection.dto;

import java.util.Date;

/**
 * @author rnarne
 *
 */
public class UserTypeMasterDTO extends CommonDTO{
		
		private Integer userTypeId;
		private Integer subscriberId;
		private String userTypeName;
		private Date createdTimestamp;
		private Date updatedTimestamp;
		private String createdBy;
		private String updatedBy;
					
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
}
