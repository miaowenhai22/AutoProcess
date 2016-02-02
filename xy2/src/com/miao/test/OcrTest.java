package com.miao.test;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrTest {
	public static void main(String[] args) {
		File imageFile = new File("D:/2.png");
		ITesseract instance = new Tesseract();
//		instance.setLanguage("chi_sim");

		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			System.out.println(e.getMessage());
		}

	}
}
