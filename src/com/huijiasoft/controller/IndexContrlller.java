package com.huijiasoft.controller;



import com.huijiasoft.model.User;
import com.huijiasoft.service.IndexService;
import com.huijiasoft.utils.DateUtils;
import com.huijiasoft.utils.MD5;
import com.huijiasoft.validate.RegistValidator;
import com.jfinal.aop.Before;
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
		redirect("loginpage");
	}
	
	
	public void loginpage(){
		String cuser = getCookie("cuser");
		if(cuser!=null){
			redirect("user");
		}
		
			setAttr("system", IndexService.getSysConfig());
			render("login.html");
	}
	
	//ǰ̨��¼����
	public void login(){
		//����������cookie�л�ȡ��ǰ��¼�û�
		String cuser = getCookie("cuser");
		User u = getSessionAttr(cuser);
		
		if(u!=null){
			redirect("/user");
		}
		
		
		
		//��֤����
		boolean result = validateCaptcha("verifycode");
		if(!result){
			setAttr("yzmErrMsg", "��֤�����");
			render("login.html");
			return;
		}
		
		String uname = getPara("uname");
		String sql = "select * from user where uname = ? limit 1";

		User user = User.usermodel.findFirst(sql,uname);
		if(user!=null){
			String pwd = MD5.GetMD5Code(getPara("password")+user.getRegDate());
			
			if(user.getPwd().equals(pwd)){
				
				//����Ψһ��ʶ
				String sessionId = SessionIdKit.me().generate(getRequest());
				//���÷�������session
				setSessionAttr(sessionId, user);
				//�����û���cookie
				setCookie("cuser", sessionId, 600);
				redirect("/user");
				
			}else{
				//���벻��ȷ
				redirect("loginpage");
				
			}
			
		}else{
			//�û���������
			redirect("loginpage");
		}
		
		
	}
	
	//ע��
	
	public void register(){
		render("regist.html");
	}
	
	
	@ActionKey("regist")
	@Before(RegistValidator.class)
	public void regist(){
		String mima = getPara("user.pwd");
		String confirm = getPara("reg.confirmpwd");
		
		if(!confirm.equals(mima)){
			setAttr("confirmMsg", "���ٴ�������ȷ���룡");
			return;
		}
		
		
		String uname = getPara("user.uname");
		User user = this.getModel(User.class);
		//����û����Ѿ���ע�ᣬ��ʾ������Ϣ
		if(user.findFirst("select * from user where uname = ?",uname)!=null){
			setAttr("unameMsg", "���û����ѱ�ע��");
			render("regist.html");
		}else{
		//ʹ�ù��߰��ѵ�ǰʱ��ת����unixʱ�����ת����string����
		//ע��ʱ�䣬����Ϊ�û�����md5���ܵ�salt
		String reg_date = DateUtils.getNowTime();
		
		//ʹ��jfinal��ʶ���ɹ��������������Ϊ�������
		
		String pwd = MD5.GetMD5Code(getPara("user.pwd")+reg_date);
		
		
		user.setRegDate(reg_date);
		user.setPwd(pwd);
		user.setStatus(0);
		user.save();
		
		//����Ψһ��ʶ
		String sessionId = SessionIdKit.me().generate(getRequest());
		//���÷�������session
		setSessionAttr(sessionId, user);
		//�����û���cookie
		setCookie("cuser", sessionId, 600);
		
		setAttr("user", user);
		//getModel(User.class).save();
		redirect("/adduserinfopage");
		}
	}
	
	
}
