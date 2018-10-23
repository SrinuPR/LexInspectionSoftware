package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.ComponentMasterDataDAO;
import com.deloitte.inspection.exception.ComponentMasterDataException;
import com.deloitte.inspection.model.LISMaintainMasterDataComponent;

@Repository
@Transactional
public class ComponentMasterDataDAOImpl implements ComponentMasterDataDAO{
	
	private static final Logger logger = LogManager.getLogger(ComponentMasterDataDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public void saveComponentMasterData(LISMaintainMasterDataComponent masterDataComponent)	throws ComponentMasterDataException {
		logger.info("Entered into saveComponentMasterData DAO");	
		getSession().saveOrUpdate(masterDataComponent);		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LISMaintainMasterDataComponent getComponentDataById(Integer componentId)	throws ComponentMasterDataException {
		logger.info("Entered into validateLoginCredentials");	
		Query query = getSession().createQuery(" From LISMaintainMasterDataComponent l where l.cmdcsId = :componentId");
		query.setParameter("componentId", componentId);
		List<LISMaintainMasterDataComponent> maintainMasterDataComponents = query.list();
		if(null != maintainMasterDataComponents && maintainMasterDataComponents.size() > 0){
			return maintainMasterDataComponents.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISMaintainMasterDataComponent> getAllComponentMasterData(String userId) throws ComponentMasterDataException {
		logger.info("Entered into validateLoginCredentials");	
		Query query = getSession().createQuery(" From LISMaintainMasterDataComponent l where l.userMasterCreate.userId = :userId and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("userId", userId);
		List<LISMaintainMasterDataComponent> list = query.list();
		return list;
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public String deleteComponent(Integer componentId) throws ComponentMasterDataException {
		String status = StatusConstants.FAILURE;
		Query query = getSession().createSQLQuery("UPDATE LIS_CMDCS SET IS_ACTIVE = :inactive WHERE CMDCS_ID = :componentId ");
		query.setParameter("componentId", componentId);
		query.setParameter("inactive", StatusConstants.IN_ACTIVE);
		int result = query.executeUpdate();
		if(result > 0){
			status = StatusConstants.SUCCESS;
		}
		return status;	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISMaintainMasterDataComponent> getAllBySubscriberId(Integer subscriberId) throws ComponentMasterDataException{
		logger.info("Entered into getAllBySubscriberId");	
		Query query = getSession().createQuery(" From LISMaintainMasterDataComponent CMDCS where CMDCS.subscriberMaster.subscriberId = :subscriberId");
		query.setParameter("subscriberId", subscriberId);
		List<LISMaintainMasterDataComponent> maintainMasterDataComponents = query.list();
		if(null != maintainMasterDataComponents && maintainMasterDataComponents.size() > 0){
			return maintainMasterDataComponents;
		}
		return null;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISMaintainMasterDataComponent getComponentDataByDrwNum(String productDrawNumber)
			throws ComponentMasterDataException {
		logger.info("Entered into getComponentDataByDrwNum");	
		Query query = getSession().createQuery(" From LISMaintainMasterDataComponent l where lower(l.componentProductDrawNumber) = :productDrawNumber and isActive = :isActive ORDER BY l.createdTimestamp DESC");
		query.setParameter("productDrawNumber", productDrawNumber);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISMaintainMasterDataComponent> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISMaintainMasterDataComponent> getAllComponentDrawingNumber() throws ComponentMasterDataException {
		logger.info("Entered into getComponentDataByDrwNum");	
		Query query = getSession().createQuery(" From LISMaintainMasterDataComponent l where l.isActive = :isActive ORDER BY l.componentProductDrawNumber ASC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISMaintainMasterDataComponent> list = query.list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISMaintainMasterDataComponent> getComponentData(Integer subscriberId) throws ComponentMasterDataException {
		logger.info("Entered into getComponentData DAO");	
		Query query = getSession().createQuery(" From LISMaintainMasterDataComponent l where l.isActive = :isActive and l.subscriberMaster.subscriberId = :subscriberId ORDER BY l.componentProductDrawNumber ASC");
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISMaintainMasterDataComponent> list = query.list();
		return list;
	}
	
}
