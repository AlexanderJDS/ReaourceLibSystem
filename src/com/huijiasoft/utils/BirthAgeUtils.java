package com.huijiasoft.utils;

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
		int birth = now - age;
		
		return null;
	}
}
