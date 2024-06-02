package com.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	
	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);
	
	public static String calculateSHA256(InputStream is) throws Exception {

        String sha256 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte buffer[] = new byte[1024];
            int length = -1;
            while ((length = is.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }

            byte[] digest = md.digest();
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
            	String hex = Integer.toHexString(0xff & b);
            	if (hex. length () == 1) {
            		hexString.append('0');
            	}
            	hexString.append(hex);
            }
            
            sha256 = hexString.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Hash Calculation Error");
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sha256;
    }
}
