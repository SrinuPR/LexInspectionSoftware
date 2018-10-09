package com.deloitte.inspection.util;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class RandomPasswordGenerator {

	private SecureRandom srand;
	
    @SuppressWarnings("rawtypes")
	private ArrayList validch;

    @SuppressWarnings("unchecked")
	private void passwordGenerator() throws NoSuchAlgorithmException, NoSuchProviderException {
        srand = new SecureRandom();
        validch = new ArrayList<>();

        //Filling the ArrayList with the characters we want to use based on ascii table:
        // https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
        for (int i = 65; i < 91; i++) {
            validch.add((char) i);// A-Z
            validch.add((char) (i + 32));// a-z
        }
        for (int i = 48; i < 58; i++) {
            validch.add((char) i);
        }
        for (int i = 35; i < 39; i++) {
            validch.add((char) i);
        }
        validch.add((char) 64);
        Collections.shuffle(validch);
    }

    private char randChar() {
        return (char) this.validch.get(srand.nextInt(this.validch.size()));
    }
    
    public String generateRandomPassword (Integer len) throws NoSuchAlgorithmException, NoSuchProviderException {
		
    	RandomPasswordGenerator pg = new RandomPasswordGenerator();
    	pg.passwordGenerator();

        StringBuilder sb = new StringBuilder();
    	
    	for (int j = 0; j < len; j++) { // Generate 10 passwords
            for (int i = 0; i < len; i++) { // Passwords are 10 characters long
                sb.append(pg.randChar());
            }
    	
    }
    	return sb.toString();
    }
}
