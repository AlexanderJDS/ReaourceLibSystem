package com.huijiasoft.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

//import sun.mi;

/**
 * @author pangPython
 *	ͼƬ������
 */
public class PhotoUtils {
	
		//��ͼƬ����base64����
		public static String getImageStr(String path) {
			InputStream in = null;
			byte[] data = null;
			
			try {
				in = new FileInputStream(path);
				data = new byte[in.available()];
				in.read(data);
				in.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			//Base64Encoder encoder = new Base64Encoder();
			Base64 base64 = new Base64();
			
			return 	base64.encodeAsString(data);

		}
}
