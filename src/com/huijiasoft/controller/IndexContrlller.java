package com.huijiasoft.controller;

import com.huijiasoft.validate.LoginValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class IndexContrlller extends Controller {
	//������ҳ
	
	public void index(){
		if(getSessionAttr("uanme")){
			setAttr("errMsg", "hahahah");
		}
		render("index.html");
	}
	
	@Before(LoginValidator.class)
	public void login(){
		//��¼֮ǰ�Ƚ����ж�session�Ƿ����û���¼
		//y logined
		//n login register
		
		System.out.println(getPara("login.name"));
		if ("admin"==getPara("login.name")) {
			setSessionAttr("uname", "login");
		}
		render("index.html");
	}
	
	
	
	
}
