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
* @Description: �ԱȰ�������㸶�����Ƿ��в��죨���п��ź����֤�ţ�
* @author Miao
* @date 2016��1��18�� ����3:38:09
*
*/
public class CompareHuiFu {

	public static void main(String[] args) {
		int countTrue = 0;
		int no = 0;
		try {
			// ��ȡ���ǵ�����
			Map<String, List<String>> whBindCard = getWHBindCard();
			// ��ȡ�㸶����
			Map<String, List<String>> huiFu = getHuiFu();
			for (String key : whBindCard.keySet()) {
				for (String s : whBindCard.get(key)) {
					if (huiFu.containsKey(key)) {
						System.out.println(key + "|" + s + "|" + huiFu.get(key).contains(s));
						countTrue++;
					} else {
						// System.out.println(key + "|" + s + "|�㸶������");
						no++;
					}
				}
			}
			System.out.println("ƥ��:" + countTrue + "   ������:" + no);
			countTrue = 0;
			no = 0;
			for (String key : huiFu.keySet()) {
				for (String s : huiFu.get(key)) {
					if (whBindCard.containsKey(key)) {
						System.out.println(key + "|" + s + "|" + whBindCard.get(key).contains(s));
						countTrue++;
					} else {
						// System.out.println(key + "|" + s + "|����������");
						no++;
					}
				}
			}
			System.out.println("ƥ��:" + countTrue + "   ������:" + no);

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����п���Ϊkey��ȡ���б���
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
				key = json.getString("CardNo");// ���п���
				value = json.getString("CertId");// ���֤��
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
	 * �����п���Ϊkey��ȡ���л㸶����
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static Map<String, List<String>> getHuiFu() throws BiffException, IOException {
		List<String> list;
		Map<String, List<String>> map = new HashMap<>();
		Workbook wb = Workbook.getWorkbook(new File("huifu.xls")); // ���ļ����л�ȡExcel����������WorkBook��
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
	 * ����sql���
	 * @throws BiffException
	 * @throws IOException
	 */
	public static void generateSqlTxt() throws BiffException, IOException {
		int count = 0;
		String fileName = "huifu.xls"; // Excel�ļ�����·��
		Workbook wb = Workbook.getWorkbook(new File(fileName)); // ���ļ����л�ȡExcel����������WorkBook��
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

		// ������ı���
		File bankcardFile = new File("bankcard.txt");
		if (!bankcardFile.exists()) {
			bankcardFile.createNewFile();
		}
		// true = append file
		FileWriter fileWritter = new FileWriter(bankcardFile.getName(), false);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(bankcard.toString());
		bufferWritter.close();

		// ������ı���
		File idcardFile = new File("idcard.txt");
		if (!idcardFile.exists()) {
			idcardFile.createNewFile();
		}
		// true = append file
		fileWritter = new FileWriter(idcardFile.getName(), false);
		bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(idcard.toString());
		bufferWritter.close();

		// ������ı���
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
