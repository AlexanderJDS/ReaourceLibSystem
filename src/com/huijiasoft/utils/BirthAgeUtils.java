package com.huijiasoft.utils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author pangPython
 *	�������� ���� ������
 */
public class BirthAgeUtils {

	//���������ȡ���� �����ַ�����
	public static String getBirthByAge(String age){
		int now = Integer.parseInt(DateUtils.getNowTime("yyyy"));
		int a = Integer.parseInt(age);
		return (now - a)+"";
	}
	
	//���������ȡ���� ����������
	public static Date getBirthByAge(int age){
		int now = Integer.parseInt(DateUtils.getNowTime("yyyy"));
		String birth = (now - age)+"";
		
		return DateUtils.parse(birth);
	}
	
	public static String getMinBirthByAge(int age) throws ParseException{
		int now = Integer.parseInt(DateUtils.getNowTime("yyyy"));
		String birth = (now - age)+"-12-30 59:59:59";
		System.out.println(birth+"======");
		
		return birth;
		
	}
	
	
	public static String getMaxBirthByAge(int age){
		int now = Integer.parseInt(DateUtils.getNowTime("yyyy"));
		String birth = (now - age)+"-01-01 00:00:00";
		
		return birth;
	}
	
	
}
