/**
 * 
 */
package com.miao.huifu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONObject;

/**
* @Description: 对比绑卡请求与汇付数据是否有差异（银行卡号和身份证号）
* @author Miao
* @date 2016年1月18日 下午3:38:09
*
*/
public class CompareHuiFu {

	public static void main(String[] args) {
		int countTrue = 0;
		int no = 0;
		try {
			// 获取我们的数据
			Map<String, List<String>> whBindCard = getWHBindCard();
			// 获取汇付数据
			Map<String, List<String>> huiFu = getHuiFu();
			for (String key : whBindCard.keySet()) {
				for (String s : whBindCard.get(key)) {
					if (huiFu.containsKey(key)) {
						System.out.println(key + "|" + s + "|" + huiFu.get(key).contains(s));
						countTrue++;
					} else {
						// System.out.println(key + "|" + s + "|汇付无数据");
						no++;
					}
				}
			}
			System.out.println("匹配:" + countTrue + "   无数据:" + no);
			countTrue = 0;
			no = 0;
			for (String key : huiFu.keySet()) {
				for (String s : huiFu.get(key)) {
					if (whBindCard.containsKey(key)) {
						System.out.println(key + "|" + s + "|" + whBindCard.get(key).contains(s));
						countTrue++;
					} else {
						// System.out.println(key + "|" + s + "|请求无数据");
						no++;
					}
				}
			}
			System.out.println("匹配:" + countTrue + "   无数据:" + no);

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 以银行卡号为key获取所有报文
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static Map<String, List<String>> getWHBindCard() throws BiffException, IOException {
		Gson gson = new Gson();
		Map<String, List<String>> map = new HashMap<>();
		Request request;
		JSONObject json;
		List<String> list;
		String str, key, value;
		Workbook wb = Workbook.getWorkbook(new File("request.xls"));
		Sheet sheet = wb.getSheet(0);

		for (int i = 0; i < sheet.getRows(); i++) {
			str = sheet.getCell(0, i).getContents();
			json = JSONObject.fromObject(str.substring(0, str.lastIndexOf(",")) + "}");
			if (json.getString("CmdId").equals("WHBindCard")) {
				key = json.getString("CardNo");// 银行卡号
				value = json.getString("CertId");// 身份证号
				if (map.containsKey(key)) {
					list = map.get(key);
					if (!list.contains(value)) {
						list.add(value);
					}
				} else {
					list = new ArrayList<>();
					list.add(value);
					map.put(key, list);
				}
			}
		}
		return map;
	}

	/**
	 * 以银行卡号为key获取所有汇付数据
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static Map<String, List<String>> getHuiFu() throws BiffException, IOException {
		List<String> list;
		Map<String, List<String>> map = new HashMap<>();
		Workbook wb = Workbook.getWorkbook(new File("huifu.xls")); // 从文件流中获取Excel工作区对象（WorkBook）
		Sheet sheet = wb.getSheet(0);
		for (int i = 1; i < sheet.getRows(); i++) {
			String bankcard = sheet.getCell(1, i).getContents();
			String idcard = sheet.getCell(2, i).getContents();
			if (map.containsKey(bankcard)) {
				list = map.get(bankcard);
				map.put(bankcard, list);
			} else {
				list = new ArrayList<>();
				list.add(idcard);
				map.put(bankcard, list);
			}
		}
		return map;
	}

	/**
	 * 生成sql语句
	 * @throws BiffException
	 * @throws IOException
	 */
	public static void generateSqlTxt() throws BiffException, IOException {
		int count = 0;
		String fileName = "huifu.xls"; // Excel文件所在路径
		Workbook wb = Workbook.getWorkbook(new File(fileName)); // 从文件流中获取Excel工作区对象（WorkBook）
		Sheet sheet = wb.getSheet(0);
		StringBuffer bankcard = new StringBuffer();
		bankcard.append("select number from bankcards where number in (");
		StringBuffer idcard = new StringBuffer();
		idcard.append("select idcard_number from  idcards where idcard_number in (");
		StringBuffer and = new StringBuffer();
		and.append("select number from bankcards join idcards on");
		for (int i = 1; i < sheet.getRows(); i++) {
			bankcard.append("'" + sheet.getCell(1, i).getContents() + "'");
			idcard.append("'" + sheet.getCell(2, i).getContents() + "'");
			and.append("(bankcards.number='" + sheet.getCell(1, i).getContents() + "' and idcards.idcard_number='"
					+ sheet.getCell(2, i).getContents() + "')");
			if (i != sheet.getRows() - 1) {
				bankcard.append(",");
				idcard.append(",");
				and.append(" or ");
			}
			count++;
		}
		bankcard.append(");");
		idcard.append(");");
		and.append(";");
		System.out.println(count);

		// 输出到文本中
		File bankcardFile = new File("bankcard.txt");
		if (!bankcardFile.exists()) {
			bankcardFile.createNewFile();
		}
		// true = append file
		FileWriter fileWritter = new FileWriter(bankcardFile.getName(), false);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(bankcard.toString());
		bufferWritter.close();

		// 输出到文本中
		File idcardFile = new File("idcard.txt");
		if (!idcardFile.exists()) {
			idcardFile.createNewFile();
		}
		// true = append file
		fileWritter = new FileWriter(idcardFile.getName(), false);
		bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(idcard.toString());
		bufferWritter.close();

		// 输出到文本中
		File andFile = new File("and.txt");
		if (!andFile.exists()) {
			andFile.createNewFile();
		}
		// true = append file
		fileWritter = new FileWriter(andFile.getName(), false);
		bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(and.toString());
		bufferWritter.close();
	}

}
