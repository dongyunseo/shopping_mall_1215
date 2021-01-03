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
			// 배열복사?
			System.arraycopy(salt, 0, bytes, a.length, salt.length);

			try { 
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				// 암호화같은 경우는 자바 개발도구에 기본적으로 포함된 MessageDigest를 이용함
				md.update(bytes);
				// 사용자가 입력한 값의 바이트를 가져와 업데이트함 
				// SHA-256 해쉬를 시키기 위한 준비
				
				byte[] byteData = md.digest();
				// 해쉬 결과값을 digest함수를 이용해서 바이트스트림으로 받아온 다음
				
				StringBuffer sb = new StringBuffer();
				// 하나의 문자열을 만들 수 있는 객체 StringBuffer
				
				for(int i=0; i< byteData.length; i++) {
					// 다시 문자열 형태로 화면에 출력할수 있도록 
					sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 32).substring(1));
					//append 함수에다가 문자열 형태로 담을 수 있도록 하는 코드
				}
				result = sb.toString();
				// 만들어진 해쉬 결과값을 반환함
			}catch(NoSuchAlgorithmException e) { 
				e.printStackTrace();
			}
			return result; 
		}
}
