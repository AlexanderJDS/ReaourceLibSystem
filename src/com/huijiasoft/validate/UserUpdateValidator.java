package com.huijiasoft.validate;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;




/**
 * @author pangPython
 *		�����û���Ϣ��֤��
 */
public class UserUpdateValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequired("user.true_name", "nameErrMsg", "�û�������Ϊ�գ�");
		validateRequired("user.birth", "birthErrMsg", "�������²���Ϊ�գ�");
		validateRequired("user.join_work", "jwErrMsg", "�ϰ�ʱ�䲻��Ϊ�գ�");
		validateRequired("user.card", "cardErrMsg", "���֤�Ų���Ϊ�գ�");
		validateRequired("user.health", "hErrMsg", "����״������Ϊ�գ�");
		validateRequired("user.company", "comErrMsg", "��˾��Ϣ����Ϊ�գ�");
		validateRequired("user.company_tel", "comtelErrMsg", "��λ�绰����Ϊ�գ�");
		validateRequired("user.address", "addrErrMsg", "��ַ����Ϊ��");
		validateRequired("user.telephone", "telErrMsg", "�ƶ��绰����Ϊ�գ�");
		validateRequired("user.ysjj","ysjjErrMsg","������鲻��Ϊ��!");
		validateRequired("user.business_achievement","baErrMsg","ҵ��ɾͲ���Ϊ�գ�");
		validateRequired("user.awards","awsErrMsg","���������Ϊ�գ�");
		validateRequired("user.opinion","opErrMsg","���������������Ϊ�գ�");
	}

	@Override
	protected void handleError(Controller c) {
		c.renderText("��������д�����ֶΣ�");
	}

}
