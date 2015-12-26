/**
 * 
 */
package com.miao.test;

import java.awt.AWTException;
import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import com.miao.developer.Converter;
import com.miao.developer.ImgSpot;
import com.miao.developer.Screen;
import com.miao.xy2.Dw;

/**
 * @Description:
 * @author Miao
 * @date 2015年12月15日 下午4:34:26
 *
 */
public class Test {
	static {
		// 加载OpenCV库
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) throws AWTException {
		BufferedImage screenshot = Screen.screenshot(0, 0, 1300, 768);
		Mat c = Converter.bufferedImageToMat(screenshot);

		Point locationForTemplate = ImgSpot.getLocationForTemplate(c,
				Dw.minIcon);

		System.out.println(locationForTemplate.x + "," + locationForTemplate.y);
	}
}
