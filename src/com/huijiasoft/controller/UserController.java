package com.huijiasoft.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import com.huijiasoft.interceptor.UserAuthInterceptor;
import com.huijiasoft.model.Area;
import com.huijiasoft.model.Degree;
import com.huijiasoft.model.Edu;
import com.huijiasoft.model.Mz;
import com.huijiasoft.model.UploadPhoto;
import com.huijiasoft.model.Uploads;
import com.huijiasoft.model.User;
import com.huijiasoft.model.Zzmm;
import com.huijiasoft.utils.ControllerUtils;
import com.huijiasoft.utils.DBUtils;
import com.huijiasoft.utils.DateUtils;
import com.huijiasoft.utils.MD5;
import com.huijiasoft.utils.PathUtils;
import com.huijiasoft.utils.RenderDocxTemplate;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.SessionIdKit;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.oreilly.servlet.MultipartRequest;

/**
 * @author pangPython
 *		�û�������
 */
@Before(UserAuthInterceptor.class)
public class UserController extends Controller {
	
	 private final int MAXSize = 50 * 1024 * 1024; // 5M
	
	public void index() {
		
		User user = getSessionAttr(getCookie("cuser"));
		setAttr("user",user);
		
		render("index.html");
	}
	
	//����
	public void welcome(){
		User user = getSessionAttr(getCookie("cuser"));
		setAttr("user", user);
		render("welcome.html");
	}
	
	
	//�û���Ϣ�鿴
	public void info() {
		User user = (User) getSessionAttr(getCookie("cuser"));
		
		setAttr("user", user);
		render("info.html");
	}
	//�Ѿ���¼�û����˳�����
	public void logout(){
		removeSessionAttr(getCookie("cuser"));
		removeCookie("cuser");
		redirect("/");
	}
	
	//������Ƶ��Ƶ����
	public void mediainfo(){
		User user = (User) getSession().getAttribute(getCookie("cuser"));
		setAttr("user", user);
		setAttr("mediaList", Uploads.dao.getAllMediaById(user.getId().toString()));
		render("/mediainfo.html");
	}
	
	
	public void edit(){
		User user = (User) getSession().getAttribute(getCookie("cuser"));
		
		setAttr("user", user);
		setAttr("areaList", Area.dao.getAllArea());
		setAttr("nationList", Mz.dao.getAllMz());
		setAttr("zzmmList", Zzmm.dao.getAllZzmm());
		setAttr("eduList", Edu.dao.getAllEdu());
		setAttr("degreeList", Degree.dao.getAllDegree());
		
		render("edit.html");
	}
	
	//�޸ĸ�����Ϣ
	public void updateinfo(){
		User user = (User) getSession().getAttribute(getCookie("cuser"));
		getModel(User.class).set("id", user.getId()).set("status",0).update();
		user = User.usermodel.findById(user.getId());
		setSessionAttr(getCookie("cuser"),user);
		renderText("�޸ĳɹ���");
	}
	
	
	public void media_upload(){
		
	}
	
	
	
	// �û��ϴ�ý���ļ�
	public void upload() {}
	
	//�ϴ�һ����Ƭ
	public void upload_person_photo(){
		User user =  getSessionAttr(getCookie("cuser"));
		user.setPhotoPath(getFile().getFileName());
		user.update();
		renderText("�ϴ��ɹ���");
		
	}
	
	public void media_pic(){
		User user = getSessionAttr(getCookie("cuser"));
		//int user_id = user.getId();
		String path = PathKit.getWebRootPath()+"\\upload\\photo\\"+user.getMediaPath()+"\\";
		List<String> list = PathUtils.getAllFilePath(path);
		
		setAttr("picList", list);
		setAttr("user", user);
		render("media_pic.html");
	}
	//����
	public void ttt(){
		User user = getSessionAttr(getCookie("cuser"));
		String path = PathKit.getWebRootPath()+"\\upload\\photo\\"+user.getMediaPath()+"\\";
		List<String> list = PathUtils.getAllFilePath(path);
		String str = "";
		
		for (int i = 0; i < list.size(); i++) {
			str += list.get(i);
		}
//		Iterator<String> it = list.iterator();
//		
//		String str = "";
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
		
		renderText(str);
	}
	
