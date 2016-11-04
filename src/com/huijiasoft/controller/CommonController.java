package com.huijiasoft.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.render.CaptchaRender;

/**
 * @author pangPython
 * ��ȡһЩǰ��˶��õõ��Ĺ�������
 */

public class CommonController extends Controller {
	public void index() {
		renderText("common index");
	}
	//��֤������
	@ActionKey("/verifycode")
	public void verifycode(){
		render(new CaptchaRender());
	}
	
	public void test(){
		renderJson("{status:0}");
		
	}
	

}
