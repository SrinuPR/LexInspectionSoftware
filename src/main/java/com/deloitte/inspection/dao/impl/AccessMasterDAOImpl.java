package com.deloitte.inspection.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.AccessMasterDAO;
import com.deloitte.inspection.dao.LoginDAO;
import com.deloitte.inspection.dto.AccessMasterDTO;
import com.deloitte.inspection.exception.LoginException;
import com.deloitte.inspection.model.LISAccessMaster;
import com.deloitte.inspection.model.LISFacilityMaster;
import com.deloitte.inspection.model.LISLogin;
import com.deloitte.inspection.model.LISUserMasterCreate;

@Repository
@Transactional
public class AccessMasterDAOImpl implements AccessMasterDAO{
	
	private static final Logger logger = LogManager.getLogger(AccessMasterDAOImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public AccessMasterDTO saveAccessMaster(AccessMasterDTO accessMasterDTO) throws Exception {

		LISAccessMaster lisAccessMaster = new LISAccessMaster();
		LISAccessMaster dataExist = new LISAccessMaster();
		
		if (null != accessMasterDTO && null != accessMasterDTO.getSubscriberId()) {
			
			dataExist = getAccessScreens (accessMasterDTO.getSubscriberId(), accessMasterDTO.getUserTypeId());
			if (null == dataExist) {
				try {
				lisAccessMaster.setSubsId(accessMasterDTO.getSubscriberId());
				if (null != accessMasterDTO.getUserTypeId()) {
					lisAccessMaster.setUserTypeId(accessMasterDTO.getUserTypeId());
				}
				lisAccessMaster.setCreatedBy(accessMasterDTO.getCreatedBy());
				lisAccessMaster.setCreatedTimestamp(new Date());
				
				String value = (String) getSession().save(lisAccessMaster);
				if(value != null)
					return accessMasterDTO;
				} catch (HibernateException ex) {
					ex.printStackTrace();
					logger.error(ex.getMessage());
				}
			} else {
				try {
					dataExist.setSubsId(accessMasterDTO.getSubscriberId());
					if (null != accessMasterDTO.getUserTypeId()) {
						dataExist.setUserTypeId(accessMasterDTO.getUserTypeId());
					}
					dataExist.setUpdatedBy(accessMasterDTO.getUpdatedBy());
					dataExist.setUpdatedTimeStamp(new Date());
					getSession().saveOrUpdate(dataExist);
					return accessMasterDTO;
					} catch (HibernateException ex) {
						ex.printStackTrace();
						logger.error(ex.getMessage());
					}
			}
		return null;
		}
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
	public LISAccessMaster getAccessScreens(Integer subscriberId, Integer userTypId) throws Exception {
		
		logger.info("Entered into getAccessScreens");
		
		if (null != subscriberId && null != userTypId) {
			
			logger.info("Fetching data on basis of subdId and UserTpyeId");
			Query query = getSession().createQuery(" From LISAccessMaster LACM where LACM.subsId = :subsId AND LACM.userTypeId = :userTypeId");
			query.setInteger("subsId", subscriberId);
			query.setInteger("userTypeId", userTypId);
			List<LISAccessMaster> accessMasterScreenList = query.list();
			if(null != accessMasterScreenList && accessMasterScreenList.size() > 0) {
				return accessMasterScreenList.get(0);
			}
		} else if (null != subscriberId) {
			
			logger.info("Fetching data on basis of subdId");
			Query query = getSession().createQuery(" From LISAccessMaster LACM where LACM.subsId = :subsId");
			query.setInteger("subsId", subscriberId);
			List<LISAccessMaster> accessMasterScreenList = query.list();
			if(null != accessMasterScreenList && accessMasterScreenList.size() > 0) {
				return accessMasterScreenList.get(0);
			}
		}
		return null;
	}
}
