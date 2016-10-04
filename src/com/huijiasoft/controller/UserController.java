package com.huijiasoft.controller;

import com.huijiasoft.interceptor.UserAuthInterceptor;
import com.huijiasoft.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * @author pangPython
 *
 */
@Before(UserAuthInterceptor.class)
public class UserController extends Controller {
	
	public void index() {
		//ʹ�ÿͻ��������е�cookie�е�user��Ψһ��ʶ��Ϊkey
		//ȡ�������˵�session
		User user = (User) getSession().getAttribute(getCookie("cuser"));
		
			setAttr("user", user);
			render("/user.html");
		
		
	}
	
	//�û����ķ���
	public void center() {
		renderText("�û����ģ�");
	}
	//�Ѿ���¼�û����˳�����
	public void logout(){
		removeSessionAttr(getCookie("cuser"));
		removeCookie("cuser");
		redirect("/");
	}
	
	

}
