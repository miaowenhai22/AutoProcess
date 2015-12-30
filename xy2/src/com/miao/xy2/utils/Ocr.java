/**
 * 
 */
package com.miao.xy2.utils;

import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @Description: 文字识别工具
 * @author Miao
 * @date 2015年12月14日 下午5:16:15
 *
 */
public class Ocr {
	static {
		tesseract = new Tesseract();
	}

	private static ITesseract tesseract;

	/**
	 * 测试默认字库识别
	 * 
	 * @param img
	 * @return
	 * @throws TesseractException
	 */
	public static String doOcr(BufferedImage img) throws TesseractException {
		return tesseract.doOCR(img);
	}

	/**
	 * 测试设置字库
	 * 
	 * @param img
	 * @return
	 * @throws TesseractException
	 */
	public static String odOcr(BufferedImage img) throws TesseractException {
		tesseract.setLanguage("chi_sim");

		return tesseract.doOCR(img);
	}

	/**
	 * 获取当前地图位置
	 * @param img
	 * @return 
	 * @throws TesseractException
	 */
	public static String getLocation(BufferedImage img)
			throws TesseractException {
		tesseract.setLanguage("xy2");
		/*tesseract.setTessVariable("tessedit_char_whitelist",
				"[],0123456789"
				+ "高级双人庭院"
				+ "东海渔村");*/
		return tesseract.doOCR(img).replaceAll(" ", "");
	}
}
