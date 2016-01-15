/**
 * 
 */
package com.miao.excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
* @Description: 
* @author Miao
* @date 2016��1��12�� ����10:08:54
*
*/
public class ExcelTest {
	public static void main(String[] args) {
		int count = 0;
		String name;
		String id;

		try {

			String fileName = "����.xls"; // Excel�ļ�����·��
			Workbook wb = Workbook.getWorkbook(new File(fileName)); // ���ļ����л�ȡExcel����������WorkBook��
			Sheet[] sheets = wb.getSheets();// ��ȡ����sheet

			List<Staff> list = new ArrayList<>();
			for (Sheet sheet : sheets) {
				for (int i = 0; i < sheet.getRows(); i++) {
					id = sheet.getCell(0, i).getContents();// ���
					name = sheet.getCell(1, i).getContents();// ����
					list.add(new Staff(name, id, sheet.getName()));// ��ӵ�list
				}
			}

			Collections.shuffle(list);// ����˳��

			// // תjson�ַ���
			// Gson gson = new Gson();
			// String data = gson.toJson(list);
			// System.out.println(data);

			// ת����
			// [id="",
			// name="",
			// company=""],[],[],[],[]
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				sb.append("[\n");
				sb.append(list.get(i).getId());
				sb.append(",\n");
				sb.append("\"" + list.get(i).getName() + "\"");
				sb.append(",\n");
				sb.append("\"" + list.get(i).getCompany() + "\"");
				sb.append("\n]");
				if (i != list.size() - 1) {
					sb.append(",\n");
				}
				count++;
			}
			String data = sb.toString();
			System.out.println(data);
			System.out.println("������:" + count);

			// ������ı���
			File file = new File("result.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			FileWriter fileWritter = new FileWriter(file.getName(), false);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(data);
			bufferWritter.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}

	}

}
