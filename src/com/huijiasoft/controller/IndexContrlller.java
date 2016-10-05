package com.huijiasoft.controller;

import com.huijiasoft.model.User;
import com.huijiasoft.service.IndexService;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.SessionIdKit;

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
		//����������cookie�п��Ի�ȡ����ǰ��¼�û�
		//���Ƴ����¼��Ϣ
		String cuser = getCookie("cuser");
		
		if(cuser!=null){
			removeSessionAttr(cuser);
		}
		
		String uname = getPara("uname");
		String pwd = getPara("password");
		
		String sql = "select * from user where uname = ? and pwd = ? limit 1";
		
		User user = User.usermodel.findFirst(sql,uname,pwd);
		//��¼�ɹ�
		if(user!=null){
			//����Ψһ��ʶ
			String sessionId = SessionIdKit.me().generate(getRequest());
			//���÷�������session
			setSessionAttr(sessionId, user);
			//�����û���cookie
			setCookie("cuser", sessionId, 600);
			redirect("/user");
		}else{
			setAttr("system", IndexService.getSysConfig());
			render("login.html");
		}
		
	}
	
	//ע��
	
	public void regist(){
		render("regist.html");
	}
	
	
	
}
