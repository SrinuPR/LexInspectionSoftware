package com.deloitte.inspection.response.dto;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.PurchaseOrderDataDTO;

public class PurchaseOrderResponseDataDTO extends CommonDTO{

	List<PurchaseOrderDataDTO>  result;

	public List<PurchaseOrderDataDTO> getResult() {
		return result;
	}

	public void setResult(List<PurchaseOrderDataDTO> result) {
		this.result = result;
	}
	
	
}
