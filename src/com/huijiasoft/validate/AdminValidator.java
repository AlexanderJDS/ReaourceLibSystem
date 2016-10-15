package com.huijiasoft.validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * @author pangPython
 *
 */
public class AdminValidator extends Validator{

	@Override
	protected void handleError(Controller arg0) {
		// 
		
	}

	@Override
	protected void validate(Controller arg0) {
		validateRequiredString("admin.name", "LoginNameMsg","�������û�����");
		validateRequiredString("admin.pwd", "LoginPwdMsg","���������룡");
		validateCaptcha("verifycode", "codeMsg", "����ȷ������֤�룡");
	}

}
