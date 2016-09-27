package com.huijiasoft.controller;

import com.huijiasoft.interceptor.AdminAuthInterceptor;
import com.huijiasoft.model.Admin;
import com.huijiasoft.model.Area;
import com.huijiasoft.model.DeclareType;
import com.huijiasoft.model.Degree;
import com.huijiasoft.model.Edu;
import com.huijiasoft.model.Mz;
import com.huijiasoft.model.System;
import com.huijiasoft.model.User;
import com.huijiasoft.model.Zzmm;
import com.huijiasoft.validate.AdminValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.render.CaptchaRender;


@Before(AdminAuthInterceptor.class)
public class AdminController extends Controller {
	
	public void index(){
		render("login.html");
	}
	
	@Clear
	@Before(AdminValidator.class)
	public void login(){
		
		String admin_name = getPara("admin.name");
		String admin_pwd = getPara("admin.pwd");
		
		String sql = "select * from admin where adminname = ? and adminpassword = ? limit 1";
		
		Admin admin = Admin.dao.findFirst(sql,admin_name,admin_pwd);
		
		
		if(admin != null){
			this.setSessionAttr("Admin", admin);
			render("index.html");
		}else{
			redirect("index");
		}
		
	}
	
	public void logout(){
		removeSessionAttr("Admin");
		redirect("index");
	}
	
	
	public void img(){
		render(new CaptchaRender());
	}
	
	
	//��̨����ҳ��
	public void search(){
		setAttr("userPage", User.usermodel.paginate(getParaToInt(0,1), 10));
		render("article-list.html");
	}
	
	//�걨���͹���
	public void declare(){
		setAttr("declarePage",DeclareType.dao.paginate(getParaToInt(0,1), 10));
		render("declare.html");
	}
	
	//����������
	
	public void nation(){
		setAttr("nationPage", Mz.dao.paginate(getParaToInt(0,1), 10));
		render("nation.html");
	}
	
	
	//������ò����
	public void zzmm(){
		setAttr("zzmmPage", Zzmm.dao.paginate(getParaToInt(0,1), 10));
		render("zzmm.html");
	}
	
	//ѧ����Ϣ����
	public void education(){
		
		setAttr("educationPage", Edu.dao.paginate(getParaToInt(0,1), 10));
		render("education.html");
		
	}
	
	//ѧλ��Ϣ����
	
	public void degree(){
		setAttr("degreePage", Degree.dao.paginate(getParaToInt(0,1), 10));
		render("degree.html");
	}
	
	//��������
	public void area(){
		setAttr("areaPage", Area.dao.paginate(getParaToInt(0,1),10));
		render("area.html");
	}
	
	//ϵͳ��������
	public void system(){
		setAttr("system", System.dao.getSytem());
		render("system-base.html");
	}
	
	
	
}
