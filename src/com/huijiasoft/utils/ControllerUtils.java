package com.huijiasoft.utils;

import com.jfinal.core.Controller;

/**
 * @author pangPython
 *	������������
 */
public class ControllerUtils {
		//ͨ���ͻ�������ķ���session�д洢��model
		public static Object getMFromSbyIdinC(Controller controller,String cookieStr){
			 return controller.getSessionAttr(controller.getCookie(cookieStr));
		}
}
