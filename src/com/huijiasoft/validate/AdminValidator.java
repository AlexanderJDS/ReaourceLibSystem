package com.huijiasoft.validate;

import com.huijiasoft.model.Admin;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * @author pangPython
 *
 */
public class AdminValidator extends Validator{

	@Override
	protected void handleError(Controller controller) {
		controller.keepModel(Admin.class);
	}

	@Override
	protected void validate(Controller arg0) {
		validateRequiredString("admin.name", "LoginNameMsg","�������û�����");
		validateRequiredString("admin.pwd", "LoginPwdMsg","���������룡");
		validateRequiredString("verifycode", "codeMeg", "��������֤�룡");
		//validateCaptcha("verifycode", "codeMsg", "����ȷ������֤�룡");
	}

}
