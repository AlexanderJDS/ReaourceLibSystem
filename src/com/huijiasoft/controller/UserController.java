package com.huijiasoft.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.huijiasoft.interceptor.UserAuthInterceptor;
import com.huijiasoft.model.Area;
import com.huijiasoft.model.DeclareType;
import com.huijiasoft.model.Degree;
import com.huijiasoft.model.Edu;
import com.huijiasoft.model.Msg;
import com.huijiasoft.model.Mz;
import com.huijiasoft.model.Uploads;
import com.huijiasoft.model.User;
import com.huijiasoft.model.Zzmm;
import com.huijiasoft.utils.ControllerUtils;
import com.huijiasoft.utils.DBUtils;
import com.huijiasoft.utils.MD5;
import com.huijiasoft.utils.PathUtils;
import com.huijiasoft.utils.RenderDocxTemplate;
import com.huijiasoft.validate.UserUpdateValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;

/**
 * @author pangPython �û�������
 */
@Before(UserAuthInterceptor.class)
public class UserController extends Controller {

	private final int MAXSize = 50 * 1024 * 1024; // 5M

	public void index() {

		User user = getSessionAttr(getCookie("cuser"));
		setAttr("user", user);

		render("index.html");
	}

	// ����
	public void welcome() {
		User user = getSessionAttr(getCookie("cuser"));
		setAttr("user", user);
		render("welcome.html");
	}

	// �û���Ϣ�鿴
	public void info() {
		User user = (User) getSessionAttr(getCookie("cuser"));
		String area = " ";
		String mz = " ";
		String zzmm = " ";
		String dec = " ";
		String fedu = " ";
		String pedu = " ";
		String fdegree = " ";
		String pdegree = " ";
		String feduscho = " ";
		String peduscho = " ";
		String fdegreescho =" ";
		String pdegreescho = " ";
		
		setAttr("user", user);
		
		try {
			area = Area.dao.getAreaNameById(user.getAreaId());
			mz = Mz.dao.getMzNameById(user.getMzId());
			zzmm = Zzmm.dao.getZzmmNameById(user.getZzmmId());
			dec = DeclareType.dao.getDecNameById(user.getDecId());
			
			fedu = Edu.dao.getEduNameById(user.getFEduId());
			pedu = Edu.dao.getEduNameById(user.getPEduId());
			fdegree = Degree.dao.getDegreeNameById(user.getFDegreeId());
			pdegree = Degree.dao.getDegreeNameById(user.getPDegreeId());
			feduscho = user.getFEduSchool();
			peduscho = user.getPEduSchool();
			fdegreescho = user.getFDegreeSchool();
			pdegreescho = user.getPDegreeSchool();
					
		} catch (Exception e) {
		}
		
		setAttr("fedu", fedu);
		setAttr("pedu", pedu);
		setAttr("fdegree", fdegree);
		setAttr("pdegree", pdegree);
		setAttr("feduscho", feduscho);
		setAttr("peduscho", peduscho);
		setAttr("fdegreescho", fdegreescho);
		setAttr("pdegreescho", pdegreescho);
		
		setAttr("area", area);
		setAttr("mz", mz);
		setAttr("zzmm", zzmm);
		setAttr("dec", dec);
		render("info.html");
		
	}

	// �Ѿ���¼�û����˳�����
	public void logout() {
		removeSessionAttr(getCookie("cuser"));
		removeCookie("cuser");
		redirect("/");
	}

	// ������Ƶ��Ƶ����
	public void mediainfo() {
		User user = (User) getSession().getAttribute(getCookie("cuser"));
		setAttr("user", user);
		setAttr("mediaList", Uploads.dao.getAllMediaById(user.getId().toString()));
		render("/mediainfo.html");
	}

	
	//�û�������Ϣ
	
	public void edit() {
		User user = (User) getSession().getAttribute(getCookie("cuser"));

		setAttr("user", user);
		setAttr("areaList", Area.dao.getAllArea());
		setAttr("nationList", Mz.dao.getAllMz());
		setAttr("zzmmList", Zzmm.dao.getAllZzmm());
		setAttr("eduList", Edu.dao.getAllEdu());
		setAttr("degreeList", Degree.dao.getAllDegree());
		setAttr("decList", DeclareType.dao.getAllDecType());

		render("edit.html");
	}

