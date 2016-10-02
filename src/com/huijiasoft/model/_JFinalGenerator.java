package com.huijiasoft.model;

import javax.sql.DataSource;

import com.huijiasoft.config.MyJFinalConfig;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * @author pangPython
 * �ο�JFinal�ٷ�
 */
public class _JFinalGenerator {
	public static DataSource getDataSource(){
		PropKit.use("myconfig.txt");
		C3p0Plugin c3p0Plugin = MyJFinalConfig.createC3p0Plugin();
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}

	public static void main(String[] args) {
		String baseModelPackageName = "com.huijiasoft.model.base";
		String baseModelOutputDir = PathKit.getWebRootPath()+"/../src/com/huijiasoft/model/base";
		String modelPackage = "com.huijiasoft.model";
		String modelOutputDir = baseModelOutputDir+"/..";
		Generator generator = new Generator(getDataSource(), baseModelPackageName,baseModelOutputDir,modelPackage,modelOutputDir);
		// ��Ӳ���Ҫ���ɵı���
		//generator.addExcludedTable("adv");
		// �����Ƿ��� Model ������ dao ����
		generator.setGenerateDaoInModel(true);
		// �����Ƿ������ֵ��ļ�
		generator.setGenerateDataDictionary(false);
		// ������Ҫ���Ƴ��ı���ǰ׺��������modelName��������� "osc_user"���Ƴ�ǰ׺ "osc_"�����ɵ�model��Ϊ "User"���� OscUser
		//generator.setRemovedTableNamePrefixes("t_");
		// ����
		generator.generate();
	}
	
}
