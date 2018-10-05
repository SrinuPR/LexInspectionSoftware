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
	public List<LISMaintainMasterDataComponent> getAllComponentMasterData() throws ComponentMasterDataException {
		logger.info("Entered into validateLoginCredentials");	
		Query query = getSession().createQuery(" From LISMaintainMasterDataComponent l ORDER BY l.createdTimestamp DESC");
		List<LISMaintainMasterDataComponent> list = query.list();
		return list;
	}

}
