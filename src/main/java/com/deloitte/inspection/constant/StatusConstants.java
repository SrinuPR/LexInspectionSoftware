package com.deloitte.inspection.constant;

public class StatusConstants {

	public static final String INCORRECT_CREDENTIALS="User id/Password is wrong";
	public static final String USER_ID_EMPTY = "User Id is empty";
	public static final String PASSWORD_EMPTY = "Password is empty";
	public static final String LOGIN_SUCCESS = "Success";
	
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failed";
	public static final String DUPLICATE = "Duplicate";
	public static final String WARNING = "Warning";
	
	public static final String ERROR="Error";
	
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

	public static final String DEFAULT_USER_NAME = "Admin";
	public static final String DEFAULT_USER_ID = "Admin";
	
	public static final String UPDATE ="Update";
	public static final String INSERT ="Insert";
	
	public static final String ADMIN_ROLE = "ADMIN";
	public static final String OTHER_ROLE = "USER/SUBSCRIBER";
	
	public static final String VALIDATE_ADMIN_SUCCESS = "Admin Id is not exist";
	public static final String VALIDATE_ADMIN_FAILURE = "Admin Id Exist";
	
	public static final String ADMIN_DELETE_SUCCESS = "Admin delete successfully";
	public static final String ADMIN_DELETE_FAILURE = "Admin deletion failed";
	
	public static final String ADMIN_SAVE_SUCCESS = "Admin saved";
	public static final String ADMIN_SAVE_FAILURE = "Admin not saved";

}
