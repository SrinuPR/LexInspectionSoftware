/**
 * 
 */
package com.deloitte.inspection.dao.impl;

import java.util.Calendar;
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
import com.deloitte.inspection.dao.UserTypeMasterDAO;
import com.deloitte.inspection.dto.UserTypeMasterDTO;
import com.deloitte.inspection.exception.UserTypeMasterException;
import com.deloitte.inspection.model.LISSubscriberMaster;
import com.deloitte.inspection.model.LISUserTypeMaster;

/**
 * @author rnarne
 *
 */
@Repository
@Transactional
public class UserTypeMasterDAOImpl implements UserTypeMasterDAO {

	
	private static final Logger logger = LogManager.getLogger(UserTypeMasterDAOImpl.class);  
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    
    /* (non-Javadoc)
     * @see com.deloitte.inspection.dao.UserTypeMasterDAO#validateUserType(java.lang.Integer)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public LISUserTypeMaster validateUserType(UserTypeMasterDTO userTypeMasDTO) throws UserTypeMasterException {
		logger.info("Entered into validateUserType");
		List<LISUserTypeMaster> userTypeList =  null;
		if(userTypeMasDTO.getUserTypeId() != null && userTypeMasDTO.getUserTypeName() != null 
				&& !userTypeMasDTO.getUserTypeName().equals("")) {
			Query query = getSession().createQuery(" From LISUserTypeMaster UTMAS where UTMAS.userTypeId = :userTypeId"
					+ " and UTMAS.userTypeName = :userTypeName");
			query.setParameter("userTypeId", userTypeMasDTO.getUserTypeId());
			query.setParameter("userTypeName", userTypeMasDTO.getUserTypeName());
			userTypeList = query.list();
		} else if (userTypeMasDTO.getUserTypeId() != null) {
			Query query = getSession().createQuery(" From LISUserTypeMaster UTMAS where UTMAS.userTypeId = :userTypeId");
			query.setParameter("userTypeId", userTypeMasDTO.getUserTypeId());
			userTypeList = query.list();
		}
		if(null != userTypeList && userTypeList.size() > 0) {
			return userTypeList.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.deloitte.inspection.dao.UserTypeMasterDAO#createUserTypeMaster(com.deloitte.inspection.dto.UserTypeMasterDTO)
	 */
	@Override
	public UserTypeMasterDTO createUserTypeMaster(UserTypeMasterDTO userTypeMasDTO) throws UserTypeMasterException {
		logger.info("Entered into createUserTypeMaster");	
		try {
			LISUserTypeMaster userTypeMasModel = new LISUserTypeMaster();
			userTypeMasModel.setCreatedBy(userTypeMasDTO.getCreatedBy());
			userTypeMasModel.setCreatedTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
			userTypeMasModel.setUserTypeId(userTypeMasDTO.getUserTypeId());
			userTypeMasModel.setUserTypeName(userTypeMasDTO.getUserTypeName());
			userTypeMasModel.setIsActive(StatusConstants.IS_ACTIVE);
			LISSubscriberMaster subMaster = new LISSubscriberMaster();
			subMaster.setSubscriberId(userTypeMasDTO.getSubscriberId());
			userTypeMasModel.setSubscriberMaster(subMaster);
			Integer value = (Integer) getSession().save(userTypeMasModel);
			if(value != null)
				return userTypeMasDTO;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		} 
		return null;
	}
}
