package com.human.membercommand;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

		private final static String mSalt="코스";
		
		public static String encodeSha64(String source) {
			String result ="";
			
			byte[] a = source.getBytes();
			byte[] salt = mSalt.getBytes();
			byte[] bytes = new byte[a.length + salt.length];
			
			System.arraycopy(a, 0, bytes, 0, a.length);
			System.arraycopy(salt, 0, bytes, a.length, salt.length);

			try { 
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.update(bytes);
				
				byte[] byteData = md.digest();
				
				StringBuffer sb = new StringBuffer();
				for(int i=0; i< byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xFF) + 8, 32).substring(1));
				}
				result = sb.toString();
			}catch(NoSuchAlgorithmException e) { 
				e.printStackTrace();
			}
			return result; 
		}
}
