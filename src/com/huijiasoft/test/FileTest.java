package com.huijiasoft.test;
import java.io.File;
import java.util.ArrayList;

import com.jfinal.kit.PathKit;


public class FileTest {
 private static ArrayList<String> filelist = new ArrayList<String>();
 
 public static void main(String[] args) throws Exception {
   
    String filePath = PathKit.getWebRootPath()+"\\upload\\photo\\1busn9y9ac92y1ap9j4khzaej8\\";
    getFiles(filePath);
 } 
 /*
  * ͨ���ݹ�õ�ĳһ·�������е�Ŀ¼�����ļ�
  */
 static void getFiles(String filePath){
  File root = new File(filePath);
    File[] files = root.listFiles();
    for(File file:files){    
     if(file.isDirectory()){
      /*
       * �ݹ����
       */
      getFiles(file.getAbsolutePath());
      filelist.add(file.getAbsolutePath());
      System.out.println("��ʾ"+filePath+"��������Ŀ¼�����ļ�"+file.getName());
     }else{
      System.out.println("��ʾ"+filePath+"��������Ŀ¼  "+file.getName());
     }    
    }
 }
}