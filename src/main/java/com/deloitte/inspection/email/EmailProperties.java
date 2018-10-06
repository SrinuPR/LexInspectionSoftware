package com.deloitte.inspection.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailProperties {

	@Value("${email.address}")
	public String USER_NAME;
	
	@Value("${email.password}")
	public String PASSWORD;
	
	@Value("${email.server.smtp.host}")
	public String SMTP_HOST;
	
	@Value("${email.server.smtp.port}")
	public String SMTP_PORT;
	
	
}
