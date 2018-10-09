package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;

public interface ComponentMasterDataDAO {

	public void saveComponentMasterData(LISMaintainMasterDataComponent masterDataComponent) throws ComponentMasterDataException;

	public LISMaintainMasterDataComponent getComponentDataById(Integer componentId) throws ComponentMasterDataException;

	public List<LISMaintainMasterDataComponent> getAllComponentMasterData() throws ComponentMasterDataException;

	public String deleteComponent(Integer componentId) throws ComponentMasterDataException;

	public LISMaintainMasterDataComponent getComponentDataByDrwNum(String productDrawNumber) throws ComponentMasterDataException;

}
