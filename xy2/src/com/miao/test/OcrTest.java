package com.miao.test;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrTest {
	public static void main(String[] args) {
		File imageFile = new File("D:/1.tif");
		ITesseract instance = new Tesseract();
		instance.setLanguage("chi_sim");
//		instance.setLanguage("xy2");
		instance.setTessVariable("tessedit_char_whitelist", "£¬0123456789");
		
	

		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			System.out.println(e.getMessage());
		}

	}
}
