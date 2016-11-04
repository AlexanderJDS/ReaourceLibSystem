package com.huijiasoft.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author pangPython
 *	sql������
 */
public class SQLUtils {

	//�������������̬����sql�����˿�ֵ
	//������������ѯ
	public static String DynamicSQL(Map<String,Object> map){
		
		String sql = " where ";
		
		//����map������valueΪ�յ�ɾ��
		Iterator it = map.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry mapentry = (Entry) it.next();
			if(mapentry.getValue()!=null){
				sql = sql + mapentry.getKey() + " = " + mapentry.getValue()+" and ";
			}
			
		}
		
		if(sql.trim().endsWith("and")){
			sql = sql.substring(0, sql.lastIndexOf("and"));
		}
		
		if(sql.equals(" where ")){
			return "";
		}
		
		//��Ϊ�õ���ı�������Ҫ�滻
//		sql = sql.replaceAll("user.usersex", "u.usersex");
//		sql = sql.replaceAll("user.", "u.");
		
		System.out.println(sql);
		
		return sql;
	}
}
