package com.huijiasoft.utils;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import com.huijiasoft.model.User;

/**
 * @author pangPython
 *	���ݿ⹤����
 */
public class DBUtils {
	
	
	
	//�ж����ݿ�ĳ����¼�������ֶ��Ƿ������ֵ
	public static Boolean RecordAttrHasNull(User user){
		Set<Entry<String, Object>> set = user._getAttrsEntrySet();
		
		Iterator<Entry<String, Object>> it = set.iterator();
		
		while(it.hasNext()){
			Entry<String, Object> attr = it.next();
			if(attr.getValue()==null){
				return true;
			}
			
		}
		return false;
	}
	

	
	
	
}
