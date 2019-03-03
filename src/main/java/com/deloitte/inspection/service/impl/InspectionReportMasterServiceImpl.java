package com.deloitte.inspection.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.inspection.constant.InspectionMeasurementConstants;
import com.deloitte.inspection.constant.InspectionReportMasterConstants;
import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.InspectionReportMasterDAO;
import com.deloitte.inspection.dto.InspectionReportMasterDTO;
import com.deloitte.inspection.exception.InspectionReportMasterException;
import com.deloitte.inspection.model.LISInspectionReportMaster;
import com.deloitte.inspection.response.dto.InspectionReportMasterResponseDTO;
import com.deloitte.inspection.service.InspectionReportMasterService;
import com.deloitte.inspection.util.InspectionUtils;

@Service
public class InspectionReportMasterServiceImpl implements InspectionReportMasterService{
	
	@Autowired
	private InspectionReportMasterDAO inspReportMasterDAO;
	
	@Override
	public InspectionReportMasterResponseDTO saveBlankReport(InspectionReportMasterDTO inspRptMasterDto, String userId,
			String userName) throws InspectionReportMasterException {
		InspectionReportMasterResponseDTO inspRptMasResDto = new InspectionReportMasterResponseDTO();
		try{
			LISInspectionReportMaster lisInspRptMaster = new LISInspectionReportMaster();
			if(null != inspRptMasterDto && inspRptMasterDto.getInspReportNumber() != null && inspRptMasterDto.getSubscriberId() != null 
				&& inspRptMasterDto.getComponentProductDrawNumber() != null	&& inspRptMasterDto.getComponentProdcuctName() != null 
				&& inspRptMasterDto.getWorkJobOrderNumber() != null && inspRptMasterDto.getLotNumber() != null && inspRptMasterDto.getLotSize() != null
				&& inspRptMasterDto.getInspectionTypeId() != null && inspRptMasterDto.getInspectionStageId() != null && inspRptMasterDto.getManufacturingBatchNumber() != null
				&& inspRptMasterDto.getManufacturingBatchSize() != null	&& inspRptMasterDto.getCustomerPoNumber() != null && inspRptMasterDto.getCustomerPoDate() != null
				&& inspRptMasterDto.getCustomerPoQuantity() != 0) {
					lisInspRptMaster.setCreatedBy(userName);
					lisInspRptMaster.setCreatedTimestamp(new Date());
					lisInspRptMaster.setInspReportNumber(inspRptMasterDto.getInspReportNumber());
					lisInspRptMaster.setSubscriberId(inspRptMasterDto.getSubscriberId());
					lisInspRptMaster.setSubscriberName(inspRptMasterDto.getSubscriberName());
					lisInspRptMaster.setCompProdDrawNum(inspRptMasterDto.getComponentProductDrawNumber());
					lisInspRptMaster.setComponentProdcuctName(inspRptMasterDto.getComponentProdcuctName());
					lisInspRptMaster.setWorkJobOrderNumber(inspRptMasterDto.getWorkJobOrderNumber());
					lisInspRptMaster.setLotNumber(inspRptMasterDto.getLotNumber());
					lisInspRptMaster.setLotSize(inspRptMasterDto.getLotSize());
					lisInspRptMaster.setInspectionTypeId(inspRptMasterDto.getInspectionTypeId());
					lisInspRptMaster.setInspectionStageId(inspRptMasterDto.getInspectionStageId());
					lisInspRptMaster.setManufacturingBatchNumber(inspRptMasterDto.getManufacturingBatchNumber());
					lisInspRptMaster.setManufacturingBatchSize(inspRptMasterDto.getManufacturingBatchSize());
					lisInspRptMaster.setCustomerPoNumber(inspRptMasterDto.getCustomerPoNumber());
					lisInspRptMaster.setCustomerPoQuantity(inspRptMasterDto.getCustomerPoQuantity());
					lisInspRptMaster.setCustomerPoDate(InspectionUtils.convertStringToDate(inspRptMasterDto.getCustomerPoDate()));
					lisInspRptMaster.setUserID(userId);
					lisInspRptMaster.setIsActive(String.valueOf(StatusConstants.IS_ACTIVE));
					lisInspRptMaster.setReportStatus(InspectionMeasurementConstants.ACTIVE);
					lisInspRptMaster.setWorkJobOrderId(inspRptMasterDto.getWorkJobOrderId());
					inspReportMasterDAO.saveReport(lisInspRptMaster);
					inspRptMasResDto.setStatus(StatusConstants.SUCCESS);
					inspRptMasResDto.setMessage(InspectionReportMasterConstants.INSPECTION_REPORT_SAVE);
					//inspRptMasterDto.setUserName(userName);
					inspRptMasterDto.setUserID(userId);
					List<InspectionReportMasterDTO> inspRptMasterlist = new ArrayList<InspectionReportMasterDTO>();
					inspRptMasterlist.add(inspRptMasterDto);
					inspRptMasResDto.setResult(inspRptMasterlist);
			}else{
				inspRptMasResDto.setStatus(StatusConstants.ERROR);
				inspRptMasResDto.setMessage(InspectionReportMasterConstants.INPUT_MISSING);
			}
			
		}catch (Exception exception) {
			inspRptMasResDto.setStatus(StatusConstants.ERROR);
			inspRptMasResDto.setMessage(InspectionReportMasterConstants.INSPECTION_REPORT_FAILURE);
		}
		return inspRptMasResDto;
	}

	@Override
	public InspectionReportMasterResponseDTO validateInspReportNumber(Integer inspReportNum) {
		InspectionReportMasterResponseDTO inspRptMasResDto = new InspectionReportMasterResponseDTO();
		try{
			if(inspReportNum != 0){
				LISInspectionReportMaster lisInsRptMaster = inspReportMasterDAO.validateInspReportNumber(inspReportNum);
				if(null != lisInsRptMaster){
					inspRptMasResDto.setStatus(StatusConstants.SUCCESS);
					inspRptMasResDto.setMessage(InspectionReportMasterConstants.INSPECTION_REPORT_NUMBER_EXIST);
				}else{
					inspRptMasResDto.setStatus(StatusConstants.SUCCESS);
					inspRptMasResDto.setMessage(InspectionReportMasterConstants.INSPECTION_REPORT_NUMBER_NOT_EXIST);
				}
			}else{
				inspRptMasResDto.setStatus(StatusConstants.ERROR);
				inspRptMasResDto.setMessage(InspectionReportMasterConstants.INPUT_MISSING);
			}
		}catch(Exception exception){
			inspRptMasResDto.setStatus(StatusConstants.ERROR);
			inspRptMasResDto.setMessage(InspectionReportMasterConstants.UN_EXPECTED_EXCEPTION);
		}
		return inspRptMasResDto;
	}	
}
