package com.deloitte.inspection.email;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
	
	private static final Logger logger = LogManager.getLogger(EmailService.class);
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Resource(mappedName="java:/jboss/mail/gmail")
    private Session session;
	
	public void sendEmail(String toEmail, String messageBody, String subject){
		
		logger.info("Sending email to :"+toEmail);
		Session session = loadEmailProperties();
		try{
			MimeMessage message = new MimeMessage(session);
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			helper.setTo(InternetAddress.parse(toEmail));
			helper.setText(messageBody, false);
			helper.setSubject(subject);
			helper.setFrom(new InternetAddress(emailProperties.USER_NAME));
			Transport.send(message);
			logger.info("Email sent to :"+toEmail);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private Session loadEmailProperties(){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", emailProperties.SMTP_HOST);
		props.put("mail.smtp.port", emailProperties.SMTP_PORT);

		return session;
	}
}
