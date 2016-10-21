package com.huijiasoft.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.huijiasoft.interceptor.AdminAuthInterceptor;
import com.huijiasoft.model.Admin;
import com.huijiasoft.model.Area;
import com.huijiasoft.model.DeclareType;
import com.huijiasoft.model.Degree;
import com.huijiasoft.model.Edu;
import com.huijiasoft.model.Log;
import com.huijiasoft.model.Mz;
import com.huijiasoft.model.System;
import com.huijiasoft.model.User;
import com.huijiasoft.model.Zzmm;
import com.huijiasoft.utils.DateUtils;
import com.huijiasoft.utils.JavaMysqlUtil;
import com.huijiasoft.utils.MD5;
import com.huijiasoft.utils.ReportExcel;
import com.huijiasoft.utils.WriteToDocx;
import com.huijiasoft.validate.AdminValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.SessionIdKit;
import com.jfinal.kit.PathKit;


/**
 * @author pangPython
 *	��̨������
 */
@Before(AdminAuthInterceptor.class)
public class AdminController extends Controller {
	
	public void index(){
		render("index.html");
	}
	
	@ActionKey("/admin/")
	public void root(){
		render("index.html");
	}
	
	//ȡ����¼��֤�ĺ�̨��¼����
	@Clear
	@Before(AdminValidator.class)
	public void login(){
		
		String admin_name = getPara("admin.name");
		String sql = "select * from admin where name = ? limit 1";
		
		Admin admin = Admin.dao.findFirst(sql,admin_name);
		if(admin != null){
			String admin_pwd = MD5.GetMD5Code(getPara("admin.pwd")+admin.getCreateTime());
			
			if(admin.getStatus() == 0){
				//setAttr("LoginNameMsg", "�ù���Ա�˺��ѽ��ã�");
				setAttr("LoginNameMsg","�û������������");
				render("login.html");
				return;
			}
			
			//debug
		
			
			if(!admin.getPwd().equals(admin_pwd)){
				setAttr("LoginNameMsg","�û������������");
				render("login.html");
				return;
			}
			
			//����Ψһ��ʶ �Ự����
			String sessionId = SessionIdKit.me().generate(getRequest());
			//���÷�������session
			setSessionAttr(sessionId, admin);
			//�����û���cookie
			setCookie("cadmin", sessionId, 60000);
			
			
			//��¼֮���ȼ�¼��־
			
			String ip = this.getRequest().getRemoteAddr();
			String uname = admin.getName();
			String date = DateUtils.getNowTime();
			
			Log log = getModel(Log.class);
			
			log.setIp(ip);
			log.setUname(uname);
			log.setDate(date);
			log.save();

			if(admin.getType() == 0){
				
				redirect("index");
				
			}else{
				redirect("xqadmin");
			}
			
			
		}else{
			
			render("login.html");
			
		}
		
	}
	
	
	//��������Ա��ҳ
	public void xqadmin(){
		
		render("countryadmin.html");
	}
	
	
	//��������Ա����
	public void xqsearch(){
		Admin admin = getSessionAttr(getCookie("cadmin"));
		setAttr("userList", User.usermodel.getUserByAreaId(admin.getAreaId()));
		render("xqsearch.html");
	}
	
	
	
	//�˳�����
	public void logout(){
		removeSessionAttr(getCookie("cadmin"));
		redirect("index");
	}
	
	//��̨��������Ϣ����ҳ��
	public void welcome() {
		Admin admin = getSessionAttr(getCookie("cadmin"));
		setAttr("admin", admin);
		render("welcome.html");
	}
	
	//��̨����ҳ��
	public void search(){
		setAttr("userList", User.usermodel.getAllUser());
		render("article-list.html");
	}
	
	public void useraddpage(){
		setAttr("areaList", Area.dao.getAllArea());
		setAttr("nationList", Mz.dao.getAllMz());
		setAttr("zzmmList", Zzmm.dao.getAllZzmm());
		setAttr("eduList", Edu.dao.getAllEdu());
		setAttr("degreeList", Degree.dao.getAllDegree());
		render("user-add.html");
	}
	
	public void adduser(){
		User user = getModel(User.class);
		String reg_time = DateUtils.dateToUnixTimestamp(DateUtils.getNowTime())+"";
		user.setRegDate(reg_time);
		user.save();
		renderText("��ӳɹ���");
	}
	
	//��˲�������
	public void examineset(){
		render("set-examine.html");
	}
	
	//������������
	public void attachmentset(){
		render("set-attachment.html");
	}
	
	
	//�걨�����б�ҳ��
	public void declare(){
		setAttr("declareList",DeclareType.dao.getAllDecType());
		render("declare.html");
	}
	
	//�걨���ͱ༭ҳ��
	public void declareedit(){
		setAttr("declare", DeclareType.dao.findById(getPara("id")));
		render("decedit.html");
	}
	//�걨���ͱ���
	public void decupdate(){
		DeclareType.dao.set("dec_id",getPara("dec_id")).set("decname", getPara("decname")).update();
		renderText("�걨���͸��³ɹ���");
	}
	
	//����������
	
	public void nation(){
		
		List<Mz> nationList = Mz.dao.getAllMz();
		String listsize = nationList.size()+"";
		setAttr("nationList", nationList);
		setAttr("listsize", listsize);
		render("nation.html");
	}
	
