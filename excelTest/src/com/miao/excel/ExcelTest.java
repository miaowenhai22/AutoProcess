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
* @date 2016年1月12日 上午10:08:54
*
*/
public class ExcelTest {
	public static void main(String[] args) {
		int count = 0;
		String name;
		String id;

		try {

			String fileName = "名单.xls"; // Excel文件所在路径
			Workbook wb = Workbook.getWorkbook(new File(fileName)); // 从文件流中获取Excel工作区对象（WorkBook）
			Sheet[] sheets = wb.getSheets();// 获取所有sheet

			List<Staff> list = new ArrayList<>();
			for (Sheet sheet : sheets) {
				for (int i = 0; i < sheet.getRows(); i++) {
					id = sheet.getCell(0, i).getContents();// 编号
					name = sheet.getCell(1, i).getContents();// 姓名
					list.add(new Staff(name, id, sheet.getName()));// 添加到list
				}
			}

			Collections.shuffle(list);// 打乱顺序

			// // 转json字符串
			// Gson gson = new Gson();
			// String data = gson.toJson(list);
			// System.out.println(data);

			// 转数组
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
			System.out.println("总人数:" + count);

			// 输出到文本中
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
