package com.huijiasoft.controller;

import com.huijiasoft.service.IndexService;
import com.jfinal.core.Controller;

public class IndexContrlller extends Controller {
	//������ҳ
	
	public void index(){
		//if(IndexService.siteIsOpen()){
			setAttr("system", IndexService.getSysConfig());
			render("index.html");	
	//	}else{
	//		renderText("վ���Ѿ��رգ�");
	//	}
		
	}
	
	
	public void login(){
		setAttr("system", IndexService.getSysConfig());
		render("login.html");
	}
	
	public void regist(){
		render("regist.html");
	}
	
	
}
