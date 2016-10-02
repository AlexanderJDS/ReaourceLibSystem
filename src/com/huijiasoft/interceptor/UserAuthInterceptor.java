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
			User user = controller.getSessionAttr("user");
			
			if(user == null || inv.getMethodName().equals("login")){
				controller.redirect("/login");
			}else{
				inv.invoke();
			}
			
	}

}
