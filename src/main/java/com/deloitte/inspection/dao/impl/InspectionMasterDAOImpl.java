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
import com.deloitte.inspection.dao.InspectionMasterDAO;
import com.deloitte.inspection.dto.InspectionMasterDTO;
import com.deloitte.inspection.model.LISInspectionMaster;

@Repository
@Transactional
public class InspectionMasterDAOImpl implements InspectionMasterDAO {
	private static final Logger logger = LogManager.getLogger(WorkJobOrderDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISInspectionMaster getInspectionStage(InspectionMasterDTO inspectionDTO) {
		logger.info("Inside getInspectionStage DAO");
		Query<LISInspectionMaster> query = getSession().createQuery(
				" From LISInspectionMaster master where master.componentMasterData.componentProductDrawNumber = :componentProductDrawNumber and lower(master.inspTypeId) = :inspTypeId and lower(master.inspStageId) = :inspStageId and master.isActive = :isActive ORDER BY master.createdTimestamp DESC");
		query.setParameter("componentProductDrawNumber", inspectionDTO.getComponentProductDrawNumber());
		query.setParameter("inspTypeId", inspectionDTO.getInspectionType());
		query.setParameter("inspStageId", inspectionDTO.getInspectionStage());
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISInspectionMaster> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveInspectionMaster(LISInspectionMaster inspectionMaster) {
		logger.info("InspectionMasterDAOImpl - Saving Inspection Master");
		this.getSession().saveOrUpdate(inspectionMaster);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<LISInspectionMaster> getInspectionMasterList(String userId) {
		logger.info("Entered into getInspectionMasterList DAO");
		Query<LISInspectionMaster> query = getSession().createQuery(
				"select master From LISInspectionMaster master where master.createdBy = :createdBy and master.isActive = :isActive ORDER BY master.createdTimestamp DESC");
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		query.setParameter("createdBy", userId);
		return query.list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISInspectionMaster getInspectionMasterById(Integer inspectionMasterId) {
		logger.info("Inside getWorkJobOrderById DAO");
		Query query = getSession().createQuery(
				" From LISInspectionMaster master where master.inspId = :inspectionMasterId and master.isActive = :isActive ORDER BY master.createdTimestamp DESC");
		query.setParameter("inspectionMasterId", inspectionMasterId);
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISInspectionMaster> list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LISInspectionMaster getInspectionStageOtherThanCurrent(InspectionMasterDTO inspectionDTO) {
		logger.info("Inside getInspectionStage DAO");
		Query<LISInspectionMaster> query = getSession().createQuery(
				" From LISInspectionMaster master where master.componentMasterData.componentProductDrawNumber = :componentProductDrawNumber and lower(master.inspTypeId) = :inspTypeId and lower(master.inspId) != :inspId and lower(master.inspStageId) = :inspStageId and master.isActive = :isActive ORDER BY master.createdTimestamp DESC");
		query.setParameter("componentProductDrawNumber", inspectionDTO.getComponentProductDrawNumber());
		query.setParameter("inspTypeId", inspectionDTO.getInspectionType());
		query.setParameter("inspStageId", inspectionDTO.getInspectionStage());
		query.setParameter("inspId", inspectionDTO.getInspectionMasterId());
		query.setParameter("isActive", StatusConstants.IS_ACTIVE);
		List<LISInspectionMaster> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String deleteInspectionMaster(Integer inspectionMasterId) {
		logger.info("inside deleteInspectionMaster DAO method");
		String status = StatusConstants.FAILURE;
		Query query = getSession().createQuery(
				"UPDATE LISInspectionMaster master set master.isActive = :isActive WHERE master.inspId = :inspId");
		query.setParameter("inspId", inspectionMasterId);
		query.setParameter("isActive", StatusConstants.IN_ACTIVE);
		int result = query.executeUpdate();
		if (result > 0) {
			status = StatusConstants.SUCCESS;
		}
		return status;
	}
}
