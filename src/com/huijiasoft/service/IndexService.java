package com.huijiasoft.service;

import com.huijiasoft.model.System;

public class IndexService {
	public static System getSysConfig(){
		return System.dao.findFirst("select * from system limit 1");
	}
	//���ϵͳ������վ�㿪�Ų���
	public static boolean siteIsOpen(){
		return "1".equals(getSysConfig().getOpen());
	}
	
}
