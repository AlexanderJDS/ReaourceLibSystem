package com.huijiasoft.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huijiasoft.interceptor.AdminAuthInterceptor;
import com.huijiasoft.model.Admin;
import com.huijiasoft.model.Area;
import com.huijiasoft.model.DeclareType;
import com.huijiasoft.model.Degree;
import com.huijiasoft.model.Edu;
import com.huijiasoft.model.Log;
import com.huijiasoft.model.Msg;
import com.huijiasoft.model.Mz;
import com.huijiasoft.model.System;
import com.huijiasoft.model.User;
import com.huijiasoft.model.Zzmm;
import com.huijiasoft.service.IndexService;
import com.huijiasoft.utils.DBUtils;
import com.huijiasoft.utils.DateUtils;
import com.huijiasoft.utils.JavaMysqlUtil;
import com.huijiasoft.utils.MD5;
import com.huijiasoft.utils.PathUtils;
import com.huijiasoft.utils.RenderDocxTemplate;
import com.huijiasoft.utils.ReportExcel;
import com.huijiasoft.validate.AdminValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.DateKit;
import com.jfinal.ext.kit.SessionIdKit;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;


/**
 * @author pangPython
 *	��̨������
 */
@Before(AdminAuthInterceptor.class)
public class AdminController extends Controller {
	
