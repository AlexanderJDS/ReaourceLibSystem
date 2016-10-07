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
		
		//��֤����
		boolean result = validateCaptcha("verifycode");
		
		String sql = "select * from user where uname = ? and pwd = ? limit 1";
		
		User user = User.usermodel.findFirst(sql,uname,pwd);
		//��¼�ɹ�
		if(user!=null && result){
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
	
	public void register(){
		render("regist.html");
	}
	
	
	@ActionKey("regist")
	@Before(RegistValidator.class)
	public void regist(){
		//ʹ�ù��߰��ѵ�ǰʱ��ת����unixʱ�����ת����string����
		//ע��ʱ�䣬����Ϊ�û�����md5���ܵ�salt
		String reg_date = DateUtils.unixTimestampToDate(DateUtils.dateToUnixTimestamp(DateUtils.getNowTime()));
		
		//ʹ��jfinal��ʶ���ɹ��������������Ϊ�������
		
		String pwd = MD5.GetMD5Code(getPara("user.pwd")+reg_date);
		
		System.out.println(pwd+"md5 ���룺��");
		
		User user = this.getModel(User.class);
		
		user.setRegDate(reg_date);
		user.setPwd(pwd);
		user.save();
		//getModel(User.class).save();
		renderText("ע��ɹ�!");
	}
	
	
}
