package com.miao.test;

import java.awt.AWTException;
import java.io.File;

import com.miao.developer.Screen;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

public class OcrTest {
	public static void main(String[] args) throws AWTException {
		File imageFile = new File("D:/1.tif");
		ITesseract instance = new Tesseract1();
//		instance.setLanguage("chi_sim");
//		instance.setLanguage("xy2");
		instance.setLanguage("xy2");
//		instance.setTessVariable("tessedit_char_whitelist", "[]£¬0123456789");
//		instance.setTessVariable("language", "xy2+chi_sim");
		

		try {
			String result = instance.doOCR(Screen.screenshot(258 + 8, 105 + 9, 141, 17));
			System.out.println(result);
		} catch (TesseractException e) {
			System.out.println(e.getMessage());
		}

	}
}
