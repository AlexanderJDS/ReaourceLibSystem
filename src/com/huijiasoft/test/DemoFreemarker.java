package com.huijiasoft.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class DemoFreemarker {

	public static void main(String[] args) {
		creatWord();
	}
	
	public static void creatWord() {
		try {
			//��������ʵ��
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("utf-8");
			cfg.setDirectoryForTemplateLoading(new File("E://"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			//��ȡģ��
			Template temp = cfg.getTemplate("1.ftl");
			temp.setEncoding("utf-8");
			
			//��������ģ��
			Map<String, String> root = new HashMap<String, String>();
			root.put("true_name", "�ַ�������");
			//��ģ�������ģ�ͺϲ������ļ�
			File docFile = new File("demo.doc");
			Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile),"UTF-8"));
			temp.process(root, docout);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
