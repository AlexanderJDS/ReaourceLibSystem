package com.huijiasoft.utils;

import java.io.File;
import java.util.ArrayList;

/**
 * @author pangPython
 *	·��������
 */
public class PathUtils {
	
	private static ArrayList<String> file_path_list = new ArrayList<String>();
	
	//���·�������ھʹ���
	public static boolean createNotExist(String path){
		File file = new File(path);
		if(!file.exists()){
			if(file.mkdir()){
				return true;
			}
			return false;
		}
		return true;
	}
	
	//�����ļ����е������ļ�
	public static ArrayList<String> getAllFilePath(String path){
		
		file_path_list.clear();
		
		File root = new File(path);
		File[] files = root.listFiles();

		if(files==null){
			return null;
		}
		
		for(File file:files){
			if(file.isDirectory()){
				getAllFilePath(file.getAbsolutePath());
				//System.out.println("��Ŀ¼�����ļ�  "+file.getName());
			}
			//System.out.println("zimulu "+file.getName());
			file_path_list.add(file.getName());
			
		}
		
		return file_path_list;
	}
}
