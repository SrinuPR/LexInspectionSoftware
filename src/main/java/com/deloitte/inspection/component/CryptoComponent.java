package com.deloitte.inspection.component;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.inspection.exception.CryptoException;


@Component
public class CryptoComponent {
	private static final Logger logger = LogManager.getLogger(CryptoComponent.class);
	
	@Value("${pwd.encryption.key}")
	private String key;
	
	public String encrypt(String text) throws CryptoException{
		try {
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			String enc = Base64.getEncoder().encodeToString(encrypted);
			return enc;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CryptoException(e.getMessage());
		} 
	}
	
	public String decrypt(String text) throws CryptoException{
		try {
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, aesKey);
	        String decrypted = new String(cipher.doFinal(Base64.getDecoder().decode(text)));
	        return decrypted;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CryptoException(e.getMessage());
		} 
	}
}
