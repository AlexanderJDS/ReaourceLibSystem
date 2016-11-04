package com.huijiasoft.utils;

import java.text.ParseException;
import java.util.Date;
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
	public static String DynamicSQL(Map<String,Object> map) throws ParseException{
		
		String end = null;
		String start = null;
		try {
			int minage = (Integer) map.get("minage");
			int maxage = (Integer) map.get("maxage");
			
			map.remove("minage");
			map.remove("maxage");
			
			start = BirthAgeUtils.getMinBirthByAge(minage);
			end = BirthAgeUtils.getMaxBirthByAge(maxage);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String sql = " where ";
		
		//����map������valueΪ�յ�ɾ��
		Iterator it = map.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry mapentry = (Entry) it.next();
			if(mapentry.getValue()!=null){
				sql = sql + mapentry.getKey() + " = " + mapentry.getValue()+" and ";
			}
			
		}
		if(start!=null && end!=null){
			if(sql.trim().endsWith("where") || sql.trim().endsWith("and")){
				sql = sql + " p.birth > '" + end + "' and p.birth < '"+start+"'";
			}else{
				
				sql = sql + " and p.birth > '" + end + "' and p.birth < '"+start+"'";
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
