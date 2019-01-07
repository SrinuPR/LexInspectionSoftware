package com.deloitte.inspection.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.deloitte.inspection.dao.LoginDAO;
import com.deloitte.inspection.exception.LoginException;

@Component
public class LISListener {
	
	@Autowired
	private LoginDAO loginDAO;
	
	private static final Logger logger = LogManager.getLogger(LISListener.class);
	
	@EventListener(ContextRefreshedEvent.class)
	public void loginEvent() {
		try {
			logger.info("Listner Called");
			loginDAO.updateSessionActiveSwToN();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

}
