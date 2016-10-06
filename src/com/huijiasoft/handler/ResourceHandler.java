package com.huijiasoft.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jfinal.kit.HandlerKit;

/**
 * @author pangPython
 *	��ֱֹ�ӷ���ģ���ļ��Ĺ�����
 *
 */

public class ResourceHandler extends Handler {
	
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		System.out.println("target: "+target);
		if(isDisableAccess(target)){
			HandlerKit.renderError404(request, response, isHandled);
		}
		next.handle(target, request, response, isHandled);
	}

	private static boolean isDisableAccess(String target) {
		String suffixHtml = ".html";
		String suffixFTL  = ".ftl";
		// ��ֱֹ�ӷ���ģ���ļ�
		if (target.endsWith(suffixHtml) || target.endsWith(suffixFTL)) {
			return true;
		}

		return false;
}
	

}
