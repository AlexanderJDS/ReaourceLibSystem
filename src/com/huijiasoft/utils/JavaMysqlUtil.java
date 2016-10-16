package com.huijiasoft.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jfinal.kit.PropKit;

public class JavaMysqlUtil {

	 /**
     * 
     * mysql���ݱ��� ���սű����������ش�·��
     * 
     * sqlΪ���ݵĽű�������xxx.sql
     * 
     */
    //�������ݿ�
    public static void backup(String sql) {
    
    	PropKit.use("prop.properties");
    
     // �����Ƕ�ȡ�������ļ���Ҳ����ֱ��ʹ��
    
     String username = PropKit.get("username");
    
     String password = PropKit.get("password");
    
     // �õ�MYSQL���û����������� mysql �� cmd:
    
     String mysqlpaths = PropKit.get("mysqlpath");
     String databaseName = PropKit.get("databaseName");
     String address = PropKit.get("address");
     String sqlpath = PropKit.get("sql");
     File backupath = new File(sqlpath);
     if (!backupath.exists()) {
      backupath.mkdir();
     }
    
     StringBuffer sb = new StringBuffer();
    
     sb.append(mysqlpaths);
           sb.append("mysqldump ");
     sb.append("--opt ");
     sb.append("-h ");
     sb.append(address);
     sb.append(" ");
     sb.append("--user=");
     sb.append(username);
     sb.append(" ");
     sb.append("--password=");
     sb.append(password);
     sb.append(" ");
     sb.append("--lock-all-tables=true ");
     sb.append("--result-file=");
     sb.append(sqlpath);
     sb.append(sql);
     sb.append(" ");
     sb.append("--default-character-set=utf8 ");
     sb.append(databaseName);
     Runtime cmd = Runtime.getRuntime();
     try {
      Process p = cmd.exec(sb.toString());
     } catch (IOException e) {
      e.printStackTrace();
     }
    
    }
    
    // ��ȡ����ֵ
    
    public static Properties getPprVue(String properName) {
    
     InputStream inputStream = JavaMysqlUtil.class.getClassLoader().getResourceAsStream(properName);
     Properties p = new Properties();
    
     try {
      p.load(inputStream);
      inputStream.close();
     } catch (IOException e) {
      e.printStackTrace();
     }
    
     return p;
    
    }
    //��ԭ����
    public static void load(String filename) {
     Properties pros = getPprVue("prop.properties");
    
     // �����Ƕ�ȡ�������ļ���Ҳ����ֱ��ʹ��
    
     String root = PropKit.get("jdbc.username");
    
     String pass = PropKit.get("jdbc.password");
    
     // �õ�MYSQL���û����������� mysql �� cmd:
    
     String mysqlpaths = PropKit.get("mysqlpath");
     String sqlpath = PropKit.get("sql");
     String filepath = mysqlpaths + sqlpath + filename; // ���ݵ�·����ַ
    
     // �½����ݿ�finacing
     String stmt1 =mysqlpaths+ "mysqladmin -u " + root + " -p" + pass + " create finacing"; // -p����ӵ����������
     String stmt2 = mysqlpaths+"mysql -u " + root + " -p" + pass + " finacing < "+ filepath;
     String[] cmd = { "cmd", "/c", stmt2 };
     try {
      Runtime.getRuntime().exec(stmt1);
      Runtime.getRuntime().exec(cmd);
      System.out.println("�����Ѵ� " + filepath + " ���뵽���ݿ���");
     } catch (IOException e) {
      e.printStackTrace();
     }
    
    }

}