	//����༭ҳ��
	public void nationedit(){
		setAttr("nation", Mz.dao.findById(getPara("id")));
		render("mzedit.html");
	}
	
	//�����ֶθ���
	public void nationupdate(){
		Mz.dao.set("mz_id", getPara("mz_id")).set("mzname", getPara("mzname")).update();
		renderText("������Ϣ���³ɹ���");
	}
	
	
	//������ò����
	public void zzmm(){
		setAttr("zzmmList", Zzmm.dao.getAllZzmm());
		render("zzmm.html");
	}
	
	//������ò�༭ҳ��
	public void zzmmedit(){
		setAttr("zzmm", Zzmm.dao.findById(getPara("id")));
		render("zzmmedit.html");
	}
	//������ò���·���
	public void zzmmupdate(){
		Zzmm.dao.set("zzmm_id", getPara("zzmm_id")).set("zzmmname", getPara("zzmmname")).update();
		renderText("������ò���³ɹ���");
	}
	
	
	//ѧ����Ϣ����
	public void education(){
		
		setAttr("educationList", Edu.dao.getAllEdu());
		render("education.html");
		
	}
	//ѧ����Ϣ�༭
	public void eduedit(){
		setAttr("edu", Edu.dao.findById(getPara("id")));
		render("eduedit.html");
	}
	//ѧ����Ϣ����
	public void eduupdate(){
		Edu.dao.set("edu_id", getPara("edu_id")).set("eduname", getPara("eduname")).update();
		renderText("ѧ����Ϣ���³ɹ���");
	}
	
	//ѧλ��Ϣ����
	public void degree(){
		setAttr("degreeList", Degree.dao.getAllDegree());
		render("degree.html");
	}
	
	//ѧλ��Ϣ�༭ҳ��
	public void degreeedit(){
		setAttr("degree", Degree.dao.findById(getPara("id")));
		render("degreeedit.html");
	}
	//ѧλ��Ϣ����
	public void degreeupdate(){
		Degree.dao.set("degree_id", getPara("degree_id")).set("degreename", getPara("degreename")).update();
		renderText("ѧλ��Ϣ���³ɹ���");
	}
	
	//��������
	public void area(){
		setAttr("areaList", Area.dao.getAllArea());
		render("area.html");
	}
	
	//������Ϣ�༭ҳ��
	public void areaedit(){
		setAttr("area", Area.dao.findById(getPara("id")));
		render("areaedit.html");
		
	}
	//������Ϣ����
	public void areaupdate(){
		Area.dao.set("area_id", getPara("area_id")).set("area_name", getPara("areaname")).update();
		renderText("������Ϣ���³ɹ���");
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
		int uid = getParaToInt("id");
		User user = User.usermodel.findById_Relation(uid);
		String mzname = user.getStr("mzname");
		setAttr("user", user);
		setAttr("mz", mzname);
		setAttr("zzmm", user.getStr("zzmmname"));
		render("user-check.html");
	}
	
	
	//ͨ�����
	public void examine(){
		getModel(User.class).set("id", getPara("id")).set("status", 1).update();
		renderText("��˳ɹ���");
	}
	
	//��ӡ
	public void printer(){
		
		String fileName = "";
		int id = getParaToInt("id");
		try {
			fileName = WriteToDocx.write(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		renderFile(new File(fileName));
	}
	//��������Ա�б�
	public void adminList(){
		setAttr("adminList", Admin.dao.getAllAdmin());
		render("admin-list.html");
	}
	
	//��Ⱦ��ӹ���Աҳ��
	public void addAdmin(){
		render("admin-add.html");
	}
	
	//��ӹ���Ա����
	public void adminadd(){
		//����ע��ʱ���Ĭ��ע��״̬
		//��ӹ���Աע�����Ʋ����ظ�
		String reg_date = DateUtils.getNowTime();
		String pwd = MD5.GetMD5Code(getPara("admin.pwd")+reg_date);
		
		getModel(Admin.class).set("pwd", pwd).set("create_time",reg_date).set("status", 1).save();
		renderText("��ӹ���Ա�ɹ���"+getPara("admin.name"));
	}
	
	
	//�����й���Ա�б�
	public void countryadminList(){
		setAttr("caList", Admin.dao.getAllCountryAdmin());
		render("countryadminlist.html");
	}
	
	//���ݿⱸ��ҳ��
	public void dbbackup(){
		render("system-data.html");
	}
	
	//���ݴ�����
	@ActionKey("/admin/backupalldb")
	public void backupalldb(){
		String file_name = DateUtils.dateToUnixTimestamp(DateUtils.getNowTime())+".sql";
		JavaMysqlUtil.backup(file_name);
		renderFile(new File("WebRoot\\download\\dbbackup\\"+file_name));
	}
	
	//��ȡ���й���Ա��¼��־
	public void log(){
		setAttr("logList", Log.dao.getAllLog());
		render("system-log.html");
	}
	
	public void reportpage(){
		render("report.html");
	}
	
	//����
	public void excelall(){
		List<User> userlist = User.usermodel.getAllUser();
		String filename = "";
		try {
			filename = ReportExcel.report(userlist);
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderFile(new File(filename));
	}
	
	
	
}
