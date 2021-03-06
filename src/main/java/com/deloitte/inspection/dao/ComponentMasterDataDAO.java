package com.deloitte.inspection.dao;

import java.util.List;

import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;

public interface ComponentMasterDataDAO {

	public void saveComponentMasterData(LISMaintainMasterDataComponent masterDataComponent) throws ComponentMasterDataException;

	public LISMaintainMasterDataComponent getComponentDataById(Integer componentId) throws ComponentMasterDataException;

	public List<LISMaintainMasterDataComponent> getAllComponentMasterData(Integer subscriberId) throws ComponentMasterDataException;

	public String deleteComponent(Integer componentId) throws ComponentMasterDataException;
	
	public List<LISMaintainMasterDataComponent> getAllBySubscriberId(Integer subscriberId) throws ComponentMasterDataException;

	public LISMaintainMasterDataComponent getComponentDataByDrwNum(String productDrawNumber) throws ComponentMasterDataException;

	public List<LISMaintainMasterDataComponent> getAllComponentDrawingNumber() throws ComponentMasterDataException;

	public List<LISMaintainMasterDataComponent> getComponentData(Integer subscriberId) throws ComponentMasterDataException;

	public List<LISMaintainMasterDataComponent> getAllComponentMasterDataByUserID(String userId) throws ComponentMasterDataException;
	
}
