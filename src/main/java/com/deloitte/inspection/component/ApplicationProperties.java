package com.deloitte.inspection.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
	
	@Value("${each.subscriber.user.count}")
	public int EACH_SUBSCRIBER_USER_COUNT;

}
