package com.deloitte.inspection.constant;

import java.util.ArrayList;
import java.util.List;

public class AuthorizeConstants {

	public static List<String> noAuthUrls = new ArrayList<String>();
	static {
		noAuthUrls.add("/user/login");
		noAuthUrls.add("/index.html");
		noAuthUrls.add("/login");
	}
}
