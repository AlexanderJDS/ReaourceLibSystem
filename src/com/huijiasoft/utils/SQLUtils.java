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
		

		System.out.println(sql+" ---------ƴװ��SQL---------");
		
		if(sql.trim().endsWith("and")){
			sql = sql.substring(0, sql.lastIndexOf("and"));
		}
		
		sql = sql.replaceAll("user", "u");
		
		System.out.println(sql+" ---------ƴװ��SQL---------");
		//ʣ�µ���װ��sql
		return sql;
	}
}
