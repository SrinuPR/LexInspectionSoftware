package com.deloitte.inspection.service;

import java.util.List;

import com.deloitte.inspection.dto.ComponentMasterDataDTO;
import com.deloitte.inspection.exception.ComponentMasterDataException;

public interface ComponentMasterDataService {

	public String saveComponentMasterData(ComponentMasterDataDTO componentMasterDataDTO, String userName) throws ComponentMasterDataException;

	public ComponentMasterDataDTO getComponentDataById(Integer componentId) throws ComponentMasterDataException;

	public String updateComponentMasterData(ComponentMasterDataDTO componentMasterDataDTO, String userName) throws ComponentMasterDataException;

	public List<ComponentMasterDataDTO> getAllComponentMasterData()  throws ComponentMasterDataException;

}
