package com.deloitte.inspection.dto;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class ShiftMasterDTO extends AbstractDTO {
	 
	private String shiftId;
	private String shiftName;
	private Integer subscriberId;
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public Integer getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}
	

}
