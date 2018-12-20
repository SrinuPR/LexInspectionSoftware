package com.deloitte.inspection.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.dao.InspectionLineItemMasterDAO;
import com.deloitte.inspection.dao.InspectionMeasurementDAO;
import com.deloitte.inspection.dao.InspectionReportMasterDAO;
import com.deloitte.inspection.dao.PurchaseOrderDataDAO;
import com.deloitte.inspection.dao.WorkJobOrderDAO;
import com.deloitte.inspection.dto.DashBoardDTO;
import com.deloitte.inspection.model.LISInspectionLineItemMaster;
import com.deloitte.inspection.model.LISInspectionMeasurements;
import com.deloitte.inspection.model.LISInspectionReportMaster;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;
import com.deloitte.inspection.response.dto.DashBoardResponseDTO;
import com.deloitte.inspection.service.DashBoardService;

@Service
public class DashBoardServiceImpl implements DashBoardService{
	
	private static final Logger logger = LogManager.getLogger(DashBoardServiceImpl.class);
	
	@Autowired
	private ComponentMasterDataDAO componentMasterDataDAO;
	
	@Autowired
	private PurchaseOrderDataDAO purchaseOrderDataDAO;
	
	@Autowired
	private WorkJobOrderDAO workJobOrderDAO;
	
	@Autowired
	private InspectionLineItemMasterDAO inspectionLineItemMasterDAO;
	
	@Autowired
	private InspectionMeasurementDAO inspectionMeasurementDAO;
	
	@Autowired
	private InspectionReportMasterDAO inspectionReportMasterDAO;

	@Override
	public DashBoardResponseDTO getDashboardData(String userId) throws Exception {
		DashBoardResponseDTO dashBoardResponseDTO = new DashBoardResponseDTO();
		logger.info("Inside getDashboardData Service");
		try{
			DashBoardDTO dashBoardDTO = new DashBoardDTO();
			List<LISMaintainMasterDataComponent> componentList = componentMasterDataDAO.getAllComponentMasterDataByUserID(userId);
			if(null != componentList && componentList.size() > 0)
				dashBoardDTO.setComponentCount(componentList.size());
			else
				dashBoardDTO.setComponentCount(0);
			
			List<LISPurchaseOrderMaster> purchaseOrderList = purchaseOrderDataDAO.getAllByUserId(userId);
			if(null != purchaseOrderList && purchaseOrderList.size() > 0)
				dashBoardDTO.setCustomerPOCount(purchaseOrderList.size());
			else
				dashBoardDTO.setCustomerPOCount(0);
			
			List<LISWorkJobOrderMaster> workJobOrderList = workJobOrderDAO.WorkJobOrderListByUserId(userId);
			if(null != workJobOrderList && workJobOrderList.size() > 0)
				dashBoardDTO.setWorkJobOrderCount(workJobOrderList.size());
			else
				dashBoardDTO.setWorkJobOrderCount(0);
			
			List<LISInspectionReportMaster> reportList = inspectionReportMasterDAO.getAllInspectionReportList(userId);
			if(null != reportList && reportList.size() > 0)
				dashBoardDTO.setInspectionReportCount(reportList.size());
			else
				dashBoardDTO.setInspectionReportCount(0);
			
			List<LISInspectionLineItemMaster> inspectionLineItemMastersList = inspectionLineItemMasterDAO.getAllInspectionLineItemByUserID(userId); 
			if(null != inspectionLineItemMastersList && inspectionLineItemMastersList.size() > 0){
				Set<String> componentDrawNum = new HashSet<String>();
				for(LISInspectionLineItemMaster item : inspectionLineItemMastersList){
					componentDrawNum.add(item.getComponentProductDrawNumber());
				}
				dashBoardDTO.setInspectionLineItemCount(componentDrawNum.size());
			}else{
				dashBoardDTO.setInspectionLineItemCount(0);
			}
			
			List<LISInspectionMeasurements> measurementList = inspectionMeasurementDAO.getAllMeasurementList(userId);
			if(null != measurementList && measurementList.size() > 0)
				dashBoardDTO.setInspectionMeasurementCount(measurementList.size());
			else
				dashBoardDTO.setInspectionMeasurementCount(0);
			dashBoardResponseDTO.setDashBoardDTO(dashBoardDTO);
			dashBoardResponseDTO.setStatus(StatusConstants.SUCCESS);
			dashBoardResponseDTO.setMessage("Dash Board Results");
		}catch(Exception exception){
			logger.info("Exception While fetching DashboardData Service" +exception.getMessage());
			dashBoardResponseDTO.setStatus(StatusConstants.ERROR);
			dashBoardResponseDTO.setMessage("Dash Board Results Service Failed");
		}
		return dashBoardResponseDTO;
	}

}
