package com.app;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {
	
	private static final Logger log = LoggerFactory.getLogger(VerificationService.class);

    public String verifyHash(String hash) throws NoSuchAlgorithmException {
    	
    	String verificationResult = "Failed";
    	
    	SecureRandom random = SecureRandom.getInstanceStrong();
    	
    	int number = random.nextInt(10);
    	
    	if(number > 5) {
    		verificationResult = "Success";
    	}
    	
    	log.info("number: {}", number);
    	
		return verificationResult;
    }

}
