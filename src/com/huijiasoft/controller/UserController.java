package com.huijiasoft.controller;

import com.huijiasoft.interceptor.UserAuthInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * @author pangPython
 *
 */
@Before(UserAuthInterceptor.class)
public class UserController extends Controller {
	
	public void index() {
		renderText("�û���");
	}
	
	//�û����ķ���
	public void center() {
		renderText("�û����ģ�");
	}
	
	
	

}
