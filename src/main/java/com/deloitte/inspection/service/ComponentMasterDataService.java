package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.CommonDTO;
import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.response.dto.ComponentMasterResponseDataDTO;

public interface ComponentMasterDataService {

	public String saveComponentMasterData(ComponentMasterDataDTO componentMasterDataDTO, String userName, String userId) throws ComponentMasterDataException;

	public ComponentMasterResponseDataDTO getComponentDataById(Integer componentId) throws ComponentMasterDataException;

	public String updateComponentMasterData(ComponentMasterDataDTO componentMasterDataDTO, String userName) throws ComponentMasterDataException;

	public List<ComponentMasterDataDTO> getAllComponentMasterData(Integer subscriberId)  throws ComponentMasterDataException;

	public String deleteComponent(Integer componentId) throws ComponentMasterDataException;

	public CommonDTO validateComponentDrawNumber(String lowerCase) throws ComponentMasterDataException;

}
