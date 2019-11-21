package com.helloworld.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	private String md5_16;
	private String md5_32;
	
	public String encrypt(String sourcecode) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourcecode.getBytes());
			byte [] b = md.digest();
			StringBuffer buffer = new StringBuffer("");
			for(int offset = 0;offset < b.length; offset++) {
				int i = b[offset];
				if(i<0) i+=256;
				if(i<16) buffer.append("0");
				buffer.append(Integer.toHexString(i));
				}
			md5_16 = buffer.toString().substring(8,24);
			md5_32 = buffer.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
		}
		return md5_32;
	}
	public String get16() {
		return md5_16;
	}
	public String get32() {
		return md5_32;
	}
}
