package com.huijiasoft.controller;

import com.huijiasoft.service.IndexService;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * @author pangPython
 *	ǰ�˿�����
 */
public class IndexContrlller extends Controller {
	//������ҳ
	
	@ActionKey("/")
	public void index(){
			setAttr("system", IndexService.getSysConfig());
			render("index.html");	
	}
	
	//ǰ̨��¼����
	public void login(){
		setAttr("system", IndexService.getSysConfig());
		render("login.html");
	}
	
	//ע��
	public void regist(){
		render("regist.html");
	}
	
	
	
}
