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
import com.deloitte.inspection.dao.FacilitiesMasterDAO;
import com.deloitte.inspection.dto.FacilityMasterDTO;
import com.deloitte.inspection.exception.FacilityMasterException;
import com.deloitte.inspection.model.LISFacilityMaster;
import com.deloitte.inspection.model.LISSubscriberMaster;

/**
 * @author rnarne
 *
 */
@Repository
@Transactional
public class FacilitiesMasterDAOImpl implements FacilitiesMasterDAO {

	private static final Logger logger = LogManager.getLogger(FacilitiesMasterDAOImpl.class);  
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    /**
     * @param facilityNum
     * @return
     * @throws FacilityMasterException
     */
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
	public LISFacilityMaster getFacilityNumber(String facilityNum) throws FacilityMasterException {
		logger.info("Entered into getFacilityNumber");	
		Query query = getSession().createQuery(" From LISFacilityMaster FMACS where FMACS.facilityNumber = :facilityNumber");
		query.setString("facilityNumber", facilityNum);
		List<LISFacilityMaster> facilitiesMasterList = query.list();
		if(null != facilitiesMasterList && facilitiesMasterList.size() > 0) {
			return facilitiesMasterList.get(0);
		}
		return null;
	}

	/**
	 * @param facilityMasterDTO
	 * @return
	 * @throws FacilityMasterException
	 */
	@Override
	public FacilityMasterDTO createFacility(FacilityMasterDTO facilityMasterDTO) throws FacilityMasterException {
		logger.info("Entered into createFacility");	
		try {
			LISFacilityMaster facilityMaster = new LISFacilityMaster();
			facilityMaster.setCreatedBy(facilityMasterDTO.getCreatedBy());
			facilityMaster.setCreatedTimestamp(new Date(Calendar.getInstance().getTimeInMillis()));
			facilityMaster.setFacilityNumber(facilityMasterDTO.getFacilityNumber());
			facilityMaster.setFacilityName(facilityMasterDTO.getFacilityName());
			LISSubscriberMaster subMaster = new LISSubscriberMaster();
			subMaster.setSubscriberId(facilityMasterDTO.getSubscriberId());
			facilityMaster.setSubscriberMaster(subMaster);
			facilityMaster.setIsActive(StatusConstants.IS_ACTIVE);
			String value = (String) getSession().save(facilityMaster);
			if(value != null)
				return facilityMasterDTO;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		} 
		return null;
	}
	
	/**
	 * @return
	 * @throws FacilityMasterException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<LISFacilityMaster> getFacilitiesMasterData() throws FacilityMasterException {
		logger.info("Entered into getFacilitiesMasterData");	
		Query query = getSession().createQuery(" From LISFacilityMaster FMACS ORDER BY FMACS.createdTimestamp DESC");
		List<LISFacilityMaster> list = query.list();
		return list;
	}
}