	//��ʾҳ��
	public void media_pic_upload(){}
	
	//��ͼƬ�ϴ�
	@ActionKey("/user/upload_pic")
	public void upload_pic() throws IOException{
		User user = getSessionAttr(getCookie("cuser"));

		String picture_upload_path = user.getMediaPath();
		
		//�����ϴ�·��
		if(!PathUtils.createNotExist(PathKit.getWebRootPath()+"\\upload\\photo\\"+picture_upload_path)){
			renderText("�ϴ�·������!");
			return;
		}
		
		//������ļ��ϴ�
		getFiles("\\photo\\"+picture_upload_path, MAXSize, "utf-8");
		
		renderText("�ϴ��ɹ���");
	
	}
	
	//��ʾ�û�����Ƿ�ͨ���Ĺ���Ա��Ϣ
	public void msg(){
		User user = getSessionAttr(getCookie("cuser"));
		if(user.getStatus()==1){
			render("msg-success.html");
			return;
		}else{
			render("msg-failed.html");
			return;
		}
	}
	
	
	public void media_audio(){
		
	}
	
public void media_audio_upload(){
		
	}
	
	public void media_video(){
		
	}
	
	
public void media_video_upload(){
		
	}

	

	
	
	public void application_std(){}
	public void application_my(){}
	
	//���ر�׼������
	@ActionKey("/user/download_std")
	public void download_std(){
		renderFile(new File("WebRoot\\download\\application\\��Ӫ���Ļ������˲���Ϣ�ǼǱ�.doc"));
	}
	
	public void download_my(){
		User user = getSessionAttr(getCookie("cuser"));
		
		if(DBUtils.RecordAttrHasNull(user)){
			renderText("��ı�����Ϣ������,����������Ϣ��");
			return;
		}
		
		String file_name = RenderDocxTemplate.creatWord(user);
		renderFile(new File(file_name));	
	}
	
	
	

	
	//��ʾ�޸�����ҳ��
	public void change_pwd(){
		User user = (User) ControllerUtils.getMFromSbyIdinC(this, "cuser");
		setAttr("user", user);
		render("change_pwd.html");
	}
	
	//�޸�����
	//TODO
	public void updatepwd(){
		
		User user = (User) ControllerUtils.getMFromSbyIdinC(this, "cuser");
		//��֤��
		boolean yzm = this.validateCaptcha("yzm");
		if(!yzm){
			
			setAttr("user", user);
			setAttr("yzmErrMsg", "����ȷ������֤�룡");
			render("change_pwd.html");
			return;
		}
		
		String reg_time = user.getRegDate();
		
		//��֤ԭ����
		String old_pwd = MD5.GetMD5Code(getPara("oldpwd")+reg_time);
		if(!user.getPwd().equals(old_pwd)){
			setAttr("user", user);
			setAttr("oldpwdErrMsg", "����ȷ����ԭ���룡");
			render("change_pwd.html");
			return;
		}
		
		//�ȶ�ȷ������		
		String new_pwd = getPara("newpwd");
		String confirm_new_pwd = getPara("confirmnewpwd");
		
		
		if(!new_pwd.equals(confirm_new_pwd)){
			setAttr("user", user);
			setAttr("confirmpwdErrMsg", "����ȷ���������룡");
			render("change_pwd.html");
			return;
		}
		
		//�¾������Ƿ���ͬ
		new_pwd = MD5.GetMD5Code(new_pwd+reg_time);
		
		if(new_pwd.equals(old_pwd)){
			setAttr("user", user);
			setAttr("newpwdErrMsg", "����������������ͬ��");
			render("change_pwd.html");
			return;
		}
		
		//�޸�����
		user.setPwd(new_pwd);
		user.update();
		renderText("������ĳɹ���");
		
	}
	
	
	
	
	

}
