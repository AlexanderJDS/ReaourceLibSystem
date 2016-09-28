package com.huijiasoft.model;



import java.util.List;

import com.huijiasoft.model.base.BaseUser;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class User extends BaseUser<User> {
	
	public static final User usermodel = new User();
	
	
	//Jfinal�ṩ��ҳ
	public Page<User> paginate(int pageNumber,int pageSize){
		
		return paginate(pageNumber, pageSize, "select *", "from user order by id asc");
	}
	
	//δʹ��Jfinal��ҳ��ʹ��h-ui-admin�ṩ��js��ҳ
	public List<User> getAllUser(){
		return  usermodel.find("select * from user order by id asc");
	}
	

}
