package com.ayuantalking.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*@author ayuantalking
*@createTime 2019年3月22日上午11:45:58
* ....
*/
public class ValidatorUtil {
	
	private static Pattern mobile_pattern = Pattern.compile("1\\d{10}");
	
	/**
	 * 手机号码格式不正确
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		Matcher matcher = mobile_pattern.matcher(mobile);
		if(!matcher.matches()) {
			return false;
		}
		return true;
	}

}
