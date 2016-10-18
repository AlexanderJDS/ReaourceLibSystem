package com.huijiasoft.model;

import java.util.List;

import com.huijiasoft.model.base.BaseAdmin;
import com.huijiasoft.utils.MD5;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Admin extends BaseAdmin<Admin> {
	public static final Admin dao = new Admin();
	
	//获取所有admin
	public List<Admin> getAllAdmin(){
		return dao.find("select * from admin order by id desc");
	}
	
	//获取所有县区管理员
		public List<Admin> getAllCountryAdmin(){
			return dao.find("select * from admin where type = ?",1);
		}
		
		public String MD5_ID(){
			return MD5.GetMD5Code(dao.getId().toString());
		}
		
	
}