	// �޸ĸ�����Ϣ
	@Before(UserUpdateValidator.class)
	@ActionKey("/user/updateinfo")
	public void updateinfo() {
		//�쳣����
		String media_path = " ";
		
		try {
			media_path = getFile().getFileName();
		} catch (Exception e) {
			
		}
		
		User user = (User) getSession().getAttribute(getCookie("cuser"));

		String f_edu_school = " ";
		String f_degree_school = " ";
		String p_edu_school = " ";
		String p_degree_school = " ";
		
		int f_edu_id = getParaToInt("user.f_edu_id");
		int f_degree_id = getParaToInt("user.f_degree_id");
		int p_edu_id = getParaToInt("user.p_edu_id");
		int p_degree_id = getParaToInt("user.p_degree_id");
		
		try {
			if(f_edu_id!=0){
				f_edu_school = getPara("user.f_edu_school");
			}
			if(f_degree_id!=0){
				f_degree_school = getPara("user.f_degree_school");
			}
			if(p_edu_id!=0){
				p_edu_school = getPara("user.p_edu_school");
			}
			if(p_degree_id!=0){
				p_degree_school = getPara("user.p_degree_school");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		User u = getModel(User.class);
		u.set("id", user.getId());
		u.set("status", 0);
		u.setPhotoPath(media_path);
		
		u.setFDegreeId(f_degree_id);
		u.setFEduId(f_edu_id);
		u.setPDegreeId(p_degree_id);
		u.setPEduId(p_edu_id);
		
		u.setFDegreeSchool(f_degree_school);
		u.setFEduSchool(f_edu_school);
		u.setPDegreeSchool(p_degree_school);
		u.setPEduSchool(p_edu_school);
		
		
		u.update();
		
		user = User.usermodel.findById(user.getId());
		setSessionAttr(getCookie("cuser"), user);
		renderText("��Ϣ��д�ɹ�,���Զ��ύ��ˣ�");
	}

//�ϴ�·�����ļ�ϵͳ�ָ���Ҫ����ƽ̨linux--/  windows--\ 
	
	//��ʾ�û��ϴ���ͼƬ�ļ�
	public void media_pic() {
		User user = getSessionAttr(getCookie("cuser"));
		String path = PathKit.getWebRootPath() + "\\upload\\photo\\" + user.getMediaPath() + "\\";
		List<String> list = PathUtils.getAllFilePath(path);

		if(list==null){
			setAttr("ErrMsg", "��δ�ϴ�ͼƬ����!");
			render("error.html");
			return;
		}
		
		setAttr("picList", list);
		setAttr("user", user);
		render("media_pic.html");
	}



	// ��ʾҳ��
	public void media_pic_upload() {
	}

	// ��ͼƬ�ϴ�
	@ActionKey("/user/uploadpic")
	public void uploadpic() throws IOException {
		User user = getSessionAttr(getCookie("cuser"));

		String picture_upload_path = user.getMediaPath();

		// �����ϴ�·��
		if (!PathUtils.createNotExist(PathKit.getWebRootPath() + "\\upload\\photo\\" + picture_upload_path)) {
			setAttr("ErrMsg", "�ϴ�·������!");
			render("error.html");
			return;
		}

		// ������ļ��ϴ�
		getFiles("\\photo\\" + picture_upload_path, MAXSize, "utf-8");

		renderText("ͼƬ�����ϴ��ɹ���");

	}
	
	//ɾ��ͼƬ
	public void delpic(){
		boolean flag = false;
		User user = User.usermodel.findById(getPara("uid"));
		//��ȡͼƬ·��
		String upath = PathKit.getWebRootPath()+"\\upload\\photo\\"+user.getMediaPath()+"\\";
		//��ȡ�û��ύ��Ҫɾ�����ļ���
		String[] pic_name = getParaValues("filename[]");
		File file = null;
		
		if(pic_name==null){
			renderNull();
			return;
		}
		
		for (int i = 0; i < pic_name.length; i++) {
			file = new File(upath+pic_name[i]);
			
			if(!file.exists()){
				//�ļ�������
				renderJson("{\"status\":0,\"errmsg\":\"�ļ������ڣ�\"}");
				return;
			}else{
				if(!file.isFile()){
					//�����ļ�
					renderJson("{\"status\":0,\"errmsg\":\"�����ļ���\"}");
					return;
				}
					if(file.delete()){
						flag = true;
					}else{
						flag = false;
					}
			}
			
		}
		
		if(flag){
			renderJson("{\"status\":1}");
		}else{
			renderJson("{\"status\":0,\"errmsg\":\"δ֪����\"}");
		}
	}
	
	//ɾ����Ƶ�ļ�
	public void delaudio(){

		boolean flag = false;
		User user = User.usermodel.findById(getPara("userid"));
		//��ȡͼƬ·��
		String upath = PathKit.getWebRootPath()+"\\upload\\audio\\"+user.getMediaPath()+"\\";
		//��ȡ�û��ύ��Ҫɾ�����ļ���
		String audio_name = getPara("filename");
		File file = null;
		
			file = new File(upath+audio_name);
			
			if(!file.exists()){
				//�ļ�������
				renderJson("{\"status\":0,\"errmsg\":\"�ļ������ڣ�\"}");
				return;
			}else{
				if(!file.isFile()){
					//�����ļ�
					renderJson("{\"status\":0,\"errmsg\":\"�����ļ���\"}");
					return;
				}
					if(file.delete()){
						flag = true;
					}else{
						flag = false;
					}
			}
			

		
		if(flag){
			renderJson("{\"status\":1}");
		}else{
			renderJson("{\"status\":0,\"errmsg\":\"δ֪����\"}");
		}
	}
	
	
	//ɾ����Ƶ�ļ�
	public void delvideo(){

		boolean flag = false;
		User user = User.usermodel.findById(getPara("userid"));
		//��ȡͼƬ·��
		String upath = PathKit.getWebRootPath()+"\\upload\\video\\"+user.getMediaPath()+"\\";
		//��ȡ�û��ύ��Ҫɾ�����ļ���
		String audio_name = getPara("filename");
		File file = null;
		
			file = new File(upath+audio_name);
			
			if(!file.exists()){
				//�ļ�������
				renderJson("{\"status\":0,\"errmsg\":\"�ļ������ڣ�\"}");
				return;
			}else{
				if(!file.isFile()){
					//�����ļ�
					renderJson("{\"status\":0,\"errmsg\":\"�����ļ���\"}");
					return;
				}
					if(file.delete()){
						flag = true;
					}else{
						flag = false;
					}
			}
			

		
		if(flag){
			renderJson("{\"status\":1}");
		}else{
			renderJson("{\"status\":0,\"errmsg\":\"δ֪����\"}");
		}
	}

	// ��ʾ�û�����Ƿ�ͨ���Ĺ���Ա��Ϣ
	public void msg() {
		User user = getSessionAttr(getCookie("cuser"));
		if (user.getStatus() == 1) {
			render("msg-success.html");
			return;
		} else {
			try {
				//���δͨ��,Ҫ��ʾʧ����Ϣ
				Msg m = Msg.dao.findByUserId(user.getId());
				
				if(m==null){
					renderText("���ύ���Ϻ����ĵȴ����...");
					return;
				}
				setAttr("Msg", m);
				render("msg-failed.html");
				return;
			} catch (Exception e) {
				//������Ա��δ���ʱ
				renderText("���ύ���Ϻ����ĵȴ����...");
				return;
			}
		}
	}

	//��ʾ�û��ϴ�����Ƶ�ļ�
	public void media_audio() {
		User user = getSessionAttr(getCookie("cuser"));
		// int user_id = user.getId();
		String path = PathKit.getWebRootPath() + "\\upload\\audio\\" + user.getMediaPath() + "\\";
		List<String> list = PathUtils.getAllFilePath(path);

		if(list==null){
			setAttr("ErrMsg", "��δ�ϴ���Ƶ����!");
			render("error.html");
			return;
		}
		
		setAttr("audioList", list);
		setAttr("user", user);
		render("media_audio.html");
	}

	public void media_audio_upload() {
		
	}
	
	public void upload_audio() {
		User user = getSessionAttr(getCookie("cuser"));

		String picture_upload_path = user.getMediaPath();

		// �����ϴ�·��
		if (!PathUtils.createNotExist(PathKit.getWebRootPath() + "\\upload\\audio\\" + picture_upload_path)) {
			setAttr("ErrMsg", "�ϴ�·������!");
			render("error.html");
			return;
		}

		// ������ļ��ϴ�
		getFiles("\\audio\\" + picture_upload_path, MAXSize, "utf-8");

		renderText("��Ƶ�����ϴ��ɹ���");

	}

	public void media_video() {
		User user = getSessionAttr(getCookie("cuser"));
		// int user_id = user.getId();
		String path = PathKit.getWebRootPath() + "\\upload\\video\\" + user.getMediaPath() + "\\";
		List<String> list = PathUtils.getAllFilePath(path);
		
		//����ļ���Ϊ��
		if(list==null){
			setAttr("ErrMsg", "��δ�ϴ���Ƶ�ļ���");
			render("error.html");
			return;
		}
		
		setAttr("videoList", list);
		setAttr("user", user);
		render("media_video.html");

	}

	public void media_video_upload() {
		
	}
	
	public void upload_video(){
		User user = getSessionAttr(getCookie("cuser"));

		String picture_upload_path = user.getMediaPath();

		// �����ϴ�·��
		if (!PathUtils.createNotExist(PathKit.getWebRootPath() + "\\upload\\video\\" + picture_upload_path)) {
			setAttr("ErrMsg", "�ϴ�·������!");
			render("error.html");
			return;
		}

		// ������ļ��ϴ�
		getFiles("\\video\\" + picture_upload_path, MAXSize, "utf-8");

		renderText("��Ƶ�����ϴ��ɹ���");
	}

	public void application_std() {
	}

	public void application_my() {
	}

	// ���ر�׼������
	@ActionKey("/user/download_std")
	public void download_std() {
		
		renderFile(new File("WebRoot\\1.doc"));
	}

	public void download_my() {
		User user = getSessionAttr(getCookie("cuser"));

		if (DBUtils.RecordAttrHasNull(user)) {
			setAttr("ErrMsg", "��ı�����Ϣ������,����������Ϣ��");
			render("error.html");
			return;
		}

		String file_name = RenderDocxTemplate.creatWord(user);
		renderFile(new File(file_name));
	}

	// ��ʾ�޸�����ҳ��
	public void change_pwd() {
		User user = (User) ControllerUtils.getMFromSbyIdinC(this, "cuser");
		setAttr("user", user);
		render("change_pwd.html");
	}

	// �޸�����
	// TODO
	public void updatepwd() {

		User user = (User) ControllerUtils.getMFromSbyIdinC(this, "cuser");
		// ��֤��
		boolean yzm = this.validateCaptcha("yzm");
		if (!yzm) {

			setAttr("user", user);
			setAttr("yzmErrMsg", "����ȷ������֤�룡");
			render("change_pwd.html");
			return;
		}

		String reg_time = user.getRegDate();

		// ��֤ԭ����
		String old_pwd = MD5.GetMD5Code(getPara("oldpwd") + reg_time);
		if (!user.getPwd().equals(old_pwd)) {
			setAttr("user", user);
			setAttr("oldpwdErrMsg", "����ȷ����ԭ���룡");
			render("change_pwd.html");
			return;
		}

		// �ȶ�ȷ������
		String new_pwd = getPara("newpwd");
		String confirm_new_pwd = getPara("confirmnewpwd");

		if (!new_pwd.equals(confirm_new_pwd)) {
			setAttr("user", user);
			setAttr("confirmpwdErrMsg", "����ȷ���������룡");
			render("change_pwd.html");
			return;
		}

		// �¾������Ƿ���ͬ
		new_pwd = MD5.GetMD5Code(new_pwd + reg_time);

		if (new_pwd.equals(old_pwd)) {
			setAttr("user", user);
			setAttr("newpwdErrMsg", "����������������ͬ��");
			render("change_pwd.html");
			return;
		}

		// �޸�����
		user.setPwd(new_pwd);
		user.update();
		renderText("������ĳɹ���");

	}

}
