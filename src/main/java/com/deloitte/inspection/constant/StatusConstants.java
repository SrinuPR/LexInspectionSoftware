package com.deloitte.inspection.constant;

public class StatusConstants {

	public static final String INCORRECT_CREDENTIALS="User id/Password is wrong";
	public static final String USER_ID_EMPTY = "User Id is empty";
	public static final String PASSWORD_EMPTY = "Password is empty";
	public static final String LOGIN_SUCCESS = "Success";
	public static final String SUBSCRIBER_ID_EMPTY = "Subscriber ID is empty";
	public static final String SUBSCRIBER_NAME_EMPTY = "Subscriber name is empty";
	public static final String SUBSCRIBER_ADDRESS_EMPTY = "Subscriber address is empty";
	public static final String INVALID_SUBSCRIBER="Invalid Subscriber ID";
	public static final String CREATE_SUBSCRIBER_FAILED = "Subscriber NOT saved, please try again";
	public static final String CREATE_SUBSCRIBER_SUCCESS = "Subscriber Saved";
	public static final String SUBSCRIBER_AVAILABLE="Subscriber ID exists";
	public static final String SUBSCRIBER_NOT_AVAILABLE="Subscriber ID not exists";
	
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failed";
	public static final String DUPLICATE = "Duplicate";
	
	public static final String ERROR="Error";
	
	public static final String USER_TYPE_ID_EMPTY = "User Type ID is empty";
	public static final String USER_TYPE_NAME_EMPTY = "User Type name is empty";
	public static final String INVALID_USER_TYPE = "Invalid User Type ID";
	public static final String CREATE_USER_TYPE_FAILED = "User Type NOT saved, please try again";
	public static final String CREATE_USER_TYPE_SUCCESS = "User Type Master Saved";
	public static final String USER_TYPE_AVAILABLE = "User Type ID exists";
	public static final String USER_TYPE_NOT_AVAILABLE = "User Type ID not exists";
	public static final String USER_TYPE_NAME_AVAILABLE = "User Type ID and User Type Name combination exists";
	public static final String USER_TYPE_NAME_NOT_AVAILABLE = "User Type ID and User Type Name combination not exists";
	
	public static final char IS_ACTIVE = 'Y';
	public static final char IN_ACTIVE = 'N';
	public static final String IN_ACTIVE_LOGIN_USER = "User is in active in the system";
	
	public static final String PASSWORD_CHANGED_SUCCESS = "Passowrd Changed Successfully";
	public static final String PASSWORD_CHANGED_FAIL = "Passowrd Change Failed";
	public static final String INVALID_USER = "Invalid User Id";
	
	public static final Integer RANDOM_PASSWORD_LENGTH = 10;
	
	public static final String FORGOT_PASSWORD_MAIL_BODY = "Hi, \n Please find your new password :- \n\n";
	public static final String EMAIL_SENT_FAILED = "Email sending failed";
	public static final String USER_CREATE_FIALED="User NOT saved, Please try again";
	public static final String USER_CREATE_SUCCESS="User saved";
	public static final String USER_EXISTS="User ID Exists";
	public static final String EMPTY="";
	
	public static final String CREATE_PURCHASE_ORDER_SUCCESS="Customer P.O. Saved";
	public static final String CREATE_PURCHASE_ORDER_FAILURE="Customer P.O. NOT Saved. Please try again";
	public static final String PURCHASE_ORDER_EXISTS="Customer P.O. Exists";
	public static final String INVALID_DATE="Date format is dd/mm/yyyy";
	public static final String CUSTOMER_PO_EXISTS="Customer P.O. Exists";
	public static final String WARN_PO_QUANTITY="P.O. Quantity less than Produced Quantity!!";

}
