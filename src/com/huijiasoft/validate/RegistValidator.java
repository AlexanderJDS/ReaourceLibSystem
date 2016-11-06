package com.huijiasoft.validate;

import com.huijiasoft.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;


/**
 * @author pangPython
 *  �û�ע����֤�� 
 */
public class RegistValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		//�Ƿ�Ϊ����֤
		validateRequired("user.uname", "unameMsg", "�������û���!");
		validateRequired("user.pwd", "pwdMsg", "����������!");
		validateRequired("reg.confirmpwd", "confirmMsg", "��������һ������!");
		validateRequired("reg.verifycode", "vcMsg", "��������֤��!");
		
		//validateString("user.pwd", 6, 20, "pwdMsg", "�������6λ,�20λ��");
		
	}

	@Override
	protected void handleError(Controller c) {
			c.keepModel(User.class);
	}

}
