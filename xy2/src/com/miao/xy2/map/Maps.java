package com.miao.xy2.map;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import net.sourceforge.tess4j.TesseractException;

import org.opencv.core.Mat;
import org.opencv.core.Point;

import com.miao.xy2.map.bean.Location;
import com.miao.xy2.utils.Converter;
import com.miao.xy2.utils.ImgSpot;
import com.miao.xy2.utils.Ocr;
import com.miao.xy2.utils.screen.Screen;

/**
 * 地图工具类
 * @author mc
 *
 */
public class Maps {
	private final static Logger logger = Logger.getLogger(Maps.class.getName());
	/** 窗口起始坐标点x */
	private static int winX;
	/** 窗口起始坐标点y */
	private static int winY;


	/**
	 * 获取当前坐标位置
	 * 
	 * @return
	 * @throws TesseractException
	 * @throws AWTException
	 */
	public static Location getLocation() throws TesseractException, AWTException {
		// 初次启动时，获取游戏窗口位置
		if (winX == 0 || winY == 0) {
			getWindowPosition();
		}
		// 截取当前坐标位置
		BufferedImage screenshot = Screen.screenshot(winX + 8, winY + 9, 141, 17);
		// 识别当前坐标
		String str = Ocr.getLocation(screenshot);
		int start = str.indexOf("[");
		int indexOf = str.indexOf(",");
		int end = str.indexOf("]");

		if (start != -1 && indexOf != -1 && end != -1) {
			String name = str.substring(0, start);
			int x = Integer.parseInt(str.substring(start + 1, indexOf));
			int y = Integer.parseInt(str.substring(indexOf + 1, end));
			return new Location(name, x, y);
		}
		return null;
	}

	/**
	 * 获取窗口位置
	 * 
	 */
	private static void getWindowPosition() throws AWTException {
		// 获取屏幕大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 截屏获取游戏窗口位置，以窗口左上角图片为参考位置获取
		Mat screenImg = Converter.bufferedImageToMat(Screen.screenshot(0, 0, screenSize.width, screenSize.height));
		Point window = ImgSpot.getLocationForTemplate(screenImg, Dw.minIcon);
		winX = (int) (window.x);
		winY = (int) (window.y + 23);
	}
}
