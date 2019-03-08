package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.DatabaseSequenceDAO;
import com.deloitte.inspection.dao.PurchaseOrderDataDAO;
import com.deloitte.inspection.dto.PurchaseOrderDataDTO;
import com.deloitte.inspection.exception.DatabaseSequenceException;
import com.deloitte.inspection.exception.PurchaseOrderMasterException;
import com.deloitte.inspection.mapper.LISPurchaseOrderMasterResult;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;

@Repository
@Transactional
public class PurchaseOrderDataDAOImpl implements PurchaseOrderDataDAO{
	
	private static final Logger logger = LogManager.getLogger(PurchaseOrderDataDAOImpl.class);
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Autowired
	private DatabaseSequenceDAO databaseSequence;

	@Override
	public void savePurchaseOrderData(LISPurchaseOrderMaster purchaseOrderMaster)
			throws PurchaseOrderMasterException {
		logger.info("Entered into savePurchaseOrderData DAO");	
		try {
			if (purchaseOrderMaster.getCustomerPoId() == null) {
				purchaseOrderMaster.setCustomerPoId(String.valueOf(databaseSequence.getNextSequenceId("LIS_CPMCS")));
			} 
		} catch (DatabaseSequenceException e) {
			e.printStackTrace();
		}
		mongoTemplate.save(purchaseOrderMaster,"LIS_CPMCS");		
	}

	@Override
	public List<LISPurchaseOrderMaster> getAllByUserId(String userId)
			throws PurchaseOrderMasterException {
		logger.info("Entered into getAllByUserId");	
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("userMasterCreateId").is(userId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
						Aggregation.sort(Sort.Direction.DESC, "createdTimestamp"));
		return mongoTemplate.aggregate(aggregation, "LIS_CPMCS", LISPurchaseOrderMaster.class).getMappedResults();
	}


	@Override
	public String validatePOQuantity(PurchaseOrderDataDTO PurchaseOrderDataDTO, String userName)
			throws PurchaseOrderMasterException {
		return null;
	}

	@Override
	public String deletePurchaseOrder(Integer customerPoId) throws PurchaseOrderMasterException {
		String status = StatusConstants.FAILURE;
		Query query = new Query(new Criteria("customerPoId").is(String.valueOf(customerPoId)));
		LISPurchaseOrderMaster master = mongoTemplate.findAndRemove(query, LISPurchaseOrderMaster.class);
		if(master != null){
			status = StatusConstants.SUCCESS;
		}
		return status;	
	}

	@Override
	public LISPurchaseOrderMaster getByCustomerPONumber(String customerPONumber) throws PurchaseOrderMasterException {
		logger.info("Entered into validatePoNumber");
		Query query = new Query();
		query.addCriteria(new Criteria("customerPONumber").is(customerPONumber)
				.and("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
		query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
		return mongoTemplate.findOne(query, LISPurchaseOrderMaster.class);
	}
	
	@Override
	public List<LISPurchaseOrderMasterResult> getCustomerPOData(Integer subscriberId) throws PurchaseOrderMasterException {
		logger.info("Entered into getCustomerPOData DAO");
		LookupOperation lookupOperation = LookupOperation.newLookup().from("LIS_SUMAS").localField("subscriberMasterId")
				.foreignField("subscriberId").as("subscriberMaster");
		LookupOperation componentLookup = LookupOperation.newLookup().from("LIS_CMDCS").localField("maintainMasterDataComponentId")
				.foreignField("componentProductDrawNumber").as("maintainMasterDataComponent");
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("subscriberMasterId").is(subscriberId)),
				Aggregation.match(Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))),
				lookupOperation,
				componentLookup,
				Aggregation.sort(Sort.Direction.DESC, "createdTimestamp"));
		return mongoTemplate.aggregate(aggregation, "LIS_CPMCS", LISPurchaseOrderMasterResult.class).getMappedResults();
	}

	@Override
	public List<LISPurchaseOrderMasterResult> getAllBySubscriberId(Integer subscriberId) throws PurchaseOrderMasterException {
		logger.info("Entered into getAllBySubscriberId");	
		return getCustomerPOData(subscriberId);
	}
	
}
