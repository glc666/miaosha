package com.ayuantalking.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
*@author ayuantalking
*@createTime 2019年3月22日下午3:04:43
* ....
*/
public class MD5Util {
	
	private static String salt = "1a2b3c4d";

	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}

	public static String inputPasstoFormPass(String pass) {
		pass = ""+salt.charAt(0)+salt.charAt(3)+pass+salt.charAt(4)+salt.charAt(6);
		return md5(pass);
	}
	
	public static String formPasstoDBPass(String pass, String salt2) {
		pass = ""+salt2.charAt(0)+salt2.charAt(3)+pass+salt2.charAt(4)+salt2.charAt(6);
		return md5(pass);
	}
	
	public static void main(String[] args) {
		String password = "123456";
		//System.out.println(password);
		System.out.println("formPass:"+inputPasstoFormPass(password));//4242e87b71aa307b37afaca4cfb8afea
		System.out.println("dbPass:"+formPasstoDBPass(inputPasstoFormPass(password),salt));
		// formPass:4242e87b71aa307b37afaca4cfb8afea
		// dbPass:f5976ba8484db8b97d954a9d99a9efbc
	}
}
