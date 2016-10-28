package com.huijiasoft.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.huijiasoft.model.User;

/**
 * @author pangPython
 *		��̨����
 */
public class ReportExcel {
	public static String report(List<User> userlist) throws IOException, ParseException{
		 Workbook wb = new HSSFWorkbook();
		    //Workbook wb = new XSSFWorkbook();
		    CreationHelper createHelper = wb.getCreationHelper();
		    Sheet sheet = wb.createSheet("new sheet");

		    // Create a row and put some cells in it. Rows are 0 based.
		    Row row = sheet.createRow((short)0);
		    // Create a cell and put a value in it.
		    Cell cell = row.createCell(0);
		    cell.setCellValue(createHelper.createRichTextString("����"));
		    row.createCell(1).setCellValue(createHelper.createRichTextString("�Ա�"));
		    row.createCell(2).setCellValue(createHelper.createRichTextString("��������"));
		    row.createCell(3).setCellValue(createHelper.createRichTextString("����"));
		    row.createCell(4).setCellValue(createHelper.createRichTextString("������ò"));
		    row.createCell(5).setCellValue(createHelper.createRichTextString("�걨���"));
		    row.createCell(6).setCellValue(createHelper.createRichTextString("���֤��"));
		    row.createCell(7).setCellValue(createHelper.createRichTextString("ѧλ"));
		    row.createCell(8).setCellValue(createHelper.createRichTextString("ѧ��"));
		    row.createCell(9).setCellValue(createHelper.createRichTextString("���ڵ���"));
		    row.createCell(10).setCellValue(createHelper.createRichTextString("��ͥסַ"));
		    row.createCell(11).setCellValue(createHelper.createRichTextString("�ֻ���"));
		    row.createCell(12).setCellValue(createHelper.createRichTextString("���ڵ�λ"));
		    row.createCell(13).setCellValue(createHelper.createRichTextString("��λ�绰"));
		    row.createCell(14).setCellValue(createHelper.createRichTextString("����״��"));
		    row.createCell(15).setCellValue(createHelper.createRichTextString("��Ҫ����ְ"));
		    row.createCell(16).setCellValue(createHelper.createRichTextString("��Ҫ��������������"));
		    row.createCell(17).setCellValue(createHelper.createRichTextString("��Ҫҵ��ɾ�"));
		    row.createCell(18).setCellValue(createHelper.createRichTextString("�����"));
		    row.createCell(19).setCellValue(createHelper.createRichTextString("�����������"));
		    
		    int user_age = 20;
		    
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    
		    for(int i=0;i<userlist.size();i++){
		    	 Row data = sheet.createRow((short)i+1);
		    	 User user = userlist.get(i);
		    	 
		    	 //DBUtils.setEmptyIfNull(user);
		    	 String tn = user.getTrueName();
		    	 String us = user.getUsersex();
		    	 String birth = user.getBirth();
		    	 Long mz = user.getMzId();
		    	 Long zzmm = user.getZzmmId();
		    	 if(tn==null){
		    		 tn = "";
		    	 }
		    	 
		    	 Long dec_id = user.getDecId();
		    	 String card = user.getCard();
		    	 
		    	 Long degree_id = user.getDegreeId();
		    	 
		    	 Long edu_id = user.getEduId();
		    	 
		    	 String area_id = user.getAreaId();
		    	 
		    	 String address = user.getAddress();
		    	 
//		    	 String 
		    	 
		    	 data.createCell(0).setCellValue(tn);
		    	 data.createCell(1).setCellValue(us);
		    	 //Date birth = sdf.parse(user.getBirth());
		    	// user_age = DateUtils.compareDateWithNow(birth);
		    	 data.createCell(2).setCellValue(birth);
		    	 data.createCell(3).setCellValue(mz);
		    	 data.createCell(4).setCellValue(zzmm);
		    	 data.createCell(5).setCellValue(user.getDecId());
		    	 data.createCell(6).setCellValue(user.getCard());
		    	 data.createCell(7).setCellValue(user.getDegreeId());
		    	 data.createCell(8).setCellValue(user.getEduId());
		    	 data.createCell(9).setCellValue(user.getAreaId());
		    	 data.createCell(10).setCellValue(user.getAddress());
		    	 data.createCell(11).setCellValue(user.getTelephone());
		    	 data.createCell(12).setCellValue(user.getCompany());
		    	 data.createCell(13).setCellValue(user.getCompanyTel());
		    	 data.createCell(14).setCellValue(user.getHealth());
		    	 data.createCell(15).setCellValue(user.getSocioPartTime());
		    	 data.createCell(16).setCellValue(user.getYsjj());
		    	 data.createCell(17).setCellValue(user.getBusinessAchievement());
		    	 data.createCell(18).setCellValue(user.getAwards());
		    	 data.createCell(19).setCellValue(user.getOpinion());
		    }
		    
		    String filename = DateUtils.dateToUnixTimestamp(DateUtils.getNowTime())+".xls";
		    String path = "WebRoot\\download\\report\\"+filename;
		    FileOutputStream fileOut = new FileOutputStream(path);
	
		    try {
		    	
		    	wb.write(fileOut);
				fileOut.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
		  //  System.out.println(path+"  Excel path");
		    
		    return path;
	}
}
