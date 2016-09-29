package com.huijiasoft.controller;

import java.io.File;
import java.io.IOException;

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
import com.huijiasoft.utils.WriteToDocx;
import com.huijiasoft.validate.AdminValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.render.CaptchaRender;


@Before(AdminAuthInterceptor.class)
public class AdminController extends Controller {
	
	public void index(){
		render("index.html");
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
			redirect("index");
		}else{
			render("login.html");
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
		setAttr("userList", User.usermodel.getAllUser());
		render("article-list.html");
	}
	
	//�걨���͹���
	public void declare(){
		setAttr("declareList",DeclareType.dao.getAllDecType());
		render("declare.html");
	}
	
	//����������
	
	public void nation(){
		setAttr("nationList", Mz.dao.getAllMz());
		render("nation.html");
	}
	
	
	//������ò����
	public void zzmm(){
		setAttr("zzmmList", Zzmm.dao.getAllZzmm());
		render("zzmm.html");
	}
	
	//ѧ����Ϣ����
	public void education(){
		
		setAttr("educationList", Edu.dao.getAllEdu());
		render("education.html");
		
	}
	
	//ѧλ��Ϣ����
	
	public void degree(){
		setAttr("degreeList", Degree.dao.getAllDegree());
		render("degree.html");
	}
	
	//��������
	public void area(){
		setAttr("areaList", Area.dao.getAllArea());
		render("area.html");
	}
	
	//ϵͳ��������
	public void system(){
		setAttr("system", System.dao.getSytem());
		render("system-base.html");
	}
	
	

	//ϵͳ��������
	public void systemINIupdate(){
		getModel(System.class).update();
		renderText("���³ɹ���");
	}
	
	//�鿴ĳ���û�
	public void checkUser(){
		setAttr("user", User.usermodel.findById(getPara("id")));
		render("user-check.html");
	}
	
	//��ӡ
	public void printer(){
		
		String fileName = "";
		int id = getParaToInt("id");
		try {
			fileName = WriteToDocx.write(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		renderFile(new File(fileName));
		
	}
	
	
	
	
}
