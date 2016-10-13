package com.huijiasoft.test;
 
import java.io.File;
import java.util.List;
 
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
 
/**
 * FileUploadController �����ϴ����ļ�
 */
public class FileUploadController extends Controller {
    private final int MAXSize = 50 * 1024 * 1024; // 50M
    private String filedir=PathKit.getWebRootPath() + "\\upload\\uservideo\\";//ָ���û�ѵ����Ƶ�ļ��ϴ�·��
 
    /**
     * �����ϴ����ļ�
     * ע�⣺ǰ����lib������cos-26Dec2008.jar��
     */
    public void upload() {
        // TODO Auto-generated method stub
        try {
//          UploadFile upFile = getFile();//�����ϴ��ļ�һ��㶨  Ĭ��·���� upload
//          UploadFile upFile = getFile("FILE", filedir, maxSize, "utf-8");//ֻ���ڱ��ύ��ʽ�� �����ϴ��ļ�
            List<UploadFile> upFiles = getFiles(filedir, MAXSize, "utf-8");//�����ϴ��ļ�
            for (UploadFile fileItem : upFiles) {
                 
                String fPath=filedir+fileItem.getOriginalFileName();
                System.out.println("�ϴ�fPath"+fPath);
                String newPath=filedir+fileItem.getOriginalFileName().replace(".", "1.");//���磺����Ӵ�����1.3gp
                File oldFile=new File(fPath);
                File newFile=new File(newPath);
                if(newFile.exists()){
                    System.out.println("-------------ɾ��"+fileItem.getOriginalFileName());
                    //ɾ���ɵ�
                    oldFile.delete();
                    //�µ�������
                    boolean updateName=newFile.renameTo(oldFile);
                    System.out.println("-------------updateName:"+updateName);
                     
                }
                 
            }
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            renderJson("status", "0");//ʧ��
        }
        renderJson("status", "1");//�ɹ�
 
    }
}