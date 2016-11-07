package com.huijiasoft.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.huijiasoft.model.Degree;
import com.huijiasoft.model.Edu;
import com.huijiasoft.model.Mz;
import com.huijiasoft.model.User;
import com.huijiasoft.model.Zzmm;
import com.jfinal.kit.PathKit;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;



/**
 * @author pangPython
 *	ʹ��FreeMarker xmlģ������word������
 */
public class RenderDocxTemplate {

	
	
	public static String creatWord(User user) {
		
		String file_name = null;
		
		try {
			//��������ʵ��
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("utf-8");
			cfg.setDirectoryForTemplateLoading(new File(PathKit.getWebRootPath()));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			//��ȡģ��
			Template temp = cfg.getTemplate("1.ftl");
			temp.setEncoding("utf-8");
			
			//��������ģ��
			Map<String, String> root = new HashMap<String, String>();
			
			root.put("true_name", user.getTrueName());
			String sex = "��";
			if("0".equals(user.getUsersex())){
				sex = "Ů";
			}
			
			
			root.put("sex", sex);
			root.put("birth", user.getBirth()+"");
			root.put("minzu", Mz.dao.getMzNameById(user.getMzId()));
			root.put("zzmm", Zzmm.dao.getZzmmNameById(user.getZzmmId()));
			root.put("join_work", user.getJoinWork());
			root.put("card",user.getCard());
			root.put("health", user.getHealth());
			root.put("zyzw", user.getTechnicalPosition());
			
			root.put("email", user.getEmail());
			root.put("qqwx", user.getQqwx());
			
			root.put("fedu", Edu.dao.getEduNameById(user.getFEduId()));
			root.put("fdegree",Degree.dao.getDegreeNameById(user.getFDegreeId()));
			root.put("feduscho", user.getFEduSchool());
			root.put("fdegreescho", user.getFDegreeSchool());
			
			root.put("pedu", Edu.dao.getEduNameById(user.getPEduId()));
			root.put("pdegree",Degree.dao.getDegreeNameById(user.getPDegreeId()));
			root.put("peduscho", user.getPEduSchool());
			root.put("pdegreescho", user.getPDegreeSchool());
			
			
			root.put("company", user.getCompany());
			root.put("com_tel", user.getCompanyTel());
			root.put("address",user.getAddress());
			root.put("tel", user.getTelephone());
			root.put("shehuijianzhi", user.getSocioPartTime());
			root.put("yishujianjie",user.getYsjj());
			root.put("yewuchengjiu",user.getBusinessAchievement());
			root.put("huojiang", user.getAwards());
			root.put("shenqingyijian", user.getOpinion());
			
			root.put("pic", PhotoUtils.getImageStr(PathKit.getWebRootPath()+"\\upload\\"+user.getPhotoPath()));
			
			//��ģ�������ģ�ͺϲ������ļ�
			file_name = PathKit.getWebRootPath()+"\\download\\application\\my\\"+user.getTrueName()+".doc";
			File docFile = new File(file_name);
			Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile),"UTF-8"));
			temp.process(root, docout);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
}
