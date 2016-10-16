package com.huijiasoft.interceptor;

import com.huijiasoft.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * @author pangPython
 *	�û�������Ȩ��������
 */
public class UserAuthInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		
			Controller controller = inv.getController();
			//ͨ���ͻ��������е�cookie��key��ȡcookie��value
			String cuser = controller.getCookie("cuser");
			//�ڷ�������session�в����Ƿ���ڵ�ǰ�û�
			User user = controller.getSessionAttr(cuser);
			if(user == null || inv.getMethodName().equals("login")){
				controller.redirect("/loginpage");
			}else{
				inv.invoke();
			}
			
	}

}
