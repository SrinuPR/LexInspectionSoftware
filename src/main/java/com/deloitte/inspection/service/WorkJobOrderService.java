package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.WorkJobOrderDTO;
import com.deloitte.inspection.exception.WorkJobOrderException;
import com.deloitte.inspection.response.dto.WorkJobOrderResponseDTO;

public interface WorkJobOrderService {

	public String deleteWorkJobOrder(Integer workJobOrderId) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO saveWorkJobOrderData(WorkJobOrderDTO workJobOrderDTO, String userName, String userId, String action) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO getAllWorkJobOrderData(String userId) throws WorkJobOrderException;

	public List<WorkJobOrderDTO> WorkJobOrderList(String userId) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO validateWorkJobOrderNumber(WorkJobOrderDTO workJobOrderDTO) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO validateLotNumber(WorkJobOrderDTO workJobOrderDTO) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO validateManufacturerBatchNumber(WorkJobOrderDTO workJobOrderDTO) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO validateLotSize(WorkJobOrderDTO workJobOrderDTO) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO validateManufacturerBatchSize(WorkJobOrderDTO workJobOrderDTO) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO getComponentData(Integer subscriberId) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO getCustomerPOData(Integer subscriberId) throws WorkJobOrderException;
	
	public WorkJobOrderResponseDTO getComponentDataFromWO(Integer subscriberId) throws WorkJobOrderException;

	public WorkJobOrderResponseDTO getWJODataByCompProdDrawNum(String compProdDrawNum) throws WorkJobOrderException;

}