	//TODO �˴�������
	public void index(){
		setAttr("admin", getSessionAttr(getCookie("cadmin")));
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
		
		//��֤����֤
				boolean result = validateCaptcha("verifycode");
				if(!result){
					setAttr("codeMsg","��֤�����");
					setAttr("system", IndexService.getSysConfig());
					render("login.html");
					return;
				}
		
		
		String admin_name = getPara("admin.name");
		String sql = "select * from admin where name = ? limit 1";
		
		Admin admin = Admin.dao.findFirst(sql,admin_name);
		if(admin != null){
			String admin_pwd = MD5.GetMD5Code(getPara("admin.pwd")+admin.getCreateTime());
			
			if(admin.getStatus() == 0){
				//setAttr("LoginNameMsg", "�ù���Ա�˺��ѽ��ã�");
				setAttr("LoginNameMsg","�û������������");
				setAttr("system", IndexService.getSysConfig());
				render("login.html");
				return;
			}
			
			//debug
		
			
			if(!admin.getPwd().equals(admin_pwd)){
				setAttr("LoginNameMsg","�û������������");
				setAttr("system", IndexService.getSysConfig());
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
			setAttr("system", IndexService.getSysConfig());
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
	
	
	//��ʾ��������Ա����˲�ҳ��
	public void xqadduser(){
		setAttr("vv", "123");
	}
	
	//��������Ա����˲�
	public void xquseradd(){
		
	}
	
	
	//��ʾ��������Ա������Ϣ
	public void xqadmininfo(){
		Admin admin = getSessionAttr(getCookie("cadmin"));
		setAttr("xqadmin", admin);
	}
	
	//��������Ա������Ϣ
	public void xqadminedit(){
		
		setAttr("xqadmin", Admin.dao.findById(getParaToInt(0)));
	}
	
	public void xqadminupdate(){
		Admin admin = Admin.dao.findById(getParaToInt(0));
		admin.setTelephone(getPara("admin.telephone"));
		admin.setEmail(getPara("admin.email"));
		if(admin.update()){
			//����session
			setSessionAttr(getCookie("cadmin"), admin);
			renderText("�޸ĳɹ���");
		}else{
			setAttr("ErrMsg", "�޸ĳ���,�����ԣ�");
			render("error");
		}
	}
	
	//��ʾ��������Ա�޸�����
	public void xqchangepwd(){
		
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
	
	//��������Աwelcomeҳ��
	public void xqwelcome(){
		Admin admin = getSessionAttr(getCookie("cadmin"));
		setAttr("xqadmin", admin);
		render("xq-welcome.html");
		
	}
	
	//��̨����ҳ��
	public void search(){
		setAttr("userList", User.usermodel.getAllUser());
		render("article-list.html");
	}
	
	//������ѯ
	public void searchuser(){
		
		setAttr("areaList", Area.dao.getAllArea());
		setAttr("nationList", Mz.dao.getAllMz());
		setAttr("zzmmList", Zzmm.dao.getAllZzmm());
		setAttr("eduList", Edu.dao.getAllEdu());
		setAttr("degreeList", Degree.dao.getAllDegree());
		setAttr("decList", DeclareType.dao.getAllDecType());
		
		render("search-user.html");
	}
	

	
	
	//ִ�в�ѯ
	public void uschbycondition(){
		Map<String,Object> map = new HashMap<String, Object>();
		String sex = getPara("user.usersex");
		String mz_id = getPara("user.mz_id");
		String area_id = getPara("user.area_id");
		String zzmm_id = getPara("user.zzmm_id");
		String dec_id = getPara("user.dec_id");
		if(sex!=null && !sex.equals("")){
			map.put("p.usersex", sex);
		}
		if(mz_id!=null && !mz_id.equals("")){
			map.put("p.mz_id", mz_id);
		}
		if(area_id!=null && !area_id.equals("")){
			map.put("p.area_id", area_id);
		}
		if(zzmm_id!=null && !zzmm_id.equals("")){
			map.put("p.zzmm_id", zzmm_id);
		}
		if(dec_id!=null && !dec_id.equals("")){
			map.put("p.dec_id", dec_id);
		}	
		
		List<User> userList = User.usermodel.getUserListByCondition(map);
		
		String filename = "";
		try {
			filename = ReportExcel.report(userList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		File file =		new File(filename);
		String file_name = "";
		if(file!=null){
			file_name = file.getName();
		}
		
		setAttr("file", file_name);
		
		setAttr("userList", userList);
		
		render("s-u-result.html");
	}
	
	
	//��������Ա����������ѯ
	public void xqsearchuser(){

		setAttr("areaList", Area.dao.getAllArea());
		setAttr("nationList", Mz.dao.getAllMz());
		setAttr("zzmmList", Zzmm.dao.getAllZzmm());
		setAttr("eduList", Edu.dao.getAllEdu());
		setAttr("degreeList", Degree.dao.getAllDegree());
		setAttr("decList", DeclareType.dao.getAllDecType());
		render("xq-search-user.html");
	}
	
	
	//ִ�в�ѯ
		public void xquschbycondition(){
			Map<String,Object> map = new HashMap<String, Object>();
			String sex = getPara("user.usersex");
			String mz_id = getPara("user.mz_id");
			Admin admin = getSessionAttr(getCookie("cadmin"));
			String area_id = admin.getAreaId()+"";
			String zzmm_id = getPara("user.zzmm_id");
			String dec_id = getPara("user.dec_id");
			if(sex!=null && !sex.equals("")){
				map.put("p.usersex", sex);
			}
			if(mz_id!=null && !mz_id.equals("")){
				map.put("p.mz_id", mz_id);
			}
			if(area_id!=null && !area_id.equals("")){
				map.put("p.area_id", area_id);
			}
			if(zzmm_id!=null && !zzmm_id.equals("")){
				map.put("p.zzmm_id", zzmm_id);
			}
			if(dec_id!=null && !dec_id.equals("")){
				map.put("p.dec_id", dec_id);
			}	
			
			List<User> userList = User.usermodel.getUserListByCondition(map);
			
			String filename = "";
			try {
				filename = ReportExcel.report(userList);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			File file =		new File(filename);
			String file_name = "";
			if(file!=null){
				file_name = file.getName();
			}
			
			setAttr("file", file_name);
			
			setAttr("userList", userList);
			
			render("s-u-result.html");
		}
	
	
	public void useraddpage(){
		setAttr("areaList", Area.dao.getAllArea());
		setAttr("nationList", Mz.dao.getAllMz());
		setAttr("zzmmList", Zzmm.dao.getAllZzmm());
		setAttr("eduList", Edu.dao.getAllEdu());
		setAttr("degreeList", Degree.dao.getAllDegree());
		setAttr("decList", DeclareType.dao.getAllDecType());
		render("user-add.html");
	}
	
	//����Ա����û�
	public void adduser(){
		
		//����û����Ƿ����
		
		if(User.usermodel.find("select * from user where uname = ? limit 1",getPara("user.uname")) != null){
			setAttr("ErrMsg", "ǰ̨�û����Ѿ����ڣ�");
			render("error.html");
			return;
		}
		
		UploadFile upfile = getFile();
		
		if(upfile==null){
			setAttr("ErrMsg", "���ϴ�һ����Ƭ��");
			render("error.html");
			return;
		}
		
		String user_photo_name = upfile.getFileName();
		User user = getModel(User.class);
		String reg_time = DateUtils.getNowTime();
		String pwd = this.getPara("password");
		pwd = MD5.GetMD5Code(pwd+reg_time);
		user.setRegDate(reg_time);
		user.setPwd(pwd);
		user.setPhotoPath(user_photo_name);
		user.setUname(getPara("user.uname"));
		user.setStatus(1);
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
	
	//�����걨���
	public void adddec(){
		render("dec-add.html");
	}
	//����걨���ķ���
	public void decadd(){
		DeclareType dec = getModel(DeclareType.class);
		dec.set("decname", getPara("dec.name")).save();
		renderText("��ӳɹ���");
				
	}
	//ɾ�����
	public void deldec(){
		DeclareType dec = getModel(DeclareType.class);
		if(dec.dao.deleteById(getParaToInt(0))){
			renderText("1");
		}else{
			renderText("0");
		}
		
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
	
	public void mzadd(){
		render("mz-add.html");
	}
	
	public void delmz(){
		Mz mz = getModel(Mz.class);
		if(mz.dao.deleteById(getParaToInt(0))){
			renderText("1");
		}else{
			renderText("0");
		}
		
	}
	
	public void addmz(){
		Mz mz = getModel(Mz.class);
		mz.set("mzname", getPara("mz.mzname")).save();
		renderText("��ӳɹ���");
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
	
	public void delzzmm(){
		Zzmm zzmm = getModel(Zzmm.class);
		if(zzmm.dao.deleteById(getParaToInt(0))){
			renderText("1");
		}else{
			renderText("0");
		}
		
	}
	
	
	public void zzmmadd(){
		render("zzmm-add.html");
	}
	
	public void addzzmm(){
		Zzmm zzmm = getModel(Zzmm.class);
		zzmm.set("zzmmname", getPara("zzmm.zzmmname")).save();
		renderText("��ӳɹ���");
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
	
	//
	public void eduadd(){
		render("edu-add.html");
	}
	
	
	public void deledu(){
		Edu edu = getModel(Edu.class);
		if(edu.dao.deleteById(getParaToInt(0))){
			renderText("1");
		}else{
			renderText("0");
		}
	}
	
	public void addedu(){
		Edu edu  = getModel(Edu.class);
		edu.set("eduname",getPara("edu.eduname")).save();
		renderText("��ӳɹ���");
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
	
	public void deldegree(){
		Degree degree = getModel(Degree.class);
		if(degree.dao.deleteById(getParaToInt(0))){
			renderText("1");
		}else{
			renderText("0");
		}
	}
	
	
	public void degreeadd(){
		render("degree-add.html");
	}
	
	public void adddegree(){
		getModel(Degree.class).save();
		renderText("����ѧλ��Ϣ�ɹ���");
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
	
	public void delarea(){
		Area area = getModel(Area.class);
		if(area.dao.deleteById(getParaToInt(0))){
			renderText("1");
		}else{
			renderText("0");
		}
	}
	
	
	public void areaadd(){
		render("area-add.html");
	}
	
	public void addarea(){
		getModel(Area.class).save();
		renderText("���ӵ�����Ϣ�ɹ���");
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
		
		String full_time = "";
		String part_time = "";
		String full_time_school = "";
		String part_time_school = "";
		
		Map<String,String> map = User.usermodel.getEduDegreeSchool(user);
		
		setAttr("qrz",map.get("full_time"));
		setAttr("zz",map.get("part_time"));
		setAttr("qrz_school",map.get("full_time_school"));
		setAttr("zz_school",map.get("part_time_school"));
		render("user-check.html");
	}
	
	
	//ͨ�����
	public void examine(){
		getModel(User.class).set("id", getParaToInt(0)).set("status", 1).update();
		renderText("��˳ɹ���");
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
	
	
	//�༭����Ա��Ϣ
	public void editadmin(){
		Admin admin = Admin.dao.findById(getParaToInt(0));
		setAttr("admin", admin);
		render("admin-edit.html");
	}
	
	//����Ա��Ϣ����
	public void admininfoupdate(){
		Admin admin = Admin.dao.findById(getParaToInt(0));
		String create_time = admin.getCreateTime();
		
		String pwd = MD5.GetMD5Code(getPara("admin.pwd")+create_time);
		getModel(Admin.class)
		.set("id", admin.getId())
		.set("telephone", getPara("admin.telephone"))
		.set("email", getPara("admin.email"))
		.set("pwd", pwd)
		.update();
		admin = Admin.dao.findById(admin.getId());
		setSessionAttr(getCookie("cadmin"), admin);
		renderText("�޸ĳɹ���");
	}
	
	//��ӹ���Ա����
	public void adminadd(){
		//����ע��ʱ���Ĭ��ע��״̬
		//��ӹ���Աע�����Ʋ����ظ�
		String reg_date = DateUtils.getNowTime();
		String pwd = MD5.GetMD5Code(getPara("admin.pwd")+reg_date);
		
		getModel(Admin.class).set("pwd", pwd).set("create_time",reg_date).set("status", 1).save();
		renderText("��ӹ���Ա "+getPara("admin.name")+" �ɹ���");
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
		renderText("���ݳɹ�����ʹ��FTP���� ���ء���ַ��WebRoot\\download\\dbbackup\\"+file_name);
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		renderFile(new File(filename));
	}
	
	//����Ա�鿴�û�ͼƬ����
	public void userphoto(){
		
		User user = User.usermodel.findById(getPara(0));
		String path = PathKit.getWebRootPath() + "\\upload\\photo\\" + user.getMediaPath() + "\\";
		List<String> list = PathUtils.getAllFilePath(path);

		if(list==null){
			setAttr("ErrMsg", "���û�δ�ϴ�ͼƬ����!");
			render("error.html");
			return;
		}
		
		setAttr("picList", list);
		setAttr("user", user);
		render("u-photo.html");
	}
	
	//����Ա�鿴�û���Ƶ����
	public void useraudio(){
		
		User user = User.usermodel.findById(getPara(0));
		// int user_id = user.getId();
		String path = PathKit.getWebRootPath() + "\\upload\\audio\\" + user.getMediaPath() + "\\";
		List<String> list = PathUtils.getAllFilePath(path);

		if(list==null){
			setAttr("ErrMsg", "���û�δ�ϴ���Ƶ�ļ���");
			render("error.html");
			return;
		}
		
		setAttr("audioList", list);
		setAttr("user", user);
		
		render("u-audio.html");
	}
	//����Ա�鿴�û���Ƶ����
	public void uservideo(){
		
		User user = User.usermodel.findById(getPara(0));
		String path = PathKit.getWebRootPath() + "\\upload\\video\\" + user.getMediaPath() + "\\";
		List<String> list = PathUtils.getAllFilePath(path);
		
		//����ļ���Ϊ��
		if(list==null){
			setAttr("ErrMsg", "���û�δ�ϴ���Ƶ�ļ���");
			render("error.html");
			return;
		}
		
		setAttr("videoList", list);
		setAttr("user", user);
		
		render("u-video.html");
	}
	
	//��ӡĳ���˲���Ϣ
	public void userinfoprint(){
		User user = User.usermodel.findById(getParaToInt(0));
		if(DBUtils.RecordAttrHasNull(user)){
			setAttr("ErrMsg", "���˲ŵı�����Ϣ������,����������Ϣ��");
			render("error.html");
			return;
		}
		String file_name = RenderDocxTemplate.creatWord(user);
		renderFile(new File(file_name));
	}
	
	//�û�����Ƿ�ͨ������Ϣ
	public void msg(){
		
		if(!User.usermodel.UserIsChecked(getParaToInt(0))){
			setAttr("userid", getParaToInt(0));
			render("message.html");
			return;
		}
		setAttr("ErrMsg", "�Ѿ�ͨ����ˣ�");
		render("error.html");
	}
	
	//������Ϣ
	public void sendmsg(){
		
		try {
			int userId = getParaToInt("uid");
			String content = getPara("content");
			Admin admin = getSessionAttr(getCookie("cadmin"));
			int adminId = admin.getId();
			String adminName = admin.getName();
			String date = DateUtils.getNowTime();
			Msg msg = getModel(Msg.class);
			Msg m = Msg.dao.findByUserId(userId);
			msg.setUserId(userId);
			msg.setAdminId(adminId);
			msg.setAdminName(adminName);
			msg.setContent(content);
			msg.setDate(date);
			
			if(m!=null){
				msg.setId(m.getId());
				msg.update();
				renderText("1");
				return;
			}
			msg.save();
			renderText("1");
			return;
		} catch (Exception e) {
			renderText("0");
			return;
		}
		
		
	}
	
	
	
	
}
