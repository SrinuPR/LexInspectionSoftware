package com.deloitte.inspection.constant;

public class WorkJobOrderConstants {
	
	public static final String WORK_JOB_ORDER_DELETE_FAILED = "Work/Job Order Deletion Failed";
	public static final String WORK_JOB_ORDER_DELETE_SUCCESS = "Work/Job Order Deleted Successfully";
	
	public static final String WORK_JOB_ORDER_SAVE_FAILED = "Work/Job Order Save Failed";
	public static final String WORK_JOB_ORDER_SAVE_SUCCESS = "Work/Job Order Save Successfully";
	
	public static final String WORK_JOB_ORDER_NUMBER_EXIST = "Work/Job Order Number Alredy Exist";
	public static final String WORK_JOB_ORDER_DATE_ISSUE = "Work/Job Order date is in invalid format";
	
	public static final String WORK_JOB_ORDER_LIST_SUCCESS = "List of Work/Job order success";
	public static final String WORK_JOB_ORDER_LIST_FALIED = "List of Work/Job order list failed";
	
	public static final String WORK_JOB_ORDER_VALIDATION_WARNING = "Work/Job Order for the selected P.O. exists";
	public static final String WORK_JOB_ORDER_VALIDATION_SUCCESS = "Work/Job Order for the selected P.O. does not exists";
	public static final String UN_EXPECTED_EXCEPTION = "UnExpected exception occurred";
	
	public static final String LOT_NUMBER_VALIDATION_WARNING = "Lot Number for selected Work/Job Order Exists";
	public static final String LOT_NUMBER_SUCCESS = "Lot Number is Exists";
	public static final String LOT_NUMBER_VALIDATION_SUCCESS = "Lot Number for selected Work/Job Order does not Exists";
	
	public static final String MANUFACTURER_BATCH_NUMBER_VALIDATION_FAILED = "Data exists. Please check Work/Job Order Number or Lot Number or Batch Number";
	public static final String MANUFACTURER_BATCH_NUMBER_VALIDATION_SUCCESS = "Data does not exists.";
	
	public static final String LOT_SIZE_VALIDATION_SUCCESS = "Lot Size have positive Quantity";
	public static final String LOT_SIZE_VALIDATION_FAILED = "Lot Size exceeds P.O. Quantity";
	
	public static final String MANUFACTURER_BATCH_SIZE_VALIDATION_SUCCESS = "Batch Size not exceeds the Lot Size";
	public static final String MANUFACTURER_BATCH_SIZE_VALIDATION_FAILED = "Batch Size exceeds the Lot Size";

}
