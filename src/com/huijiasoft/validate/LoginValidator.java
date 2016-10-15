package com.huijiasoft.validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;;


/**
 * @author pangPython
 *
 */
public class LoginValidator extends Validator {

	@Override
	protected void handleError(Controller arg0) {
		
	}

	@Override
	protected void validate(Controller ctlr) {
		validateRequiredString("login.name", "UnameErrMsg", "�������û�����");
		validateRequiredString("login.password", "PwdErrMsg", "���������룡");
		validateRequiredString("login.verifycode", "yzmErrMsg", "��������֤�룡");
		
	}

}
