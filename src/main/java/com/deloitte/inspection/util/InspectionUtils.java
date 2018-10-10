package com.deloitte.inspection.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InspectionUtils {

	
	public static boolean isValidDate(String dateToValidate) {
		 /*Returns true if dateToValidate is in format  dd/mm/yyyy */
		String regex = "^(1[0-2]|0[1-9])/(3[01]" + "|[12][0-9]|0[1-9])/[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) dateToValidate);
		return matcher.matches();
	} 
	
	public static Date convertStringToDate(String dateToConvert) throws ParseException {
		SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");  
		 Date date=formatter1.parse(dateToConvert);  
		 return date;
	}
	
	public static String convertDateToString(Date dateToConvert) throws ParseException {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();   
		String date = df.format(today);
		 return date;
	}
}
