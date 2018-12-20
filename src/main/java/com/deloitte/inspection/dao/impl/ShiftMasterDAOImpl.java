package com.deloitte.inspection.dao.impl;

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
import com.deloitte.inspection.dao.ShiftMasterDAO;
import com.deloitte.inspection.dto.ShiftMasterDTO;
import com.deloitte.inspection.exception.ShiftMasterException;
import com.deloitte.inspection.model.LISShiftMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;


@Repository
@Transactional
public class ShiftMasterDAOImpl implements ShiftMasterDAO {
private static final Logger logger = LogManager.getLogger(ShiftMasterDAOImpl.class);  
	
	@Autowired
    private SessionFactory sessionFactory;
	
	private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

	
	@Override
	public ShiftMasterDTO createShiftMaster(ShiftMasterDTO createShiftMasterDTO) throws ShiftMasterException {
		logger.info("Entered into createShiftMaster");	
		try {
			LISShiftMaster shiftMaster =new LISShiftMaster();
			shiftMaster.setIsActive(StatusConstants.IS_ACTIVE);
			shiftMaster.setShiftId(createShiftMasterDTO.getShiftId());
			shiftMaster.setShiftName(createShiftMasterDTO.getShiftName());
			LISSubscriberMaster subMaster = new LISSubscriberMaster();
			subMaster.setSubscriberId(createShiftMasterDTO.getSubscriberId());
			shiftMaster.setSubscriberMaster(subMaster);
			shiftMaster.setCreatedBy(createShiftMasterDTO.getCreatedBy());
			shiftMaster.setCreatedTimestamp(createShiftMasterDTO.getCreatedTimestamp());
			
			String value = getSession().save(shiftMaster).toString();
			if(value != null)
			return createShiftMasterDTO;
				
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} 
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
	public LISShiftMaster getShiftId(String shiftId) throws ShiftMasterException {

		logger.info("Entered into getShiftId");	
		Query query = getSession().createQuery(" From LISShiftMaster SHMCS where SHMCS.shiftId = :shiftId AND SHMCS.isActive = :isactive");
		query.setString("shiftId", shiftId);
		query.setParameter("isactive", StatusConstants.IS_ACTIVE);
		List<LISShiftMaster> shiftMasterList = query.list();
		if(null != shiftMasterList && shiftMasterList.size() > 0) {
			return shiftMasterList.get(0);
		}
		return null;
	
	}

	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public List<LISShiftMaster> findBySubscriberId(Integer subscriberId) throws ShiftMasterException {
		logger.info("Entered into getAllShifts");	
		Query query = getSession().createQuery(" From LISShiftMaster SHMCS where SHMCS.subscriberMaster.subscriberId = :subscriberId AND SHMCS.isActive = :isactive");
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isactive", StatusConstants.IS_ACTIVE);
		List<LISShiftMaster> shiftMasterList = query.list();
		if(null != shiftMasterList && shiftMasterList.size() > 0) {
			return shiftMasterList;
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public String deleteByShiftId(String shiftId,String userId) throws ShiftMasterException {
		logger.info("Entered into deleteByShiftId");
		String status = StatusConstants.FAILURE;
		//Query query = getSession().createSQLQuery("UPDATE LISShiftMaster SET isActive = :inactive,UPDATED_BY=:userId  WHERE shiftId = :shiftId ");
		Query query = getSession().createSQLQuery("DELETE FROM LIS_SHMCS WHERE SHIFT_ID = :shiftId ");
		query.setParameter("shiftId", shiftId);
		int result = query.executeUpdate();
		if(result > 0){
			status = StatusConstants.SUCCESS;
		}
		return status;	
	}

	@Override
	public ShiftMasterDTO updateShiftMaster(ShiftMasterDTO createShiftMasterDTO) throws ShiftMasterException {
		logger.info("Entered into updateShiftMaster");	
		try {
			LISShiftMaster shiftMaster =new LISShiftMaster();
			shiftMaster=getShiftId(createShiftMasterDTO.getShiftId());
			shiftMaster.setIsActive(StatusConstants.IS_ACTIVE);
			shiftMaster.setShiftId(createShiftMasterDTO.getShiftId());
			shiftMaster.setShiftName(createShiftMasterDTO.getShiftName());
			/*LISSubscriberMaster subMaster = new LISSubscriberMaster();
			subMaster.setSubscriberId(createShiftMasterDTO.getSubscriberId());
			shiftMaster.setSubscriberMaster(subMaster);*/
			shiftMaster.setUpdatedBy(createShiftMasterDTO.getUpdatedBy());
			shiftMaster.setUpdatedTimestamp(createShiftMasterDTO.getUpdatedTimestamp());
			
			if(null==shiftMaster.getCreatedBy()) {
				shiftMaster.setCreatedBy(createShiftMasterDTO.getUpdatedBy());
				shiftMaster.setCreatedTimestamp(createShiftMasterDTO.getUpdatedTimestamp());	
			}
			
			getSession().saveOrUpdate(shiftMaster);
			
			return createShiftMasterDTO;
				
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} 
		return null;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISShiftMaster> getAllShiftsBySubscriberId(Integer subscriberId) throws ShiftMasterException {
		logger.info("Entered into getAllShifts");	
		Query query = getSession().createQuery(" From LISShiftMaster SHMCS where SHMCS.subscriberMaster.subscriberId = :subscriberId AND SHMCS.isActive = :isactive");
		query.setParameter("subscriberId", subscriberId);
		query.setParameter("isactive", StatusConstants.IS_ACTIVE);
		return query.list();
	}
	
}

