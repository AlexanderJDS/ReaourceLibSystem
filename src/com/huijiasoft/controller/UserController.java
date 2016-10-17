package com.huijiasoft.controller;

import java.util.List;

import com.huijiasoft.interceptor.UserAuthInterceptor;
import com.huijiasoft.model.Area;
import com.huijiasoft.model.DeclareType;
import com.huijiasoft.model.Degree;
import com.huijiasoft.model.Edu;
import com.huijiasoft.model.Mz;
import com.huijiasoft.model.Uploads;
import com.huijiasoft.model.User;
import com.huijiasoft.model.Zzmm;
import com.huijiasoft.utils.DateUtils;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

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
	
	
	//�û����ķ���
	public void center() {
		User user = (User) getSession().getAttribute(getCookie("cuser"));
		
		setAttr("user", user);
		render("/center.html");
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
		render("/editinfo.html");
	}
	
	@ActionKey("/adduserinfo")
	public void adduserinfo(){
		User user = (User) getSession().getAttribute(getCookie("cuser"));
		getModel(User.class).set("id", user.getId()).set("status", 0).update();
		setAttr("user", user);
		render("/sbmtsucc.html");
	}
	
	@ActionKey("/adduserinfopage")
	public void adduserinfopage(){
		User user = (User) getSession().getAttribute(getCookie("cuser"));
		
		setAttr("user", user);
		List<Area> area = Area.dao.getAllArea();
		List<Mz> minzu = Mz.dao.getAllMz();
		List<Zzmm> zzmmList = Zzmm.dao.getAllZzmm();
		List<Edu> eduList = Edu.dao.getAllEdu();
		List<Degree> degreeList = Degree.dao.getAllDegree();
		List<DeclareType> decList = DeclareType.dao.getAllDecType();
		
		setAttr("area", area);
		setAttr("minzuList",minzu);
		setAttr("zzmmList", zzmmList);
		setAttr("eduList", eduList);
		setAttr("degreeList",degreeList);
		setAttr("decList", decList);
		render("/adduserinfo.html");
	}
	
	
	// �û��ϴ�ý���ļ�
	public void upload() {
		// �����ϴ��ļ�
		List<UploadFile> upFiles = getFiles("./", MAXSize, "utf-8");
		// д�����ݿ�
		User user = getSessionAttr(getCookie("cuser"));
		int user_id = user.getId();
		
		
		//�����ļ�list
		Uploads uploads = getModel(Uploads.class);
		for (int i = 0; i < upFiles.size(); i++) {
			uploads.setUserId(user_id);
			uploads.setPath(upFiles.get(i).getFileName());
			uploads.setType("1");
			uploads.setCreateTime(DateUtils.getNowTime());
			uploads.save();
			
		}
	
		// ��ʾͼƬ
		setAttr("user", user);
		redirect("showmedia?id="+user.getId());
	}
	
	//�ϴ�һ�������
	public void uploadphoto(){
		UploadFile file = getFile();
		
		if(file == null){
			render("sbmtsucc.html");
		}
		//������Ƭ·��
		User user = getSessionAttr(getCookie("cuser"));
		user.setPhotoPath(file.getFileName());
		user.update();
		
		
		redirect("showmedia");
		
	}
	
	
	//����ý������չʾҳ��
	public void showmedia(){
		User user = getSessionAttr(getCookie("cuser"));
		setAttr("user", user);
		setAttr("mediaList", Uploads.dao.getAllMediaById(getPara("id")));
		
		render("/showmedia.html");
	}
	
	
	public void baomingbiao(){
		int uid = getParaToInt("id");
		User user = User.usermodel.findById_Relation(uid);
		String mzname = user.getStr("mzname");
		setAttr("user", user);
		setAttr("mz", mzname);
		setAttr("zzmm", user.getStr("zzmmname"));
		render("/baomingbiao.html");
	}
	
	
	

}
