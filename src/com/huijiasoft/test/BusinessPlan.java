/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package com.huijiasoft.test;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.huijiasoft.utils.DateUtils;


/**
 * @author pangPython
 *		��ӡ����
 */
public class BusinessPlan {
		public static void main(String[] args) throws IOException {
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
			    row.createCell(2).setCellValue(createHelper.createRichTextString("����"));
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
			    
			    FileOutputStream fileOut = new FileOutputStream(DateUtils.dateToUnixTimestamp(DateUtils.getNowTime())+".xls");
			    wb.write(fileOut);
			    fileOut.close();
		}
}
