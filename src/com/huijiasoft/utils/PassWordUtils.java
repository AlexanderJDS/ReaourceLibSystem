package com.huijiasoft.utils;


/**
 * @author pangPython
 *	�й����빤����
 */
public class PassWordUtils {
	public static String MD5withSalt(String pwd,String salt){
		return MD5.GetMD5Code(pwd+salt);
	}
}
