package com.deloitte.inspection.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.inspection.constant.StatusConstants;
import com.deloitte.inspection.dao.WorkJobOrderDAO;
import com.deloitte.inspection.exception.WorkJobOrderException;
import com.deloitte.inspection.model.LISPurchaseOrderMaster;
import com.deloitte.inspection.model.LISWorkJobOrderMaster;
import com.mongodb.client.result.DeleteResult;

@Repository
@Transactional
public class WorkJobOrderDAOImpl implements WorkJobOrderDAO{

private static final Logger logger = LogManager.getLogger(WorkJobOrderDAOImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String deleteWorkJobOrder(Integer workJobOrderId) throws WorkJobOrderException {
		logger.info("inside deleteWorkJobOrder DAO method");
		String status = StatusConstants.FAILURE;
		Query query = new Query();
		query.addCriteria(Criteria.where("wjOrderId").is(workJobOrderId));
		DeleteResult result = mongoTemplate.remove(query, LISWorkJobOrderMaster.class);
		if(result.getDeletedCount() > 0){
			status = StatusConstants.SUCCESS;
		}
		return status;
	}

	@Override
	public LISWorkJobOrderMaster getWorkJobOrderByNumber(String workJobOrderNumber,String lotNumber,String batchNumber) throws WorkJobOrderException {
		logger.info("Inside getWorkJobOrderByNumber DAO");
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("workJobOrderNumber").is(workJobOrderNumber),
        					 Criteria.where("lotNumber").is(lotNumber),
        					 Criteria.where("manufacturingBatchNumber").is(batchNumber),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        LISWorkJobOrderMaster wjoMaster = mongoTemplate.findOne(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return wjoMaster;
	}

	@Override
	public void saveWorkJobOrderData(LISWorkJobOrderMaster workJobOrderMaster) throws WorkJobOrderException {
		logger.info("Entered into saveWorkJobOrderData DAO");	
		mongoTemplate.save(workJobOrderMaster);			
	}

	@Override
	public List<LISWorkJobOrderMaster> WorkJobOrderList(Integer subscriberId) throws WorkJobOrderException {
		logger.info("Entered into WorkJobOrderList DAO");
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(
					Criteria.where("subscriberMaster.subscriberId").is(subscriberId)
					.and("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))
					),
					Aggregation.sort(Sort.Direction.DESC, "createdTimestamp"));
		List<LISWorkJobOrderMaster> list = mongoTemplate.aggregate(aggregation, "LIS_WOMCS", LISWorkJobOrderMaster.class).getMappedResults();
		return list;
	}

	@Override
	public LISWorkJobOrderMaster validateWorkJobOrderNumber(String workJobOrderNumber, String customerPONumber)	throws WorkJobOrderException {
		logger.info("Inside validateWorkJobOrderNumber DAO");
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(
				Criteria.where("purchaseOrder.customerPONumber").is(customerPONumber)
				.and("workJobOrderNumber").is(workJobOrderNumber)
				.and("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))
				),
				Aggregation.sort(Sort.Direction.DESC, "createdTimestamp"));
        List<LISWorkJobOrderMaster> list = mongoTemplate.aggregate(aggregation, "LIS_WOMCS", LISWorkJobOrderMaster.class).getMappedResults();
		if (list != null)
			return list.get(0);
        return null;
	}

	@Override
	public LISWorkJobOrderMaster validateLotNumber(String workJobOrderNumber, String lotNumber)	throws WorkJobOrderException {
		logger.info("Inside validatelotNumber DAO");
		Criteria criteria = new Criteria();
		if(null != workJobOrderNumber) {
			criteria.andOperator(Criteria.where("workJobOrderNumber").is(workJobOrderNumber),
        					 Criteria.where("lotNumber").is(lotNumber),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
		} else {
			criteria.andOperator(Criteria.where("lotNumber").is(lotNumber),
					 		 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
		}
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        LISWorkJobOrderMaster wjoMaster = mongoTemplate.findOne(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return wjoMaster;
	}

	@Override
	public LISWorkJobOrderMaster validateManufacturerBatchNumber(String manufacturerBatchNumber, String lotNumber) throws WorkJobOrderException {
		logger.info("Inside validateManufacturerBatchNumber DAO");
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("manufacturerBatchNumber").is(manufacturerBatchNumber),
        					 Criteria.where("lotNumber").is(lotNumber),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        LISWorkJobOrderMaster wjoMaster = mongoTemplate.findOne(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return wjoMaster;
	}

	@Override
	public LISPurchaseOrderMaster getCustomerPOQuantity(String componentProdDrawNum, String customerPONumber)
			throws WorkJobOrderException {
		logger.info("Inside getCustomerPOQuantity DAO");
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(
				Criteria.where("component.componentProductDrawNumber").is(componentProdDrawNum)
				.and("customerPONumber").is(customerPONumber)
				.and("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))
				),
				Aggregation.sort(Sort.Direction.DESC, "createdTimestamp"));
        List<LISPurchaseOrderMaster> list = mongoTemplate.aggregate(aggregation, "LIS_CPMCS", LISPurchaseOrderMaster.class).getMappedResults();
		if (list != null)
			return list.get(0);
        return null;
	}

	@Override
	public List<LISWorkJobOrderMaster> getAllWorkJobOrderListByNumber(String workJobOrderNumber, String customerPONumber) throws WorkJobOrderException {
		logger.info("Inside getAllWorkJobOrderListByNumber DAO");
		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(
				Criteria.where("workJobOrderNumber").is(workJobOrderNumber)
				.and("purchaseOrder.customerPONumber").is(customerPONumber)
				.and("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE))
				),
				Aggregation.sort(Sort.Direction.DESC, "createdTimestamp"));
        List<LISWorkJobOrderMaster> list = mongoTemplate.aggregate(aggregation, "LIS_WOMCS", LISWorkJobOrderMaster.class).getMappedResults();
        return list;
	}

	@Override
	public LISWorkJobOrderMaster getWorkJobOrderBy4(String componentProductDrawNumber, String customerPONumber,
			String lotNumber, String workJobOrderNumber) throws WorkJobOrderException {
		logger.info("Inside getWorkJobOrderBy4 DAO");
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("component.componentProductDrawNumber").is(componentProductDrawNumber),
        					 Criteria.where("purchaseOrder.customerPONumber").is(customerPONumber),
        					 Criteria.where("lotNumber").is(lotNumber),
        					 Criteria.where("workJobOrderNumber").is(workJobOrderNumber),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        LISWorkJobOrderMaster master = mongoTemplate.findOne(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return master;
	}

	@Override
	public List<LISWorkJobOrderMaster> getAllWorkJobOrderListByLotNumber(String lotNumber)	throws WorkJobOrderException {
		logger.info("Inside getAllWorkJobOrderListByLotNumber DAO");
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("lotNumber").is(lotNumber),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        List<LISWorkJobOrderMaster> masterList = mongoTemplate.find(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return masterList;
	}


	@Override
	public LISWorkJobOrderMaster getWorkJobOrderById(Integer wjOrderId) throws WorkJobOrderException {
		logger.info("Inside getWorkJobOrderById DAO");
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("wjOrderId").is(wjOrderId),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        LISWorkJobOrderMaster master = mongoTemplate.findOne(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return master;
	}

	@Override
	public LISWorkJobOrderMaster getWorkOrderByBatchNumber(String manufacturingBatchNumber)
			throws WorkJobOrderException {
		logger.info("Inside getWorkOrderByBatchNumber DAO");
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("manufacturingBatchNumber").is(manufacturingBatchNumber),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        LISWorkJobOrderMaster master = mongoTemplate.findOne(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return master;
	}
	
	@Override
	public List<LISWorkJobOrderMaster> getComponentDataFromWJOBySubscriberId(Integer subscriberId) throws WorkJobOrderException {
		logger.info("Retrieving ComponentData from WorkJobOrder bt SubscriberId");
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("subscriber.subscriberId").is(subscriberId),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        List<LISWorkJobOrderMaster> list = mongoTemplate.find(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return list;
	}

	@Override
	public List<LISWorkJobOrderMaster> getWorkJobOrderByCompDrawNum(String compProdDrawNum) throws WorkJobOrderException {
		logger.info("Retrieving getWorkJobOrderByCompDrawNum ");
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("component.componentProductDrawNumber").is(compProdDrawNum),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.ASC, "workJobOrderNumber"));
        List<LISWorkJobOrderMaster> list = mongoTemplate.find(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return list;
	}

	@Override
	public List<LISWorkJobOrderMaster> WorkJobOrderListByUserId(String userId) throws WorkJobOrderException {
		logger.info("Entered into WorkJobOrderList DAO");	
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("user.userId").is(userId),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        List<LISWorkJobOrderMaster> list = mongoTemplate.find(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return list;
	}

	@Override
	public List<LISWorkJobOrderMaster> getWJOListByPONumAndSubId(String customerPONumber, Integer subscriberId)
			throws WorkJobOrderException {
		logger.info("Entered into getWJOListByPONumAndSubId DAO");	
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("purchaseOrder.customerPONumber").is(customerPONumber),
        					 Criteria.where("subscriber.subscriberId").is(subscriberId),
        					 Criteria.where("isActive").is(String.valueOf(StatusConstants.IS_ACTIVE)));
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createdTimestamp"));
        List<LISWorkJobOrderMaster> list = mongoTemplate.find(query, LISWorkJobOrderMaster.class, "LIS_WOMCS");
		return list;
	}
	
}
